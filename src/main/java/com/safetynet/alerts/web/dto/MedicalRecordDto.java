package com.safetynet.alerts.web.dto;

import com.safetynet.alerts.domain.model.MedicalRecord;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicalRecordDto {

    private String firstName;
    private String lastName;
    private String birthdate;
    private MedicalRecord medicalRecord;

}
