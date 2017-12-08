package ics.upjs.sk.spravaucebni.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class EditUcebnaSceneController {
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button pocitaceButton;

    @FXML
    private Button projektorButton;

    @FXML
    private Button spotrebaButton;

    @FXML
    private Button spravyButton;


    @FXML
    void initialize() {
        
        
        pocitaceButton.setOnAction(eh -> {
             NovyPouzivatelSceneController controller = new NovyPouzivatelSceneController();
             nextWindow(controller,"VyberPocitacScene.fxml");
        });
        projektorButton.setOnAction(eh -> {
            NovaUcebnaSceneController controller = new NovaUcebnaSceneController();
            nextWindow(controller,"VyberProjektorScene.fxml");
        });
 //       spotrebaButton.setOnAction(eh -> {
 //           ZmazatPouzivatelaSceneController controller = new ZmazatPouzivatelaSceneController();
 //           nextWindow(controller,".fxml");
 //       });
        spravyButton.setOnAction(eh -> {
            ZmazatPouzivatelaSceneController controller = new ZmazatPouzivatelaSceneController();
            nextWindow(controller,"OznamScene.fxml");
        });
    }
    
    private void nextWindow (Object controller, String resourceFXMLString){
         try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(resourceFXMLString));
            loader.setController(controller);

            Parent parentPane = loader.load();
            Scene scene = new Scene(parentPane);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("");
          

            stage.show();
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }
}
