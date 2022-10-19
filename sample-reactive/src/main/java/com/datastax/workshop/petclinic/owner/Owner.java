package com.datastax.workshop.petclinic.owner;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.datastax.workshop.petclinic.pet.Pet;

/**
 * Represents an Owner (Owner) in the presentation layer (REST)
 */
public class Owner implements Serializable {

    /** Serial. */
    private static final long serialVersionUID = -140951859331681727L;
    
    private UUID id;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String telephone;
    private Set<Pet> pets = new HashSet<>();
    
    public Owner() {}
    
    public Owner(UUID uid) {
        this.id = uid;
    }
    
    public Owner(String uid) {
        this.id = UUID.fromString(uid);
    }
    
    public Owner(UUID id, String firstName, String lastName, String address, String city, String telephone, Set<Pet> pets) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.telephone = telephone;
        this.pets = pets;
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

    /**
     * Getter accessor for attribute 'pets'.
     *
     * @return
     *       current value of 'pets'
     */
    public Set<Pet> getPets() {
        return pets;
    }

    /**
     * Setter accessor for attribute 'pets'.
     * @param pets
     * 		new value for 'pets '
     */
    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }
    
}
