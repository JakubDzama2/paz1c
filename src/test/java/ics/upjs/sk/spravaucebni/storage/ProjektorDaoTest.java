package ics.upjs.sk.spravaucebni.storage;

import ics.upjs.sk.spravaucebni.Projektor;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProjektorDaoTest {
    
    private ProjektorDao dao = DaoFactory.INSTANCE.getProjektorDao();
    
    public ProjektorDaoTest() {
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
        List<Projektor> all = dao.getAll();
        assertTrue(all.size() > 0);
    }
    
    @Test
    public void saveTest() {
        List<Projektor> all = dao.getAll();
        Projektor p = new Projektor();
        p.setKvalitaObrazu("good");
        p.setNazovModelu("A14");
        p.setOcakavanaZivotnostLampy(457);
        p.setPocetNasvietenychHodin(4564);
        p.setUcebnaId(1L);
        assertTrue(dao.save(p));
        assertTrue(all.size() + 1 == dao.getAll().size());
    }
    
    @Test
    public void deleteTest() {
        int velkost = dao.getAll().size();
        Projektor p = dao.getAll().get(velkost - 1);
        boolean jeZmazany = dao.delete(p.getId());
        assertTrue(jeZmazany);
        assertTrue(velkost - 1 == dao.getAll().size());
//        dao.save(p);
    }
}
