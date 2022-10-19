package t08.reactive.spring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import reactor.core.publisher.Flux;


/**
 * https://docs.spring.io/spring-data/cassandra/docs/current/reference/html/#cassandra.template
 *
 * @author Cedrick LUNVEN (@clunven)
 *
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { T08_SpringDataConfiguration.class})
@TestPropertySource(locations="/test.properties")
public class T08_Test {
 
    @Autowired
    TodoReactiveCassandraRepository todoRepository;
    
    @Test
    public void showSystem() {
        Flux<TodoEntity> flux = todoRepository.findAll();
        flux.blockFirst();
        System.out.println("OK");
    }
    
    
}
