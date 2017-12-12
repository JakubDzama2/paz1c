package ics.upjs.sk.spravaucebni.gui;

import ics.upjs.sk.spravaucebni.Ucebna;
import ics.upjs.sk.spravaucebni.storage.DaoFactory;
import ics.upjs.sk.spravaucebni.storage.UcebnaDao;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

public class VyberUcebnuSceneController {
    
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<Ucebna> vyberUcebnuListView;

    @FXML
    private Button pokracovatButton;
    
    @FXML
    private Button pridatButton;
    
    @FXML
    private Button zmazatButton;
        
    @FXML
    private Button editovatButton;
    
    private VyberUcebnuFxModel model;

    public VyberUcebnuSceneController() {
        this.model = new VyberUcebnuFxModel();
    }
        
    @FXML
    void initialize() {
        vyberUcebnuListView.setItems(model.getUcebne());
        zmazatButton.setDisable(true);
        pokracovatButton.setDisable(true);
        editovatButton.setDisable(true);
        vyberUcebnuListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Ucebna>() {
            @Override
            public void changed(ObservableValue<? extends Ucebna> observable, Ucebna oldValue, Ucebna newValue) {
                model.setVybrataUcebna(newValue);
                if (newValue == null) {
                    zmazatButton.setDisable(true);
                    pokracovatButton.setDisable(true);
                    editovatButton.setDisable(true);
                } else {
                    zmazatButton.setDisable(false);
                    pokracovatButton.setDisable(false);
                    editovatButton.setDisable(false);
                }
            }
        });
        model.getVybrataUcebnaProperty().addListener(new ChangeListener<Ucebna>() {
            @Override
            public void changed(ObservableValue observable, Ucebna oldValue, Ucebna newValue) {
                if (newValue == null) {
                    vyberUcebnuListView.getSelectionModel().clearSelection();
                } else {
                    vyberUcebnuListView.getSelectionModel().select(newValue);
                }
            }
        });
        
        zmazatButton.setOnAction(eh -> {
            UcebnaDao ucebnaDao = DaoFactory.INSTANCE.getUcebnaDao();
            Ucebna ucebna = model.getVybrataUcebna();
            ucebnaDao.delete(ucebna.getId());
            model.inicializuj();
        });
        
        editovatButton.setOnAction(eh -> {
            UcebnaSceneController controller = new UcebnaSceneController(model.getVybrataUcebna());
            nextWindow(controller,"UcebnaScene.fxml", "Editácia učebne");
            if (controller.isUlozeny()) {
                model.inicializuj();
            }
        });
        
        pridatButton.setOnAction(eh -> {
            UcebnaSceneController controller = new UcebnaSceneController();
            nextWindow(controller,"UcebnaScene.fxml", "Vytvorenie novej učebne");
            if (controller.isUlozeny()) {
                model.inicializuj();
            }
        });
        
        pokracovatButton.setOnAction(eh -> {
            EditUcebnaSceneController controller = new EditUcebnaSceneController(model.getVybrataUcebna());
            nextWindow(controller,"EditUcebnaScene.fxml", "Výber atribútov učebne");
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
