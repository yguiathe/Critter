package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.entity.EmployeeSkill;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.entity.dto.ScheduleDTO;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private ScheduleService scheduleService;
    private EmployeeService employeeService;
    private PetService petService;
    private ModelMapper modelMapper;

    public ScheduleController(ScheduleService scheduleService, EmployeeService employeeService, PetService petService, ModelMapper modelMapper) {
        this.scheduleService = scheduleService;
        this.employeeService = employeeService;
        this.petService = petService;
        this.modelMapper = modelMapper;
    }

    @PutMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = this.modelMapper.map(scheduleDTO, Schedule.class);
        schedule.setPets(scheduleDTO.getPetIds().stream().map(petId -> this.petService.findById(petId)).collect(Collectors.toList()));
        schedule.setEmployees(scheduleDTO.getEmployeeIds().stream().map(employeeId -> this.employeeService.findById(employeeId)).collect(Collectors.toList()));
        return this.modelMapper.map(this.scheduleService.save(schedule), ScheduleDTO.class);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return this.scheduleService.findAll().stream().map(schedule -> this.modelMapper.map(schedule, ScheduleDTO.class)).collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        return this.scheduleService.getScheduleForPet(petId).stream().map(schedule -> this.modelMapper.map(schedule, ScheduleDTO.class)).collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        return this.scheduleService.getScheduleForEmployee(employeeId).stream().map(schedule -> this.modelMapper.map(schedule, ScheduleDTO.class)).collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        return this.scheduleService.getScheduleForCustomer(customerId).stream().map(schedule -> this.modelMapper.map(schedule, ScheduleDTO.class)).collect(Collectors.toList());
    }

}
