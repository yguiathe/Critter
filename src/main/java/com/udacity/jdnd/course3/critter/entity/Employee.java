package com.udacity.jdnd.course3.critter.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Employee extends User {

    @ElementCollection
    private List<EmployeeSkill> skills = new ArrayList<>();

    @ElementCollection
    private List<DayOfWeek> daysAvailable = new ArrayList<>();

}
