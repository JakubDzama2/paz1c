package ics.upjs.sk.spravaucebni.storage;

import ics.upjs.sk.spravaucebni.Projektor;
import java.util.List;

public interface ProjektorDao {

    List<Projektor> getAll();

    public List<Projektor> getByUcebnaId(Long id);

    public void save(Projektor p);
    
}
