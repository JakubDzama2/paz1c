package ics.upjs.sk.spravaucebni.gui;

import ics.upjs.sk.spravaucebni.Oznam;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class OznamSceneController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField menoTextField;
    
    @FXML
    private TextArea oznamTextArea;

    @FXML
    private Button ulozitButton;

    private OznamFxModel model;
    private Oznam aktualnyOznam;
    
    @FXML
    void initialize() {
        
    }
}
