package ics.upjs.sk.spravaucebni.storage;

import ics.upjs.sk.spravaucebni.Ucebna;
import java.util.List;

public interface UcebnaDao {
    
    List<Ucebna> getAll();
    
    List<Ucebna> getUcebneBezPouzivatelov();
    
    List<Ucebna> getByPouzivatelId(Long id);

    public Ucebna getById(Long id);
    
    public boolean save(Ucebna u);
    
    public boolean delete(Long id);
    
}
