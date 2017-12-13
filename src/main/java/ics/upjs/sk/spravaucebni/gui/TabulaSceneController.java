package ics.upjs.sk.spravaucebni.gui;

import ics.upjs.sk.spravaucebni.Tabula;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

public class TabulaSceneController {

    @FXML
    private TextField typTextField;

    @FXML
    private Spinner<Integer> pocetPisatiekSpinner;

    @FXML
    private Button ulozitButton;
    
    private TabulaFxModel model;
    private Tabula aktualnaTabula;
    private Long ucebnaId;
    private boolean ulozeny;

    public TabulaSceneController(Long ucebnaId) {
        this.ucebnaId = ucebnaId;
        model = new TabulaFxModel(null, ucebnaId);
    }
    
    public TabulaSceneController(Tabula aktualnaTabula, Long ucebnaId) {
        this.aktualnaTabula = aktualnaTabula;
        this.ucebnaId = ucebnaId;
        model = new TabulaFxModel(aktualnaTabula, ucebnaId);
    }
    
    void initialize() {
        typTextField.textProperty().bindBidirectional(model.getTypProperty());
        
        SpinnerValueFactory.IntegerSpinnerValueFactory sprinnerValueFactory =
        new SpinnerValueFactory.IntegerSpinnerValueFactory(1,1000,model.getPocetPisatiek());
        pocetPisatiekSpinner.setValueFactory(sprinnerValueFactory);
        
        pocetPisatiekSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            model.setPocetPisatiek(newValue);
        });
        
        model.getPocetPisatiekProperty().addListener(((observable, oldValue, newValue) -> {
            pocetPisatiekSpinner.getValueFactory().setValue(newValue.intValue());
        }));
        
        if (model.getTyp() == null || model.getTyp().trim().equals("")) {
            ulozitButton.setDisable(true);
        } else {
            ulozitButton.setDisable(false);
        }
        
        ulozitButton.setOnAction(eh -> {
            model.ulozAktualnuTabulu();
            ulozeny = true;
            ulozitButton.getScene().getWindow().hide();
        });
    }
    
    public boolean jeUlozeny() {
        return ulozeny;
    }
    
}
