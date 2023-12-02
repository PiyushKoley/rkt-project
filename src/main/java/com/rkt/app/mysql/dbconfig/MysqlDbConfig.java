package com.rkt.app.mysql.dbconfig;

import com.zaxxer.hikari.util.DriverDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.lang.management.ManagementPermission;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryBean",
        basePackages = {"com.rkt.app.mysql.repository"},
        transactionManagerRef = "transactionManagerBean"
)
public class MysqlDbConfig {

    @Autowired
    private Environment environment;

    // bean of dataSource...

    @Bean
    @Primary
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setUrl(environment.getProperty("mysql.spring.datasource.url"));
        dataSource.setUsername(environment.getProperty("mysql.spring.datasource.username"));
        dataSource.setPassword(environment.getProperty("mysql.spring.datasource.password"));
        dataSource.setDriverClassName(environment.getProperty("mysql.spring.datasource.driver-class-name"));


        return dataSource;
    }

    // bean of entityManagerFactory...

    @Bean(name = "entityManagerFactoryBean")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {

        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

        Map<String,String> properties = new HashMap<>();
        properties.put("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.hbm2ddl.auto","update");


        entityManagerFactoryBean.setJpaPropertyMap(properties);
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan("com.rkt.app.mysql.entity");

        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());


        return entityManagerFactoryBean;
    }


    // bean of platformTransactionManager...

    @Primary
    @Bean(name = "transactionManagerBean")
    public PlatformTransactionManager platformTransactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();

        jpaTransactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());


        return jpaTransactionManager;
    }

}
