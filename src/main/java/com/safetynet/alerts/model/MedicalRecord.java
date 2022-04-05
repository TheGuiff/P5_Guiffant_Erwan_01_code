package com.safetynet.alerts.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class MedicalRecord {

    private List<String> listAllergies;

    private List<String> listMedication;

}
