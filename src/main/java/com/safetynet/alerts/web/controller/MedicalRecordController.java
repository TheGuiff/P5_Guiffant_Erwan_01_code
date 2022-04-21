package com.safetynet.alerts.web.controller;

import com.safetynet.alerts.domain.service.MedicalRecordService;
import com.safetynet.alerts.web.dto.MedicalRecordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MedicalRecordController {

    @Autowired
    MedicalRecordService medicalRecordService;

    @PostMapping(value = "/medicalRecord/{firstname},{lastname},{birthdate},[{medications}],[{allergies}]")
    @PutMapping("/medicalRecord/{firstname},{lastname},{birthdate},{medications},{allergies}")
    public MedicalRecordDto saveMedicalRecord(@PathVariable("firstname") String firstName,
                                              @PathVariable("lastname") String lastName,
                                              @PathVariable("birthdate") String birthdate,
                                              @PathVariable("medications") List<String > medications,
                                              @PathVariable("allergies") List<String> allergies) {
        return medicalRecordService.personToMedicalRecordDto(medicalRecordService.saveMedicalRecord(firstName,
                lastName,
                birthdate,
                medications,
                allergies));
    }

    @DeleteMapping("/medicalRecord/{firstname},{lastname}")
    public void deleteMedicalRecord(@PathVariable("firstname") String firstName,
                                              @PathVariable("lastname") String lastName) {
        medicalRecordService.deleteMedicalRecord(firstName, lastName);
    }
}
