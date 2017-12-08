package ics.upjs.sk.spravaucebni.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class OznamSceneController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea oznamTextArea;

    @FXML
    private Button ulozitButton;

    @FXML
    void initialize() {
        assert oznamTextArea != null : "fx:id=\"oznamTextArea\" was not injected: check your FXML file 'OznamScene.fxml'.";
        assert ulozitButton != null : "fx:id=\"ulozitButton\" was not injected: check your FXML file 'OznamScene.fxml'.";

    }
}
