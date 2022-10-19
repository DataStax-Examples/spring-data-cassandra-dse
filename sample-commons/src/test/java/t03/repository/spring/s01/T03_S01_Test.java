package t03.repository.spring.s01;

import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.type.DataTypes;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;


/**
 * https://docs.spring.io/spring-data/cassandra/docs/current/reference/html/#cassandra.template
 *
 * @author Cedrick LUNVEN (@clunven)
 *
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = T03_S01_SpringDataConfiguration.class)
@TestPropertySource(locations="/test.properties")
public class T03_S01_Test {
  
    @Autowired
    TodoRepositoryCassandra todoRepository;
    
    @Autowired
    CqlSession cqlSession;
    
   
    @BeforeEach
    public void createTableIfNeeded() {
        
        // Create table
        cqlSession.execute(SchemaBuilder
            .createTable(TodoEntity.TODO_TABLENAME)
            .ifNotExists()
            .withPartitionKey(TodoEntity.TODO_COLUMN_UID, DataTypes.UUID)
            .withColumn(TodoEntity.TODO_COLUMN_TITLE, DataTypes.TEXT)
            .withColumn(TodoEntity.TODO_COLUMN_COMPLETED, DataTypes.BOOLEAN)
            .withColumn(TodoEntity.TODO_COLUMN_ORDER, DataTypes.INT)
            .build());
        
        // Create search index
        cqlSession.execute(""
                + "CREATE SEARCH INDEX IF NOT EXIST ON %s"
                    .formatted(TodoEntity.TODO_TABLENAME));
        System.out.println("Table Created");
        
        // Insert Data
        IntStream.range(0, 10).forEach(i ->
            todoRepository.insert(
                    new TodoEntity("Sample task %s".formatted(i), i)));
        System.out.println("Data Inserted");
    }
    
    @Test
    public void testFindAll() {
        todoRepository.findAll()
                      .stream()
                      .map(todo -> todo.getUid() + " - " + todo.getTitle())
                      .forEach(System.out::println);
    }
    
    @Test
    public void testFindwithSolr() {
        todoRepository.findWithTitleLike("Sample task%")
                      .stream()
                      .map(todo -> todo.getUid() + " - " + todo.getTitle())
                      .forEach(System.out::println);
    }
    
    
}
