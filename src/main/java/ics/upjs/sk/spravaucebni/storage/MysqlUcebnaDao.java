package ics.upjs.sk.spravaucebni.storage;

import ics.upjs.sk.spravaucebni.Chyba;
import ics.upjs.sk.spravaucebni.Pocitac;
import ics.upjs.sk.spravaucebni.Projektor;
import ics.upjs.sk.spravaucebni.Spotreba;
import ics.upjs.sk.spravaucebni.Tabula;
import ics.upjs.sk.spravaucebni.Ucebna;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class MysqlUcebnaDao implements UcebnaDao {

    private JdbcTemplate jdbcTemplate;
    private TabulaDao tabulaDao = DaoFactory.INSTANCE.getTabulaDao();
    private PocitacDao pocitacDao = DaoFactory.INSTANCE.getPocitacDao();
    private ProjektorDao projektorDao = DaoFactory.INSTANCE.getProjektorDao();
    private SpotrebaDao spotrebaDao = DaoFactory.INSTANCE.getSpotrebaDao();
    private ChybaDao chybaDao = DaoFactory.INSTANCE.getChybaDao();

    public MysqlUcebnaDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Ucebna> getAll() {
        String sql = "SELECT id, nazov FROM ucebna ORDER BY id";
        List<Ucebna> ucebne = jdbcTemplate.query(sql, new RowMapper<Ucebna>() {
            @Override
            public Ucebna mapRow(ResultSet rs, int rowNum) throws SQLException {
                Ucebna u = new Ucebna();
                u.setId(rs.getLong("id"));
                u.setNazov(rs.getString("nazov"));
                return u;
            }
        });
        
        for (Ucebna u : ucebne) {
            u.setTabule(tabulaDao.getByUcebnaId(u.getId()));
            u.setPocitace(pocitacDao.getByUcebnaId(u.getId()));
            u.setProjektory(projektorDao.getByUcebnaId(u.getId()));
            u.setSpotreby(spotrebaDao.getByUcebnaId(u.getId()));
            u.setChyby(chybaDao.getByUcebnaId(u.getId()));
        }
        return ucebne;
    }

    @Override
    public List<Ucebna> getByPouzivatelId(Long id) {
        String sql = "SELECT id, nazov FROM ucebna WHERE pouzivatel_id = " + id  + " ORDER BY id";
        List<Ucebna> ucebne = jdbcTemplate.query(sql, new RowMapper<Ucebna>() {
            @Override
            public Ucebna mapRow(ResultSet rs, int rowNum) throws SQLException {
                Ucebna u = new Ucebna();
                u.setId(rs.getLong("id"));
                u.setNazov(rs.getString("nazov"));
                return u;
            }
        });

        for (Ucebna u : ucebne) {
            u.setTabule(tabulaDao.getByUcebnaId(u.getId()));
            u.setPocitace(pocitacDao.getByUcebnaId(u.getId()));
            u.setProjektory(projektorDao.getByUcebnaId(u.getId()));
            u.setSpotreby(spotrebaDao.getByUcebnaId(u.getId()));
            u.setChyby(chybaDao.getByUcebnaId(u.getId()));
        }
        return ucebne;
    }

    @Override
    public void save(Ucebna u) {
        if (u == null) {
            return;
        }
        if (u.getId() == null) {
            SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
            simpleJdbcInsert.withTableName("ucebna");
            simpleJdbcInsert.usingGeneratedKeyColumns("id");
            simpleJdbcInsert.usingColumns("nazov", "pouzivatel_id");
            Map<String, Object> data = new HashMap<>();
            data.put("nazov", u.getNazov());
            data.put("pouzivatel_id", u.getIdPouzivatela());
            u.setId(simpleJdbcInsert.executeAndReturnKey(data).longValue());
            savePrvkyUcebne(u);
            
        } else {
            String sql = "UPDATE ucebna SET nazov = ?, pouzivatel_id = ? WHERE id = " + u.getId();
            jdbcTemplate.update(sql, u.getNazov(), u.getIdPouzivatela());
        }
    }
    
    private void savePrvkyUcebne(Ucebna u) {
        for (Chyba chyba : u.getChyby()) {
                chybaDao.save(chyba);
        }
        for (Pocitac pocitac : u.getPocitace()) {
            pocitacDao.save(pocitac);
        }
        for (Projektor projektor : u.getProjektory()) {
            projektorDao.save(projektor);
        }
        for (Spotreba spotreba : u.getSpotreby()) {
            spotrebaDao.save(spotreba);
        }
        for (Tabula tabula : u.getTabule()) {
            tabulaDao.save(tabula);
        }
    }
}
