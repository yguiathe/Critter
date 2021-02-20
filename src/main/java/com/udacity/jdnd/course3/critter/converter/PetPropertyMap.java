package com.udacity.jdnd.course3.critter.converter;

import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.dto.PetDTO;
import org.modelmapper.PropertyMap;

public class PetPropertyMap extends PropertyMap<Pet, PetDTO> {
    @Override
    protected void configure() {
        map().setOwnerId(source.getCustomer().getId());
    }
}
