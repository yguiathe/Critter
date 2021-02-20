package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Schedule;

import java.util.List;

public interface ScheduleService {

    Schedule save(Schedule schedule);

    List<Schedule> findAll();

    List<Schedule> getScheduleForEmployee(Long employeeId);

    List<Schedule> getScheduleForPet(Long petId);

    List<Schedule> getScheduleForCustomer(Long customerId);
}
