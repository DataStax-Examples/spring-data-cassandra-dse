package t02.statements.spring.s01;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.cql.CqlTemplate;
import org.springframework.data.cassandra.core.cql.RowMapper;
import org.springframework.data.cassandra.core.cql.SimplePreparedStatementCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;

/**
 * CqlTemplate Doc
 * https://docs.spring.io/spring-data/cassandra/docs/current/reference/html/#cassandra.cql-template
 *
 *
 * The CqlTemplate can be used within a DAO implementation through direct 
 * instantiation with a SessionFactory reference or be configured in the 
 * Spring container and given to DAOs as a bean reference. CqlTemplate is 
 * a foundational building block for CassandraTemplate.
 *
 * @author Cedrick LUNVEN (@clunven)
 *
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = T02_S01_SpringDataConfiguration.class)
@TestPropertySource(locations="/test.properties")
public class T02_S01_Test {
    
    @Autowired
    CassandraOperations cassandraTemplate;
    
    @Autowired 
    CqlTemplate cqlTemplate;
    
    CqlSession cqlSession;
    
    @Test
    public void testCqlTemplate() {
        
        // Simple Statements
        cqlTemplate.execute(SimpleStatement
                           .builder("INSERT INTO users (email, firstname, lastname) "
                                   + "VALUES (?,?,?)")
                           .addPositionalValue("spring1@spring.com")
                           .addPositionalValue("spring1")
                           .addPositionalValue("spring1").build());
        
        // Execute Statements
        String name = cqlTemplate.queryForObject(""
                + "SELECT firstname "
                + "FROM users "
                + "WHERE email= ?", String.class, "spring1@spring.com");
        System.out.println(name);
        
        // Object Mapping
        User springUser = cqlTemplate.queryForObject(""
                + "SELECT * "
                + "FROM users "
                + "WHERE email= ?",
                new RowMapper<User>() {
                  public User mapRow(Row row, int rowNum) {
                    User user = new User();
                    user.firstname = row.getString("firstname");
                    user.lastname = row.getString("lastname");
                    return user;
                  }
                }, "spring1@spring.com");
        System.out.println(springUser.lastname);
        
        // -- Explicitly Preparing Statements
        SimplePreparedStatementCreator spsc = new SimplePreparedStatementCreator(""
                + "SELECT * "
                + "FROM users "
                + "WHERE email= ?");
        
        List<String> lastNames = cqlTemplate.query(
                spsc, ps -> ps.bind("spring1@spring.com"),
                (row, rowNum) -> row.getString(0));
        System.out.println(lastNames);
        
        // They used to have a cache but deprecated.
        // --------------------------------------------
        // PreparedStatementCache cache = PreparedStatementCache.create();
        // CachedPreparedStatementCreator cached = new CachedPreparedStatementCreator("SELECT * FROM users where email =?");
        // PreparedStatement ps = cached.createPreparedStatement(cqlSession);
        // --------------------------------------------
        // ==> USE FIELD METHODS
    }
    
    public static final class User {
        String firstname;
        String lastname;
        String email;
    }
    
}
