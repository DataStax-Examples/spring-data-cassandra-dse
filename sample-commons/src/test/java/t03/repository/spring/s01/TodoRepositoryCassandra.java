package t03.repository.spring.s01;

import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Cedrick LUNVEN (@clunven)
 *
 */
@Repository
public interface TodoRepositoryCassandra extends CassandraRepository<TodoEntity, UUID> {
    
    @Query("SELECT * FROM todos WHERE title LIKE ?0")
    List<TodoEntity> findWithTitleLike(String titlePattern);
    
}
