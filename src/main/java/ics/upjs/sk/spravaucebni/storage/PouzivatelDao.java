package ics.upjs.sk.spravaucebni.storage;

import ics.upjs.sk.spravaucebni.Pouzivatel;
import java.util.List;

public interface PouzivatelDao {
    
    List<Pouzivatel> getAll();
    
    public Pouzivatel getById(Long id);

    public boolean save(Pouzivatel p);
    
    public boolean delete(Long id);
}
