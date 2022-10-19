package com.datastax.workshop.petclinic.vet;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Represents a Veterinarians (Vet) in the presentation layer (REST)
 */
public class Vet implements Serializable {
    
    /** Serial. */
    private static final long serialVersionUID = 5116674190977860592L;
    
    /** unique identifier in DB. */
    private UUID id;
   
    /** vet first name. */
    private String firstName;
    
    /** vet last name. */
    private String lastName;
    
    /** Specialties for a vet. */
    private Set<VetSpecialty> specialties = new HashSet<>();
    
    public Vet() {}

    /**
     * Getter accessor for attribute 'id'.
     *
     * @return
     *       current value of 'id'
     */
    public UUID getId() {
        return id;
    }

    /**
     * Setter accessor for attribute 'id'.
     * @param id
     * 		new value for 'id '
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Getter accessor for attribute 'firstName'.
     *
     * @return
     *       current value of 'firstName'
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter accessor for attribute 'firstName'.
     * @param firstName
     * 		new value for 'firstName '
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter accessor for attribute 'lastName'.
     *
     * @return
     *       current value of 'lastName'
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter accessor for attribute 'lastName'.
     * @param lastName
     * 		new value for 'lastName '
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter accessor for attribute 'specialties'.
     *
     * @return
     *       current value of 'specialties'
     */
    public Set<VetSpecialty> getSpecialties() {
        return specialties;
    }

    /**
     * Setter accessor for attribute 'specialties'.
     * @param specialties
     * 		new value for 'specialties '
     */
    public void setSpecialties(Set<VetSpecialty> specialties) {
        this.specialties = specialties;
    }
     
}
