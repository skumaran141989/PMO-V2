package pmo.project;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBConfigurationProperties {
    @Bean
    @ConfigurationProperties("spring.datasource.readOnly")
    public DataSourceProperties readOnlyDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.readWrite")
    public DataSourceProperties readWriteDataSourceProperties() {
        return new DataSourceProperties();
    }
}
