package t01.connect.drivers;

import java.net.InetSocketAddress;

import org.junit.jupiter.api.Test;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;
import com.datastax.samples.ExampleSchema;

/**
 * Create a keyspace with Simple Strategy and replication factor 1 (for local environment)
 * 
 * Pre-requisites:
 * - Cassandra running at (ip=127.0.0.1, port=9042, datacenter=datacenter1)
 * 
 * This code below will execute the following CQL statement:
 * --------------------------------------------------------------------
 * CREATE KEYSPACE spring 
 * WITH replication = 
 *      {'class': 'SimpleStrategy', 
 *       'replication_factor': '1'}  
 * AND durable_writes = true;
 * ---------------------------------------------------------------------
 * 
 * @author DataStax Developer Advocate Team
 * 
 * Need Help ? Join us on community.datastax.com to ask your questions for free.
 */
public class Test02_CreateKeyspace implements ExampleSchema {
    
    @Test
    public void testCreateKeyspace() {
        
        try (CqlSession cqlSession = CqlSession.builder()
                .addContactPoint(new InetSocketAddress("127.0.0.1", 9042))
                .withLocalDatacenter("dc1")
                .build()) {
            
            cqlSession.execute(SchemaBuilder
                    .createKeyspace("spring")
                    .ifNotExists()
                    .withSimpleStrategy(1)
                    .withDurableWrites(true)
                    .build());
            
// Droping keyspace: 
//            cqlSession.execute(SchemaBuilder
//                    .dropKeyspace(KEYSPACE_NAME)
//                    .ifExists().build());
            
            System.out.println("+ Keyspace '%s' created (if needed).".formatted(KEYSPACE_NAME));
        }
    }
    
     
}
