package ics.upjs.sk.spravaucebni.storage;

import ics.upjs.sk.spravaucebni.Chyba;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ChybaDaoTest {

    private ChybaDao dao = DaoFactory.INSTANCE.getChybaDao();
    
    public ChybaDaoTest() {
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
        List<Chyba> all = dao.getAll();
        assertTrue(all.size() > 0);
    }
    
    @Test
    public void saveTest() {
        List<Chyba> all = dao.getAll();
        Chyba ch = new Chyba();
        ch.setCas(LocalDateTime.now());
        ch.setHlasatelChyby("Ja");
        ch.setPoznamka("ahoj");
        ch.setUcebnaId(1L);
        dao.save(ch);
        List<Chyba> all2 = dao.getAll();
        assertTrue(all.size() + 1 == all2.size());
    }
    
    @Test
    public void deleteTest() {
        int velkost = dao.getAll().size();
        Chyba ch = dao.getAll().get(velkost - 1);
        boolean jeZmazany = dao.delete(ch.getId());
        assertTrue(jeZmazany);
        assertTrue(velkost - 1 == dao.getAll().size());
        dao.save(ch);
    }
}
