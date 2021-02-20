package com.udacity.jdnd.course3.critter.converter;

import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.entity.dto.ScheduleDTO;
import org.modelmapper.AbstractConverter;

import java.util.stream.Collectors;

public class ScheduleConverter extends AbstractConverter<Schedule, ScheduleDTO> {
    @Override
    protected ScheduleDTO convert(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setId(schedule.getId());
        scheduleDTO.setDate(schedule.getDate());
        scheduleDTO.setPetIds(schedule.getPets().stream().map(pet -> pet.getId()).collect(Collectors.toList()));
        scheduleDTO.setEmployeeIds(schedule.getEmployees().stream().map(employee -> employee.getId()).collect(Collectors.toList()));
        scheduleDTO.setActivities(schedule.getActivities());
        return scheduleDTO;

    }
}
