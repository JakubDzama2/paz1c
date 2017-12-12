package ics.upjs.sk.spravaucebni.gui;

import ics.upjs.sk.spravaucebni.Pocitac;
import ics.upjs.sk.spravaucebni.Ucebna;
import ics.upjs.sk.spravaucebni.storage.DaoFactory;
import ics.upjs.sk.spravaucebni.storage.PocitacDao;
import ics.upjs.sk.spravaucebni.storage.UcebnaDao;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VyberPocitacFxModel {

    private ObservableList<Pocitac> pocitace = FXCollections.observableArrayList();
    private ObjectProperty<Pocitac> vybratyPocitac = new SimpleObjectProperty<>();
    private PocitacDao pocitacDao = DaoFactory.INSTANCE.getPocitacDao();
    private Long ucebnaId;
    
    public VyberPocitacFxModel(Long ucebnaId) {
        this.ucebnaId = ucebnaId;
        inicializuj();
    }
    
    public void inicializuj() {
        pocitace.setAll(pocitacDao.getByUcebnaId(ucebnaId));
        vybratyPocitac.set(null);
    }

    public ObservableList<Pocitac> getPocitace() {
        return pocitace;
    }

    public void setPocitace(ObservableList<Pocitac> pocitace) {
        this.pocitace = pocitace;
    }

    public ObjectProperty<Pocitac> getVybratyPocitacProperty() {
        return vybratyPocitac;
    }
    
    public Pocitac getVybratyPocitac() {
        return vybratyPocitac.get();
    }

    public void setVybratyPocitac(Pocitac vybratyPocitac) {
        this.vybratyPocitac.set(vybratyPocitac);
    }
    
    
}
