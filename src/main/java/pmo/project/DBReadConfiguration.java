package pmo.project;

import java.util.Properties;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class DBReadConfiguration {
    @Bean
    @ConfigurationProperties("spring.datasource.read-only")
    public DataSourceProperties readOnlyDataSourceProperties() {
        return new DataSourceProperties();
    }
    
    @Bean("readOnlyTM")
    public PlatformTransactionManager readOnlyTransactionManager() {
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
        		readOnlyEntityManager().getObject());
        return transactionManager;
    }
    
    @Bean("entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean readOnlyEntityManager() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(readOnlyDatasource());
        em.setPackagesToScan("pmo.project.repo");
        em.setPersistenceUnitName("ro-default");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaProperties(readOnlyJPAProperties());
        
        return em;
    }
    
    @Bean
    @ConfigurationProperties("spring.jpa.read-only")
    public Properties readOnlyJPAProperties() {
        return new Properties();
    }
    
    @Bean
    public DriverManagerDataSource readOnlyDatasource() {
    	DataSourceProperties env = readOnlyDataSourceProperties();
        DriverManagerDataSource dataSource
                = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getDriverClassName());
        dataSource.setUrl(env.getUrl());
        dataSource.setUsername(env.getUsername());
        dataSource.setPassword(env.getPassword());

        return dataSource;
    }
}
