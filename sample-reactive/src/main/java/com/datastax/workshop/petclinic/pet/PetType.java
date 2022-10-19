package com.datastax.workshop.petclinic.pet;

import java.io.Serializable;

/**
 * Represent a PetType in the presentation layer (REST) 
 *
 * As name is used as primary no need for 2 fields but this is the structure
 * expected from the Angular used to work with this demo.  
 */
public class PetType implements Serializable {
    
    /** Serial. */
    private static final long serialVersionUID = -6848331396030706076L;

    /** Pet identifier (which is also the name). */
    private String id;
    
    /** Pet name (which is also the identifer). */
    private String name;
    
    public PetType() {}
    
    /** Constuctor with the name. */
    public PetType(String name) {
        this.id   = name;
        this.name = name;
    }

    /**
     * Getter accessor for attribute 'id'.
     *
     * @return
     *       current value of 'id'
     */
    public String getId() {
        return id;
    }

    /**
     * Setter accessor for attribute 'id'.
     * @param id
     * 		new value for 'id '
     */
    public void setId(String id) {
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
}
