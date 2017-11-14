package ics.upjs.sk.spravaucebni;

import java.time.LocalDateTime;

public class Spotreba {

    private Long id;
    private int hodnota;
    private LocalDateTime cas;

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

    public LocalDateTime getCas() {
        return cas;
    }

    public void setCas(LocalDateTime cas) {
        this.cas = cas;
    }
    
    
    
}
