package com.datastax.workshop.petclinic;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.datastax.workshop.petclinic.reflist.ReferenceListReactiveDao;

import reactor.test.StepVerifier;

@SpringBootTest
public class Test02_DaoWithCqlSession {
    
    @Autowired
    ReferenceListReactiveDao referenceListDao;
    
    @Test
    public void should_list_vet_specialies() {
        System.out.println(referenceListDao.findReferenceList("vet_specialty").block());
        
        StepVerifier.create(referenceListDao.findReferenceList("vet_specialty"))
            .expectNext(Set.of("dentistry", "radiology", "surgery"))
            .expectComplete()
            .verify();
    }

}
