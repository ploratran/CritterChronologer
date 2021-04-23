package com.udacity.jdnd.course3.critter.entity;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Entity
public class Employee extends User {

    // Use @ElementCollection to denote association between Entity and Enums
    // These enums will be stored in separate table, along with id of Entity where they're contained

    // joined table of Employee and EmployeeSkills enum:
    @ElementCollection
    @JoinTable(name="employee_skills")
    private Set<EmployeeSkill> skills;

    // join table of Employee and DayOfWeek enum
    @ElementCollection
    private Set<DayOfWeek> daysAvailable;

    // Many Employees have Many Schedules, a M:M relationship:
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name="employee_schedule",
            joinColumns = {@JoinColumn(name="employee_id")},
            inverseJoinColumns = {@JoinColumn(name="schedule_id")}
    )
    private List<Schedule> schedules;

    public Employee() {}

    public Employee(Set<EmployeeSkill> skills, Set<DayOfWeek> daysAvailable) {
        this.skills = skills;
        this.daysAvailable = daysAvailable;
    }

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
}
