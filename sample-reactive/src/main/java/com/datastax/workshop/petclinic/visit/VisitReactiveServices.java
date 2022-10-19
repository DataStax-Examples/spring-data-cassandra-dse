package com.datastax.workshop.petclinic.visit;

import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import com.datastax.workshop.petclinic.db.VisitEntity;
import com.datastax.workshop.petclinic.db.VisitReactiveDao;
import com.datastax.workshop.petclinic.pet.db.PetReactiveDao;
import com.datastax.workshop.petclinic.utils.MappingUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Implementation of services for Visit
 * 
 * This class leverages on multiple Dao (visit, pet, reference lists) 
 * to compose data needed at UI level.
 */
@Component
@Validated
public class VisitReactiveServices {
    
    /** Implementation of Crud operations for pets. */
    private PetReactiveDao petDao;
    
    /** Implementation of Crud operations for visits. */
    private VisitReactiveDao visitDao;
    
    /**
     * Visit services.
     */
    public VisitReactiveServices(PetReactiveDao petDao, VisitReactiveDao visitDao) {
        this.petDao = petDao;
        this.visitDao = visitDao;
    }
    
    /**
     * Retrieve all visits from the database.
     * 
     * @return
     *      a Flux (reactor) with the stream of Owners.
     */
    public Flux<Visit> findAllVisits() {
        return Flux.from(visitDao.findAll())
                   .map(MappingUtils::mapEntityAsVisit)
                   .flatMap(petDao::populatePetForVisit);
    }
    
    /**
     * Retrieve a visit from its identifier in DB.
     * 
     * @return
     *      a Mono of Visit or empty
     */
    public Mono<Visit> findVisitById(String visitId) {
        return Mono.from(visitDao.findVisitById((UUID.fromString(visitId))))
                   .map(MappingUtils::mapEntityAsVisit)
                   .flatMap(petDao::populatePetForVisit);
    }
    
    /**
     * Create a visit in table petclinic_visit_by_pet
     * 
     * @param v
     *      curren Visit Bean
     * @return
     *      empty mono
     */
    public Mono<Visit> createVisit(Visit v) {
        VisitEntity ve = MappingUtils.mapVisitAsEntity(v);
        return Mono.from(visitDao.upsert(ve))
                   .map(rr -> ve)
                   .map(MappingUtils::mapEntityAsVisit);
    }
    
    /**
     * Delete a visit from its id if it exits.
     *
     * @param visitId
     *      unique visit identifier
     * @return
     *      emmpty mono
     */
    public Mono<Void> deleteVisitById(UUID visitId) {
        return Mono.from(visitDao.findVisitById(visitId))
                   .map(visitDao::delete)
                   .then();
    }
}
