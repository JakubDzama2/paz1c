package ics.upjs.sk.spravaucebni.gui;

import ics.upjs.sk.spravaucebni.Projektor;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ProjektorSceneController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea popisTextArea;

    @FXML
    private TextField zivotnostTextField;

    @FXML
    private TextField nasvieteneTextField;

    @FXML
    private TextField modelTextField;

    @FXML
    private Button ulozitButton;

    private ProjektorFxModel model;
    private Projektor aktualnyProjektor;
    private Long ucebnaId;
    private boolean ulozeny;

    public ProjektorSceneController(Long ucebnaId) {
        this.ucebnaId = ucebnaId;
        model = new ProjektorFxModel(null, ucebnaId);
    }
    
    public ProjektorSceneController(Projektor aktualnyProjektor, Long ucebnaId) {
        this.aktualnyProjektor = aktualnyProjektor;
        this.ucebnaId = ucebnaId;
        model = new ProjektorFxModel(aktualnyProjektor, ucebnaId);
    }
    
    @FXML
    void initialize() {
       modelTextField.textProperty().bindBidirectional(model.getNazovModeluProperty());
       modelTextField.textProperty().addListener(((observable, oldValue, newValue) -> {
           if (!goodTextValue(oldValue)) {
                model.setPocetChyb(model.getPocetChyb() - 1);
            }
            if (goodTextValue(newValue)) {
                modelTextField.setStyle("-fx-background-color: white;");
            } else {
                model.setPocetChyb(model.getPocetChyb() + 1);
                modelTextField.setStyle("-fx-background-color: lightcoral;");
            }
            setUlozitButtonDisable();
       }));
       
       nasvieteneTextField.textProperty().bindBidirectional(model.getPocetNasvHodinProperty());
       nasvieteneTextField.textProperty().addListener(((observable, oldValue, newValue) -> {
           if (!goodIntegerTextValue(oldValue)) {
                model.setPocetChyb(model.getPocetChyb() - 1);
            }
            if (goodIntegerTextValue(newValue)) {
                nasvieteneTextField.setStyle("-fx-background-color: white;");
            } else {
                model.setPocetChyb(model.getPocetChyb() + 1);
                nasvieteneTextField.setStyle("-fx-background-color: lightcoral;");
            }
            setUlozitButtonDisable();
       }));
       
       zivotnostTextField.textProperty().bindBidirectional(model.getOcakavanaZivotnostLampyProperty());
       zivotnostTextField.textProperty().addListener(((observable, oldValue, newValue) -> {
           if (!goodIntegerTextValue(oldValue)) {
                model.setPocetChyb(model.getPocetChyb() - 1);
            }
            if (goodIntegerTextValue(newValue)) {
                zivotnostTextField.setStyle("-fx-background-color: white;");
            } else {
                model.setPocetChyb(model.getPocetChyb() + 1);
                zivotnostTextField.setStyle("-fx-background-color: lightcoral;");
            }
            setUlozitButtonDisable();
       }));
       
       popisTextArea.textProperty().bindBidirectional(model.getPopisKvalityProperty());
       popisTextArea.textProperty().addListener(((observable, oldValue, newValue) -> {
           if (!goodTextValue(oldValue)) {
                model.setPocetChyb(model.getPocetChyb() - 1);
            }
            if (goodTextValue(newValue)) {
                popisTextArea.setStyle("-fx-background-color: white;");
            } else {
                model.setPocetChyb(model.getPocetChyb() + 1);
                popisTextArea.setStyle("-fx-background-color: lightcoral;");
            }
            setUlozitButtonDisable();
       }));
       setUlozitButtonDisable();
       
       ulozitButton.setOnAction(eh -> {
           model.ulozAktualnyProjektor();
           ulozeny = true;
           ulozitButton.getScene().getWindow().hide();
       });
    }
    
    public boolean jeUlozeny() {
        return ulozeny;
    }
    
    private boolean goodIntegerTextValue(String value) {
        return value != null && !value.trim().isEmpty() && (value.matches("^[1-9][0-9]*") || value.equals("0"));
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

