package com.datastax.workshop.petclinic.vet.springdata;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.workshop.petclinic.vet.db.VetTableDefinition;

import org.springframework.data.cassandra.core.mapping.CassandraType.Name;

/**
 * Entity to map the Table petclinic_vet with a bean.
 *
 * @author Cedrick LUNVEN (@clunven)
 */
@Table(VetTableDefinition.TABLE_NAME)
public class VetEntitySpring implements VetTableDefinition {
    
    @PrimaryKey
    @Column(COLUMN_ID)
    private UUID id;

    @Column(COLUMN_FIRST_NAME)
    private String firstName;

    @Column(COLUMN_LAST_NAME)
    @CassandraType(type = CassandraType.Name.TEXT)
    private String lastName;
    
    @Column(COLUMN_SPECIALTIES)
    @CassandraType(type = CassandraType.Name.SET, typeArguments = Name.TEXT)
    private Set<String> specialties = new HashSet<>(); 
    
    /**
     * Default Constructor
     */
    public VetEntitySpring() {}
    
    /**
     * Constructor with Primary KEY.
     *      
     * @param uid
     *      primary key
     */
    public VetEntitySpring(String uid) {
        this.id = UUID.fromString(uid);
    }
    
    /**
     * Full flege constructor.
     *
     * @param uid
     *      identifier
     * @param firstname
     *      first name
     * @param lastname
     *      last name
     * @param specialties
     *      specialties
     */
    public VetEntitySpring(String uid, String firstname, String lastname, String...specialties) {
       this(uid);
       this.firstName   = firstname;
       this.lastName    = lastname;
       this.specialties = Set.of(specialties);
    }
    
    /**
     * Full flege constructor.
     *
     * @param uid
     *      identifier
     * @param firstname
     *      first name
     * @param lastname
     *      last name
     * @param specialties
     *      specialties
     */
    public VetEntitySpring(UUID uid, String firstname, String lastname, Set<String> specialties) {
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
     *      new value for 'id '
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
     *      new value for 'firstName '
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
     *      new value for 'lastName '
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
     *      new value for 'specialties '
     */
    public void setSpecialties(Set<String> specialties) {
        this.specialties = specialties;
    }
    
}
