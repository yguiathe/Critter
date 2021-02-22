package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.dto.PetDTO;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    static final Logger logger = LoggerFactory.getLogger(PetController.class);

    private PetService petService;
    private ModelMapper modelMapper;

    public PetController(PetService petService, ModelMapper modelMapper) {
        this.petService = petService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/{ownerId}")
    public PetDTO savePet(@RequestBody PetDTO petDTO, @PathVariable long ownerId) {
        Pet pet = this.modelMapper.map(petDTO, Pet.class);
        return this.modelMapper.map(this.petService.save(pet, ownerId), PetDTO.class);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return this.modelMapper.map(this.petService.findById(petId), PetDTO.class);
    }

    @GetMapping
    public List<PetDTO> getPets(){
        return this.petService.findAll().stream().map(pet -> this.modelMapper.map(pet, PetDTO.class)).collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        return this.petService.findByCustomer(ownerId).stream().map(pet -> this.modelMapper.map(pet, PetDTO.class)).collect(Collectors.toList());
    }
}
