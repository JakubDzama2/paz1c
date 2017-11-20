package ics.upjs.sk.spravaucebni.storage;

import ics.upjs.sk.spravaucebni.Spotreba;
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

    public void getAllTest() {
        List<Spotreba> all = dao.getAll();
        assertTrue(all.size() > 0);
    }
}
