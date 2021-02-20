package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.EmployeeSkill;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

public interface EmployeeService {

    Employee save(Employee employee);

    Employee findById(Long id);

    Employee setAvailability(Set<DayOfWeek> daysAvailable, Long employeeId);

    List<Employee> findEmployeesForService(Set<EmployeeSkill> skills, DayOfWeek day);

}
