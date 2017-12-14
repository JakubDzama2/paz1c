package ics.upjs.sk.spravaucebni.gui;

import ics.upjs.sk.spravaucebni.Pocitac;
import ics.upjs.sk.spravaucebni.storage.DaoFactory;
import ics.upjs.sk.spravaucebni.storage.PocitacDao;
import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PocitacFxModel {

    private StringProperty serioveCislo = new SimpleStringProperty(null);
    private StringProperty macAdresa = new SimpleStringProperty(null);
    private ObjectProperty<LocalDate> poslednePouzitie = new SimpleObjectProperty<>();
    private Pocitac aktualnyPocitac;
    private Long ucebnaId;
    private int pocetChyb = 0;

    public PocitacFxModel(Pocitac aktualnyPocitac, Long ucebnaId) {
        this.aktualnyPocitac = aktualnyPocitac;
        this.ucebnaId = ucebnaId;
        inicializuj();
    }
    
    public void inicializuj() {
        if (aktualnyPocitac != null) {
            setSerioveCislo(aktualnyPocitac.getSerioveCislo());
            setMacAdresa(aktualnyPocitac.getMacAdresa());
            setPoslednePouzitie(aktualnyPocitac.getPoslednePouzitie());
        } else {
            setPocetChyb(2);
        }
    }

    public StringProperty getSerioveCisloProperty() {
        return serioveCislo;
    }
    
    public String getSerioveCislo() {
        return serioveCislo.get();
    }

    public void setSerioveCislo(String serioveCislo) {
        this.serioveCislo.set(serioveCislo);
    }

    public StringProperty getMacAdresaProperty() {
        return macAdresa;
    }
    
    public String getMacAdresa() {
        return macAdresa.get();
    }

    public void setMacAdresa(String macAdresa) {
        this.macAdresa.set(macAdresa);
    }

    public ObjectProperty<LocalDate> getPoslednePouzitieProperty() {
        return poslednePouzitie;
    }
    
    public LocalDate getPoslednePouzitie() {
        return poslednePouzitie.get();
    }

    public void setPoslednePouzitie(LocalDate poslednePouzitie) {
        this.poslednePouzitie.set(poslednePouzitie);
    }

    public int getPocetChyb() {
        return pocetChyb;
    }

    public void setPocetChyb(int pocetChyb) {
        this.pocetChyb = pocetChyb;
    }
    
    public void ulozAktualnyPocitac() {
        if (aktualnyPocitac == null) {
            aktualnyPocitac = new Pocitac();
        }
        aktualnyPocitac.setSerioveCislo(getSerioveCislo());
        aktualnyPocitac.setMacAdresa(getMacAdresa());
        aktualnyPocitac.setPoslednePouzitie(getPoslednePouzitie());
        aktualnyPocitac.setUcebnaId(ucebnaId);
        PocitacDao pocitacDao = DaoFactory.INSTANCE.getPocitacDao();
        pocitacDao.save(aktualnyPocitac);
    }
    
}
