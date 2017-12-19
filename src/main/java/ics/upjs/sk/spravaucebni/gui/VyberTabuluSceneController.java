package ics.upjs.sk.spravaucebni.gui;

import ics.upjs.sk.spravaucebni.Tabula;
import ics.upjs.sk.spravaucebni.storage.DaoFactory;
import ics.upjs.sk.spravaucebni.storage.TabulaDao;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class VyberTabuluSceneController {

    @FXML
    private ListView<Tabula> vyberTabuluListView;

    @FXML
    private Button zmazatButton;

    @FXML
    private Button pridatButton;

    @FXML
    private Button pokracovatButton;
    
    private VyberTabuluFxModel model;
    private Long ucebnaId;

    public VyberTabuluSceneController(Long ucebnaId) {
        this.ucebnaId = ucebnaId;
        model = new VyberTabuluFxModel(ucebnaId);
    }
    
    @FXML
    void initialize() {
        vyberTabuluListView.setItems(model.getTabule());
        zmazatButton.setDisable(true);
        pokracovatButton.setDisable(true);
        
        vyberTabuluListView.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            model.setVybrataTabula(newValue);
            if (newValue == null) {
                zmazatButton.setDisable(true);
                pokracovatButton.setDisable(true);
            } else {
                zmazatButton.setDisable(false);
                pokracovatButton.setDisable(false);
            }
        }));
        
        model.getVybrataTabulaProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue == null) {
                vyberTabuluListView.getSelectionModel().clearSelection();
            } else {
                vyberTabuluListView.getSelectionModel().select(newValue);
            }
        }));
        
        zmazatButton.setOnAction(eh -> {
            TabulaDao tabulaDao = DaoFactory.INSTANCE.getTabulaDao();
            tabulaDao.delete(model.getVybrataTabula().getId());
            model.inicializuj();
        });
        
        pridatButton.setOnAction(eh -> {
            TabulaSceneController controller = new TabulaSceneController(ucebnaId);
            nextWindow(controller, "TabulaScene.fxml", "Nová tabuľa");
            if (controller.jeUlozeny()) {
                model.inicializuj();
            }
        });
        
        pokracovatButton.setOnAction(eh -> {
            TabulaSceneController controller = new TabulaSceneController(model.getVybrataTabula(), ucebnaId);
            nextWindow(controller, "TabulaScene.fxml", "Editácia tabule");
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
            stage.setMinHeight(230);
            stage.setMaxHeight(626);
            stage.setMaxWidth(626);
          
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }
}
