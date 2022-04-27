package com.safetynet.alerts.web.controller;

import com.safetynet.alerts.domain.service.MedicalRecordService;
import com.safetynet.alerts.web.dto.MedicalRecordDto;
import com.safetynet.alerts.web.dto.PersonDto;
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

    @GetMapping("/{firstName},{lastName}")
    public ResponseEntity<?> getMedicalRecordByFirstNameAndLastName(@PathVariable("firstName") final String firstName,
                                                             @PathVariable("lastName") final String lastName) {
        log.info("get medical record by firstname (" + firstName + ") and lastname (" + lastName + ")");
        try {
            MedicalRecordDto medicalRecordDto = medicalRecordService.getMedicalRecordByFirstNameAndLastName(firstName, lastName);
            return ResponseEntity.ok(medicalRecordDto);
        } catch (NoSuchElementException e) {
            log.error("GET /medicalRecord error:{}",e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<?> addMedicalRecord(@RequestBody MedicalRecordDto medicalRecordDtoIn) {
        log.info("add medical record for " + medicalRecordDtoIn.getFirstName() + " " + medicalRecordDtoIn.getLastName());
        try {
            MedicalRecordDto medicalRecordDtoOut = medicalRecordService.personToMedicalRecordDto(medicalRecordService.saveMedicalRecord(medicalRecordDtoIn.getFirstName(),
                    medicalRecordDtoIn.getLastName(),
                    medicalRecordDtoIn.getBirthdate(),
                    medicalRecordDtoIn.getMedications(),
                    medicalRecordDtoIn.getAllergies()));
            return ResponseEntity.ok(medicalRecordDtoOut);
        } catch (NoSuchElementException e){
            log.error("POST /medicalRecord error:{}",e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("")
    public ResponseEntity<?> updateMedicalRecord(@RequestBody MedicalRecordDto medicalRecordDtoIn) {
        log.info("update medical record for "+ medicalRecordDtoIn.getFirstName() + " " + medicalRecordDtoIn.getLastName());
        try {
             MedicalRecordDto medicalRecordDtoOut = medicalRecordService.personToMedicalRecordDto(medicalRecordService.saveMedicalRecord(medicalRecordDtoIn.getFirstName(),
                    medicalRecordDtoIn.getLastName(),
                    medicalRecordDtoIn.getBirthdate(),
                    medicalRecordDtoIn.getMedications(),
                    medicalRecordDtoIn.getAllergies()));
            return ResponseEntity.ok(medicalRecordDtoOut);
        } catch (NoSuchElementException e){
            log.error("PUT /medicalRecord error:{}",e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteMedicalRecord(@RequestParam("firstName") String firstName,
                                    @RequestParam("lastName") String lastName) {
        log.info("delete medical record for "+ firstName + " " + lastName);
        try {
            medicalRecordService.deleteMedicalRecord(firstName, lastName);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            log.error("DELETE /medicalRecord error:{}",e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
