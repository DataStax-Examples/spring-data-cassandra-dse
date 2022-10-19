package t05.batches.spring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraBatchOperations;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;


/**
 * https://docs.spring.io/spring-data/cassandra/docs/current/reference/html/#cassandra.template
 *
 * @author Cedrick LUNVEN (@clunven)
 *
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { T05_SpringDataConfiguration.class})
@TestPropertySource(locations="/test.properties")
public class T05_Test {
    
    @Autowired
    CassandraOperations cassandraTemplate;
    
    @Autowired
    TodoRepositoryCassandra todoCassandra;
    
    @Test
    public void showSystem() {
        CassandraBatchOperations batchops =  cassandraTemplate.batchOps();
        batchops.insert(new TodoEntity("test", 10));
        batchops.insert(new TodoEntity("test", 10));
        batchops.execute();
     }
    
    
}
