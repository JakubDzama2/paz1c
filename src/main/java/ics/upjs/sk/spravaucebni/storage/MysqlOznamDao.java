package ics.upjs.sk.spravaucebni.storage;

import ics.upjs.sk.spravaucebni.Oznam;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class MysqlOznamDao implements OznamDao {

    private JdbcTemplate jdbcTemplate;

    public MysqlOznamDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Oznam> getAll() {
        String sql = "SELECT id, poznamka, cas, hlasatel, ucebna_id FROM oznam ORDER BY id";
        return jdbcTemplate.query(sql, (ResultSet rs, int rowNum) -> {
            Oznam oznam = new Oznam();
            oznam.setId(rs.getLong("id"));
            oznam.setPoznamka(rs.getString("poznamka"));
            oznam.setCas(rs.getTimestamp("cas").toLocalDateTime());
            oznam.setHlasatel(rs.getString("hlasatel"));
            oznam.setUcebnaId(rs.getLong("ucebna_id"));
            return oznam;
        });
    }

    
    @Override
    public List<Oznam> getByUcebnaId(Long id) {
        String sql = "SELECT id, poznamka, cas, hlasatel, ucebna_id FROM oznam WHERE ucebna_id = " + id + " ORDER BY id";
        return jdbcTemplate.query(sql, (ResultSet rs, int rowNum) -> {
            Oznam oznam = new Oznam();
            oznam.setId(rs.getLong("id"));
            oznam.setPoznamka(rs.getString("poznamka"));
            oznam.setCas(rs.getTimestamp("cas").toLocalDateTime());
            oznam.setHlasatel(rs.getString("hlasatel"));
            oznam.setUcebnaId(rs.getLong("ucebna_id"));
            return oznam;
        });
    }

    @Override
    public Oznam getById(Long id) {
        String sql = "SELECT id, poznamka, cas, hlasatel, ucebna_id FROM oznam WHERE id = " + id;
        return jdbcTemplate.queryForObject(sql, (ResultSet rs, int rowNum) -> {
            Oznam oznam = new Oznam();
            oznam.setId(rs.getLong("id"));
            oznam.setPoznamka(rs.getString("poznamka"));
            oznam.setCas(rs.getTimestamp("cas").toLocalDateTime());
            oznam.setHlasatel(rs.getString("hlasatel"));
            oznam.setUcebnaId(rs.getLong("ucebna_id"));
            return oznam;
        });
    }
    
    

    @Override
    public boolean save(Oznam oznam) {
        if (oznam == null) {
            return false;
        }
        try {
            if (oznam.getId() == null) {
                SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
                simpleJdbcInsert.withTableName("oznam");
                simpleJdbcInsert.usingGeneratedKeyColumns("id");
                simpleJdbcInsert.usingColumns("poznamka", "cas", "hlasatel", "ucebna_id");
                Map<String, Object> data = new HashMap<>();
                data.put("poznamka", oznam.getPoznamka());
                data.put("cas", oznam.getCas());
                data.put("hlasatel", oznam.getHlasatel());
                data.put("ucebna_id", oznam.getUcebnaId());
                oznam.setId(simpleJdbcInsert.executeAndReturnKey(data).longValue());
            } else {
                String sql = "UPDATE oznam SET poznamka = ?, cas = ?, hlasatel = ?, ucebna_id = ? WHERE id = " + oznam.getId();
                jdbcTemplate.update(sql, oznam.getPoznamka(), oznam.getCas(), oznam.getHlasatel(), oznam.getUcebnaId());
            }
        } catch (Exception exception) {
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Long id) {
        String sql = "DELETE FROM oznam WHERE id = " + id;
        try {
            int zmazanych = jdbcTemplate.update(sql);
            return zmazanych == 1;
        } catch (Exception e) {
            return false;
        }
        
    }

    
}
