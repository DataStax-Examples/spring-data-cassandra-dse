package t01.connect.drivers;

import static com.datastax.samples.ExampleUtils.createTableCommentByUser;
import static com.datastax.samples.ExampleUtils.createTableCommentByVideo;
import static com.datastax.samples.ExampleUtils.createTableUser;
import static com.datastax.samples.ExampleUtils.createTableVideo;
import static com.datastax.samples.ExampleUtils.createTableVideoViews;
import static com.datastax.samples.ExampleUtils.createUdtVideoFormat;

import java.net.InetSocketAddress;

import org.junit.jupiter.api.Test;

/**
 * Sample code to create tables, types and objects in a keyspace.
 * 
 * Pre-requisites:
 * - Cassandra running locally (127.0.0.1, port 9042)
 * - Keyspace killrvideo created {@link SampleCode4x_CONNECT_CreateKeyspace}
 */
import com.datastax.oss.driver.api.core.CqlSession;


/**
 * Sample code to create tables, types and objects in a keyspace.
 * 
 * Pre-requisites:
 * - Cassandra running locally (127.0.0.1, port 9042)
 */
public class Test03_CreateSchema {
    
    @Test
    public void testCreateSchema() {
        
        try (CqlSession cqlSession = CqlSession.builder()
                .addContactPoint(new InetSocketAddress("127.0.0.1", 9042))
                .withLocalDatacenter("dc1")
                .withKeyspace("spring")
                .build()) {
            
                createUdtVideoFormat(cqlSession);
                createTableUser(cqlSession);
                createTableVideo(cqlSession);
                createTableVideoViews(cqlSession);
                createTableCommentByVideo(cqlSession);
                createTableCommentByUser(cqlSession);
        }
        System.out.println("[OK] Success");
    }
   
}
