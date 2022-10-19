package com.datastax.workshop.petclinic.visit;

import java.io.Serializable;
import java.util.UUID;

import com.datastax.workshop.petclinic.pet.Pet;

/**
 * Represents a Visit (visit) in the presentation layer (REST)
 */
public class Visit implements Serializable {

    /** Serial. */
    private static final long serialVersionUID = -7178251172136975063L;

    /** Unique identifier for visit. */
    protected UUID id;
    
    /** Visit date. */
    protected String date;
    
    /** Visit Description. */
    protected String description;
    
    /** Related pet for the visit. */
    protected Pet pet;
    
    public Visit() {}
    
    /**
     * Create a visit from its id.
     */
    public Visit(UUID id) {
        this.id = id;
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
     * Getter accessor for attribute 'date'.
     *
     * @return
     *       current value of 'date'
     */
    public String getDate() {
        return date;
    }

    /**
     * Setter accessor for attribute 'date'.
     * @param date
     * 		new value for 'date '
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Getter accessor for attribute 'description'.
     *
     * @return
     *       current value of 'description'
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter accessor for attribute 'description'.
     * @param description
     * 		new value for 'description '
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter accessor for attribute 'pet'.
     *
     * @return
     *       current value of 'pet'
     */
    public Pet getPet() {
        return pet;
    }

    /**
     * Setter accessor for attribute 'pet'.
     * @param pet
     * 		new value for 'pet '
     */
    public void setPet(Pet pet) {
        this.pet = pet;
    }
    
}
