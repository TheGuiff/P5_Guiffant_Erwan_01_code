package com.safetynet.alerts.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PersonInfoDto {

    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private List<String> medications;
    private List<String> allergies;

}
