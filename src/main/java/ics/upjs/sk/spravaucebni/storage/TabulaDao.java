package ics.upjs.sk.spravaucebni.storage;

import ics.upjs.sk.spravaucebni.Tabula;
import java.util.List;

public interface TabulaDao {

    List<Tabula> getAll();

    public List<Tabula> getByUcebnaId(Long id);
    
}
