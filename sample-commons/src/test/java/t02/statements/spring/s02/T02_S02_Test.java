package t02.statements.spring.s02;

import static org.springframework.data.cassandra.core.query.Criteria.where;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.datastax.oss.driver.api.core.cql.SimpleStatement;


/**
 * https://docs.spring.io/spring-data/cassandra/docs/current/reference/html/#cassandra.template
 *
 * @author Cedrick LUNVEN (@clunven)
 *
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = T02_S02_SpringDataConfiguration.class)
@TestPropertySource(locations="/test.properties")
public class T02_S02_Test {
    
    @Autowired
    CassandraOperations cassandraTemplate;
    
    @Table(value = "users")
    public static final class User {
      
      @Id 
      String email;
      
      String firstname;
      
      String lastname;
      
      public User() {}
      
      public User(String email, String firstname, String lastname) {    
          this.email = email;
          this.firstname = firstname;
          this.lastname = lastname;
      }
    }
    
    @Test
    public void testCassandraTemplate() {
        
        // Used as CqlTemplate
        cassandraTemplate.execute(SimpleStatement
                           .builder("INSERT INTO users (email, firstname, lastname) VALUES (?,?,?)")
                           .addPositionalValue("spring1@spring.com")
                           .addPositionalValue("spring1")
                           .addPositionalValue("spring1").build());
        
        // Saving
        User bob = new User("a.a@a.com", "Bob", "connor");
        cassandraTemplate.insert(bob);

        // Retrieving
        User queriedBob = cassandraTemplate
                .selectOneById("a.a@a.com", User.class);
        System.out.println(queriedBob.lastname);
        
        // Retrieving
        List<User> all = cassandraTemplate
                .query(User.class)    
                .matching(Query.query(where("email").is("a.a@a.com")))
                .all();
        System.out.println(all);
        
        
    }
    
    
    
}
