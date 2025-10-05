package com.AK.Jpeproject.SpringData_JPA_OnlineGroceryStore.Config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;


/**
 * This class configures a dedicated Oracle datasource, entity manager, and transaction manager for use with Spring Data JPA repositories in the specified package, enabling clean separation and management of multiple databases in a Spring Boot application.
 * This configuration is typically used for multi-database setups in Spring Boot, where you need to define separate beans for each datasource, entity manager, and transaction manager.
 * It allows you to connect to an Oracle database, manage entities and transactions, and use Spring Data JPA repositories with this specific datasource.
 */

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "oracle1-entityManagerFactory",
        transactionManagerRef = "oracle1-jpaTransactionManager",
        basePackages = {"com.AK.Jpeproject.SpringData_JPA_OnlineGroceryStore.Oracle1.Repo"}
)
@EnableTransactionManagement

public class JPADBConfig {

    /**
     * DataSource Bean
     * Creates a DataSource bean for Oracle, using properties with prefix oracle1.datasource from your application properties/yaml.
     * DataSourceBuilder.create().build() builds the datasource.
     */
    @Bean("oracle1-data-source")
    @ConfigurationProperties(prefix = "oracle1.datasource")
    public DataSource oracle1DataSource() {
        DataSource oracleDatasource = DataSourceBuilder.create().build();  //Interface Ref assigned the object of DataSourceBuilder
        return oracleDatasource;
    }

    /**
     * EntityManagerFactory Bean
     * Creates an EntityManagerFactory bean for JPA.
     * Uses the above datasource.
     * Scans the specified package for JPA entities.
     * Sets Hibernate as the JPA vendor and configures Hibernate properties (like hbm2ddl.auto).
     * @param datasource
     * @return
     */

    @Bean(name = "oracle1-entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean oracle1EntityManagerFactory(
            @Autowired
            @Qualifier("oracle1-data-source")
            DataSource datasource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(datasource);
        em.setPackagesToScan("com.AK.Jpeproject.SpringData_JPA_OnlineGroceryStore.Oracle1.Entity");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.show_sql", true);  //should be commented, just commented to see
        properties.put("hibernate.format_sql", true);
        em.setJpaPropertyMap(properties);
        return em;
    }

    /**
     * TransactionManager Bean
     * Creates a JpaTransactionManager bean for managing transactions for this datasource/entity manager.
     * @param entityManagerFactory
     * @return
     */


    @Bean(name = "oracle1-jpaTransactionManager")
    public PlatformTransactionManager oracle1JpaTransactionManager(
            @Autowired
            @Qualifier("oracle1-entityManagerFactory")
            EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

}
