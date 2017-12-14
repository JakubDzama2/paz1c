package ics.upjs.sk.spravaucebni.storage;

import ics.upjs.sk.spravaucebni.Oznam;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class OznamDaoTest {

    private OznamDao dao = DaoFactory.INSTANCE.getOznamDao();
    
    public OznamDaoTest() {
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
        List<Oznam> all = dao.getAll();
        assertTrue(all.size() > 0);
    }
    
    @Test
    public void saveTest() {
        List<Oznam> all = dao.getAll();
        Oznam oznam = new Oznam();
        oznam.setCas(LocalDateTime.now());
        oznam.setHlasatel("Diminik");
        oznam.setPoznamka("nie su kriedy");
        oznam.setUcebnaId(1L);
        assertTrue(dao.save(oznam));
        assertTrue(all.size() + 1 == dao.getAll().size());
    }
    
    @Test
    public void deleteTest() {
        int velkost = dao.getAll().size();
        Oznam oznam = dao.getAll().get(velkost - 1);
        boolean jeZmazany = dao.delete(oznam.getId());
        assertTrue(jeZmazany);
        assertTrue(velkost - 1 == dao.getAll().size());
    }
}
