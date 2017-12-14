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
    private TextField serialTextField;
    
    @FXML
    private TextField macTextField;

    @FXML
    private DatePicker poslednePouzitieDatePicker;
    
    @FXML
    private Button ulozitButton;
    
    private Pocitac aktualnyPocitac;
    private PocitacFxModel model;
    private boolean ulozeny;
    
    public PocitacSceneController(Long ucebnaId) {
        model = new PocitacFxModel(null, ucebnaId);
        aktualnyPocitac = null;
    }

    public PocitacSceneController(Pocitac aktualnyPocitac, Long ucebnaId) {
        this.aktualnyPocitac = aktualnyPocitac;
        model = new PocitacFxModel(aktualnyPocitac, ucebnaId);
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
        if (aktualnyPocitac != null) {
            poslednePouzitieDatePicker.setValue(model.getPoslednePouzitie());
        }
        poslednePouzitieDatePicker.valueProperty().addListener(((observable, oldValue, newValue) -> {
            model.setPoslednePouzitie(newValue);
        }));
        model.getPoslednePouzitieProperty().addListener(((observable, oldValue, newValue) -> {
            poslednePouzitieDatePicker.setValue(newValue);
        }));
        
        ulozitButton.setOnAction(eh -> {
            model.ulozAktualnyPocitac();
            ulozeny = true;
            ulozitButton.getScene().getWindow().hide();
        });
        setUlozitButtonDisable();
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
