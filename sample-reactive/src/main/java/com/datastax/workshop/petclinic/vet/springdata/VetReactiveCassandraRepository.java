package com.datastax.workshop.petclinic.vet.springdata;

import java.util.UUID;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VetReactiveCassandraRepository extends ReactiveCassandraRepository<VetEntitySpring, UUID> {
    
}
