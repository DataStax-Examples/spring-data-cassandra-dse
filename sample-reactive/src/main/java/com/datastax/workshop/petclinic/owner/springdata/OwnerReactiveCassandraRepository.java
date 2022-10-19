package com.datastax.workshop.petclinic.owner.springdata;

import java.util.UUID;

import org.springframework.data.cassandra.core.ReactiveCassandraOperations;
import org.springframework.data.cassandra.core.mapping.CassandraPersistentEntity;
import org.springframework.data.cassandra.repository.support.MappingCassandraEntityInformation;
import org.springframework.data.cassandra.repository.support.SimpleReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.workshop.petclinic.owner.db.OwnerTableDefinition;

import reactor.core.publisher.Flux;

@Repository
public class OwnerReactiveCassandraRepository extends SimpleReactiveCassandraRepository<OwnerEntitySpring, UUID>
                                              implements OwnerTableDefinition {

    /**
     * CqlSession injected from the drivers 
     */
    protected final CqlSession cqlSession;
    
    /**
     * Cassandra Reactive Stuff
     */
    protected final ReactiveCassandraOperations reactiveCassandraTemplate;
    
    /**
     * Constructor to have access to CqlSession in reactive context.
     *
     * @param cqlSession
     *      target cqlsession
     * @param ops
     *      current cassandra template
     */
    @SuppressWarnings("unchecked")
    public OwnerReactiveCassandraRepository(CqlSession cqlSession, ReactiveCassandraOperations ops) {
        super(new MappingCassandraEntityInformation<OwnerEntitySpring, UUID>(
                (CassandraPersistentEntity<OwnerEntitySpring>) ops.getConverter().getMappingContext()
                .getRequiredPersistentEntity(OwnerEntitySpring.class), ops.getConverter()), ops);
        this.cqlSession = cqlSession;
        this.reactiveCassandraTemplate = ops;
    }
    
    /**
     * Custom method.
     * 
     * @param ownerLastName
     * @return
     */
    public Flux<OwnerEntitySpring> searchByOwnerName(String ownerLastName) {
        return reactiveCassandraTemplate.getReactiveCqlOperations()
                                        .query(SimpleStatement
                                            .builder("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_LASTNAME  + "=?")
                                            .addPositionalValues(ownerLastName)
                                            .build(), (row, rownum) -> new OwnerEntitySpring(row));
    }

}
