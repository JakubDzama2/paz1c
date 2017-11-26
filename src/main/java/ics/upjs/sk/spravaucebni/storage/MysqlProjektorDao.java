package ics.upjs.sk.spravaucebni.storage;

import ics.upjs.sk.spravaucebni.Projektor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class MysqlProjektorDao implements ProjektorDao {

    private JdbcTemplate jdbcTemplate;

    public MysqlProjektorDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public List<Projektor> getAll() {
        String sql = "SELECT id, pocet_nasvietenych_hodin, kvalita_obrazu, nazov_modelu, ocakavana_zivotnost_lampy FROM projektor";
        return jdbcTemplate.query(sql, new RowMapper<Projektor> () {
            @Override
            public Projektor mapRow(ResultSet rs, int rowNum) throws SQLException {
                Projektor projektor = new Projektor();
                projektor.setId(rs.getLong("id"));
                projektor.setPocetNasvietenychHodin(rs.getInt("pocet_nasvietenych_hodin"));
                projektor.setKvalitaObrazu(rs.getString("kvalita_obrazu"));
                projektor.setNazovModelu(rs.getString("nazov_modelu"));
                projektor.setOcakavanaZivotnostLampy(rs.getInt("ocakavana_zivotnost_lampy"));
                return projektor;
            }
        });
    }

    @Override
    public List<Projektor> getByUcebnaId(Long id) {
        String sql = "SELECT id, pocet_nasvietenych_hodin, kvalita_obrazu, nazov_modelu, ocakavana_zivotnost_lampy FROM projektor WHERE ucebna_id = " + id;
        return jdbcTemplate.query(sql, new RowMapper<Projektor> () {
            @Override
            public Projektor mapRow(ResultSet rs, int rowNum) throws SQLException {
                Projektor projektor = new Projektor();
                projektor.setId(rs.getLong("id"));
                projektor.setPocetNasvietenychHodin(rs.getInt("pocet_nasvietenych_hodin"));
                projektor.setKvalitaObrazu(rs.getString("kvalita_obrazu"));
                projektor.setNazovModelu(rs.getString("nazov_modelu"));
                projektor.setOcakavanaZivotnostLampy(rs.getInt("ocakavana_zivotnost_lampy"));
                return projektor;
            }
        });
    }
}
