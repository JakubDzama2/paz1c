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
}
