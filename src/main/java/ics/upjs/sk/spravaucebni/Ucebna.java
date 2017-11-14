package ics.upjs.sk.spravaucebni;

import java.util.List;

public class Ucebna {

    private Long id;
    private String nazov;
    private List<Tabula> tabule;
    private List<Pocitac> pocitace;
    private List<Projektor> projektory;
    private List<Spotreba> spotreby;
    private List<Chyba> chyby;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public List<Tabula> getTabule() {
        return tabule;
    }

    public void setTabule(List<Tabula> tabule) {
        this.tabule = tabule;
    }

    public List<Pocitac> getPocitace() {
        return pocitace;
    }

    public void setPocitace(List<Pocitac> pocitace) {
        this.pocitace = pocitace;
    }

    public List<Projektor> getProjektory() {
        return projektory;
    }

    public void setProjektory(List<Projektor> projektory) {
        this.projektory = projektory;
    }

    public List<Spotreba> getSpotreby() {
        return spotreby;
    }

    public void setSpotreby(List<Spotreba> spotreby) {
        this.spotreby = spotreby;
    }

    public List<Chyba> getChyby() {
        return chyby;
    }

    public void setChyby(List<Chyba> chyby) {
        this.chyby = chyby;
    }
    
    
    
}
