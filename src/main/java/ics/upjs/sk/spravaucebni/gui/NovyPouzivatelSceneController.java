package ics.upjs.sk.spravaucebni.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class NovyPouzivatelSceneController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ulozitButton;

    @FXML
    private TextField menoTextField;

    @FXML
    private TextField docasneHesloTextField;

    @FXML
    private TextField mailTextField;
    
    @FXML
    private ComboBox<?> ucebnaComboBox;

    @FXML
    void initialize() {
      
    }
}