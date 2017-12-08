package ics.upjs.sk.spravaucebni.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class PocitacSceneController {
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField macTextField;

    @FXML
    private TextField serialTextField;

    @FXML
    private Button ulozitButton;

    @FXML
    private DatePicker poslednePouzitieDatePicker;

    @FXML
    void initialize() {
        
    }
}
