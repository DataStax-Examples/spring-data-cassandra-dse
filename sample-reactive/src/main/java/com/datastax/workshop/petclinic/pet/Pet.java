package com.datastax.workshop.petclinic.pet;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import com.datastax.workshop.petclinic.owner.Owner;
import com.datastax.workshop.petclinic.visit.Visit;

/**
 * Represents an Pet in the presentation layer (REST)
 */
public class Pet implements Serializable {

    /** Serial. */
    private static final long serialVersionUID = 6533528868325708859L;
    
    /** unique identifier. */
    protected UUID id;
    
    /** Pet name. */
    protected String name;
    
    /** Pet birthDate formatted as yyyy/MM/dd" */
    protected String birthDate;
    
    /** Pet Type. */
    protected PetType type;
    
    /** Reference to Owner. */
    protected Owner owner;
    
    /** Set of visit for this pet. */
    protected Set<Visit> visits;
    
    public Pet() {}
    
    /**
     * Constructor with only petId (syntaxic sugar).
     */
    public Pet(UUID petId) {
        this.id = petId;
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
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * Setter accessor for attribute 'birthDate'.
     * @param birthDate
     * 		new value for 'birthDate '
     */
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Getter accessor for attribute 'type'.
     *
     * @return
     *       current value of 'type'
     */
    public PetType getType() {
        return type;
    }

    /**
     * Setter accessor for attribute 'type'.
     * @param type
     * 		new value for 'type '
     */
    public void setType(PetType type) {
        this.type = type;
    }

    /**
     * Getter accessor for attribute 'owner'.
     *
     * @return
     *       current value of 'owner'
     */
    public Owner getOwner() {
        return owner;
    }

    /**
     * Setter accessor for attribute 'owner'.
     * @param owner
     * 		new value for 'owner '
     */
    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    /**
     * Getter accessor for attribute 'visits'.
     *
     * @return
     *       current value of 'visits'
     */
    public Set<Visit> getVisits() {
        return visits;
    }

    /**
     * Setter accessor for attribute 'visits'.
     * @param visits
     * 		new value for 'visits '
     */
    public void setVisits(Set<Visit> visits) {
        this.visits = visits;
    }

}
