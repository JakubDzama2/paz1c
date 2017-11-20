package ics.upjs.sk.spravaucebni.storage;

import ics.upjs.sk.spravaucebni.Pouzivatel;
import ics.upjs.sk.spravaucebni.storage.PouzivatelDao;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

public class MysqlPouzivatelDao implements PouzivatelDao {

    private JdbcTemplate jdbcTemplate;
    
    public MysqlPouzivatelDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public List<Pouzivatel> getAll() {
        List<Pouzivatel> pouzivatelia = new ArrayList<>();
        
        return pouzivatelia;
    } 
    
    
}
