package ics.upjs.sk.spravaucebni.gui;

import ics.upjs.sk.spravaucebni.Tabula;
import ics.upjs.sk.spravaucebni.storage.DaoFactory;
import ics.upjs.sk.spravaucebni.storage.TabulaDao;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TabulaFxModel {

    private StringProperty typ = new SimpleStringProperty(null);
    private IntegerProperty pocetPisatiek = new SimpleIntegerProperty(0);
    private Tabula aktualnaTabula;
    private Long ucebnaId;

    public TabulaFxModel(Tabula aktualnaTabula, Long ucebnaId) {
        this.aktualnaTabula = aktualnaTabula;
        this.ucebnaId = ucebnaId;
        inicializuj();
    }
    
    private void inicializuj() {
        if (aktualnaTabula != null) {
            setTyp(aktualnaTabula.getTyp());
            setPocetPisatiek(aktualnaTabula.getPocetPisatiek());
        }
    }

    public StringProperty getTypProperty() {
        return typ;
    }
    
    public String getTyp() {
        return typ.get();
    }

    public void setTyp(String typ) {
        this.typ.set(typ);
    }

    public IntegerProperty getPocetPisatiekProperty() {
        return pocetPisatiek;
    }

    public Integer getPocetPisatiek() {
        return pocetPisatiek.get();
    }
    
    public void setPocetPisatiek(Integer pocetPisatiek) {
        this.pocetPisatiek.set(pocetPisatiek);
    }
    
    public void ulozAktualnuTabulu() {
        if (aktualnaTabula == null) {
            aktualnaTabula = new Tabula();
        }
        aktualnaTabula.setTyp(getTyp());
        aktualnaTabula.setPocetPisatiek(getPocetPisatiek());
        aktualnaTabula.setUcebnaId(ucebnaId);
        TabulaDao tabulaDao = DaoFactory.INSTANCE.getTabulaDao();
        tabulaDao.save(aktualnaTabula);
    }
    
    
}
