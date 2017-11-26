package ics.upjs.sk.spravaucebni.storage;

import ics.upjs.sk.spravaucebni.Pocitac;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class MysqlPocitacDao implements PocitacDao {

    private JdbcTemplate jdbcTemplate;

    public MysqlPocitacDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public List<Pocitac> getAll() {
        String sql = "SELECT seriove_cislo, funkcny FROM pocitac";
        return jdbcTemplate.query(sql, new RowMapper<Pocitac> () {
            @Override
            public Pocitac mapRow(ResultSet rs, int rowNum) throws SQLException {
                Pocitac pocitac = new Pocitac();
                pocitac.setSerioveCislo(rs.getString("seriove_cislo"));
                pocitac.setFunkcny(rs.getBoolean("funkcny"));
                return pocitac;
            }
        });
    }

    @Override
    public List<Pocitac> getByUcebnaId(Long id) {
        String sql = "SELECT seriove_cislo, funkcny FROM pocitac WHERE ucebna_id = " + id;
        return jdbcTemplate.query(sql, new RowMapper<Pocitac> () {
            @Override
            public Pocitac mapRow(ResultSet rs, int rowNum) throws SQLException {
                Pocitac pocitac = new Pocitac();
                pocitac.setSerioveCislo(rs.getString("seriove_cislo"));
                pocitac.setFunkcny(rs.getBoolean("funkcny"));
                return pocitac;
            }
        });
    }
}
