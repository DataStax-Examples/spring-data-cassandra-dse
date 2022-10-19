package t10.lwt.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import com.datastax.oss.driver.api.core.CqlSession;

@Configuration
@EnableCassandraRepositories(basePackages = { "t10.lwt.spring" })
public class T10_SpringDataConfiguration {
    
    @Bean
    public CqlSession cqlSession() {
        return CqlSession.builder().build();
    }
    
    @Bean
    public CassandraOperations cassandraTemplate(CqlSession cqlSession) {
        CassandraTemplate ct = new CassandraTemplate(cqlSession);
        ct.setUsePreparedStatements(true);
        return ct;
    }
  
}
