package ics.upjs.sk.spravaucebni.storage;

import ics.upjs.sk.spravaucebni.Ucebna;
import java.util.List;

public interface UcebnaDao {
    
    List<Ucebna> getAll();
    
    List<Ucebna> getByPouzivatelId(Long id);
    
}
