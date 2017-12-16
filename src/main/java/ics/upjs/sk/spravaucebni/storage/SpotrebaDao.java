package ics.upjs.sk.spravaucebni.storage;

import ics.upjs.sk.spravaucebni.Spotreba;
import java.util.List;

public interface SpotrebaDao {

    List<Spotreba> getAll();

    public List<Spotreba> getByUcebnaId(Long id);
    
    public Spotreba getById(Long id);
    
    public List<Spotreba> getByDatumAndUcebnaId(int rok, int mesiac, Long id);
    
    public boolean save(Spotreba spotreba);
    
    public boolean delete(Long id);
    
}
