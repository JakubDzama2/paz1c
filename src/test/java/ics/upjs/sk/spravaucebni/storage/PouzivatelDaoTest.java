package ics.upjs.sk.spravaucebni.storage;

import ics.upjs.sk.spravaucebni.Pouzivatel;
import ics.upjs.sk.spravaucebni.Ucebna;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PouzivatelDaoTest {
    
    private PouzivatelDao dao = DaoFactory.INSTANCE.getPouzivatelDao();
    
    public PouzivatelDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void getAllTest() {
        List<Pouzivatel> pouzivatelia = dao.getAll();
        assertTrue(pouzivatelia.size() > 0);
    }
    
    @Test
    public void saveTest() {
        List<Pouzivatel> all = dao.getAll();
        Pouzivatel p = new Pouzivatel();
        p.setEmail("jan@sranda.sk");
        p.setMeno("jam");
        p.setHeslo("nevadi");
        p.setSol("sol");
        p.setPoslednePrihlasenie(LocalDateTime.now());
        p.setUcebne(new ArrayList<Ucebna>());
        dao.save(p);
        assertTrue(all.size() + 1 == dao.getAll().size());
    }
}
