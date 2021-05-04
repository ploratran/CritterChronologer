package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.dto.CustomerDTO;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.services.CustomerService;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {

        // initialize new customer:
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setNotes(customerDTO.getNotes());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());

        // retrieve petIds from Customer DTO
        // set it to new customer:
        List<Long> petIds = customerDTO.getPetIds();

        // return converted DTO with customer and petIDs:
        return convertToDTO(customerService.save(customer, petIds));
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers() {
        // find all customers using Service layer:
        List<Customer> customers = customerService.findAllCustomer();

        // return as a List of Customer DTO:
        return customers.stream().map(this::convertToDTO).collect(toList());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        throw new UnsupportedOperationException();
    }

    // utility function to convert Customer to CustomerDTO:
    public CustomerDTO convertToDTO(Customer customer) {

        // copy properties of Customer to CustomerDTO:
        CustomerDTO customerDto = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDto);

        // get petIds from Customer, then set to Customer DTO's petIds:
        List<Long> petIds = customer.getPets().stream().map(Pet::getId).collect(toList());
        customerDto.setPetIds(petIds);

        // return customer DTO:
        return customerDto;
    }
}
