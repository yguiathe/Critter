package com.udacity.jdnd.course3.critter.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer extends User {

    private String phoneNumber;
    private String notes;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private List<Pet> pets = new ArrayList<>();

}
