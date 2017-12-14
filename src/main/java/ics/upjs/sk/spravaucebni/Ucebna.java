package ics.upjs.sk.spravaucebni;

import ics.upjs.sk.spravaucebni.storage.DaoFactory;
import ics.upjs.sk.spravaucebni.storage.PouzivatelDao;
import java.util.ArrayList;
import java.util.List;

public class Ucebna {

    private Long id;
    private String nazov;
    private List<Tabula> tabule = new ArrayList<>();
    private List<Pocitac> pocitace = new ArrayList<>();
    private List<Projektor> projektory = new ArrayList<>();
    private List<Spotreba> spotreby = new ArrayList<>();
    private List<Oznam> oznamy = new ArrayList<>();
    private Long idPouzivatela;

    
    
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

    public List<Oznam> getOznamy() {
        return oznamy;
    }

    public void setOznamy(List<Oznam> oznamy) {
        this.oznamy = oznamy;
    }

    public Long getIdPouzivatela() {
        return idPouzivatela;
    }

    public void setIdPouzivatela(Long idPouzivatela) {
        this.idPouzivatela = idPouzivatela;
    }

    @Override
    public String toString() {
        PouzivatelDao pouzivatelDao = DaoFactory.INSTANCE.getPouzivatelDao();
        if (idPouzivatela != null && idPouzivatela != 0) {
            return getNazov() + "     " + pouzivatelDao.getById(idPouzivatela).getMeno();
        } else {
            return getNazov() + "     nemá správcu";
        }
    }
    
    
    
}
