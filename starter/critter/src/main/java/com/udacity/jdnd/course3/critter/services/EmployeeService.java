package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    // save new Employee:
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    // get Employee by its id:
    public Employee findEmployeeById(Long employeeId) {
        return employeeRepository.getOne(employeeId);
    }
}
