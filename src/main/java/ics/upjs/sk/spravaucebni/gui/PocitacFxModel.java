package ics.upjs.sk.spravaucebni.gui;

import ics.upjs.sk.spravaucebni.Pocitac;
import java.time.LocalDateTime;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PocitacFxModel {

    private StringProperty serioveCislo = new SimpleStringProperty(null);
    private StringProperty macAdresa = new SimpleStringProperty(null);
    private ObjectProperty<LocalDateTime> poslednePouzitie = new SimpleObjectProperty<>();
    private Pocitac aktualnyPocitac;
    private int pocetChyb = 0;

    public PocitacFxModel(Pocitac aktualnyPocitac) {
        this.aktualnyPocitac = aktualnyPocitac;
        inicializuj();
    }
    
    private void inicializuj() {
        if (aktualnyPocitac != null) {
            setSerioveCislo(aktualnyPocitac.getSerioveCislo());
            setMacAdresa(aktualnyPocitac.getMacAdresa());          
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

    public ObjectProperty<LocalDateTime> getPoslednePouzitieProperty() {
        return poslednePouzitie;
    }
    
    public LocalDateTime getPoslednePouzitie() {
        return poslednePouzitie.get();
    }

    public void setPoslednePouzitie(LocalDateTime poslednePouzitie) {
        this.poslednePouzitie.set(poslednePouzitie);
    }

    public int getPocetChyb() {
        return pocetChyb;
    }

    public void setPocetChyb(int pocetChyb) {
        this.pocetChyb = pocetChyb;
    }
    
    
    
}
