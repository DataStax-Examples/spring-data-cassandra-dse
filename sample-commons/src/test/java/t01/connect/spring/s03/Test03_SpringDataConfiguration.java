package t01.connect.spring.s03;

import java.net.InetSocketAddress;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.datastax.oss.driver.api.core.CqlSession;

@Configuration
public class Test03_SpringDataConfiguration {
    
    @Value("${spring.data.cassandra.keyspace-name}")
    private String keyspaceName;
    
    @Value("${spring.data.cassandra.contact-points}")
    private String contactPoint;
    
    @Value("${spring.data.cassandra.local-datacenter}")
    private String locaDataCenter;
    
    @Bean
    public CqlSession cqlSession() {
        return CqlSession.builder()
                .addContactPoint(new InetSocketAddress("localhost", 9042))
                .withLocalDatacenter(locaDataCenter)
                .withKeyspace(keyspaceName)
                .build();
    }
    
}
