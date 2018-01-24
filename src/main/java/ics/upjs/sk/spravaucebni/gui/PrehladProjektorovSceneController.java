package ics.upjs.sk.spravaucebni.gui;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PrehladProjektorovSceneController {
    
    @FXML
    private TableView<JedenProjektorFxModel> projektoryTableView;

    private PrehladProjektorovFxModel model;
    private Long pouzivatelId;

    public PrehladProjektorovSceneController(Long pouzivatelId) {
        this.pouzivatelId = pouzivatelId;
        model = new PrehladProjektorovFxModel(pouzivatelId);
    }
    
    @FXML
    void initialize() {
        projektoryTableView.getColumns().clear();
        
        TableColumn<JedenProjektorFxModel, String> nazovUcebneCol = new TableColumn<>("Názov učebne");
        nazovUcebneCol.setCellValueFactory(new PropertyValueFactory<>("nazovUcebne"));
        nazovUcebneCol.setMinWidth(120);
        projektoryTableView.getColumns().add(nazovUcebneCol);
        
        TableColumn<JedenProjektorFxModel, String> nazovModeluCol = new TableColumn<>("Názov modelu");
        nazovModeluCol.setCellValueFactory(new PropertyValueFactory<>("nazovModelu"));
        nazovModeluCol.setMinWidth(120);
        projektoryTableView.getColumns().add(nazovModeluCol);
        
        TableColumn<JedenProjektorFxModel, String> pocetNasvietenychHodinCol = new TableColumn<>("Počet\nnasvietených hodín");
        pocetNasvietenychHodinCol.setCellValueFactory(new PropertyValueFactory<>("pocetNasvietenychHodin"));
        pocetNasvietenychHodinCol.setMinWidth(150);
        projektoryTableView.getColumns().add(pocetNasvietenychHodinCol);
        
        TableColumn<JedenProjektorFxModel, String> kvalitaObrazuCol = new TableColumn<>("Kvalita obrazu");
        kvalitaObrazuCol.setCellValueFactory(new PropertyValueFactory<>("kvalitaObrazu"));
        kvalitaObrazuCol.setMinWidth(160);
        projektoryTableView.getColumns().add(kvalitaObrazuCol);
        
        TableColumn<JedenProjektorFxModel, String> ocakavanaZivotnostLampyCol = new TableColumn<>("Očakávaná\nživotnosť lampy");
        ocakavanaZivotnostLampyCol.setCellValueFactory(new PropertyValueFactory<>("ocakavanaZivotnostLampy"));
        ocakavanaZivotnostLampyCol.setMinWidth(120);
        projektoryTableView.getColumns().add(ocakavanaZivotnostLampyCol);

        projektoryTableView.setItems(model.getProjektory());
    }
}
