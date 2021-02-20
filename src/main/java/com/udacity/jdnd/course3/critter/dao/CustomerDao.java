package com.udacity.jdnd.course3.critter.dao;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDao extends CrudRepository<Customer, Long> {

    List<Customer> findAll();

    Customer findByPetsIn(List<Pet> pets);
}
