package ics.upjs.sk.spravaucebni.gui;

import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SpotrebaSceneController {
    
    
    @FXML
    private Spinner<Integer> rokSpinner;

    @FXML
    private Spinner<Integer> mesiacSpinner;
    
    @FXML
    private TableView<JednaSpotrebaFxModel> spotrebyTableView;

    @FXML
    private Button vykresliGrafButton;

    @FXML
    private Label priemernaSpotrebaLabel;

    @FXML
    private Label celkovaSpotrebaLabel;

    @FXML
    private Label celkovaCenaLabel;

    private SpotrebaFxModel model;
    private Long ucebnaId;
    
    public SpotrebaSceneController(Long ucebnaId) {
        this.ucebnaId = ucebnaId;
        model = new SpotrebaFxModel(ucebnaId);
    }
    
    @FXML
    void initialize() {
        int rok = LocalDate.now().getYear();
        int mesiac = LocalDate.now().getMonthValue();
        SpinnerValueFactory.IntegerSpinnerValueFactory sprinnerValueFactory =
        new SpinnerValueFactory.IntegerSpinnerValueFactory(2015, rok, model.getRok());
        rokSpinner.setValueFactory(sprinnerValueFactory);
        rokSpinner.getValueFactory().setValue(rok);
        
        rokSpinner.valueProperty().addListener(((observable, oldValue, newValue) -> {
            model.setRok(newValue);
            nastavLabely();
        }));
        
        sprinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12, model.getMesiac());
        mesiacSpinner.setValueFactory(sprinnerValueFactory);
        mesiacSpinner.getValueFactory().setValue(mesiac);
        
        mesiacSpinner.valueProperty().addListener(((observable, oldValue, newValue) -> {
            model.setMesiac(newValue);
            nastavLabely();
        }));
        
        spotrebyTableView.getColumns().clear();
        
        TableColumn<JednaSpotrebaFxModel, String> datumCol = new TableColumn<>("Dátum");
        datumCol.setCellValueFactory(new PropertyValueFactory<>("datum"));
        datumCol.setMinWidth(100);
        spotrebyTableView.getColumns().add(datumCol);
        
        TableColumn<JednaSpotrebaFxModel, String> hodnotaCol = new TableColumn<>("Spotreba (kWh)");
        hodnotaCol.setCellValueFactory(new PropertyValueFactory<>("hodnota"));
        hodnotaCol.setMinWidth(140);
        spotrebyTableView.getColumns().add(hodnotaCol);
        
        TableColumn<JednaSpotrebaFxModel, String> cenaCol = new TableColumn<>("Cena (0.15 €/kWh)");
        cenaCol.setCellValueFactory(new PropertyValueFactory<>("cena"));
        cenaCol.setMinWidth(150);
        spotrebyTableView.getColumns().add(cenaCol);
        
        spotrebyTableView.setItems(model.getSpotreby());
        
        nastavLabely();
        
        vykresliGrafButton.setOnAction(eh -> {
            
        });
    }
    
    private void nastavLabely() {
        celkovaSpotrebaLabel.setText("Celková spotreba v tomto mesiaci je " + model.getCelkovaSpotreba());
        celkovaCenaLabel.setText("Celková cena v tomto mesiaci je " + model.getCelkovaCena());
        priemernaSpotrebaLabel.setText("Priemerná spotreba v tomto mesiaci je " + model.getPriemernaSpotreba());
    }
}
