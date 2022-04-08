package com.safetynet.alerts.domain;

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

}
