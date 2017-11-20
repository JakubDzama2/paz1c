package ics.upjs.sk.spravaucebni.storage;

import ics.upjs.sk.spravaucebni.Projektor;
import ics.upjs.sk.spravaucebni.storage.ProjektorDao;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

public class MysqlProjektorDao implements ProjektorDao {

    private JdbcTemplate jdbcTemplate;

    public MysqlProjektorDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public List<Projektor> getAll() {
        List<Projektor> projektory = new ArrayList<>();
        
        return projektory;
    }
}
