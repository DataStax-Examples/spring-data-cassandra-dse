package t01.connect.drivers;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.config.DriverConfigLoader;
import com.datastax.samples.ExampleSchema;

/**
 * Sample code to create tables, types and objects in a keyspace.
 * 
 * Pre-requisites:
 * - Cassandra running locally (127.0.0.1, port 9042)
 * - Keyspace killrvideo created {@link Test02_CreateKeyspace}
 * 
 * @author Cedrick LUNVEN (@clunven)
 */
public class Test05_DriverConfigLoader implements ExampleSchema {
    
    /** Logger for the class. */
    private static Logger LOGGER = LoggerFactory.getLogger(Test05_DriverConfigLoader.class);
    
   @Test
   public void testDriverConfigLoader() {
        
        // Load Configuration from file
        String confFilePath = Test05_DriverConfigLoader
                .class.getResource("/application-dse.conf").getFile();
        
        // Create a Load with this file
        DriverConfigLoader loader = 
                DriverConfigLoader.fromFile(new File(confFilePath));
        
        // Use it to create the session
        try (CqlSession cqlSession = CqlSession.builder().withConfigLoader(loader).build()) {
            
            // Use session
            LOGGER.info("[OK] Connected to Keyspace {}", cqlSession.getKeyspace().get());
        }
        LOGGER.info("[OK] Success");
    }
    
}
