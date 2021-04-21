package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.types.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
public class Schedule {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDate date;

    @ManyToMany
    @JoinTable(name="schedule_employee")
    private List<Employee> employees;

    @ManyToMany
    @JoinTable(name="schedule_pet")
    private List<Pet> pets;

    @ElementCollection
    private Set<EmployeeSkill> activities;
}
