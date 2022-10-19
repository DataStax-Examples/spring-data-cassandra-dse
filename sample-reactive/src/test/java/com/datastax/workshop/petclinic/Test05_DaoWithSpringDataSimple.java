package com.datastax.workshop.petclinic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.datastax.workshop.petclinic.owner.springdata.OwnerEntitySpring;
import com.datastax.workshop.petclinic.owner.springdata.OwnerReactiveCassandraRepository;

import reactor.test.StepVerifier;

@SpringBootTest
public class Test05_DaoWithSpringDataSimple {
    
    // Providing an explicit UUID to avoid inserting 10 times the same guy
    static final String MY_OWNER_ID = "5d219ed9-409b-43cf-b6b3-51592b489eee";
    
    @Autowired
    OwnerReactiveCassandraRepository ownerRepo;
    
    @Test
    public void should_create_and_show_vet() {
        OwnerEntitySpring oes = new OwnerEntitySpring(MY_OWNER_ID, "Cedrick", "lunven"); 
        
        StepVerifier.create(ownerRepo.insert(oes))
            .expectNextCount(1)
            .expectComplete()
            .verify();
        System.out.println("Owner saved.");
        
        StepVerifier.create(ownerRepo.searchByOwnerName("lunven"))
        .expectNextCount(1)
        .expectComplete()
        .verify();
        System.out.println("Owner Retrieved.");
    }

}
