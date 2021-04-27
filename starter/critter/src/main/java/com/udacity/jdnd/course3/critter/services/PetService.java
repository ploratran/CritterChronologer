package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PetService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PetRepository petRepository;

    // save new Pet:
    public Pet save(Pet pet, Long ownerId) {
        // find owner by owner Id:
        Customer owner = customerRepository.getOne(ownerId);

        // set pet's owner to new owner:
        pet.setCustomer(owner);
        // save newly updated pet into pet repository:
        pet = petRepository.save(pet);

        // save newly added pet into customer repository:
        owner.getPets().add(pet);
        customerRepository.save(owner);

        // return newly added pet:
        return pet;
    }

//    // find Pet by pet Id:
//    public Optional<Pet> findPetById(Long petId) {
//        return petRepository.findById(petId);
//    }
//
//    // find all Pets:
//    public List<Pet> findAllPet() {
//        return petRepository.findAll();
//    }

    // find pet by owner Id:
}
