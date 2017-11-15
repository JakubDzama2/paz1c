package ics.upjs.sk.spravaucebni;

import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

public class MysqlUcebnaDao implements UcebnaDao{

    private JdbcTemplate jdbcTemplate;
    
    public MysqlUcebnaDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }   
    
    @Override
    public List<Ucebna> getAll() {
        List<Ucebna> ucebne = new ArrayList<>();
        
        return ucebne;
    }


    
}
