package com.datastax.workshop.petclinic.owner.springdata;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.workshop.petclinic.owner.db.OwnerTableDefinition;

/**
 * Entity to map the Table petclinic_vet with a bean.
 *
 * @author Cedrick LUNVEN (@clunven)
 */
@Table(OwnerTableDefinition.TABLE_NAME)
public class OwnerEntitySpring implements OwnerTableDefinition, Serializable {
    
    /** Serial Number. */
    private static final long serialVersionUID = 5469961645441103854L;

    @PrimaryKey
    @Column(COLUMN_ID)
    @CassandraType(type = CassandraType.Name.UUID)
    private UUID id;

    @Column(COLUMN_FIRSTNAME)
    @CassandraType(type = CassandraType.Name.TEXT)
    private String firstName;

    @Column(COLUMN_LASTNAME)
    @CassandraType(type = CassandraType.Name.TEXT)
    private String lastName;
    
    @Column(COLUMN_ADDRESS)
    @CassandraType(type = CassandraType.Name.TEXT)
    private String address;

    @Column(COLUMN_CITY)
    @CassandraType(type = CassandraType.Name.TEXT)
    private String city;
    
    @Column(COLUMN_TELEPHONE)
    @CassandraType(type = CassandraType.Name.TEXT)
    private String telephone;
    
    /**
     * Default Constructor
     */
    public OwnerEntitySpring() {}
    
    /**
     * Constructor to TODO
     * @param r
     */
    public OwnerEntitySpring(Row r) {
        this.id = r.getUuid(COLUMN_ID);
        this.firstName = r.getString(COLUMN_FIRSTNAME);
        this.lastName = r.getString(COLUMN_LASTNAME);
        this.address = r.getString(COLUMN_ADDRESS);
        this.city = r.getString(COLUMN_CITY);
        this.telephone = r.getString(COLUMN_TELEPHONE);
    }
    
    /**
     * Constructor with Primary KEY.
     *      
     * @param uid
     *      primary key
     */
    public OwnerEntitySpring(String uid) {
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
    public OwnerEntitySpring(String uid, String firstname, String lastname) {
       this(uid);
       this.firstName   = firstname;
       this.lastName    = lastname;
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
     * Getter accessor for attribute 'address'.
     *
     * @return
     *       current value of 'address'
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter accessor for attribute 'address'.
     * @param address
     * 		new value for 'address '
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Getter accessor for attribute 'city'.
     *
     * @return
     *       current value of 'city'
     */
    public String getCity() {
        return city;
    }

    /**
     * Setter accessor for attribute 'city'.
     * @param city
     * 		new value for 'city '
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Getter accessor for attribute 'telephone'.
     *
     * @return
     *       current value of 'telephone'
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Setter accessor for attribute 'telephone'.
     * @param telephone
     * 		new value for 'telephone '
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    
    
}
