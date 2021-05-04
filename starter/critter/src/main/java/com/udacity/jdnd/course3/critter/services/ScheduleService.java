package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PetRepository petRepository;

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
}
