package com.example.multidatabase.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.example.multidatabase.properties.DatabaseProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;


@Configuration
@EnableConfigurationProperties(DatabaseProperties.class)
@EnableJpaRepositories(
        basePackages = "com.example.multidatabase.first",
        entityManagerFactoryRef = FirstDataSourceConfig.ENTITY_MANAGER_BEAN_NAME,
        transactionManagerRef = XaDataSourceConfig.TRANSACTION_MANAGER_BEAN_NAME
)
public class FirstDataSourceConfig {

    public static final String ENTITY_MANAGER_BEAN_NAME = "firstEntityManager";
    private static final String DATASOURCE_BEAN_NAME = "firstDataSource";
    private static final String DATASOURCE_PROPERTIES_PREFIX = "spring.datasource.first";
    private static final String HIBERNATE_PROPERTIES = "firstHibernateProperties";

//    @Primary
//    @Bean(name = ENTITY_MANAGER_BEAN_NAME)
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
//            EntityManagerFactoryBuilder builder,
//            @Qualifier(DATASOURCE_BEAN_NAME) DataSource dataSource,
//            @Qualifier(HIBERNATE_PROPERTIES) DatabaseProperties.Hibernate hibernateProperties
//    ) {
//        return builder.dataSource(dataSource).packages("com.example.multidatabase.first.entity")
//                .persistenceUnit(ENTITY_MANAGER_BEAN_NAME)
//                .properties(DatabaseProperties.Hibernate.propertiesToMap(hibernateProperties)).build();
//    }

    @Primary
    @Bean(name = ENTITY_MANAGER_BEAN_NAME)
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier(DATASOURCE_BEAN_NAME) DataSource dataSource,
            @Qualifier(HIBERNATE_PROPERTIES) DatabaseProperties.Hibernate hibernateProperties
    ) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.example.multidatabase.first");
        em.setJpaPropertyMap(DatabaseProperties.Hibernate.propertiesToMap(hibernateProperties));
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return em;
    }

    @Bean(name = HIBERNATE_PROPERTIES)
    @ConfigurationProperties(DATASOURCE_PROPERTIES_PREFIX + ".hibernate")
    public DatabaseProperties.Hibernate hibernateProperties() {
        return new DatabaseProperties.Hibernate();
    }

    @Primary
    @Bean(name = DATASOURCE_BEAN_NAME)
    @ConfigurationProperties(prefix = DATASOURCE_PROPERTIES_PREFIX)
    public DataSource dataSource() {
        return new AtomikosDataSourceBean();
    }
}
