package com.datastax.workshop.petclinic.vet;

import java.io.Serializable;

/**
 * Represent a vet specialty at presentation layer (REST)
 */
public class VetSpecialty implements Serializable {
    
    /** Serial. */
    private static final long serialVersionUID = -6848331396030706076L;

    /** identifier for the specialty. */
    private String id;
    
    /** name for the specialty (same as id in Cassandra DB>) */
    private String name;
    
    public VetSpecialty() {}
            
    /** Constructor with single Parameter. */
    public VetSpecialty(String name) {
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
