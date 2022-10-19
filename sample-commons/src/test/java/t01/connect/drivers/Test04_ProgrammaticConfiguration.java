package t01.connect.drivers;

import java.time.Duration;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.config.DefaultDriverOption;
import com.datastax.oss.driver.api.core.config.DriverConfigLoader;

/**
 * Sample code to create tables, types and objects in a keyspace.
 * 
 * Pre-requisites:
 * - Cassandra running locally (127.0.0.1, port 9042)
 * - Keyspace killrvideo created {@link Test02_CreateKeyspace}
 * 
 * Doc : https://docs.datastax.com/en/developer/java-driver/4.5/manual/core/configuration/
 */
public class Test04_ProgrammaticConfiguration {
    
    /** Logger for the class. */
    private static Logger LOGGER = LoggerFactory.getLogger(Test04_ProgrammaticConfiguration.class);
    
    @Test
    public void testProgrammatic() {
        
        DriverConfigLoader loader = DriverConfigLoader.programmaticBuilder()
            .withStringList(DefaultDriverOption.CONTACT_POINTS, Arrays.asList("127.0.0.1:9042"))
            .withString(DefaultDriverOption.LOAD_BALANCING_LOCAL_DATACENTER, "dc1")
            .withString(DefaultDriverOption.SESSION_KEYSPACE, "spring")
            .withDuration(DefaultDriverOption.REQUEST_TIMEOUT, Duration.ofSeconds(5))
            .build();
        
        try (CqlSession cqlSession = CqlSession.builder()
                .withConfigLoader(loader).build()) {
            
            // Use session
            LOGGER.info("[OK] Connected to Keyspace {}", cqlSession.getKeyspace().get());
        }
    }
    
}
