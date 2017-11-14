package ics.upjs.sk.spravaucebni;

public class Projektor {

    private Long id;
    private int pocetNasvietenychHodin;
    private String kvalitaObrazu;
    private String nazovModelu;
    private int ocakavanaZivotnostLampy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPocetNasvietenychHodin() {
        return pocetNasvietenychHodin;
    }

    public void setPocetNasvietenychHodin(int pocetNasvietenychHodin) {
        this.pocetNasvietenychHodin = pocetNasvietenychHodin;
    }

    public String getKvalitaObrazu() {
        return kvalitaObrazu;
    }

    public void setKvalitaObrazu(String kvalitaObrazu) {
        this.kvalitaObrazu = kvalitaObrazu;
    }

    public String getNazovModelu() {
        return nazovModelu;
    }

    public void setNazovModelu(String nazovModelu) {
        this.nazovModelu = nazovModelu;
    }

    public int getOcakavanaZivotnostLampy() {
        return ocakavanaZivotnostLampy;
    }

    public void setOcakavanaZivotnostLampy(int ocakavanaZivotnostLampy) {
        this.ocakavanaZivotnostLampy = ocakavanaZivotnostLampy;
    }
    
    
    
}
