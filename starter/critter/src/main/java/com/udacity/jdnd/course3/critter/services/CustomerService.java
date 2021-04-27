package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PetRepository petRepository;

    // save new customer:
    public Customer save(Customer owner, List<Long> petIds) {
        // make a list of pets:
        List<Pet> pets = new ArrayList<>();

        // check if petIds exist:
        if (petIds != null) {
            // iterate through all petIds:
            // find pet by specific petId
            pets = petIds.stream().map((petId) -> petRepository.getOne(petId)).collect(Collectors.toList());
        }
        // add pets to owner:
        owner.setPets(pets);

        // save new owner to db:
        return customerRepository.save(owner);
    }

    // find all customers:
    public List<Customer> findAllCustomer() {
        return customerRepository.findAll();
    }

    // find owner by pet ID:
}
