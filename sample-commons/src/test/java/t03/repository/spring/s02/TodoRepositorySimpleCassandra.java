package t03.repository.spring.s02;

import java.util.UUID;

import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.mapping.CassandraPersistentEntity;
import org.springframework.data.cassandra.repository.support.MappingCassandraEntityInformation;
import org.springframework.data.cassandra.repository.support.SimpleCassandraRepository;
import org.springframework.stereotype.Repository;

import com.datastax.oss.driver.api.core.CqlSession;

@Repository
@SuppressWarnings("unchecked")
public class TodoRepositorySimpleCassandra extends SimpleCassandraRepository<TodoEntity, UUID> {

    protected final CqlSession cqlSession;
    
    protected final CassandraOperations cassandraTemplate;
    
    public TodoRepositorySimpleCassandra(CqlSession cqlSession, CassandraOperations ops) {
        super(new MappingCassandraEntityInformation<TodoEntity, UUID>(
                (CassandraPersistentEntity<TodoEntity>) ops.getConverter().getMappingContext()
                .getRequiredPersistentEntity(TodoEntity.class), ops.getConverter()), ops);
        this.cqlSession = cqlSession;
        this.cassandraTemplate = ops;
    }
    
    @Override
    public TodoEntity save(TodoEntity todo) {
        // Change Behaviour
        // ..
        return cassandraTemplate.insert(todo);
    }
    
    
}
