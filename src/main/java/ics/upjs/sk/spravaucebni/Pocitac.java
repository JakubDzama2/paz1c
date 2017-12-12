package ics.upjs.sk.spravaucebni;

import java.time.LocalDateTime;

public class Pocitac {

    private Long id;
    private String serioveCislo;
    private String macAdresa;
    private LocalDateTime poslednePouzitie;
    private Long ucebnaId;

    public String getSerioveCislo() {
        return serioveCislo;
    }

    public void setSerioveCislo(String serioveCislo) {
        this.serioveCislo = serioveCislo;
    }
    
     public Long getUcebnaId() {
        return ucebnaId;
    }

    public void setUcebnaId(Long ucebnaId) {
        this.ucebnaId = ucebnaId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getPoslednePouzitie() {
        return poslednePouzitie;
    }

    public void setPoslednePouzitie(LocalDateTime poslednePouzitie) {
        this.poslednePouzitie = poslednePouzitie;
    }

    public String getMacAdresa() {
        return macAdresa;
    }

    public void setMacAdresa(String macAdresa) {
        this.macAdresa = macAdresa;
    }

    @Override
    public String toString() {
        return getSerioveCislo();
    }
    
    
}
