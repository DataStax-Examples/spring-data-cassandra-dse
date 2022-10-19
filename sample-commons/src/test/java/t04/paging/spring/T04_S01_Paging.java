package t04.paging.spring;

import java.nio.ByteBuffer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.Slice;
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
        T04_S01_SpringDataConfiguration.class, 
        PageableUserRepository.class})
@TestPropertySource(locations="/test.properties")
public class T04_S01_Paging {
 
    @Autowired
    PageableUserRepository userRepository;
    
    @Test
    public void showSystem() {
        //System.out.println(userRepository.findAll());
        Slice<EntityUser> page1 = userRepository.findAll(CassandraPageRequest.first(10));
        
        System.out.println("Page1 " + page1.getContent().size());
        if (!page1.isLast()) {
            Slice<EntityUser> page2 = userRepository.findAll(page1.nextPageable());
            System.out.println("Page2 " + page2.getContent().size());
        }
    }
    
    @Test
    public void customQuery() {
        CassandraPageRequest requestPage = CassandraPageRequest.of(0, 10);
        Slice<EntityUser> page1 = userRepository.findBylastname(requestPage, "lastname");
        System.out.println("Page1 " + page1.getContent().size());
        
        // To rebuild the query later
        ByteBuffer pagingState = ((CassandraPageRequest) page1.nextPageable()).getPagingState();
        Slice<EntityUser> page2 = userRepository.findBylastname( CassandraPageRequest.of(requestPage, pagingState), "lastname");
        System.out.println("Page2 " + page2.getContent().size());
    }
    
    
}
