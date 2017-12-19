package ics.upjs.sk.spravaucebni.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class SpravaUcebniApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        MainMenuSceneController controller = new MainMenuSceneController();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("MainMenuScene.fxml"));
        loader.setController(controller);
        
        Parent parentPane = loader.load();
        Scene scene = new Scene(parentPane);
        
        stage.setScene(scene);
        stage.setTitle("Správa učební");
        
        stage.setMaxWidth(600);
        stage.setMinWidth(380);
        stage.setMaxHeight(408);
        stage.setMinHeight(360);
        
        //stage.getIcons().add(new Image("src/main/resources/settings.png"));
        scene.getStylesheets().add(SpravaUcebniApp.class.getResource("MainMenuScene.css").toExternalForm());
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
