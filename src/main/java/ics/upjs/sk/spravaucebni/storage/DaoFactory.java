package ics.upjs.sk.spravaucebni.storage;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public enum DaoFactory {
    
    INSTANCE;
    
    private JdbcTemplate jdbcTemplate;
    
    private JdbcTemplate getJDBCTemplate() {
        if (jdbcTemplate == null) {
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setUser("spravcaUcebni");
            dataSource.setPassword("paz1c");
//            dataSource.setDatabaseName("prezenckovnik");
            dataSource.setUrl("jdbc:mysql://localhost/sprava_ucebni?serverTimezone=Europe/Bratislava");

            jdbcTemplate = new JdbcTemplate(dataSource);
        }
        return jdbcTemplate;
    }
    
    public UcebnaDao getUcebnaDao() {
        return new MysqlUcebnaDao(getJDBCTemplate());
    }
    
    public PouzivatelDao getPouzivatelDao() {
        return new MysqlPouzivatelDao(getJDBCTemplate());
    }
    
    public ProjektorDao getProjektorDao() {
        return new MysqlProjektorDao(getJDBCTemplate());
    }
    
    public SpotrebaDao getSpotrebaDao() {
        return new MysqlSpotrebaDao(getJDBCTemplate());
    }
    
    public TabulaDao getTabulaDao() {
        return new MysqlTabulaDao(getJDBCTemplate());
    }
    
    public PocitacDao getPocitacDao() {
        return new MysqlPocitacDao(getJDBCTemplate());
    }
    
    public OznamDao getOznamDao() {
        return new MysqlOznamDao(getJDBCTemplate());
    }
}
