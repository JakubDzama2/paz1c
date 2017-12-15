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
    public void getByUcebnaIdTest() {
        UcebnaDao ucebnaDao = DaoFactory.INSTANCE.getUcebnaDao();
        Long ucebnaId = ucebnaDao.getAll().get(0).getId();
        List<Tabula> allByUcebnaId = dao.getByUcebnaId(ucebnaId);
        for (Tabula tabula : allByUcebnaId) {
            assertTrue(tabula.getUcebnaId() == ucebnaId);
        }
    }
    
    @Test
    public void getByIdTest() {
        Tabula o1 = dao.getAll().get(0);
        Tabula o2 = dao.getById(o1.getId());
        assertEquals(o1.getId(), o2.getId());
    }
    
    @Test
    public void saveTest() {
        List<Tabula> all = dao.getAll();
        Tabula t = new Tabula();
        t.setTyp("na kriedy");
        t.setPocetPisatiek(51);
        t.setUcebnaId(1L);
        assertTrue(dao.save(t));
        assertTrue(all.size() + 1 == dao.getAll().size());
    }
    
    @Test
    public void deleteTest() {
        int velkost = dao.getAll().size();
        Tabula t = dao.getAll().get(velkost - 1);
        boolean jeZmazany = dao.delete(t.getId());
        assertTrue(jeZmazany);
        assertTrue(velkost - 1 == dao.getAll().size());
 //       dao.save(t);
    }
}
