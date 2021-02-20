package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.dao.CustomerDao;
import com.udacity.jdnd.course3.critter.dao.PetDao;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service("CustomerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private PetDao petDao;

    static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Override
    public Customer save(Customer customer) {
        return customerDao.save(customer);
    }

    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    @Override
    public Customer getOwnerByPet(Long petId) {
        return customerDao.findById(petDao.findById(petId).orElseThrow(NoSuchElementException::new).getCustomer().getId()).orElseThrow(NoSuchElementException::new);
    }
}
