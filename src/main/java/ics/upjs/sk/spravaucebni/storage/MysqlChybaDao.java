package ics.upjs.sk.spravaucebni.storage;

import ics.upjs.sk.spravaucebni.Chyba;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class MysqlChybaDao implements ChybaDao {

    private JdbcTemplate jdbcTemplate;

    public MysqlChybaDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Chyba> getAll() {
        String sql = "SELECT id, poznamka, cas, hlasatel_chyby, ucebna_id FROM chyba ORDER BY id";
        return jdbcTemplate.query(sql, (ResultSet rs, int rowNum) -> {
            Chyba chyba = new Chyba();
            chyba.setId(rs.getLong("id"));
            chyba.setPoznamka(rs.getString("poznamka"));
            chyba.setCas(rs.getTimestamp("cas").toLocalDateTime());
            chyba.setHlasatelChyby(rs.getString("hlasatel_chyby"));
            chyba.setUcebnaId(rs.getLong("ucebna_id"));
            return chyba;
        });
    }

    
    @Override
    public List<Chyba> getByUcebnaId(Long id) {
        String sql = "SELECT id, poznamka, cas, hlasatel_chyby, ucebna_id FROM chyba WHERE ucebna_id = " + id + " ORDER BY id";
        return jdbcTemplate.query(sql, (ResultSet rs, int rowNum) -> {
            Chyba chyba = new Chyba();
            chyba.setId(rs.getLong("id"));
            chyba.setPoznamka(rs.getString("poznamka"));
            chyba.setCas(rs.getTimestamp("cas").toLocalDateTime());
            chyba.setHlasatelChyby(rs.getString("hlasatel_chyby"));
            chyba.setUcebnaId(rs.getLong("ucebna_id"));
            return chyba;
        });
    }

    @Override
    public Chyba getById(Long id) {
        String sql = "SELECT id, poznamka, cas, hlasatel_chyby, ucebna_id FROM chyba WHERE id = " + id;
        return jdbcTemplate.queryForObject(sql, (ResultSet rs, int rowNum) -> {
            Chyba chyba = new Chyba();
            chyba.setId(rs.getLong("id"));
            chyba.setPoznamka(rs.getString("poznamka"));
            chyba.setCas(rs.getTimestamp("cas").toLocalDateTime());
            chyba.setHlasatelChyby(rs.getString("hlasatel_chyby"));
            chyba.setUcebnaId(rs.getLong("ucebna_id"));
            return chyba;
        });
    }
    
    

    @Override
    public boolean save(Chyba chyba) {
        if (chyba == null) {
            return false;
        }
        try {
            if (chyba.getId() == null) {
                SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
                simpleJdbcInsert.withTableName("chyba");
                simpleJdbcInsert.usingGeneratedKeyColumns("id");
                simpleJdbcInsert.usingColumns("poznamka", "cas", "hlasatel_chyby", "ucebna_id");
                Map<String, Object> data = new HashMap<>();
                data.put("poznamka", chyba.getPoznamka());
                data.put("cas", chyba.getCas());
                data.put("hlasatel_chyby", chyba.getHlasatelChyby());
                data.put("ucebna_id", chyba.getUcebnaId());
                chyba.setId(simpleJdbcInsert.executeAndReturnKey(data).longValue());
            } else {
                String sql = "UPDATE chyba SET poznamka = ?, cas = ?, hlasatel_chyby = ?, ucebna_id = ? WHERE id = " + chyba.getId();
                jdbcTemplate.update(sql, chyba.getPoznamka(), chyba.getCas(), chyba.getHlasatelChyby(), chyba.getUcebnaId());
            }
        } catch (Exception exception) {
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Long id) {
        String sql = "DELETE FROM chyba WHERE id = " + id;
        try {
            int zmazanych = jdbcTemplate.update(sql);
            return zmazanych == 1;
        } catch (Exception e) {
            return false;
        }
        
    }

    
}
