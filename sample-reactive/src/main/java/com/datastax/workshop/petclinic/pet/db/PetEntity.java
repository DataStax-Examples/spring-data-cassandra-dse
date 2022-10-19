package com.datastax.workshop.petclinic.pet.db;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import com.datastax.oss.driver.api.mapper.annotations.ClusteringColumn;
import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;

/**
 * Represents a Pet (Pet) in the data access layer (Cassandra)
 */
@Entity
@CqlName(PetEntity.PET_TABLE)
public class PetEntity implements Serializable {
    
    /** Serial id. */
    private static final long serialVersionUID = -5340639715920598448L;
    
    /** Group constants in a single Class. */
    public static final String PET_TABLE          = "petclinic_pet_by_owner";
    public static final String PET_INDEX          = "petclinic_idx_petid";
    public static final String PET_ATT_OWNER_ID   = "owner_id";
    public static final String PET_ATT_PET_ID     = "pet_id";
    public static final String PET_ATT_PET_TYPE   = "pet_type";
    public static final String PET_ATT_NAME       = "name";
    public static final String PET_ATT_BIRTHDATE  = "birth_date";
    
    @PartitionKey
    @CqlName(PET_ATT_OWNER_ID)
    private UUID ownerId;
    
    @ClusteringColumn
    @CqlName(PET_ATT_PET_ID)
    private UUID petId;
    
    @CqlName(PET_ATT_PET_TYPE)
    private String petType;
    
    @CqlName(PET_ATT_NAME)
    private String name;
    
    /**
     *  A CQL Date is mapped as java LocalDate
     * - Date      <-> java.time.LocalDate
     * - Timestamp <-> java.time.LocalDate
     * - Time      <-> java.time.LocalTime
     * 
     * @see https://docs.datastax.com/en/developer/java-driver/4.8/manual/core/#cql-to-java-type-mapping
     */
    @CqlName(PET_ATT_BIRTHDATE)
    private LocalDate birthDate;
    
    public PetEntity() {}
    
    /**
     * Constructor from the id.
     */
    public PetEntity(UUID petId) {
        this.petId = petId;
    }

    /**
     * Getter accessor for attribute 'ownerId'.
     *
     * @return
     *       current value of 'ownerId'
     */
    public UUID getOwnerId() {
        return ownerId;
    }

    /**
     * Setter accessor for attribute 'ownerId'.
     * @param ownerId
     * 		new value for 'ownerId '
     */
    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * Getter accessor for attribute 'petId'.
     *
     * @return
     *       current value of 'petId'
     */
    public UUID getPetId() {
        return petId;
    }

    /**
     * Setter accessor for attribute 'petId'.
     * @param petId
     * 		new value for 'petId '
     */
    public void setPetId(UUID petId) {
        this.petId = petId;
    }

    /**
     * Getter accessor for attribute 'petType'.
     *
     * @return
     *       current value of 'petType'
     */
    public String getPetType() {
        return petType;
    }

    /**
     * Setter accessor for attribute 'petType'.
     * @param petType
     * 		new value for 'petType '
     */
    public void setPetType(String petType) {
        this.petType = petType;
    }

    /**
     * Getter accessor for attribute 'name'.
     *
     * @return
     *       current value of 'name'
     */
    public String getName() {
        return name;
    }

    /**
     * Setter accessor for attribute 'name'.
     * @param name
     * 		new value for 'name '
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter accessor for attribute 'birthDate'.
     *
     * @return
     *       current value of 'birthDate'
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * Setter accessor for attribute 'birthDate'.
     * @param birthDate
     * 		new value for 'birthDate '
     */
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    
}
