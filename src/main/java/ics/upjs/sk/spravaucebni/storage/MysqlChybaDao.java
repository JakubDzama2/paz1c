package ics.upjs.sk.spravaucebni.storage;

import ics.upjs.sk.spravaucebni.Chyba;
import java.sql.ResultSet;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

public class MysqlChybaDao implements ChybaDao {

    private JdbcTemplate jdbcTemplate;

    public MysqlChybaDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public List<Chyba> getAll() {
        String sql = "SELECT id, poznamka, cas, hlasatel_chyby FROM chyba";
        return jdbcTemplate.query(sql, (ResultSet rs, int rowNum) -> {
            Chyba chyba = new Chyba();
            chyba.setId(rs.getLong("id"));
            chyba.setPoznamka(rs.getString("poznamka"));
            chyba.setCas(rs.getTimestamp("cas").toLocalDateTime());
            chyba.setHlasatelChyby(rs.getString("hlasatel_chyby"));
            return chyba;
        });
    }

    @Override
    public List<Chyba> getByUcebnaId(Long id) {
        String sql = "SELECT id, poznamka, cas, hlasatel_chyby FROM chyba WHERE ucebna_id = " + id;
        return jdbcTemplate.query(sql, (ResultSet rs, int rowNum) -> {
            Chyba chyba = new Chyba();
            chyba.setId(rs.getLong("id"));
            chyba.setPoznamka(rs.getString("poznamka"));
            chyba.setCas(rs.getTimestamp("cas").toLocalDateTime());
            chyba.setHlasatelChyby(rs.getString("hlasatel_chyby"));
            return chyba;
        });
    }
}
