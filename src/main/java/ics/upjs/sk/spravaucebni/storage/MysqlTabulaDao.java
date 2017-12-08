package ics.upjs.sk.spravaucebni.storage;

import ics.upjs.sk.spravaucebni.Tabula;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class MysqlTabulaDao implements TabulaDao {

    private JdbcTemplate jdbcTemplate;

    public MysqlTabulaDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public List<Tabula> getAll() {
        String sql = "SELECT id, typ, pocet_pisatiek, ucebna_id FROM tabula ORDER BY id";
        return jdbcTemplate.query(sql, new RowMapper<Tabula> () {
            @Override
            public Tabula mapRow(ResultSet rs, int rowNum) throws SQLException {
                Tabula tabula = new Tabula();
                tabula.setId(rs.getLong("id"));
                tabula.setTyp(rs.getString("typ"));
                tabula.setPocetPisatiek(rs.getInt("pocet_pisatiek"));
                tabula.setUcebnaId(rs.getLong("ucebna_id"));
                return tabula;
            }
        });
    }

    @Override
    public List<Tabula> getByUcebnaId(Long id) {
        String sql = "SELECT id, typ, pocet_pisatiek, ucebna_id FROM tabula WHERE ucebna_id = " + id + " ORDER BY id";
        return jdbcTemplate.query(sql, new RowMapper<Tabula> () {
            @Override
            public Tabula mapRow(ResultSet rs, int rowNum) throws SQLException {
                Tabula tabula = new Tabula();
                tabula.setId(rs.getLong("id"));
                tabula.setTyp(rs.getString("typ"));
                tabula.setPocetPisatiek(rs.getInt("pocet_pisatiek"));
                tabula.setUcebnaId(rs.getLong("ucebna_id"));
                return tabula;
            }
        });
    }

    @Override
    public boolean save(Tabula t) {
        if (t == null) {
            return false;
        }
        try {
            if (t.getId() == null) {
                SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
                simpleJdbcInsert.withTableName("tabula");
                simpleJdbcInsert.usingGeneratedKeyColumns("id");
                simpleJdbcInsert.usingColumns("typ", "pocet_pisatiek", "ucebna_id");
                Map<String, Object> data = new HashMap<>();
                data.put("typ", t.getTyp());
                data.put("pocet_pisatiek", t.getPocetPisatiek());
                data.put("ucebna_id", t.getUcebnaId());
                t.setId(simpleJdbcInsert.executeAndReturnKey(data).longValue());
            } else {
                String sql = "UPDATE tabula SET typ = ?, pocet_pisatiek = ?, ucebna_id = ? WHERE id = " + t.getId();
                jdbcTemplate.update(sql, t.getTyp(), t.getPocetPisatiek(), t.getUcebnaId());
            }
        } catch (Exception exception) {
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Long id) {
        String sql = "DELETE FROM tabula WHERE id = " + id;
        try {
            int zmazanych = jdbcTemplate.update(sql);
            return zmazanych == 1;
        } catch (Exception e) {
            return false;
        }
    }
    
}
