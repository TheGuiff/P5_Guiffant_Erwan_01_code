package com.safetynet.alerts.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MedicalRecord {

    List<String> medication;
    List<String> allergies;

}
