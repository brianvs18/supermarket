package com.system.config;

import com.system.jpa.config.DBSecret;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class JpaConfig {

    @Bean
    public DBSecret dbSecret(Environment environment){
        return DBSecret.builder()
                .url(environment.getProperty("spring.datasource.url"))
                .username(environment.getProperty("spring.datasource.username"))
                .password(environment.getProperty("spring.datasource.password"))
                .build();
    }

    @Bean
    public DataSource datasource(DBSecret secret, @Value("${spring.datasource.driverClassName}") String driverClass) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(secret.getUrl());
        config.setUsername(secret.getUsername());
        config.setPassword(secret.getPassword());
        config.setDriverClassName(driverClass);
        return new HikariDataSource(config);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            DataSource dataSource,
            @Value("${spring.jpa.databasePlatform}") String dialect) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.softlond.jpa");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Properties properties = new Properties();
        properties.setProperty("spring.jpa.hibernate.ddl-auto", "validate");
        properties.setProperty("hibernate.hbm2ddl.auto", "update"); // TODO: remove this for non auto create schema
        em.setJpaProperties(properties);

        return em;
    }
}
