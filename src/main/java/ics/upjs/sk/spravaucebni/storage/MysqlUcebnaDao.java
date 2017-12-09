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
        naplnUcebne(ucebne);
        
        return ucebne;
    }
    
    private void naplnUcebne(List<Ucebna> ucebne) {
        for (Ucebna u : ucebne) {
            u.setTabule(tabulaDao.getByUcebnaId(u.getId()));
            u.setPocitace(pocitacDao.getByUcebnaId(u.getId()));
            u.setProjektory(projektorDao.getByUcebnaId(u.getId()));
            u.setSpotreby(spotrebaDao.getByUcebnaId(u.getId()));
            u.setChyby(chybaDao.getByUcebnaId(u.getId()));
        }
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
                u.setIdPouzivatela(id);
                return u;
            }
        });

        naplnUcebne(ucebne);
        return ucebne;
    }

    @Override
    public Ucebna getById(Long id) {
        String sql = "SELECT id, nazov FROM ucebna WHERE id = " + id;
        Ucebna ucebna = jdbcTemplate.queryForObject(sql, new RowMapper<Ucebna>() {
            @Override
            public Ucebna mapRow(ResultSet rs, int rowNum) throws SQLException {
                Ucebna u = new Ucebna();
                u.setId(rs.getLong("id"));
                u.setNazov(rs.getString("nazov"));
                u.setIdPouzivatela(id);
                return u;
            }
        });
        ucebna.setTabule(tabulaDao.getByUcebnaId(ucebna.getId()));
        ucebna.setPocitace(pocitacDao.getByUcebnaId(ucebna.getId()));
        ucebna.setProjektory(projektorDao.getByUcebnaId(ucebna.getId()));
        ucebna.setSpotreby(spotrebaDao.getByUcebnaId(ucebna.getId()));
        ucebna.setChyby(chybaDao.getByUcebnaId(ucebna.getId()));
        return ucebna;
    }
    
    

    @Override
    public List<Ucebna> getUcebneBezPouzivatelov() {
        String sql = "SELECT id, nazov FROM ucebna WHERE pouzivatel_id IS NULL ORDER BY id";
        List<Ucebna> ucebne = jdbcTemplate.query(sql, new RowMapper<Ucebna>() {
            @Override
            public Ucebna mapRow(ResultSet rs, int rowNum) throws SQLException {
                Ucebna u = new Ucebna();
                u.setId(rs.getLong("id"));
                u.setNazov(rs.getString("nazov"));
                return u;
            }
        });

        naplnUcebne(ucebne);
        return ucebne;
    }

    @Override
    public boolean save(Ucebna u) {
        if (u == null) {
            return false;
        }
        try {
            if (u.getId() == null) {
                SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
                simpleJdbcInsert.withTableName("ucebna");
                simpleJdbcInsert.usingGeneratedKeyColumns("id");
                simpleJdbcInsert.usingColumns("nazov", "pouzivatel_id");
                Map<String, Object> data = new HashMap<>();
                data.put("nazov", u.getNazov());
                data.put("pouzivatel_id", u.getIdPouzivatela());
                u.setId(simpleJdbcInsert.executeAndReturnKey(data).longValue());
            } else {
  //              delete(u.getId());
                String sql = "UPDATE ucebna SET nazov = ?, pouzivatel_id = ? WHERE id = " + u.getId();
                jdbcTemplate.update(sql, u.getNazov(), u.getIdPouzivatela());                
            }
            savePrvkyUcebne(u);
        } catch (Exception exception) {
            return false;
        }
        return true;
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

    @Override
    public boolean delete(Long id) {
        if (id == null) {
            return false;
        }
        String sql = "DELETE FROM ucebna WHERE id = " + id;
        try {
            int zmazanych = jdbcTemplate.update(sql);
            return zmazanych == 1;
        } catch (Exception e) {
            return false;
        }
    }
}
