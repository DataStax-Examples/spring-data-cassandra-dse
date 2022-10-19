package t04.paging.spring;

import static org.springframework.data.cassandra.core.query.Criteria.where;

import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.mapping.CassandraPersistentEntity;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.cassandra.core.query.Query;
import org.springframework.data.cassandra.repository.support.MappingCassandraEntityInformation;
import org.springframework.data.cassandra.repository.support.SimpleCassandraRepository;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.core.support.PersistentEntityInformation;
import org.springframework.stereotype.Repository;

import com.datastax.oss.driver.api.core.CqlSession;

@Repository
@SuppressWarnings("unchecked")
public class PageableUserRepository extends SimpleCassandraRepository<EntityUser, String> {

    protected final CqlSession cqlSession;

    protected final CassandraOperations cassandraTemplate;
    
    protected PersistentEntityInformation<EntityUser,String> entityInfo;

    public PageableUserRepository(CqlSession cqlSession, CassandraOperations ops) {
        super(new MappingCassandraEntityInformation<EntityUser, String>(
                (CassandraPersistentEntity<EntityUser>) ops.getConverter()
                .getMappingContext().getRequiredPersistentEntity(EntityUser.class), 
                ops.getConverter()), 
                ops);
        
        this.entityInfo = new MappingCassandraEntityInformation<EntityUser, String>(
                (CassandraPersistentEntity<EntityUser>) ops.getConverter()
                .getMappingContext().getRequiredPersistentEntity(EntityUser.class), 
                ops.getConverter());
        this.cqlSession = cqlSession;
        this.cassandraTemplate = ops;
    }
    
    
    public Slice<EntityUser> findBylastname(CassandraPageRequest pageRequest, String lastname) {
       return cassandraTemplate
               .slice(Query.query(where("lastname").is(lastname)).pageRequest(pageRequest), 
                       this.entityInfo.getJavaType());
    }
    
}
