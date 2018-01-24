package ics.upjs.sk.spravaucebni.gui;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class JedenProjektorFxModel {

    private IntegerProperty pocetNasvietenychHodin = new SimpleIntegerProperty();
    private StringProperty kvalitaObrazu = new SimpleStringProperty();
    private StringProperty nazovModelu = new SimpleStringProperty();
    private IntegerProperty ocakavanaZivotnostLampy = new SimpleIntegerProperty();
    private StringProperty nazovUcebne = new SimpleStringProperty();

    public IntegerProperty getPocetNasvietenychHodinProperty() {
        return pocetNasvietenychHodin;
    }
    
    public Integer getPocetNasvietenychHodin() {
        return pocetNasvietenychHodin.get();
    }

    public void setPocetNasvietenychHodin(Integer pocetNasvietenychHodin) {
        this.pocetNasvietenychHodin.set(pocetNasvietenychHodin);
    }

    public StringProperty getKvalitaObrazuProperty() {
        return kvalitaObrazu;
    }
    
    public String getKvalitaObrazu() {
        return kvalitaObrazu.get();
    }

    public void setKvalitaObrazu(String kvalitaObrazu) {
        this.kvalitaObrazu.set(kvalitaObrazu);
    }

    public StringProperty getNazovModeluProperty() {
        return nazovModelu;
    }
    
    public String getNazovModelu() {
        return nazovModelu.get();
    }

    public void setNazovModelu(String nazovModelu) {
        this.nazovModelu.set(nazovModelu);
    }

    public IntegerProperty getOcakavanaZivotnostLampyProperty() {
        return ocakavanaZivotnostLampy;
    }
    
    public Integer getOcakavanaZivotnostLampy() {
        return ocakavanaZivotnostLampy.get();
    }

    public void setOcakavanaZivotnostLampy(Integer ocakavanaZivotnostLampy) {
        this.ocakavanaZivotnostLampy.set(ocakavanaZivotnostLampy);
    }

    public StringProperty getNazovUcebneProperty() {
        return nazovUcebne;
    }
    
    public String getNazovUcebne() {
        return nazovUcebne.get();
    }

    public void setNazovUcebne(String nazovUcebne) {
        this.nazovUcebne.set(nazovUcebne);
    }
    
    
}
