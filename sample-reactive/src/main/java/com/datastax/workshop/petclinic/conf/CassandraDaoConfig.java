package com.datastax.workshop.petclinic.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.workshop.petclinic.db.VisitReactiveDao;
import com.datastax.workshop.petclinic.db.VisitReactiveDaoMapper;
import com.datastax.workshop.petclinic.db.VisitReactiveDaoMapperBuilder;
import com.datastax.workshop.petclinic.owner.db.OwnerReactiveDao;
import com.datastax.workshop.petclinic.owner.db.OwnerReactiveDaoMapper;
import com.datastax.workshop.petclinic.owner.db.OwnerReactiveDaoMapperBuilder;
import com.datastax.workshop.petclinic.pet.db.PetReactiveDao;
import com.datastax.workshop.petclinic.pet.db.PetReactiveDaoMapper;
import com.datastax.workshop.petclinic.pet.db.PetReactiveDaoMapperBuilder;
import com.datastax.workshop.petclinic.vet.db.VetReactiveDao;
import com.datastax.workshop.petclinic.vet.db.VetReactiveDaoMapper;
import com.datastax.workshop.petclinic.vet.db.VetReactiveDaoMapperBuilder;

/**
 * Setup connectivity to Cassandra (locally or using Dbaas) using the Datastax Java driver and configuration files.
 * Define different dao(s) as singletons for the application, initializing table and statements when relevant.
 * 
 * - Documentation on CqlSession with Java Driver
 * {@link https://docs.datastax.com/en/developer/java-driver/latest/manual/core/}
 * 
 * @author Cedrick LUNVEN (@clunven)
 */
@Configuration
public class CassandraDaoConfig {
    
    /**
     * Initialized {@link VetReactiveDaos} as a Spring Singleton.
     * 
     * It will hold  implementations of accesses to Cassandra DB
     * for Vet business domain.
     */
    @Bean
    public VetReactiveDao vetDao(CqlSession cqlSession) {
        // A mapper is initiliazed with a Session.
        VetReactiveDaoMapper vetMapper = new VetReactiveDaoMapperBuilder(cqlSession).build();
        // Create tables required for this DAO.
        vetMapper.createSchema(cqlSession);
        // From the mapper we can access the Dao instance by specifying the proper keyspace.
        VetReactiveDao vetDao = vetMapper.vetDao(cqlSession.getKeyspace().get());
        return vetDao;
    }
 
    /**
     * Initialized {@link OwnerReactiveDao} as a Spring Singleton.
     * It will hold the implementations of access to Cassandra DB
     */
    @Bean
    public OwnerReactiveDao ownerDao(CqlSession cqlSession) {
        OwnerReactiveDaoMapper ownerMapper = new OwnerReactiveDaoMapperBuilder(cqlSession).build();
        ownerMapper.createSchema(cqlSession);
        OwnerReactiveDao ownerDao = ownerMapper.ownerDao(cqlSession.getKeyspace().get());
        return ownerDao;
    }
    
    /**
     * Initialized {@link PetReactiveDao} as a Spring Singleton.
     * It will hold the implementations of access to Cassandra DB
     */
    @Bean
    public PetReactiveDao petDao(CqlSession cqlSession) {
        PetReactiveDaoMapper petMapper = new PetReactiveDaoMapperBuilder(cqlSession).build(); 
        petMapper.createSchema(cqlSession);
        PetReactiveDao petDao = petMapper.petDao(cqlSession.getKeyspace().get());
        return petDao;
    }
    
    /**
     * Initialized {@link VisitReactiveDao} as a Spring Singleton.
     * It will hold the implementations of access to Cassandra DB
     */
    @Bean
    public VisitReactiveDao visitDao(CqlSession cqlSession) {
        VisitReactiveDaoMapper visitMapper = new VisitReactiveDaoMapperBuilder(cqlSession).build();
        visitMapper.createSchema(cqlSession);
        VisitReactiveDao visitDao = visitMapper.visitDao(cqlSession.getKeyspace().get());
        return visitDao;
    }
}
