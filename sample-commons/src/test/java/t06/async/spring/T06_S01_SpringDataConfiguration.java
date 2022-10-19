package t06.async.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import com.datastax.oss.driver.api.core.CqlSession;

@Configuration
@EnableCassandraRepositories(basePackages = { "t06.async.spring" })
public class T06_S01_SpringDataConfiguration {
    
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
    
    
    // Multiple keyspaces = multiple CqlSession and multiple CassandraOperation
    // == Qualifiers
  
}
