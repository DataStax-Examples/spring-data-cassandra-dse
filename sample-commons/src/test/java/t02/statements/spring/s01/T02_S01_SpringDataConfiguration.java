package t02.statements.spring.s01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.cql.CqlTemplate;

import com.datastax.oss.driver.api.core.CqlSession;

@Configuration
public class T02_S01_SpringDataConfiguration {
    
    @Bean
    public CqlSession cqlSession() {
        return CqlSession.builder().build();
    }
    
    @Bean
    public CqlTemplate cqlTemplate(CqlSession cqlSession) {
        return new CqlTemplate(cqlSession);
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
