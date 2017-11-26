package ics.upjs.sk.spravaucebni.storage;

import ics.upjs.sk.spravaucebni.Pouzivatel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class MysqlPouzivatelDao implements PouzivatelDao {

    private JdbcTemplate jdbcTemplate;
    
    public MysqlPouzivatelDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public List<Pouzivatel> getAll() {
        String sql = "SELECT * FROM pouzivatel";
        return jdbcTemplate.query(sql, new RowMapper<Pouzivatel>() {
            @Override
            public Pouzivatel mapRow(ResultSet rs, int rowNum) throws SQLException {
                Pouzivatel pouzivatel = new Pouzivatel();
                pouzivatel.setId(rs.getLong("id"));
                pouzivatel.setMeno(rs.getString("meno"));
                pouzivatel.setPoslednePrihlasenie(rs.getTimestamp("posledne_prihlasenie").toLocalDateTime());
                pouzivatel.setEmail(rs.getString("email"));
                return pouzivatel;
            }
            
        });
    } 
    
    
}
