package t01.connect.spring.s02;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.CqlSessionFactoryBean;
import org.springframework.data.cassandra.config.SessionBuilderConfigurer;

import com.datastax.oss.driver.api.core.CqlSessionBuilder;
import com.datastax.oss.driver.api.core.config.DefaultDriverOption;
import com.datastax.oss.driver.api.core.config.DriverConfigLoader;

@Configuration
public class Test02_SpringDataConfiguration<CqlSessionBuilderCustomizer> {
    
    @Value("${spring.data.cassandra.keyspace-name}")
    private String keyspaceName;
    
    @Value("${spring.data.cassandra.contact-points}")
    private String contactPoint;
    
    @Value("${spring.data.cassandra.local-datacenter}")
    private String locaDataCenter;
    
    @Bean
    public CqlSessionFactoryBean cassandraSession() {
        final CqlSessionFactoryBean cqlSessionFactoryBean = new CqlSessionFactoryBean();
        cqlSessionFactoryBean.setContactPoints(contactPoint);
        cqlSessionFactoryBean.setKeyspaceName(keyspaceName);
        cqlSessionFactoryBean.setLocalDatacenter(locaDataCenter);
        cqlSessionFactoryBean.setPort(9042);
        
        
        cqlSessionFactoryBean.setSessionBuilderConfigurer(getSessionBuilderConfigurer());
        return cqlSessionFactoryBean;
    }
    
    /**
     * More customizers in Spring Boot later FTW !
     */
    protected SessionBuilderConfigurer getSessionBuilderConfigurer() {
        return new SessionBuilderConfigurer() {
            @Override
            public CqlSessionBuilder configure(CqlSessionBuilder cqlSessionBuilder) {
                return cqlSessionBuilder.withConfigLoader(DriverConfigLoader.programmaticBuilder()
                        .withDuration(DefaultDriverOption.REQUEST_TIMEOUT, Duration.ofSeconds(5))
                        .build());
            }
        };
    }
    
}
