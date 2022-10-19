package t01.connect.drivers;

import java.net.InetSocketAddress;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.metadata.Metadata;
import com.datastax.oss.driver.api.core.metadata.Node;
import com.datastax.oss.driver.api.core.metadata.schema.KeyspaceMetadata;

/**
 * Standalone class to log metadata of a running cluster.
 * 
 * We expect you to have a running Cassandra on 127.0.0.1 with default port 9042
 */
public class Test01_ShowClusterMetaData {
    
    /** Logger for the class. */
    private static Logger LOGGER = LoggerFactory.getLogger(Test01_ShowClusterMetaData.class.getSimpleName());
    
    @Test
    public void should_show() {
        LOGGER.info("Starting 'ClusterShowMetaData' sample...");
        try (CqlSession cqlSession = CqlSession.builder()
                .addContactPoint(new InetSocketAddress("127.0.0.1", 9042))
                .withLocalDatacenter("dc1")
                .build()) {
            
            LOGGER.info("Connected to cluster with Session '{}'",
                    cqlSession.getName());
            
            LOGGER.info("Protocol Version: {}", 
                    cqlSession.getContext().getProtocolVersion());
            
            Metadata metaData = cqlSession.getMetadata();
            
            LOGGER.info("Listing available Nodes:");
            for (Node host : metaData.getNodes().values()) {
                LOGGER.info("+ [{}]: datacenter='{}' and rack='{}'", 
                        host.getListenAddress().orElse(null),
                        host.getDatacenter(), 
                        host.getRack());
            }
            
            LOGGER.info("Listing available keyspaces:");
            for (KeyspaceMetadata meta : metaData.getKeyspaces().values()) {
                LOGGER.info("+ [{}]", meta.getName());
            }
            
            LOGGER.info("[OK] Success");
            LOGGER.info("");
        }
    }
}
