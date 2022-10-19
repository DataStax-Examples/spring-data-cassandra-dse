package t02.statements.drivers;

import static com.datastax.samples.ExampleUtils.connect;
import static com.datastax.samples.ExampleUtils.createKeyspace;
import static com.datastax.samples.ExampleUtils.createTableUser;
import static com.datastax.samples.ExampleUtils.truncateTable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.samples.ExampleSchema;
import com.datastax.samples.dto.UserDto;

/**
 * Sample codes using Cassandra OSS Driver 4.x
 * 
 * Disclaimers:
 *  - Tests for arguments nullity has been removed for code clarity
 *  - Packaged as a main class for usability
 *  
 * Pre-requisites:
 * - Cassandra running locally (127.0.0.1, port 9042)
 * 
 * @author Cedrick LUNVEN (@clunven)
 * @author Erick  RAMIREZ (@@flightc)
 */
public class Test02_PreparedStatements implements ExampleSchema {

    /** Logger for the class. */
    private static Logger LOGGER = LoggerFactory.getLogger(Test02_PreparedStatements.class);

    // This will be used as singletons for the sample
    private static CqlSession session;
    
    // Prepare your statements once and execute multiple times 
    private static PreparedStatement stmtCreateUser;
    private static PreparedStatement stmtUpsertUser;
    private static PreparedStatement stmtExistUser;
    private static PreparedStatement stmtDeleteUser;
    private static PreparedStatement stmtFindUser;
    
    @BeforeAll
    public static void initStatements() {
        // Create killrvideo keyspace (if needed)
        createKeyspace();
        // Initialize Cluster and Session Objects (connected to keyspace killrvideo)
        session = connect();
        // Create working table User (if needed)
        createTableUser(session);
        // Empty tables for tests
        truncateTable(session, USER_TABLENAME);
        
        // Prepare your statements once and execute multiple times 
        stmtCreateUser = session.prepare(QueryBuilder.insertInto(USER_TABLENAME)
                .value(USER_EMAIL, QueryBuilder.bindMarker())
                .value(USER_FIRSTNAME, QueryBuilder.bindMarker())
                .value(USER_LASTNAME, QueryBuilder.bindMarker())
                .ifNotExists().build());
        // Using a - SLOW - lightweight transaction to check user existence
        stmtUpsertUser = session.prepare(QueryBuilder.insertInto(USER_TABLENAME)
                .value(USER_EMAIL, QueryBuilder.bindMarker())
                .value(USER_FIRSTNAME, QueryBuilder.bindMarker())
                .value(USER_LASTNAME, QueryBuilder.bindMarker())
                .build());
        stmtExistUser = session.prepare(QueryBuilder
                .selectFrom(USER_TABLENAME).column(USER_EMAIL)
                .whereColumn(USER_EMAIL)
                .isEqualTo(QueryBuilder.bindMarker())
                .build());
        stmtDeleteUser = session.prepare(QueryBuilder
                .deleteFrom(USER_TABLENAME)
                .whereColumn(USER_EMAIL)
                .isEqualTo(QueryBuilder.bindMarker())
                .build());
        stmtFindUser = session.prepare(QueryBuilder
                .selectFrom(USER_TABLENAME).all()
                .whereColumn(USER_EMAIL)
                .isEqualTo(QueryBuilder.bindMarker())
                .build());
    }
    
    
    @Test
    public void testWorkWithStatements() {
        // -- Create 
        String userEmail = "clun@sample.com";
        session.execute(stmtCreateUser.bind(userEmail, "Cedric", "Lunven"));
        if (session.execute(stmtExistUser.bind(userEmail))
                   .getAvailableWithoutFetching() > 0) {
             LOGGER.info("+ {}  now exists in table 'user'", userEmail);
        }
            
        // -- Update
        String userEmail2 = "eram@sample.com";
        session.execute(stmtUpsertUser.bind(userEmail2, "Eric", "Ramirez"));
          
        
        // -- Delete
        session.execute(stmtDeleteUser.bind(userEmail2));   
        
        Optional<UserDto> erick = findUserById(userEmail2);
        System.out.println("eric Deleted ? " + erick.isPresent());    
        
        
        // -- Read
        
        Optional<UserDto> cedrick = findUserById(userEmail);
        System.out.println(cedrick.get().getFirstName());
        
            
        // -- Find All
        List<UserDto > allUsers = session
                    .execute(QueryBuilder.selectFrom(USER_TABLENAME).all().build())
                    .all().stream().map(UserDto::new)
                    .collect(Collectors.toList());
        System.out.println(allUsers);
    }
    
    private Optional < UserDto > findUserById(String email) {
        ResultSet rs = session.execute(stmtFindUser.bind(email));
        // We query by the primary key ensuring unicity
        Row record = rs.one();
        return (null != record) ? Optional.of(new UserDto(record)) :Optional.empty();
    }
    
    @Test
    public void statements() {
        String insert = "INSERT INTO users (email, firstname, lastname) VALUES (?,?,?)";
        PreparedStatement ps1 = session.prepare(insert);
        PreparedStatement ps2 = session.prepare(insert);
        Assertions.assertEquals(ps1.getId(), ps2.getId());
    }
    
    @AfterAll
    public static void gracefulShutDown() {
        session.close();
    }
    
    
     
    
  
}
