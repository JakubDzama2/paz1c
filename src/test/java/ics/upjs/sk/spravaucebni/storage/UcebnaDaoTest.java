package ics.upjs.sk.spravaucebni.storage;

import ics.upjs.sk.spravaucebni.Ucebna;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UcebnaDaoTest {
    
    private UcebnaDao dao;
    
    public UcebnaDaoTest() {
        dao = DaoFactory.INSTANCE.getUcebnaDao();
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
        List<Ucebna> ucebne = dao.getAll();
        assertTrue(ucebne.size() > 0);
    }
    
    @Test
    public void getByPouzivatelIdTest() {
        PouzivatelDao pouzivatelDao = DaoFactory.INSTANCE.getPouzivatelDao();
        Long pouzivatelId = pouzivatelDao.getAll().get(0).getId();
        List<Ucebna> allByPouzivatelId = dao.getByPouzivatelId(pouzivatelId);
        for (Ucebna ucebna : allByPouzivatelId) {
            assertTrue(ucebna.getIdPouzivatela() == pouzivatelId);
        }
    }
    
    @Test
    public void getByIdTest() {
        Ucebna o1 = dao.getAll().get(0);
        Ucebna o2 = dao.getById(o1.getId());
        assertEquals(o1.getId(), o2.getId());
    }
    
    @Test
    public void getUcebneBezPouzivatelovTest() {
        List<Ucebna> allBezPouzivatelov = dao.getUcebneBezPouzivatelov();
        for (Ucebna ucebna : allBezPouzivatelov) {
            assertTrue(ucebna.getIdPouzivatela() == null);
        }
    }
    
    @Test
    public void saveTest() {
        List<Ucebna> all = dao.getAll();
        Ucebna u = new Ucebna();
        u.setNazov("XI.A");
        u.setIdPouzivatela(1L);
        assertTrue(dao.save(u));
        assertTrue(all.size() + 1 == dao.getAll().size());
    }
    
    
    @Test
    public void deleteTest() {
        int velkost = dao.getAll().size();
        Ucebna u = dao.getAll().get(velkost - 1);
        boolean jeZmazany = dao.delete(u.getId());
        assertTrue(jeZmazany);
        assertTrue(velkost - 1 == dao.getAll().size());
        //dao.save(u);
    }
}
