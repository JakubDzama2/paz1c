package ics.upjs.sk.spravaucebni.storage;

import ics.upjs.sk.spravaucebni.Spotreba;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class MysqlSpotrebaDao implements SpotrebaDao {

    private JdbcTemplate jdbcTemplate;

    public MysqlSpotrebaDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public List<Spotreba> getAll() {
        String sql = "SELECT id, datum, hodnota, ucebna_id FROM spotreba ORDER BY id";
        return jdbcTemplate.query(sql, new RowMapper<Spotreba> () {
            @Override
            public Spotreba mapRow(ResultSet rs, int rowNum) throws SQLException {
                Spotreba spotreba = new Spotreba();
                spotreba.setId(rs.getLong("id"));
                spotreba.setHodnota(rs.getInt("hodnota"));
                spotreba.setDatum(rs.getDate("datum").toLocalDate());
                spotreba.setUcebnaId(rs.getLong("ucebna_id"));
                return spotreba;
            }
        });
    }

    @Override
    public List<Spotreba> getByUcebnaId(Long id) {
        String sql = "SELECT id, datum, hodnota, ucebna_id FROM spotreba WHERE ucebna_id = " + id + " ORDER BY id";
        return jdbcTemplate.query(sql, new RowMapper<Spotreba> () {
            @Override
            public Spotreba mapRow(ResultSet rs, int rowNum) throws SQLException {
                Spotreba spotreba = new Spotreba();
                spotreba.setId(rs.getLong("id"));
                spotreba.setHodnota(rs.getInt("hodnota"));
                spotreba.setDatum(rs.getDate("datum").toLocalDate());
                spotreba.setUcebnaId(rs.getLong("ucebna_id"));
                return spotreba;
            }
        });
    }

    @Override
    public Spotreba getById(Long id) {
        String sql = "SELECT id, datum, hodnota, ucebna_id FROM spotreba WHERE id = " + id;
        return jdbcTemplate.queryForObject(sql, new RowMapper<Spotreba> () {
            @Override
            public Spotreba mapRow(ResultSet rs, int rowNum) throws SQLException {
                Spotreba spotreba = new Spotreba();
                spotreba.setId(rs.getLong("id"));
                spotreba.setHodnota(rs.getInt("hodnota"));
                spotreba.setDatum(rs.getDate("datum").toLocalDate());
                spotreba.setUcebnaId(rs.getLong("ucebna_id"));
                return spotreba;
            }
        });
    }
    
    @Override
    public List<Spotreba> getByDatumAndUcebnaId(int rok, int mesiac, Long id) {
        String sql = "SELECT id, datum, hodnota, ucebna_id FROM spotreba WHERE year(datum) = " + rok + " AND"
                + " month(datum) = " + mesiac + " AND ucebna_id = " + id + " ORDER BY datum";
        return jdbcTemplate.query(sql, new RowMapper<Spotreba> () {
            @Override
            public Spotreba mapRow(ResultSet rs, int rowNum) throws SQLException {
                Spotreba spotreba = new Spotreba();
                spotreba.setId(rs.getLong("id"));
                spotreba.setHodnota(rs.getInt("hodnota"));
                spotreba.setDatum(rs.getDate("datum").toLocalDate());
                spotreba.setUcebnaId(rs.getLong("ucebna_id"));
                return spotreba;
            }
        });
    }
    
    

    @Override
    public boolean save(Spotreba spotreba) {
        if (spotreba == null) {
            return false;
        }
        try {
            if (spotreba.getId() == null) {         //INSERT
                SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
                simpleJdbcInsert.withTableName("spotreba");
                simpleJdbcInsert.usingGeneratedKeyColumns("id");
                simpleJdbcInsert.usingColumns("datum", "hodnota", "ucebna_id");
                Map<String, Object> data = new HashMap<>();
                data.put("datum", spotreba.getDatum());
                data.put("hodnota", spotreba.getHodnota());
                data.put("ucebna_id", spotreba.getUcebnaId());
                spotreba.setId(simpleJdbcInsert.executeAndReturnKey(data).longValue());
            } else {          //UPDATE
                String sql = "UPDATE spotreba SET cas = ?, hodnota = ?, ucebna_id = ? WHERE id = " + spotreba.getId();
                jdbcTemplate.update(sql, spotreba.getDatum(), spotreba.getHodnota(), spotreba.getUcebnaId());
            }
        } catch (Exception exception) {
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Long id) {
        String sql = "DELETE FROM spotreba WHERE id = " + id;
        try {
            int zmazanych = jdbcTemplate.update(sql);
            return zmazanych == 1;
        } catch (Exception e) {
            return false;
        }
    }
    
    
}
