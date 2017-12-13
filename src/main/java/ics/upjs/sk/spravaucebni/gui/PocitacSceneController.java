package ics.upjs.sk.spravaucebni.gui;

import ics.upjs.sk.spravaucebni.Pocitac;
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

    private Pocitac aktualnyPocitac;
    private PocitacFxModel model;
    private boolean ulozeny;
    
    public PocitacSceneController() {
        model = new PocitacFxModel(null);
        aktualnyPocitac = null;
    }

    public PocitacSceneController(Pocitac aktualnyPocitac) {
        this.aktualnyPocitac = aktualnyPocitac;
        model = new PocitacFxModel(aktualnyPocitac);
    }  
    
    @FXML
    void initialize() {
        serialTextField.textProperty().bindBidirectional(model.getSerioveCisloProperty());
        serialTextField.textProperty().addListener(((observable, oldValue, newValue) -> {
            if (!goodTextValue(oldValue)) {
                model.setPocetChyb(model.getPocetChyb() - 1);
            }
            if (goodTextValue(newValue)) {
                serialTextField.setStyle("-fx-background-color: white;");
            } else {
                model.setPocetChyb(model.getPocetChyb() + 1);
                serialTextField.setStyle("-fx-background-color: lightcoral;");
            }
            setUlozitButtonDisable();
        }));
        
        macTextField.textProperty().bindBidirectional(model.getMacAdresaProperty());
        macTextField.textProperty().addListener(((observable, oldValue, newValue) -> {
            if (!goodTextValue(oldValue)) {
                model.setPocetChyb(model.getPocetChyb() - 1);
            }
            if (goodTextValue(newValue)) {
                macTextField.setStyle("-fx-background-color: white;");
            } else {
                model.setPocetChyb(model.getPocetChyb() + 1);
                macTextField.setStyle("-fx-background-color: lightcoral;");
            }
            setUlozitButtonDisable();
        }));
        
        
        
        //TODO DATEPICKER
        
        ulozitButton.setOnAction(eh -> {
            
        });
    }
    
    public boolean jeUlozeny() {
        return ulozeny;
    }
    
    private boolean goodTextValue(String value) {
        return value != null && !value.trim().isEmpty();
    }

    private void setUlozitButtonDisable() {
        if (model.getPocetChyb() == 0) {
            ulozitButton.setDisable(false);
        } else {
            ulozitButton.setDisable(true);
        }
    }
}
