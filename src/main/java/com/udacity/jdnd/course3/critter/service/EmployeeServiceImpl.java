package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.dao.EmployeeDao;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.EmployeeSkill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service("EmployeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Override
    public Employee save(Employee employee) {
        return employeeDao.save(employee);
    }

    @Override
    public Employee findById(Long id) {
        return employeeDao.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Employee setAvailability(Set<DayOfWeek> daysAvailable, Long employeeId) {
        Employee employee = employeeDao.findById(employeeId).orElseThrow(NoSuchElementException::new);
        employee.setDaysAvailable(new ArrayList<>(daysAvailable));
        return employeeDao.save(employee);
    }

    @Override
    public List<Employee> findEmployeesForService(Set<EmployeeSkill> skills, DayOfWeek day) {
        return employeeDao.findByDaysAvailable(day).stream().filter(employee -> employee.getSkills().containsAll(skills)).collect(Collectors.toList());
    }
}
