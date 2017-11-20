package ics.upjs.sk.spravaucebni.storage;

import ics.upjs.sk.spravaucebni.Chyba;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

public class MysqlChybaDao implements ChybaDao {

    private JdbcTemplate jdbcTemplate;

    public MysqlChybaDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public List<Chyba> getAll() {
        List<Chyba> chyby = new ArrayList<>();
        
        return chyby;
    }
}
