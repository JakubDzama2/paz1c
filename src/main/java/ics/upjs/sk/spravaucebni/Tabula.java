package ics.upjs.sk.spravaucebni;

public class Tabula {

    private Long id;
    private String typ;
    private int pocetPisatiek;
    private Long ucebnaId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public int getPocetPisatiek() {
        return pocetPisatiek;
    }

    public void setPocetPisatiek(int pocetPisatiek) {
        this.pocetPisatiek = pocetPisatiek;
    }
    
     public Long getUcebnaId() {
        return ucebnaId;
    }

    public void setUcebnaId(Long ucebnaId) {
        this.ucebnaId = ucebnaId;
    }

    @Override
    public String toString() {
        return getTyp();
    }
    
    
}
