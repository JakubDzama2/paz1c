package ics.upjs.sk.spravaucebni.gui;

import ics.upjs.sk.spravaucebni.Pouzivatel;
import ics.upjs.sk.spravaucebni.Ucebna;
import ics.upjs.sk.spravaucebni.storage.DaoFactory;
import ics.upjs.sk.spravaucebni.storage.PouzivatelDao;
import ics.upjs.sk.spravaucebni.storage.UcebnaDao;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UcebnaFxModel {

    private StringProperty nazov = new SimpleStringProperty();
    private ObservableList<Pouzivatel> pouzivatelia;
    private ObjectProperty<Pouzivatel> pouzivatel = new SimpleObjectProperty<Pouzivatel>();
    private UcebnaDao ucebnaDao = DaoFactory.INSTANCE.getUcebnaDao();
    private PouzivatelDao pouzivatelDao = DaoFactory.INSTANCE.getPouzivatelDao();
    private Ucebna aktualnaUcebna;

    public UcebnaFxModel(Ucebna aktualnaUcebna) {
        this.aktualnaUcebna = aktualnaUcebna;
        pouzivatelia = FXCollections.observableArrayList(pouzivatelDao.getAll());
        Pouzivatel nullovyPouzivatel = new Pouzivatel();
        nullovyPouzivatel.setMeno("--bez správcu--");
        pouzivatelia.add(nullovyPouzivatel);
        if (aktualnaUcebna != null) {
            setNazov(aktualnaUcebna.getNazov());
        } else {
            setPouzivatel(nullovyPouzivatel);
        }
    }

    public StringProperty getNazovProperty() {
        return nazov;
    }
    
    public String getNazov() {
        return nazov.get();
    }

    public void setNazov(String nazov) {
        this.nazov.set(nazov);
    }

    public ObjectProperty<Pouzivatel> getPouzivatelProperty() {
        return pouzivatel;
    }
    
    public Pouzivatel getPouzivatel() {
        return pouzivatel.get();
    }

    public void setPouzivatel(Pouzivatel pouzivatel) {
        this.pouzivatel.set(pouzivatel);
    }

    public ObservableList<Pouzivatel> getPouzivatelia() {
        return pouzivatelia;
    }
    
    public boolean ulozAktualnuUcebnu() {
        if (aktualnaUcebna == null) {
            Ucebna ucebna = new Ucebna();
            aktualnaUcebna = ucebna;
        }
        aktualnaUcebna.setNazov(getNazov());
        if (getPouzivatel().getMeno().equals("--bez správcu--")) {
            aktualnaUcebna.setIdPouzivatela(null);
        } else {
            aktualnaUcebna.setIdPouzivatela(getPouzivatel().getId());
        }
        return ucebnaDao.save(aktualnaUcebna);
    }
    
}
