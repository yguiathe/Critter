package com.udacity.jdnd.course3.critter.configuration;

import com.udacity.jdnd.course3.critter.converter.CustomerConverter;
import com.udacity.jdnd.course3.critter.converter.PetPropertyMap;
import com.udacity.jdnd.course3.critter.converter.ScheduleConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addConverter(new CustomerConverter());
        modelMapper.addConverter(new ScheduleConverter());
        modelMapper.addMappings(new PetPropertyMap());
        return modelMapper;
    }
}
