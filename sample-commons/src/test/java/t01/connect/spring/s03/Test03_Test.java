package t01.connect.spring.s03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.CqlSession;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Test03_SpringDataConfiguration.class)
@TestPropertySource(locations="/test.properties")
public class Test03_Test {
    
    @Autowired
    CqlSession cqlSession;
    
    @Test
    public void testShouldConnect() {
        Assertions.assertEquals(
                CqlIdentifier.fromCql("spring"), 
                cqlSession.getKeyspace().get());
        
        System.out.println("[OK] - Connected to '%s'".formatted( 
                cqlSession.getMetadata().getClusterName().get()));
    }
    
}
