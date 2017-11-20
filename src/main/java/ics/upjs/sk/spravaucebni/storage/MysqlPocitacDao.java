package ics.upjs.sk.spravaucebni.storage;

import ics.upjs.sk.spravaucebni.Pocitac;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

public class MysqlPocitacDao implements PocitacDao {

    private JdbcTemplate jdbcTemplate;

    public MysqlPocitacDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public List<Pocitac> getAll() {
        List<Pocitac> pocitace = new ArrayList<>();
        
        return pocitace;
    }
}
