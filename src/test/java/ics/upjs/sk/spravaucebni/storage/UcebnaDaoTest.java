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
}
