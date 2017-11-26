package ics.upjs.sk.spravaucebni.storage;

import ics.upjs.sk.spravaucebni.Ucebna;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class MysqlUcebnaDao implements UcebnaDao {

    private JdbcTemplate jdbcTemplate;

    public MysqlUcebnaDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Ucebna> getAll() {
        String sql = "SELECT id, nazov FROM ucebna;";
        List<Ucebna> ucebne = jdbcTemplate.query(sql, new RowMapper<Ucebna>() {
            @Override
            public Ucebna mapRow(ResultSet rs, int rowNum) throws SQLException {
                Ucebna u = new Ucebna();
                u.setId(rs.getLong("id"));
                u.setNazov(rs.getString("nazov"));
                return u;
            }
        });

        TabulaDao tabulaDao = DaoFactory.INSTANCE.getTabulaDao();
        PocitacDao pocitacDao = DaoFactory.INSTANCE.getPocitacDao();
        ProjektorDao projektorDao = DaoFactory.INSTANCE.getProjektorDao();
        SpotrebaDao spotrebaDao = DaoFactory.INSTANCE.getSpotrebaDao();
        ChybaDao chybaDao = DaoFactory.INSTANCE.getChybaDao();
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
        String sql = "SELECT id, nazov FROM ucebna WHERE pouzivatel_id = " + id;
        List<Ucebna> ucebne = jdbcTemplate.query(sql, new RowMapper<Ucebna>() {
            @Override
            public Ucebna mapRow(ResultSet rs, int rowNum) throws SQLException {
                Ucebna u = new Ucebna();
                u.setId(rs.getLong("id"));
                u.setNazov(rs.getString("nazov"));
                return u;
            }
        });

        TabulaDao tabulaDao = DaoFactory.INSTANCE.getTabulaDao();
        PocitacDao pocitacDao = DaoFactory.INSTANCE.getPocitacDao();
        ProjektorDao projektorDao = DaoFactory.INSTANCE.getProjektorDao();
        SpotrebaDao spotrebaDao = DaoFactory.INSTANCE.getSpotrebaDao();
        ChybaDao chybaDao = DaoFactory.INSTANCE.getChybaDao();
        for (Ucebna u : ucebne) {
            u.setTabule(tabulaDao.getByUcebnaId(u.getId()));
            u.setPocitace(pocitacDao.getByUcebnaId(u.getId()));
            u.setProjektory(projektorDao.getByUcebnaId(u.getId()));
            u.setSpotreby(spotrebaDao.getByUcebnaId(u.getId()));
            u.setChyby(chybaDao.getByUcebnaId(u.getId()));
        }
        return ucebne;
    }
}
