package com.safetynet.alerts.domain.model;

import lombok.Getter;
import lombok.Setter;

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
    private MedicalRecord medicalRecord;

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

}
