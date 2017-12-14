package ics.upjs.sk.spravaucebni.storage;

import ics.upjs.sk.spravaucebni.Oznam;
import java.util.List;

public interface OznamDao {

    List<Oznam> getAll();

    public List<Oznam> getByUcebnaId(Long id);
    
    public Oznam getById(Long id);
    
    public boolean save(Oznam chyba);
    
    public boolean delete(Long id);
    
}
