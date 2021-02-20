package com.udacity.jdnd.course3.critter.dao;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.EmployeeSkill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Repository
public interface EmployeeDao extends CrudRepository<Employee, Long> {

    List<Employee> findDistinctBySkillsInAndDaysAvailable(Set<EmployeeSkill> skills, DayOfWeek day);
    List<Employee> findByDaysAvailable(DayOfWeek day);
}
