package com.manage.pure.apis.common.config.r2dbc;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;



@Configuration
public class JpaConfig{

    @Autowired
    private Environment env;

    @Value("${spring.datasource.driver-class-name}")
    private String driver;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String userName;

    @Value("${spring.datasource.password}")
    private String passwd;

    @Bean
    public DataSource jpaDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(driver);
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(userName);
        hikariConfig.setPassword(passwd);
        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setConnectionTimeout(5000);
        hikariConfig.setValidationTimeout(4000);
        hikariConfig.setMaxLifetime(600000);
        hikariConfig.setPoolName("jpaDataSource");
        DataSource dataSource = new HikariDataSource(hikariConfig);
        return dataSource;
    }

}
