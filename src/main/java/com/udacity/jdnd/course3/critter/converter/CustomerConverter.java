package com.udacity.jdnd.course3.critter.converter;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.dto.CustomerDTO;
import org.modelmapper.AbstractConverter;

import java.util.stream.Collectors;

public class CustomerConverter extends AbstractConverter<Customer, CustomerDTO> {

    @Override
    protected CustomerDTO convert(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        customerDTO.setNotes(customer.getNotes());
        customerDTO.setPetIds(customer.getPets().stream().map(pet -> pet.getId()).collect(Collectors.toList()));
        return customerDTO;
    }
}
