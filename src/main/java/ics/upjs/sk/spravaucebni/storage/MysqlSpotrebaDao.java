package ics.upjs.sk.spravaucebni.storage;

import ics.upjs.sk.spravaucebni.Spotreba;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class MysqlSpotrebaDao implements SpotrebaDao {

    private JdbcTemplate jdbcTemplate;

    public MysqlSpotrebaDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public List<Spotreba> getAll() {
        String sql = "SELECT id, pocet_nasvietenych_hodin, kvalita_obrazu, nazov_modelu, ocakavana_zivotnost_lampy FROM projektor";
        return jdbcTemplate.query(sql, new RowMapper<Spotreba> () {
            @Override
            public Spotreba mapRow(ResultSet rs, int rowNum) throws SQLException {
                Spotreba spotreba = new Spotreba();
                spotreba.setId(rs.getLong("id"));
                spotreba.setHodnota(rs.getInt("hodnota"));
                spotreba.setCas(rs.getTimestamp("cas").toLocalDateTime());
                return spotreba;
            }
        });
    }

    @Override
    public List<Spotreba> getByUcebnaId(Long id) {
        String sql = "SELECT id, pocet_nasvietenych_hodin, kvalita_obrazu, nazov_modelu, ocakavana_zivotnost_lampy FROM projektor WHERE ucebna_id = " + id;
        return jdbcTemplate.query(sql, new RowMapper<Spotreba> () {
            @Override
            public Spotreba mapRow(ResultSet rs, int rowNum) throws SQLException {
                Spotreba spotreba = new Spotreba();
                spotreba.setId(rs.getLong("id"));
                spotreba.setHodnota(rs.getInt("hodnota"));
                spotreba.setCas(rs.getTimestamp("cas").toLocalDateTime());
                return spotreba;
            }
        });
    }
    
}
