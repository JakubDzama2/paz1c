package ics.upjs.sk.spravaucebni.storage;

import ics.upjs.sk.spravaucebni.Spotreba;
import java.time.LocalDate;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class SpotrebaDaoTest {
    
    private SpotrebaDao dao = DaoFactory.INSTANCE.getSpotrebaDao();
    
    public SpotrebaDaoTest() {
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
        List<Spotreba> all = dao.getAll();
        assertTrue(all.size() > 0);
    }
    
    @Test
    public void getByUcebnaIdTest() {
        UcebnaDao ucebnaDao = DaoFactory.INSTANCE.getUcebnaDao();
        Long ucebnaId = ucebnaDao.getAll().get(0).getId();
        List<Spotreba> allByUcebnaId = dao.getByUcebnaId(ucebnaId);
        for (Spotreba spotreba : allByUcebnaId) {
            assertTrue(spotreba.getUcebnaId() == ucebnaId);
        }
    }
    
    @Test
    public void getByIdTest() {
        Spotreba o1 = dao.getAll().get(0);
        Spotreba o2 = dao.getById(o1.getId());
        assertEquals(o1.getId(), o2.getId());
    }
    
    @Test
    public void getByDatumAndUcebnaIdTest() {
        Spotreba spotreba = dao.getAll().get(0);
        List<Spotreba> spotreby = dao.getByDatumAndUcebnaId
        (spotreba.getDatum().getYear(), spotreba.getDatum().getMonthValue(), spotreba.getUcebnaId());
        for (Spotreba spotreba1 : spotreby) {
            assertTrue(spotreba1.getDatum().getYear() == spotreba.getDatum().getYear());
            assertTrue(spotreba1.getDatum().getMonthValue() == spotreba.getDatum().getMonthValue());
            assertTrue(spotreba1.getUcebnaId() == spotreba.getUcebnaId());
        }
        
    }
    
    @Test
    public void saveTest() {
        List<Spotreba> all = dao.getAll();
        Spotreba s = new Spotreba();
        s.setDatum(LocalDate.now());
        s.setHodnota(45);
        s.setUcebnaId(1L);
        assertTrue(dao.save(s));
        assertTrue(all.size() + 1 == dao.getAll().size());
    }
    
    @Test
    public void deleteTest() {
        int velkost = dao.getAll().size();
        Spotreba s = dao.getAll().get(velkost - 1);
        boolean jeZmazany = dao.delete(s.getId());
        assertTrue(jeZmazany);
        assertTrue(velkost - 1 == dao.getAll().size());
    }
}
