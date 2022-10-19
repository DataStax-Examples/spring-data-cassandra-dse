package t01.connect.spring.s01;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.DropKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;
import org.springframework.data.cassandra.core.cql.session.init.KeyspacePopulator;
import org.springframework.data.cassandra.core.cql.session.init.ResourceKeyspacePopulator;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.type.DataTypes;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;

@Configuration
public class Test01_SpringDataConfiguration extends AbstractCassandraConfiguration {
    
    @Value("${spring.data.cassandra.keyspace-name}")
    private String keyspaceName;
    
    @Value("${spring.data.cassandra.contact-points}")
    private String contactPoint;
    
    @Value("${spring.data.cassandra.local-datacenter}")
    private String locaDataCenter;
    
    @Override
    public String getContactPoints() {
      return contactPoint;
    }
    
    @Override
    protected String getKeyspaceName() {
        return keyspaceName;
    }
    
    /** {@inheritDoc} */
    @Override
    protected String getLocalDataCenter() {
        return locaDataCenter;
    }
    
    // ----------------------------------------------------------------------------
    
    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
      return Arrays.asList(CreateKeyspaceSpecification
              .createKeyspace(CqlIdentifier.fromCql("spring2"))
              .with(KeyspaceOption.DURABLE_WRITES, true)
              .ifNotExists()
              .withSimpleReplication(1));
    }
    
    @Override
    protected KeyspacePopulator keyspacePopulator() {
      return new ResourceKeyspacePopulator(scriptOf(
              SchemaBuilder.createTable("todo")
               .ifNotExists()
               .withPartitionKey("id", DataTypes.UUID)
               .withColumn("title", DataTypes.TEXT)
               .withColumn("completed", DataTypes.BOOLEAN)
               .withColumn("offset", DataTypes.INT)
               .build().getQuery()));
    }
    
    @Override
    protected KeyspacePopulator keyspaceCleaner() {
      return new ResourceKeyspacePopulator(scriptOf("DROP TABLE todo;"));
    }
    
    @Override
    protected List<DropKeyspaceSpecification> getKeyspaceDrops() {
        //return Arrays.asList(DropKeyspaceSpecification.dropKeyspace(keyspaceName).ifExists());
      return Arrays.asList();
    }
    
    //----------------------------------------------------------------------------
    
    /*
    @Bean
    SessionFactoryInitializer sessionFactoryInitializer(SessionFactory sessionFactory) {

      SessionFactoryInitializer initializer = new SessionFactoryInitializer();
      initializer.setSessionFactory(sessionFactory);

      ResourceKeyspacePopulator populator1 = new ResourceKeyspacePopulator();
      populator1.setSeparator(";");
      populator1.setScripts(new ClassPathResource("com/myapp/cql/db-schema.cql"));

      ResourceKeyspacePopulator populator2 = new ResourceKeyspacePopulator();
      populator2.setSeparator("@@");
      populator2.setScripts(new ClassPathResource("classpath:com/myapp/cql/db-test-data-1.cql"), //
          new ClassPathResource("classpath:com/myapp/cql/db-test-data-2.cql"));

      initializer.setKeyspacePopulator(new CompositeKeyspacePopulator(populator1, populator2));

      return initializer;
    }*/

    
    
}
