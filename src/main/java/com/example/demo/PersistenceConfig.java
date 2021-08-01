package com.example.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;


@Configuration
public class PersistenceConfig {

    @Bean (name = "mysql")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://angelinne.com:3306/angel1_CENTeamProject?enabledTLSProtocols=TLSv1.2");
        dataSource.setUsername("angel1_admin");
        dataSource.setPassword("9@;]*a*8XZrf");
        return dataSource;
    }

    @Bean(name = "jdbctemplate")
    public JdbcTemplate jdbcTemplate (@Qualifier("mysql") DataSource mysqlds) throws SQLException {
        return new JdbcTemplate(mysqlds);
    }
}
