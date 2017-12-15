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
    private Long ucebnaId;
    private boolean ulozeny;

    public OznamSceneController(Oznam aktualnyOznam, Long ucebnaId) {
        this.aktualnyOznam = aktualnyOznam;
        this.ucebnaId = ucebnaId;
        model = new OznamFxModel(aktualnyOznam, ucebnaId);
    }

    public OznamSceneController(Long ucebnaId) {
        this.ucebnaId = ucebnaId;
        model = new OznamFxModel(null, ucebnaId);
    }
    
    @FXML
    void initialize() {
        menoTextField.textProperty().bindBidirectional(model.getHlasatelProperty());
        menoTextField.textProperty().addListener(((observable, oldValue, newValue) -> {
            if (!goodTextValue(oldValue)) {
                model.setPocetChyb(model.getPocetChyb() - 1);
            }
            if (goodTextValue(newValue)) {
                menoTextField.setStyle("-fx-background-color: white;");
            } else {
                model.setPocetChyb(model.getPocetChyb() + 1);
                menoTextField.setStyle("-fx-background-color: lightcoral;");
            }
            setUlozitButtonDisable();
        }));
        
        oznamTextArea.textProperty().bindBidirectional(model.getPoznamkaProperty());
        oznamTextArea.textProperty().addListener(((observable, oldValue, newValue) -> {
            if (!goodTextValue(oldValue)) {
                model.setPocetChyb(model.getPocetChyb() - 1);
            }
            if (goodTextValue(newValue)) {
                oznamTextArea.setStyle("-fx-background-color: white;");
            } else {
                model.setPocetChyb(model.getPocetChyb() + 1);
                oznamTextArea.setStyle("-fx-background-color: lightcoral;");
            }
            setUlozitButtonDisable();
        }));
        
        ulozitButton.setOnAction(eh -> {
            model.ulozAktualnyOznam();
            ulozeny = true;
            ulozitButton.getScene().getWindow().hide();
        });
        setUlozitButtonDisable();
        
        // nechceme vediet editovat oznam
        if (aktualnyOznam != null) {
            menoTextField.setEditable(false);
            oznamTextArea.setEditable(false);
            ulozitButton.setDisable(true);
        }
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
