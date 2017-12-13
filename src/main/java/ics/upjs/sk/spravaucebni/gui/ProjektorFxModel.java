package ics.upjs.sk.spravaucebni.gui;

import ics.upjs.sk.spravaucebni.Projektor;
import ics.upjs.sk.spravaucebni.storage.DaoFactory;
import ics.upjs.sk.spravaucebni.storage.ProjektorDao;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProjektorFxModel {

    private StringProperty nazovModelu = new SimpleStringProperty(null);
    private StringProperty pocetNasvHodin = new SimpleStringProperty("0");
    private StringProperty ocakavanaZivotnostLampy = new SimpleStringProperty("730");
    private StringProperty popisKvality = new SimpleStringProperty(null);
    private Projektor aktualnyProjektor;
    private Long ucebnaId;
    private int pocetChyb = 0;

    public ProjektorFxModel(Projektor aktualnyProjektor, Long ucebnaId) {
        this.aktualnyProjektor = aktualnyProjektor;
        this.ucebnaId = ucebnaId;
        inicializuj();
    }
    
    private void inicializuj() {
        if (aktualnyProjektor != null) {
            nazovModelu.set(aktualnyProjektor.getNazovModelu());
            String pom = "";
            pom += aktualnyProjektor.getPocetNasvietenychHodin();
            pocetNasvHodin.set(pom);
            pom = "" + aktualnyProjektor.getOcakavanaZivotnostLampy();
            ocakavanaZivotnostLampy.set(pom);
            popisKvality.set(aktualnyProjektor.getKvalitaObrazu());
        } else {
            setPocetChyb(2);
        }
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

    public StringProperty getPocetNasvHodinProperty() {
        return pocetNasvHodin;
    }

    public String getPocetNasvHodin() {
        return pocetNasvHodin.get();
    }
    
    public void setPocetNasvHodin(String pocetNasvHodin) {
        this.pocetNasvHodin.set(pocetNasvHodin);
    }

    public StringProperty getOcakavanaZivotnostLampyProperty() {
        return ocakavanaZivotnostLampy;
    }

    public String getOcakavanaZivotnostLampy() {
        return ocakavanaZivotnostLampy.get();
    }
    
    public void setOcakavanaZivotnostLampy(String ocakavanaZivotnostLampy) {
        this.ocakavanaZivotnostLampy.set(ocakavanaZivotnostLampy);
    }

    public StringProperty getPopisKvalityProperty() {
        return popisKvality;
    }

    public String getPopisKvality() {
        return popisKvality.get();
    }
    
    public void setPopisKvality(String popisKvality) {
        this.popisKvality.set(popisKvality);
    }

    public int getPocetChyb() {
        return pocetChyb;
    }

    public void setPocetChyb(int pocetChyb) {
        this.pocetChyb = pocetChyb;
    }
    
    public void ulozAktualnyProjektor() {
        if (aktualnyProjektor == null) {
            aktualnyProjektor = new Projektor();
        }
        aktualnyProjektor.setNazovModelu(getNazovModelu());
        aktualnyProjektor.setPocetNasvietenychHodin(Integer.parseInt(getPocetNasvHodin()));
        aktualnyProjektor.setOcakavanaZivotnostLampy(Integer.parseInt(getOcakavanaZivotnostLampy()));
        aktualnyProjektor.setKvalitaObrazu(getPopisKvality());
        aktualnyProjektor.setUcebnaId(ucebnaId);
        ProjektorDao projektorDao = DaoFactory.INSTANCE.getProjektorDao();
        projektorDao.save(aktualnyProjektor);
    }
}
