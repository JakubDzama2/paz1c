package ics.upjs.sk.spravaucebni;

import java.time.LocalDateTime;
import java.util.List;

public class Pouzivatel {

    private Long id;
    private String meno;
    private String heslo;
    private String email;
    private LocalDateTime poslednePrihlasenie;
    private List<Ucebna> ucebne;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public String getHeslo() {
        return heslo;
    }

    public void setHeslo(String heslo) {
        this.heslo = heslo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getPoslednePrihlasenie() {
        return poslednePrihlasenie;
    }

    public void setPoslednePrihlasenie(LocalDateTime poslednePrihlasenie) {
        this.poslednePrihlasenie = poslednePrihlasenie;
    }

    public List<Ucebna> getUcebne() {
        return ucebne;
    }

    public void setUcebne(List<Ucebna> ucebne) {
        this.ucebne = ucebne;
    }
    
    

}
