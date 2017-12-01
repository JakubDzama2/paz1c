package ics.upjs.sk.spravaucebni;

import java.time.LocalDateTime;

public class Chyba {

    private Long id;
    private String poznamka;
    private LocalDateTime cas;
    private String hlasatelChyby;
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

    public String getHlasatelChyby() {
        return hlasatelChyby;
    }

    public void setHlasatelChyby(String hlasatelChyby) {
        this.hlasatelChyby = hlasatelChyby;
    }

    public Long getUcebnaId() {
        return ucebnaId;
    }

    public void setUcebnaId(Long ucebnaId) {
        this.ucebnaId = ucebnaId;
    }
    
    
    
}
