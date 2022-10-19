package t03.repository.spring.s02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import com.datastax.oss.driver.api.core.CqlSession;

@Configuration
@EnableCassandraRepositories(basePackages = { "t03.repository.spring.s02" })
public class T03_S02_SpringDataConfiguration {
    
    @Bean
    public CqlSession cqlSession() {
        return CqlSession.builder().build();
    }
  
}