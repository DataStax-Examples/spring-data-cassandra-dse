package t02.statements.spring.s02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import com.datastax.oss.driver.api.core.CqlSession;

@Configuration
@EnableCassandraRepositories(basePackages = { "t02.statements.spring.s02" })
public class T02_S02_SpringDataConfiguration {
    
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
    
    // Multilple keyspaces = multiple CqlSession and multiple CassandraOperation
    // == Qualifiers
  
}
