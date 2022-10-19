package com.datastax.workshop.petclinic.vet;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import com.datastax.workshop.petclinic.reflist.ReferenceListReactiveDao;
import com.datastax.workshop.petclinic.utils.MappingUtils;
import com.datastax.workshop.petclinic.vet.db.VetEntity;
import com.datastax.workshop.petclinic.vet.db.VetReactiveDao;
import com.datastax.workshop.petclinic.vet.springdata.VetEntitySpring;
import com.datastax.workshop.petclinic.vet.springdata.VetReactiveCassandraRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Implementation of services for Vet. 
 * 
 * This class leverages on multiple Dao (pet, reference lists) 
 * to compose data needed at UI level.
 */
@Component
@Validated
public class VetReactiveServices implements InitializingBean {
    
    /** Group constants relative to Data Model in single place for the domain. */
    public static final String VET_SPECIALTY = "vet_specialty";
    
    /** Default values for the vet specialtiess. */
    public Set<String> default_vet_specialties = new HashSet<>(
            Arrays.asList("dentistry", "radiology", "surgery"));
    
    /** Implementation of Crud operations for vets (DAO). */
    private final VetReactiveDao vetDao;
    
    /** Implementation of Crud operations for vets. */
    private final VetReactiveCassandraRepository vetRepositorySpring;
    
    /** Implementation of CRUD operations for references lists (here for vet specialties). */
    private ReferenceListReactiveDao refDao;
    
    /** 
     * Constructor with injections.
     *
     * @param vetRepo
     *      Driver DAO Mapping
     * @param vetRepoSpring
     *      Spring Data Mapping
     * @param refDao
     *      Reference list
     */
    public VetReactiveServices(VetReactiveDao vetRepo, VetReactiveCassandraRepository vetRepoSpring, ReferenceListReactiveDao refDao) {
        this.vetDao = vetRepo;
        this.refDao  = refDao;
        this.vetRepositorySpring = vetRepoSpring;
    }
    
    /**
     * Spring interface {@link InitializingBean} let you execute some
     * coode after bean has been initialized.
     * 
     * Here we enter default values for Vet and Vet Specialties.
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        
        // Ref list
        refDao.saveList(VET_SPECIALTY, default_vet_specialties).subscribe();
        
        // Add all vets entities
        Mono.from(vetDao.upsert(new VetEntity("11111111-1111-1111-1111-111111111111", "James", "Carter"))).subscribe();
        Mono.from(vetDao.upsert(new VetEntity("22222222-2222-2222-2222-222222222222", "Helen", "Leary", "radiology"))).subscribe();
        Mono.from(vetDao.upsert(new VetEntity("33333333-3333-3333-3333-333333333333", "Linda", "Douglas", "dentistry", "surgery"))).subscribe();
        Mono.from(vetDao.upsert(new VetEntity("44444444-4444-4444-4444-444444444444", "Rafael", "Ortega", "surgery"))).subscribe();
        Mono.from(vetDao.upsert(new VetEntity("55555555-5555-5555-5555-555555555555", "Henry", "Stevens", "radiology"))).subscribe();
        Mono.from(vetDao.upsert(new VetEntity("66666666-6666-6666-6666-666666666666", "Sharon", "Jenkins"))).subscribe();
        // Add a vet using Spring Data Cassandra
        vetRepositorySpring.save(
                new VetEntitySpring("77777777-7777-7777-7777-777777777777", "Spring", "Data", "radiology")).subscribe();
    }
    
    /**
     * Retrieve all vets from the database.
     * 
     * @return
     *      a Flux (reactor) with the stream of Owners.
     */
    public Flux<Vet> findAllVets() {
        return Flux.from(vetDao.findAll()).map(MappingUtils::mapEntityAsVet);  
    }
    
    /**
     * Retrieve all vets from the DB
     * @return
     */
    public Flux<Vet> findAllVetsSpring() {
        return vetRepositorySpring.findAll().map(MappingUtils::mapEntitySpringAsVet);  
    }
    
    /**
     * Find a Vet from its unique identifier.
     * 
     * @param vetId
     *      unique vet identifier
     * @return
     *      Mono (reactor) containing the vet or empty
     */
    public Mono<Vet> findVetById(String vetId) {
        return Mono.from(vetDao.findById(UUID.fromString(vetId)))
                   .map(MappingUtils::mapEntityAsVet);
    }
    
    public Mono<Vet> findVetByIdSpring(String vetId) {
        return Mono.from(vetRepositorySpring.findById(UUID.fromString(vetId)))
                   .map(MappingUtils::mapEntitySpringAsVet);
    }
    
    public Mono< Vet> createVet(Vet vet) {
        VetEntity ve = MappingUtils.mapVetAsEntity(vet);
        return Mono.from(vetDao.upsert(ve))
                   .map(rr -> ve)
                   .map(MappingUtils::mapEntityAsVet);
    }
    
    public Mono< Vet> createVetSpring(Vet vet) {
        VetEntitySpring ve = MappingUtils.mapVetAsEntitySpring(vet);
        return Mono.from(vetRepositorySpring.save(ve))
                   .map(rr -> ve)
                   .map(MappingUtils::mapEntitySpringAsVet);
    }
    
    public Mono<Void> deleteVetById(String vetId) {
        return Mono.from(vetDao.delete(new VetEntity(vetId))).then();
    }
    
    public Mono<Void> deleteVetByIdSpring(String vetId) {
        return Mono.from(vetRepositorySpring.delete(new VetEntitySpring(vetId))).then();
    }
    
    // --- Operations on Vet Specialties ---
    
    public Mono<Set<String>> listVetSpecialtiesCodes() {
        return refDao.findReferenceList(VET_SPECIALTY);
    }
                
    public Mono<Set<VetSpecialty>> listVetSpecialties() {
        return refDao.findReferenceList(VET_SPECIALTY)
                     .map(Set::stream)
                     .map(s -> s.map(VetSpecialty::new)
                     .collect(Collectors.toSet()));
    }
    
    public Mono<String> addVetSpecialty(String value) {
        return refDao.addToReferenceList(VET_SPECIALTY, value);
    }
    
    public Mono<Void> removeVetSpecialty(String value) {
        return refDao.removeFromReferenceList(VET_SPECIALTY, value);
    }
    
    public Mono<String> replaceVetSpecialty(String oldValue, String newValue) {
        return removeVetSpecialty(oldValue).then(addVetSpecialty(newValue));
    }

    
    
}
