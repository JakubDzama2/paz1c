package ics.upjs.sk.spravaucebni.gui;

import ics.upjs.sk.spravaucebni.Projektor;
import ics.upjs.sk.spravaucebni.Ucebna;
import ics.upjs.sk.spravaucebni.storage.DaoFactory;
import ics.upjs.sk.spravaucebni.storage.ProjektorDao;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VyberProjektorFxModel {

    private ObservableList<Projektor> projektory = FXCollections.observableArrayList();
    private ObjectProperty<Projektor> vybratyProjektor = new SimpleObjectProperty<>();
    private ProjektorDao projektorDao = DaoFactory.INSTANCE.getProjektorDao();
    private Long ucebnaId;
    
    public VyberProjektorFxModel(Ucebna ucebna) {
        ucebnaId = ucebna.getId();
        inicializuj();
    }
    
    public void inicializuj() {
        projektory.setAll(projektorDao.getByUcebnaId(ucebnaId));
        vybratyProjektor.set(null);
    }

    public ObservableList<Projektor> getProjektory() {
        return projektory;
    }

    public void setProjektory(ObservableList<Projektor> projektory) {
        this.projektory = projektory;
    }

    public ObjectProperty<Projektor> getVybratyProjektorProperty() {
        return vybratyProjektor;
    }
    
    public Projektor getVybratyProjektor() {
        return vybratyProjektor.get();
    }

    public void setVybratyProjektor(Projektor vybratyProjektor) {
        this.vybratyProjektor.set(vybratyProjektor);
    }
    
    
    
}
