package t06.async.spring;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Cedrick LUNVEN (@clunven)
 *
 */
@Repository
public interface TodoRepositoryCassandra extends CassandraRepository<TodoEntity, UUID> {
    
    @Async
    CompletableFuture<Optional<TodoEntity>> findByUid(UUID id);  
    
    
}
