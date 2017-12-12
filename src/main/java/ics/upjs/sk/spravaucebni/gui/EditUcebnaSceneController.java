package ics.upjs.sk.spravaucebni.gui;

import ics.upjs.sk.spravaucebni.Ucebna;
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
    private Button tabuleButton;

    @FXML
    private Button projektoryButton;

    @FXML
    private Button spotrebaButton;

    @FXML
    private Button spravyButton;

    private Ucebna ucebna;

    public EditUcebnaSceneController(Ucebna ucebna) {
        this.ucebna = ucebna;
        System.out.println(ucebna.getNazov());
    }
    
    @FXML
    void initialize() {
        
        
        pocitaceButton.setOnAction(eh -> {
             VyberPocitacSceneController controller = new VyberPocitacSceneController(ucebna);
             nextWindow(controller,"VyberPocitacScene.fxml", "Počítače učebne: " + ucebna.getNazov());
        });
        projektoryButton.setOnAction(eh -> {
            VyberProjektorSceneController controller = new VyberProjektorSceneController(ucebna);
            nextWindow(controller,"VyberProjektorScene.fxml", "Projektory učebne: " + ucebna.getNazov());
        });
 //       spotrebaButton.setOnAction(eh -> {
 //           ZmazatPouzivatelaSceneController controller = new ZmazatPouzivatelaSceneController();
 //           nextWindow(controller,".fxml");
 //       });
//        spravyButton.setOnAction(eh -> {
//            ZmazatPouzivatelaSceneController controller = new ZmazatPouzivatelaSceneController();
//            nextWindow(controller,"OznamScene.fxml");
//        });
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
          

            stage.show();
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }
}
