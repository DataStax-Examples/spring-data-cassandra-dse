package t05.batches.spring;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Cedrick LUNVEN (@clunven)
 *
 */
@Repository
public interface TodoRepositoryCassandra extends CassandraRepository<TodoEntity, UUID> {
  
}
