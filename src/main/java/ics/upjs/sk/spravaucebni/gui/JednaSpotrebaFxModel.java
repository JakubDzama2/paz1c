package ics.upjs.sk.spravaucebni.gui;

import java.time.LocalDate;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class JednaSpotrebaFxModel {

    private ObjectProperty<LocalDate> datum = new SimpleObjectProperty<>(null);
    private IntegerProperty hodnota = new SimpleIntegerProperty();
    private DoubleProperty cena = new SimpleDoubleProperty();

    public ObjectProperty<LocalDate> getDatumProperty() {
        return datum;
    }
    
    public LocalDate getDatum() {
        return datum.get();
    }

    public void setDatum(LocalDate datum) {
        this.datum.set(datum);
    }

    public IntegerProperty getHodnotaProperty() {
        return hodnota;
    }

    public Integer getHodnota() {
        return hodnota.get();
    }
    
    public void setHodnota(Integer hodnota) {
        this.hodnota.set(hodnota);
    }

    public DoubleProperty getCenaProperty() {
        return cena;
    }
    
    public Double getCena() {
        return cena.get();
    }

    public void setCena(Double cena) {
        this.cena.set(cena);
    }
    
    
}
