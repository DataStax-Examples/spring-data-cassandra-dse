package com.datastax.workshop.petclinic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.datastax.workshop.petclinic.vet.VetSpecialty;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Test06_ApiController {
    
    @Autowired
    WebTestClient webClient;
    
    @Test
    public void should_retrieve_specialties_web() {
            webClient.get()
                 .uri("/petclinic/api/specialties")
                 .accept(MediaType.APPLICATION_JSON)
                 .exchange()
                 .expectStatus().isOk()
                 .expectBodyList(VetSpecialty.class);
    }

}
