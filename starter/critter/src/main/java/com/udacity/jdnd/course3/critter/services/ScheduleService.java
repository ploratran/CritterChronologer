package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PetRepository petRepository;

    @Autowired
    CustomerRepository customerRepository;

    // save new schedule, with list of employee ids and pet ids:
    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    // get all schedules:
    public List<Schedule> findAllSchedules() {
        return scheduleRepository.findAll();
    }

    // find all schedules of an employee by employee id:
    public List<Schedule> findSchedulesByEmployeeId(Long employeeId) {

        // find an employee by employee id:
        Employee employee = employeeRepository.getOne(employeeId);

        // return all schedules of an employee using JPA Repository:
        return scheduleRepository.getAllByEmployeesContains(employee);
    }

    // find all schedules of a pet by pet id:
    public List<Schedule> findSchedulesByPetId(Long petId) {

        // find a pet by pet id:
        Pet pet = petRepository.getOne(petId);

        // return all schedules for the pet:
        return scheduleRepository.getAllByPetsContains(pet);
    }

    // find all schedules of a customer by customer id
    // has to find list of pets using petRepository because customer db does not have joined table with schedule db:
    public List<Schedule> findScheduleByOwnerId(Long ownerId) {

        // find owner by owner Id:
        Customer owner = customerRepository.getOne(ownerId);

        // return list of pets of a customer
        // find owner by ownerId, then retrieve a list of pet of that owner
        // then, find in schedule table in a joined table with pet table
        return scheduleRepository.getAllByPetsIn(owner.getPets());
    }
}
