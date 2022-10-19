package com.datastax.workshop.petclinic.vet.db;

/**
 * Schema constants for Vet.
 * 
 * @author Cedrick LUNVEN (@clunven)
 */
public interface VetTableDefinition {
    
    /**
     * Table Name.
     */
    String TABLE_NAME          = "petclinic_vet";
    
    /**
     * Column Definitions
     */
    String COLUMN_ID           = "id";
    String COLUMN_LAST_NAME    = "last_name";
    String COLUMN_FIRST_NAME   = "first_name";
    String COLUMN_SPECIALTIES  = "specialties";
    
    /**
     * Index Definition
     */
    String INDEX_ON_NAME       = "petclinic_idx_vetname";

}
