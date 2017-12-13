package ics.upjs.sk.spravaucebni.gui;

import ics.upjs.sk.spravaucebni.Projektor;
import ics.upjs.sk.spravaucebni.Ucebna;
import ics.upjs.sk.spravaucebni.storage.DaoFactory;
import ics.upjs.sk.spravaucebni.storage.ProjektorDao;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class VyberProjektorSceneController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<Projektor> vyberProjektorListView;

    @FXML
    private Button zmazatProjektorButton;
    
    @FXML
    private Button pridatButton;
    
    @FXML
    private Button pokracovatButton;
    
    private Long ucebnaId;
    private VyberProjektorFxModel model;

    public VyberProjektorSceneController(Long ucebnaId) {
        this.ucebnaId = ucebnaId;
        model = new VyberProjektorFxModel(ucebnaId);
    }
    
    @FXML
    void initialize() {
        vyberProjektorListView.setItems(model.getProjektory());
        zmazatProjektorButton.setDisable(true);
        pokracovatButton.setDisable(true);
        
        vyberProjektorListView.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            model.setVybratyProjektor(newValue);
            if (newValue == null) {
                zmazatProjektorButton.setDisable(true);
                pokracovatButton.setDisable(true);
            } else {
                zmazatProjektorButton.setDisable(false);
                pokracovatButton.setDisable(false);
            }
        }));
        model.getVybratyProjektorProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue == null) {
                vyberProjektorListView.getSelectionModel().clearSelection();
            } else {
                vyberProjektorListView.getSelectionModel().select(newValue);
            }
        }));
        
        zmazatProjektorButton.setOnAction(eh -> {
            ProjektorDao projektorDao = DaoFactory.INSTANCE.getProjektorDao();
            projektorDao.delete(model.getVybratyProjektor().getId());
            model.inicializuj();
        });
        
        pridatButton.setOnAction(eh -> {
            ProjektorSceneController controller = new ProjektorSceneController(ucebnaId);
            nextWindow(controller, "ProjektorScene.fxml", "Nový projektor");
            if (controller.jeUlozeny()) {
                model.inicializuj();
            }
        });
        
        pokracovatButton.setOnAction(eh -> {
            ProjektorSceneController controller = new ProjektorSceneController(model.getVybratyProjektor(), ucebnaId);
            nextWindow(controller, "ProjektorScene.fxml", "Úprava projektoru");
            if (controller.jeUlozeny()) {
                model.inicializuj();
            }
        });
    }
    
    private void nextWindow (Object controller, String resourceFXMLString, String title){
         try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(resourceFXMLString));
            loader.setController(controller);

            Parent parentPane = loader.load();
            Scene scene = new Scene(parentPane);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle(title);
          
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }
}
