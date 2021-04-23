package com.udacity.jdnd.course3.critter.entity;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customer extends User {

    @Column(name="phone_number", length=10)
    private String phoneNumber;

    private String notes;

    // Use 1:M between Customer and Pets:
    @OneToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Pet> pets;

    public Customer() {}

    public Customer( String phoneNumber, String notes) {
        this.phoneNumber = phoneNumber;
        this.notes = notes;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Pet> getPets() {
        return this.pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
