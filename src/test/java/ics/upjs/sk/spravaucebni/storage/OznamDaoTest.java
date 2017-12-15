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
    public void getByUcebnaIdTest() {
        UcebnaDao ucebnaDao = DaoFactory.INSTANCE.getUcebnaDao();
        Long ucebnaId = ucebnaDao.getAll().get(0).getId();
        List<Oznam> allByUcebnaId = dao.getByUcebnaId(ucebnaId);
        for (Oznam oznam : allByUcebnaId) {
            assertTrue(oznam.getUcebnaId() == ucebnaId);
        }
    }
    
    @Test
    public void getByIdTest() {
        Oznam o1 = dao.getAll().get(0);
        Oznam o2 = dao.getById(o1.getId());
        assertEquals(o1.getId(), o2.getId());
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
