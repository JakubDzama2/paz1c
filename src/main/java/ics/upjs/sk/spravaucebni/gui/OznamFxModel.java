package ics.upjs.sk.spravaucebni.gui;

import ics.upjs.sk.spravaucebni.Oznam;
import ics.upjs.sk.spravaucebni.storage.DaoFactory;
import ics.upjs.sk.spravaucebni.storage.OznamDao;
import java.time.LocalDateTime;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class OznamFxModel {

    private StringProperty hlasatel = new SimpleStringProperty(null);
    private StringProperty poznamka = new SimpleStringProperty(null);
    
    private Oznam aktualnyOznam;
    private Long ucebnaId;
    private int pocetChyb;

    public OznamFxModel(Oznam aktualnyOznam, Long ucebnaId) {
        this.aktualnyOznam = aktualnyOznam;
        this.ucebnaId = ucebnaId;
        inicializuj();
    }
    
    private void inicializuj() {
        if (aktualnyOznam != null) {
            setHlasatel(aktualnyOznam.getHlasatel());
            setPoznamka(aktualnyOznam.getPoznamka());
        } else {
            setPocetChyb(2);
        }
    }

    public StringProperty getHlasatelProperty() {
        return hlasatel;
    }

    public String getHlasatel() {
        return hlasatel.get();
    }
    
    public void setHlasatel(String hlasatel) {
        this.hlasatel.set(hlasatel);
    }

    public StringProperty getPoznamkaProperty() {
        return poznamka;
    }

    public String getPoznamka() {
        return poznamka.get();
    }
    
    public void setPoznamka(String poznamka) {
        this.poznamka.set(poznamka);
    }
    
    public int getPocetChyb() {
        return pocetChyb;
    }

    public void setPocetChyb(int pocetChyb) {
        this.pocetChyb = pocetChyb;
    }
    
    public void ulozAktualnyOznam() {
        aktualnyOznam = new Oznam();
        aktualnyOznam.setHlasatel(getHlasatel());
        aktualnyOznam.setPoznamka(getPoznamka());
        aktualnyOznam.setCas(LocalDateTime.now());
        aktualnyOznam.setUcebnaId(ucebnaId);
        OznamDao oznamDao = DaoFactory.INSTANCE.getOznamDao();
        oznamDao.save(aktualnyOznam);
    }
    
}
