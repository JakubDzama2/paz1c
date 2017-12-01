package ics.upjs.sk.spravaucebni.storage;

import ics.upjs.sk.spravaucebni.Pocitac;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class MysqlPocitacDao implements PocitacDao {

    private JdbcTemplate jdbcTemplate;

    public MysqlPocitacDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Pocitac> getAll() {
        String sql = "SELECT id, seriove_cislo, posledne_prihlasenie, ucebna_id FROM pocitac ORDER BY id";
        return jdbcTemplate.query(sql, new RowMapper<Pocitac>() {
            @Override
            public Pocitac mapRow(ResultSet rs, int rowNum) throws SQLException {
                Pocitac pocitac = new Pocitac();
                pocitac.setId(rs.getLong("id"));
                pocitac.setSerioveCislo(rs.getString("seriove_cislo"));
                pocitac.setPoslednePouzitie(rs.getTimestamp("posledne_prihlasenie").toLocalDateTime());
                pocitac.setUcebnaId(rs.getLong("ucebna_id"));
                return pocitac;
            }
        });
    }

    @Override
    public List<Pocitac> getByUcebnaId(Long id) {
        String sql = "SELECT id, seriove_cislo, posledne_prihlasenie, ucebna_id FROM pocitac WHERE ucebna_id = " + id + " ORDER BY id";
        return jdbcTemplate.query(sql, new RowMapper<Pocitac>() {
            @Override
            public Pocitac mapRow(ResultSet rs, int rowNum) throws SQLException {
                Pocitac pocitac = new Pocitac();
                pocitac.setId(rs.getLong("id"));
                pocitac.setSerioveCislo(rs.getString("seriove_cislo"));
                pocitac.setPoslednePouzitie(rs.getTimestamp("posledne_prihlasenie").toLocalDateTime());
                pocitac.setUcebnaId(rs.getLong("ucebna_id"));
                return pocitac;
            }
        });
    }

    @Override
    public void save(Pocitac pocitac) {
        if (pocitac == null) {
            return;
        }
        if (pocitac.getId() == null) {
            SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
            simpleJdbcInsert.withTableName("pocitac");
            simpleJdbcInsert.usingGeneratedKeyColumns("id");
            simpleJdbcInsert.usingColumns("seriove_cislo", "posledne_pouzitie", "ucebna_id");
            Map<String, Object> data = new HashMap<>();
            data.put("seriove_cislo", pocitac.getSerioveCislo());
            data.put("posledne_pouzitie", pocitac.getPoslednePouzitie());
            data.put("ucebna_id", pocitac.getUcebnaId());
            pocitac.setId(simpleJdbcInsert.executeAndReturnKey(data).longValue());
        } else {
            String sql = "UPDATE pocitac SET seriove_cislo = ?, posledne_pouzitie = ?, ucebna_id = ? WHERE id = " + pocitac.getId();
            jdbcTemplate.update(sql, pocitac.getSerioveCislo(), pocitac.getPoslednePouzitie(), pocitac.getUcebnaId());
        }

    }

    @Override
    public Pocitac delete(Pocitac p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
