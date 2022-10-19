package t01.connect.spring.s01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.CqlSession;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Test01_SpringDataConfiguration.class)
@TestPropertySource(locations="/test.properties")
public class Test01_Test {
    
    @Value("${spring.data.cassandra.keyspace-name}")
    private String keyspaceName;
    
    @Autowired
    CqlSession cqlSession;
    
    @Test
    public void testShouldConnect() {
        Assertions.assertEquals(
                CqlIdentifier.fromCql(keyspaceName), 
                cqlSession.getKeyspace().get());
    }
    
}
