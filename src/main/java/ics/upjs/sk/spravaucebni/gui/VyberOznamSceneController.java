package ics.upjs.sk.spravaucebni.gui;

import ics.upjs.sk.spravaucebni.Oznam;
import ics.upjs.sk.spravaucebni.storage.DaoFactory;
import ics.upjs.sk.spravaucebni.storage.OznamDao;
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

public class VyberOznamSceneController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<Oznam> vyberOznamListView;

    @FXML
    private Button zmazatButton;

    @FXML
    private Button pridatButton;

    @FXML
    private Button pokracovatButton;

    private VyberOznamFxModel model;
    private Long ucebnaId;

    public VyberOznamSceneController(Long ucebnaId) {
        this.ucebnaId = ucebnaId;
        model = new VyberOznamFxModel(ucebnaId);
    }
    
    @FXML
    void initialize() {
        vyberOznamListView.setItems(model.getOznamy());
        zmazatButton.setDisable(true);
        pokracovatButton.setDisable(true);
        
        vyberOznamListView.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            model.setVybratyOznam(newValue);
            if (newValue == null) {
                zmazatButton.setDisable(true);
                pokracovatButton.setDisable(true);
            } else {
                zmazatButton.setDisable(false);
                pokracovatButton.setDisable(false);
            }
        }));
        model.getVybratyOznamProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue == null) {
                vyberOznamListView.getSelectionModel().clearSelection();
            } else {
                vyberOznamListView.getSelectionModel().select(newValue);
            }
        }));
        
        zmazatButton.setOnAction(eh -> {
            OznamDao oznamDao = DaoFactory.INSTANCE.getOznamDao();
            oznamDao.delete(model.getVybratyOznam().getId());
            model.inicializuj();
        });
        
        pridatButton.setOnAction(eh -> {
            OznamSceneController controller = new OznamSceneController(ucebnaId);
            nextWindow(controller, "OznamScene.fxml", "Nový oznam");
            if (controller.jeUlozeny()) {
                model.inicializuj();
            }
        });
        
        pokracovatButton.setOnAction(eh -> {
            OznamSceneController controller = new OznamSceneController(model.getVybratyOznam(), ucebnaId);
            nextWindow(controller, "OznamScene.fxml", "Úprava oznamu");
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
            
            stage.setMinWidth(310);
            stage.setMinHeight(280);
            stage.setMaxWidth(626);
            stage.setMaxHeight(626);
          
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }
    }
