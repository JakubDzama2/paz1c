package ics.upjs.sk.spravaucebni;

import java.time.LocalDate;

public class Spotreba {

    private Long id;
    private int hodnota;
    private LocalDate datum;
    private Long ucebnaId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getHodnota() {
        return hodnota;
    }

    public void setHodnota(int hodnota) {
        this.hodnota = hodnota;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }
    
     public Long getUcebnaId() {
        return ucebnaId;
    }

    public void setUcebnaId(Long ucebnaId) {
        this.ucebnaId = ucebnaId;
    }

    @Override
    public String toString() {
        return getDatum() + " : " + getHodnota();
    }
    
    
}
