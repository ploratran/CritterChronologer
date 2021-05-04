package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.Set;

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

    // set available days for employee by employee Id:
    public void setDayAvailableByEmployeeId(Set<DayOfWeek> daysAvailable, Long employeeId) {
        // find employee by Id
        // set days available by employee:
        Employee employee = employeeRepository.getOne(employeeId);
        employee.setDaysAvailable(daysAvailable);
        // save newly updated data to Employee db:
        employeeRepository.save(employee);
    }
}
