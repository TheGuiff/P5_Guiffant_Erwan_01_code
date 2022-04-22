package com.safetynet.alerts.web.controller;

import com.safetynet.alerts.domain.model.MedicalRecord;
import com.safetynet.alerts.domain.service.MedicalRecordService;
import com.safetynet.alerts.web.dto.MedicalRecordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {

    @Autowired
    MedicalRecordService medicalRecordService;

    /*@PostMapping(value = "")
    @PutMapping("/medicalRecord/{firstname},{lastname},{birthdate},{medications},{allergies}")
    public MedicalRecordDto saveMedicalRecord(@RequestBody MedicalRecordDto medicalRecordDto) {
        return medicalRecordService.personToMedicalRecordDto(medicalRecordService.saveMedicalRecord(medicalRecordDto.g,
                lastName,
                birthdate,
                medications,
                allergies));
    }*/

    @DeleteMapping("")
    public void deleteMedicalRecord(@RequestParam("firstname") String firstName,
                                             @RequestParam("lastname") String lastName) {
        medicalRecordService.deleteMedicalRecord(firstName, lastName);
    }
}
