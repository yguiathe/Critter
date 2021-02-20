package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.dto.CustomerDTO;
import com.udacity.jdnd.course3.critter.entity.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.entity.dto.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private CustomerService customerService;
    private EmployeeService employeeService;
    private ModelMapper modelMapper;

    public UserController(CustomerService customerService, EmployeeService employeeService, ModelMapper modelMapper) {
        this.customerService = customerService;
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        Customer customer = this.modelMapper.map(customerDTO, Customer.class);
        return this.modelMapper.map(this.customerService.save(customer), CustomerDTO.class);
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        return this.customerService.findAll().stream().map(customer -> this.modelMapper.map(customer, CustomerDTO.class)).collect(Collectors.toList());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        return this.modelMapper.map(this.customerService.getOwnerByPet(petId), CustomerDTO.class);
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = this.modelMapper.map(employeeDTO, Employee.class);
        return this.modelMapper.map(this.employeeService.save(employee), EmployeeDTO.class);
    }

    @GetMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        return this.modelMapper.map(this.employeeService.findById(employeeId), EmployeeDTO.class);
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        this.employeeService.setAvailability(daysAvailable, employeeId);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        return this.employeeService.findEmployeesForService(employeeDTO.getSkills(), employeeDTO.getDate().getDayOfWeek()).stream().map(employee -> this.modelMapper.map(employee, EmployeeDTO.class)).collect(Collectors.toList());
    }

}
