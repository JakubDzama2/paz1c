package ics.upjs.sk.spravaucebni.storage;

import ics.upjs.sk.spravaucebni.Tabula;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TabulaDaoTest {
    
    private TabulaDao dao = DaoFactory.INSTANCE.getTabulaDao();
    
    public TabulaDaoTest() {
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
        List<Tabula> all = dao.getAll();
        assertTrue(all.size() > 0);
    }
    
    @Test
    public void saveTest() {
        List<Tabula> all = dao.getAll();
        Tabula t = new Tabula();
        t.setTyp("na kriedy");
        t.setPocetPisatiek(51);
        t.setUcebnaId(1L);
        dao.save(t);
        assertTrue(all.size() + 1 == dao.getAll().size());
    }
}
