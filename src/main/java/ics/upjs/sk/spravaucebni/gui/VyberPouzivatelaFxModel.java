package ics.upjs.sk.spravaucebni.gui;

import ics.upjs.sk.spravaucebni.Pouzivatel;
import ics.upjs.sk.spravaucebni.storage.DaoFactory;
import ics.upjs.sk.spravaucebni.storage.PouzivatelDao;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VyberPouzivatelaFxModel {
    private ObservableList<Pouzivatel> pouzivatelia = FXCollections.observableArrayList();
    private PouzivatelDao pouzivatelDao = DaoFactory.INSTANCE.getPouzivatelDao();
    private ObjectProperty<Pouzivatel> vybratyPouzivatel = new SimpleObjectProperty<>();
    
    public VyberPouzivatelaFxModel() {
        nacitaj();
        
    }
    
    public void nacitaj(){
        setVybratyPouzivatel(null);
        this.pouzivatelia.setAll(pouzivatelDao.getAll());
    }

    public ObservableList<Pouzivatel> getPouzivatelov() {
        return pouzivatelia;
    }

    public void setPouzivatelov(ObservableList<Pouzivatel> pouzivatelia) {
        this.pouzivatelia = pouzivatelia;
    }

    public Pouzivatel getVybratyPouzivatel() {
        return vybratyPouzivatel.get();
    }

    public ObjectProperty getVybratyPouzivatelProperty() {
        return vybratyPouzivatel;
    }
    
    public void setVybratyPouzivatel(Pouzivatel vybratyPouzivatel) {
        this.vybratyPouzivatel.set(vybratyPouzivatel);
    }
}
