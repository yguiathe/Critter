package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.dao.CustomerDao;
import com.udacity.jdnd.course3.critter.dao.ScheduleDao;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service("ScheduleService")
@Transactional
public class ScheduleServiceImpl implements ScheduleService {

    static final Logger logger = LoggerFactory.getLogger(ScheduleServiceImpl.class);

    @Autowired
    private ScheduleDao scheduleDao;

    @Autowired
    private CustomerDao customerDao;

    @Override
    public Schedule save(Schedule schedule) {
        return scheduleDao.save(schedule);
    }

    @Override
    public List<Schedule> findAll() {
        return scheduleDao.findAll();
    }

    @Override
    public List<Schedule> getScheduleForEmployee(Long employeeId) {
        return scheduleDao.findByEmployees_Id(employeeId);
    }

    @Override
    public List<Schedule> getScheduleForPet(Long petId) {
        return scheduleDao.findByPets_Id(petId);
    }

    @Override
    public List<Schedule> getScheduleForCustomer(Long customerId) {
        List<Pet> pets = customerDao.findById(customerId).orElseThrow(NoSuchElementException::new).getPets();
        List<Schedule> schedules = new ArrayList<>();
        for(Pet pet : pets) {
            schedules.addAll(scheduleDao.findByPets_Id(pet.getId()));
        }
        return schedules;
    }
}
