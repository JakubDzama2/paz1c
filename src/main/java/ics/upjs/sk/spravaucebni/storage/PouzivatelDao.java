package ics.upjs.sk.spravaucebni.storage;

import ics.upjs.sk.spravaucebni.Pouzivatel;
import java.util.List;

public interface PouzivatelDao {
    
    List<Pouzivatel> getAll();

    public boolean save(Pouzivatel p);
    
    public boolean delete(Long id);
}
