package com.safetynet.alerts.web.controller;

import com.safetynet.alerts.domain.service.MedicalRecordService;
import com.safetynet.alerts.web.dto.MedicalRecordDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@Slf4j
@RequestMapping("/medicalRecord")
public class MedicalRecordController {

    @Autowired
    MedicalRecordService medicalRecordService;

    @PostMapping("")
    public ResponseEntity<?> addMedicalRecord(@RequestBody MedicalRecordDto medicalRecordDto) {
        log.info("add medical record");
        try {
            medicalRecordService.personToMedicalRecordDto(medicalRecordService.saveMedicalRecord(medicalRecordDto.getFirstName(),
                    medicalRecordDto.getLastName(),
                    medicalRecordDto.getBirthdate(),
                    medicalRecordDto.getMedications(),
                    medicalRecordDto.getAllergies()));
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e){
            log.error("POST /medicalRecord error:{}",e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("")
    public ResponseEntity<?> updateMedicalRecord(@RequestBody MedicalRecordDto medicalRecordDto) {
        log.info("update medical record");
        try {
            medicalRecordService.personToMedicalRecordDto(medicalRecordService.saveMedicalRecord(medicalRecordDto.getFirstName(),
                    medicalRecordDto.getLastName(),
                    medicalRecordDto.getBirthdate(),
                    medicalRecordDto.getMedications(),
                    medicalRecordDto.getAllergies()));
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e){
            log.error("PUT /medicalRecord error:{}",e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("")
    public void deleteMedicalRecord(@RequestParam("firstname") String firstName,
                                             @RequestParam("lastname") String lastName) {
        medicalRecordService.deleteMedicalRecord(firstName, lastName);
    }
}
