package ics.upjs.sk.spravaucebni.storage;

import ics.upjs.sk.spravaucebni.Tabula;
import ics.upjs.sk.spravaucebni.storage.TabulaDao;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

public class MysqlTabulaDao implements TabulaDao {

    private JdbcTemplate jdbcTemplate;

    public MysqlTabulaDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public List<Tabula> getAll() {
        List<Tabula> tabule = new ArrayList<>();
        
        return tabule;
    }
    
}
