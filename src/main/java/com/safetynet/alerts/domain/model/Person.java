package com.safetynet.alerts.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
public class Person {

    private String firstName;
    private String lastName;
    private String birthdate;
    private String address;
    private String city;
    private String zip;
    private String phone;
    private String email;
    private List<String> medications;
    private List<String> allergies;

    public Person (String firstNameIn,
                          String lastNameIn,
                          String addressIn,
                          String cityIn,
                          String zipIn,
                          String phoneIn,
                          String emailIn) {
        this.setFirstName(firstNameIn);
        this.setLastName(lastNameIn);
        this.setAddress(addressIn);
        this.setCity(cityIn);
        this.setZip(zipIn);
        this.setPhone(phoneIn);
        this.setEmail(emailIn);
    }

    public int getAge() {
        LocalDate currentDate = LocalDate.now();
        LocalDate birtDate = LocalDate.parse(this.getBirthdate(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        return Period.between(birtDate, currentDate).getYears();
    }

}
