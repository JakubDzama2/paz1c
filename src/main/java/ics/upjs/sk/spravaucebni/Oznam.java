package ics.upjs.sk.spravaucebni;

import java.time.LocalDateTime;

public class Oznam {

    private Long id;
    private String poznamka;
    private LocalDateTime cas;
    private String hlasatel;
    private Long ucebnaId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPoznamka() {
        return poznamka;
    }

    public void setPoznamka(String poznamka) {
        this.poznamka = poznamka;
    }

    public LocalDateTime getCas() {
        return cas;
    }

    public void setCas(LocalDateTime cas) {
        this.cas = cas;
    }

    public String getHlasatel() {
        return hlasatel;
    }

    public void setHlasatel(String hlasatel) {
        this.hlasatel = hlasatel;
    }

    public Long getUcebnaId() {
        return ucebnaId;
    }

    public void setUcebnaId(Long ucebnaId) {
        this.ucebnaId = ucebnaId;
    }

    @Override
    public String toString() {
        return getHlasatel() + " - " + getPoznamka();
    }
    
    
    
}
