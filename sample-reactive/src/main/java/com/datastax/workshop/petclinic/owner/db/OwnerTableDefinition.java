package com.datastax.workshop.petclinic.owner.db;

/**
 * Share constants.
 * @author Cedrick LUNVEN (@clunven)
 */
public interface OwnerTableDefinition {
    
    /**
     * Table Name.
     */
    String TABLE_NAME        = "petclinic_owner";
    
    /**
     * Column Definitions.
     */
    String COLUMN_ID         = "id";
    String COLUMN_LASTNAME   = "last_name";
    String COLUMN_FIRSTNAME  = "first_name";
    String COLUMN_ADDRESS    = "address";
    String COLUMN_CITY       = "city";
    String COLUMN_TELEPHONE  = "telephone";
    
    /**
     * Index Definitions
     */
    String INDEX_ON_NAME     = "petclinic_idx_ownername";
    
}
