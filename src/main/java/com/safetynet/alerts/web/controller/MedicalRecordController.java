package com.safetynet.alerts.web.controller;

import com.safetynet.alerts.domain.service.MedicalRecordService;
import com.safetynet.alerts.web.dto.MedicalRecordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {

    @Autowired
    MedicalRecordService medicalRecordService;

    @PostMapping("")
    public MedicalRecordDto addMedicalRecord(@RequestBody MedicalRecordDto medicalRecordDto) {
        return medicalRecordService.personToMedicalRecordDto(medicalRecordService.saveMedicalRecord(medicalRecordDto.getFirstName(),
                medicalRecordDto.getLastName(),
                medicalRecordDto.getBirthdate(),
                medicalRecordDto.getMedications(),
                medicalRecordDto.getAllergies()));
    }

    @PutMapping("")
    public MedicalRecordDto updateMedicalRecord(@RequestBody MedicalRecordDto medicalRecordDto) {
        return medicalRecordService.personToMedicalRecordDto(medicalRecordService.saveMedicalRecord(medicalRecordDto.getFirstName(),
                medicalRecordDto.getLastName(),
                medicalRecordDto.getBirthdate(),
                medicalRecordDto.getMedications(),
                medicalRecordDto.getAllergies()));
    }

    @DeleteMapping("")
    public void deleteMedicalRecord(@RequestParam("firstname") String firstName,
                                             @RequestParam("lastname") String lastName) {
        medicalRecordService.deleteMedicalRecord(firstName, lastName);
    }
}
