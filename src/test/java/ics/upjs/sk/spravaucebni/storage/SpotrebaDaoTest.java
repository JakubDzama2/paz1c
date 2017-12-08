package ics.upjs.sk.spravaucebni.storage;

import ics.upjs.sk.spravaucebni.Spotreba;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    public void saveTest() {
        List<Spotreba> all = dao.getAll();
        Spotreba s = new Spotreba();
        s.setDatum(LocalDate.now());
        s.setHodnota(45);
        s.setUcebnaId(1L);
        dao.save(s);
        List<Spotreba> all2 = dao.getAll();
        assertTrue(all.size() + 1 == all2.size());
    }
    
    @Test
    public void deleteTest() {
        int velkost = dao.getAll().size();
        Spotreba s = dao.getAll().get(0);
        boolean jeZmazany = dao.delete(s.getId());
        assertTrue(jeZmazany);
        assertTrue(velkost - 1 == dao.getAll().size());
        dao.save(s);
    }
}
