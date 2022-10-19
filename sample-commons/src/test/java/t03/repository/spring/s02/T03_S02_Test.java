package t03.repository.spring.s02;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;


/**
 * https://docs.spring.io/spring-data/cassandra/docs/current/reference/html/#cassandra.template
 *
 * @author Cedrick LUNVEN (@clunven)
 *
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { 
        T03_S02_SpringDataConfiguration.class, 
        TodoRepositorySimpleCassandra.class})
@TestPropertySource(locations="/test.properties")
public class T03_S02_Test {
 
    @Autowired
    TodoRepositorySimpleCassandra todoRepository;
    
    @Test
    public void showSystem() {
        todoRepository.findAll()
            .stream()
            .map(todo -> todo.getUid() + " - " + todo.getTitle())
            .forEach(System.out::println);
  
    }
    
    
}
