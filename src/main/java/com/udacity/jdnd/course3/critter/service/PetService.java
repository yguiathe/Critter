package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;

import java.util.List;

public interface PetService {

    Pet save(Pet pet, Long ownerID);

    List<Pet> findAll();

    Pet findById(Long petId);

    List<Pet> findByCustomer(Long ownerId);
}
