package com.safetynet.alerts.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class Person {

    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private Date birthDate;

    @Autowired
    private Address personalAddress;

    @Autowired
    private MedicalRecord medicalRecord;

}
