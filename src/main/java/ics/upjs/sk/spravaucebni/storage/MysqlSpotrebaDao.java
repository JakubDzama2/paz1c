package ics.upjs.sk.spravaucebni.storage;

import ics.upjs.sk.spravaucebni.Spotreba;
import ics.upjs.sk.spravaucebni.storage.SpotrebaDao;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

public class MysqlSpotrebaDao implements SpotrebaDao {

    private JdbcTemplate jdbcTemplate;

    public MysqlSpotrebaDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public List<Spotreba> getAll() {
        List<Spotreba> spotreby = new ArrayList<>();
        
        return spotreby;
    }
    
}
