package com.udacity.jdnd.course3.critter.dao;

import com.udacity.jdnd.course3.critter.entity.Schedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleDao extends CrudRepository<Schedule, Long> {

    List<Schedule> findAll();

    List<Schedule> findByEmployees_Id(Long employeeId);

    List<Schedule> findByPets_Id(Long petId);

}
