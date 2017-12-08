package ics.upjs.sk.spravaucebni.storage;

import ics.upjs.sk.spravaucebni.Chyba;
import java.util.List;

public interface ChybaDao {

    List<Chyba> getAll();

    public List<Chyba> getByUcebnaId(Long id);
    
    public boolean save(Chyba chyba);
    
    public boolean delete(Long id);
    
}
