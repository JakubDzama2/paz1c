package ics.upjs.sk.spravaucebni.gui;

import ics.upjs.sk.spravaucebni.Tabula;
import ics.upjs.sk.spravaucebni.storage.DaoFactory;
import ics.upjs.sk.spravaucebni.storage.TabulaDao;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VyberTabuluFxModel {

    private ObservableList<Tabula> tabule = FXCollections.observableArrayList();
    private ObjectProperty<Tabula> vybrataTabula = new SimpleObjectProperty<>();
    private TabulaDao tabulaDao = DaoFactory.INSTANCE.getTabulaDao();
    private Long ucebnaId;
    
    public VyberTabuluFxModel(Long ucebnaId) {
        this.ucebnaId = ucebnaId;
        inicializuj();
    }
    
    public void inicializuj() {
        tabule.setAll(tabulaDao.getByUcebnaId(ucebnaId));
        vybrataTabula.set(null);
    }

    public ObservableList<Tabula> getTabule() {
        return tabule;
    }

    public void setTabule(ObservableList<Tabula> tabule) {
        this.tabule = tabule;
    }

    public ObjectProperty<Tabula> getVybrataTabulaProperty() {
        return vybrataTabula;
    }
    
    public Tabula getVybrataTabula() {
        return vybrataTabula.get();
    }

    public void setVybrataTabula(Tabula vybrataTabula) {
        this.vybrataTabula.set(vybrataTabula);
    }
    
    
    
}
