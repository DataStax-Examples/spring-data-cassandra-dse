package t08.reactive.spring;

import java.util.UUID;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoReactiveCassandraRepository 
    extends ReactiveCassandraRepository<TodoEntity, UUID> {
    
}
