package t03.repository.spring.s02;

import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.type.DataTypes;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;


/**
 * https://docs.spring.io/spring-data/cassandra/docs/current/reference/html/#cassandra.template
 *
 * @author Cedrick LUNVEN (@clunven)
 *
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = T03_S02_SpringDataConfiguration.class)
@TestPropertySource(locations="/test.properties")
public class T03_S02_Test {
  
    @Autowired
    CqlSession cqlSession;
    
    @Test
    public void showSystem() {
        cqlSession.execute("");
  
    }
    
    
}
