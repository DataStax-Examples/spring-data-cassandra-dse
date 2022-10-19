package com.datastax.workshop.petclinic;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.datastax.workshop.petclinic.vet.springdata.VetEntitySpring;
import com.datastax.workshop.petclinic.vet.springdata.VetReactiveCassandraRepository;

import reactor.test.StepVerifier;

@SpringBootTest
public class Test04_DaoWithSpringData {
    
    // Providing an explicit UUID to avoid inserting 10 times the same guy
    static final String MY_VET_ID = "5d219ed9-409b-43cf-b6b3-51592b489eee";
    
    @Autowired
    VetReactiveCassandraRepository vetRepositorySpring;
    
    @Test
    public void should_create_and_show_vet() {
        VetEntitySpring jurassicParkVet = new VetEntitySpring(MY_VET_ID, "Alan", "Grant", "surgery");
        
        //vetRepositorySpring.insert(jurassicParkVet).block();
        
        StepVerifier.create(vetRepositorySpring.insert(jurassicParkVet))
            .expectNextCount(1)
            .expectComplete()
            .verify();
       
        System.out.println("Item saved.");
        
        StepVerifier.create(vetRepositorySpring.findById(UUID.fromString(MY_VET_ID)))
        .expectNextCount(1)
        .expectComplete()
        .verify();
    }

}
