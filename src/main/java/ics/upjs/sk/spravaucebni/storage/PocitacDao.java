package ics.upjs.sk.spravaucebni.storage;

import ics.upjs.sk.spravaucebni.Pocitac;
import java.util.List;

public interface PocitacDao {

    List<Pocitac> getAll();

    public List<Pocitac> getByUcebnaId(Long id);
    
}
