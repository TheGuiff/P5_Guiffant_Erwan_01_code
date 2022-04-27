package com.safetynet.alerts.domain.service;

import com.safetynet.alerts.dal.repository.MedicalRecordRepository;
import com.safetynet.alerts.domain.model.Person;
import com.safetynet.alerts.web.dto.MedicalRecordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MedicalRecordService {

    @Autowired
    MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordDto personToMedicalRecordDto (Person person) {
        MedicalRecordDto medicalRecordDto = new MedicalRecordDto();
        medicalRecordDto.setFirstName(person.getFirstName());
        medicalRecordDto.setLastName(person.getLastName());
        medicalRecordDto.setBirthdate(person.getBirthdate());
        medicalRecordDto.setMedications(person.getMedications());
        medicalRecordDto.setAllergies(person.getAllergies());
        return medicalRecordDto;
    }

    public MedicalRecordDto getMedicalRecordByFirstNameAndLastName(String firsName, String lastName) {
        return medicalRecordRepository.findById(firsName, lastName).orElseThrow(() -> new NoSuchElementException("Unknown person with this firstname and lastname"));
    }

    public Person saveMedicalRecord(String firstName, String lastName, String birthdate, List<String> medications, List<String> allergies) {
        return medicalRecordRepository.save(firstName, lastName, birthdate, medications, allergies);
    }

    public void deleteMedicalRecord(String firstName, String lastName) {
        medicalRecordRepository.delete(firstName, lastName);
    }
}
