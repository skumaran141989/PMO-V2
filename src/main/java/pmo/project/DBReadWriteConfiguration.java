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
public class DBReadWriteConfiguration {
	
    @Bean
    @ConfigurationProperties("spring.datasource.read-write")
    public DataSourceProperties readWriteDataSourceProperties() {
        return new DataSourceProperties();
    }
    
    @Bean
    @ConfigurationProperties("spring.jpa.read-write")
    public Properties readWriteJPAProperties() {
        return new Properties();
    }
    
    @Bean("readWriteTM")
    public PlatformTransactionManager readWriteTransactionManager() {
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
        		readWriteEntityManager().getObject());
        return transactionManager;
    }
    
    @Bean("entityManagerFactoryB")
    public LocalContainerEntityManagerFactoryBean readWriteEntityManager() {;
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(readWriteDatasource());
        em.setPackagesToScan("pmo.project.repo");
        em.setPersistenceUnitName("rw-default");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaProperties(readWriteJPAProperties());
        
        return em;
    }
    
    @Bean
    public DriverManagerDataSource readWriteDatasource() {
    	DataSourceProperties env = readWriteDataSourceProperties();
        DriverManagerDataSource dataSource
                = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getDriverClassName());
        dataSource.setUrl(env.getUrl());
        dataSource.setUsername(env.getUsername());
        dataSource.setPassword(env.getPassword());

        return dataSource;
    }
}
