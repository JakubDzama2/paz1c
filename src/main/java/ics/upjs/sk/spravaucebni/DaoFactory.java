package ics.upjs.sk.spravaucebni;

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
            dataSource.setUrl("jdbc:mysql://localhost/prezenckovnik?serverTimezone=Europe/Bratislava");

            jdbcTemplate = new JdbcTemplate(dataSource);
        }
        return jdbcTemplate;
    }
    
    public UcebnaDao getUcebnaDao() {
        return new MysqlUcebnaDao(getJDBCTemplate());
    }
}
