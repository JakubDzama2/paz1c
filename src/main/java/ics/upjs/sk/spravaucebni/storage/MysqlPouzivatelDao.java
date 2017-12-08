package ics.upjs.sk.spravaucebni.storage;

import ics.upjs.sk.spravaucebni.Pouzivatel;
import ics.upjs.sk.spravaucebni.Ucebna;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class MysqlPouzivatelDao implements PouzivatelDao {

    private JdbcTemplate jdbcTemplate;
    
    public MysqlPouzivatelDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public List<Pouzivatel> getAll() {
        String sql = "SELECT * FROM pouzivatel ORDER BY id";
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

    @Override
    public boolean save(Pouzivatel p) {
        if (p == null) {
            return false;
        }
        try {
            if (p.getId() == null) {
                SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
                simpleJdbcInsert.withTableName("pouzivatel");
                simpleJdbcInsert.usingGeneratedKeyColumns("id");
                simpleJdbcInsert.usingColumns("meno", "heslo", "sol", "posledne_prihlasenie", "email");
                Map<String, Object> data = new HashMap<>();
                data.put("meno", p.getMeno());
                data.put("heslo", p.getHeslo());
                data.put("sol", p.getSol());
                data.put("posledne_prihlasenie", p.getPoslednePrihlasenie());
                data.put("email", p.getEmail());
                p.setId(simpleJdbcInsert.executeAndReturnKey(data).longValue());
            } else {
                String sql = "UPDATE spotreba SET meno = ?, heslo = ?, sol = ?, posledne_prihlasenie = ?, email = ? WHERE id = " + p.getId();
                jdbcTemplate.update(sql, p.getMeno(), p.getHeslo(), p.getSol(), p.getPoslednePrihlasenie(), p.getEmail());
            }
        } catch (Exception exception) {
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Long id) {
        UcebnaDao ucebnaDao = DaoFactory.INSTANCE.getUcebnaDao();
        List<Ucebna> ucebne = ucebnaDao.getByPouzivatelId(id);
        for (Ucebna ucebna : ucebne) {
            ucebna.setIdPouzivatela(null);
            ucebnaDao.save(ucebna);
        }
        String sql = "DELETE FROM pouzivatel WHERE id = " + id;
        try {
            int zmazanych = jdbcTemplate.update(sql);
            return zmazanych == 1;
        } catch (Exception e) {
            return false;
        }
    }
    
    
}
