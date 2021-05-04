package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(value="select * from (Employee AS e inner join EmployeeAvailability as ea on e.id = ea.employee_id) inner join EmployeeSkills as ek on e.id = ek.employee_id")
    List<Employee> findEmployeeWithSkillsAndDaysAvailable(Set<EmployeeSkill> skills, LocalDate date);
}
