package com.safetynet.alerts.web.dto;

import com.safetynet.alerts.domain.model.Person;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FireDto {

    private String firstName;
    private String lastName;
    private String phone;
    private int age;
    private List<String> medications;
    private List<String> allergies;

    public FireDto (Person person) {
        this.setFirstName(person.getFirstName());
        this.setLastName(person.getLastName());
        this.setPhone(person.getPhone());
        this.setAge(person.getAge());
        this.setMedications(person.getMedications());
        this.setAllergies(person.getAllergies());
    }
}
