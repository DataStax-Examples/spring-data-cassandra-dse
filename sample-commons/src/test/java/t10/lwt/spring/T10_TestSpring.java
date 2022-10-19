package t10.lwt.spring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.InsertOptions;
import org.springframework.data.cassandra.core.WriteResult;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.datastax.oss.driver.api.core.ConsistencyLevel;

import t06.async.spring.TodoEntity;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = T10_SpringDataConfiguration.class)
@TestPropertySource(locations="/test.properties")
public class T10_TestSpring {
    
    @Autowired
    CassandraOperations cassandraTemplate;
    
    @Test
    public void testLightweightTransactions() {
        TodoEntity te = new TodoEntity("test", 1);
        InsertOptions uo = InsertOptions.builder()
                    .consistencyLevel(ConsistencyLevel.LOCAL_ONE)
                    .ifNotExists(true)
                    .build();
        
        WriteResult wr =  cassandraTemplate.insert(te, uo);
        System.out.println(wr.wasApplied());
        WriteResult wr2 = cassandraTemplate.insert(te, uo);
        System.out.println(wr2.wasApplied());
    }
    
    

}
