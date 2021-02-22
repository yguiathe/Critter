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
import java.util.List;
import java.util.NoSuchElementException;

@Service("PetService")
@Transactional
public class PetServiceImpl implements PetService {

    @Autowired
    private PetDao petDao;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerDao customerDao;

    static final Logger logger = LoggerFactory.getLogger(PetServiceImpl.class);


    @Override
    public Pet save(Pet pet, Long ownerId) {
        Customer customer = customerDao.findById(ownerId).orElseThrow(IllegalArgumentException::new);
        pet.setCustomer(customer);
        pet = petDao.save(pet);
        customerService.addPetToCustomer(pet, customer);
        return pet;
    }

    @Override
    public List<Pet> findAll() {
        return petDao.findAll();
    }

    @Override
    public Pet findById(Long petId) {
        return this.petDao.findById(petId).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Pet> findByCustomer(Long ownerId) {
        Customer customer = customerDao.findById(ownerId).orElseThrow(NoSuchElementException::new);
        return this.petDao.findByCustomer(customer);
    }
}
