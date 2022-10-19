package com.datastax.workshop.petclinic.vet.db;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;

/**
 * Represents a Veterinarians (Vet) in the Data Accesslayer (Cassandra)
 *
 * @author Cedrick LUNVEN (@clunven)
*/
@Entity
@CqlName(VetTableDefinition.TABLE_NAME)
public class VetEntity implements VetTableDefinition, Serializable {

    /** Serial. */
    private static final long serialVersionUID = 7407715795842376538L;
  
    @PartitionKey
    @CqlName(COLUMN_ID)
    private UUID id;

    @CqlName(COLUMN_FIRST_NAME)
    private String firstName;

    @CqlName(COLUMN_LAST_NAME)
    private String lastName;
    
    @CqlName(COLUMN_SPECIALTIES)
    private Set<String> specialties = new HashSet<>();  
        
    /**
     * Defaut constructor
     */
    public VetEntity() {
    }
    
    /**
     * Constructor with initialized uid
     */
    public VetEntity(String uid) {
        this.id = UUID.fromString(uid);
    }
    
    /**
     * Contructor
     * @param uid
     * @param firstname
     * @param lastname
     * @param specialties
     */
    public VetEntity(String uid, String firstname, String lastname, String...specialties) {
       this(uid);
       this.firstName   = firstname;
       this.lastName    = lastname;
       this.specialties = Set.of(specialties);
    }
    
    public VetEntity(UUID uid, String firstname, String lastname, Set<String> specialties) {
        this.id          = uid;
        this.firstName   = firstname;
        this.lastName    = lastname;
        this.specialties = specialties;
    }

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
    public Set<String> getSpecialties() {
        return specialties;
    }

    /**
     * Setter accessor for attribute 'specialties'.
     * @param specialties
     * 		new value for 'specialties '
     */
    public void setSpecialties(Set<String> specialties) {
        this.specialties = specialties;
    }
    
}
