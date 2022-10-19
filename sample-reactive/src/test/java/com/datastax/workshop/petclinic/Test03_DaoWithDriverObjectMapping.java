package com.datastax.workshop.petclinic;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.datastax.workshop.petclinic.vet.db.VetEntity;
import com.datastax.workshop.petclinic.vet.db.VetReactiveDao;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
public class Test03_DaoWithDriverObjectMapping {
    
    // Providing an explicit UUID to avoid inserting 10 times the same guy
    static final String MY_VET_ID = "5d219ed9-409b-43cf-b6b3-51592b489eed";
            
    @Autowired
    VetReactiveDao vetDao;
    
    @Test
    public void should_create_and_show_vet() {
        // Save a VET
        VetEntity theNuttyProfessor = 
                new VetEntity(MY_VET_ID, "Eddie", "Murphy", "surgery");
        
        StepVerifier.create(Mono.from(vetDao.upsert(theNuttyProfessor)))
            .expectComplete()
            .verify();
       
        System.out.println("Item saved.");
        
        StepVerifier.create(Mono.from(vetDao.findById(UUID.fromString(MY_VET_ID))).map(VetEntity::getFirstName))
            .expectNext("Eddie")
            .expectComplete()
            .verify();
        
    }

}
