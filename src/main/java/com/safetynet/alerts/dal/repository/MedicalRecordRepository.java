package com.safetynet.alerts.dal.repository;

import com.safetynet.alerts.domain.model.Person;
import com.safetynet.alerts.web.dto.MedicalRecordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MedicalRecordRepository {

    @Autowired
    PersonRepository personRepository;

    public Optional<MedicalRecordDto> findById (String firstName, String lastName) {
        return personRepository.getListPersons().stream()
                .filter(person -> Objects.equals(person.getFirstName(), firstName) && Objects.equals(person.getLastName(), lastName))
                .map(person -> {
                    MedicalRecordDto medicalRecordDto = new MedicalRecordDto();
                    medicalRecordDto.setFirstName(person.getFirstName());
                    medicalRecordDto.setLastName(person.getLastName());
                    medicalRecordDto.setBirthdate(person.getBirthdate());
                    medicalRecordDto.setMedications(person.getMedications());
                    medicalRecordDto.setAllergies(person.getAllergies());
                    return medicalRecordDto;
                })
                .findFirst();
    }

    public Person save(String firstName, String lastName, String birthdate, List<String> medications, List<String> allergies) {
        personRepository.setListPersons(personRepository.getListPersons().stream()
                .peek(p -> {if (Objects.equals(p.getFirstName(), firstName) && Objects.equals(p.getLastName(), lastName)) {
                    p.setBirthdate(birthdate);
                    p.setAllergies(allergies);
                    p.setMedications(medications);
                }
                })
                .collect(Collectors.toList()));
        Optional<Person> person = personRepository.findById(firstName, lastName);
        person.orElseThrow(() -> new NoSuchElementException(("Unknown person for creation ou update medical record")));
        return person.get();
    }

    public void delete(String firstName, String lastName) {
        personRepository.findById(firstName, lastName).orElseThrow(() -> new NoSuchElementException(("Unknown person for delete medical record")));
        personRepository.setListPersons(personRepository.getListPersons().stream()
                .peek(p -> {if (Objects.equals(p.getFirstName(), firstName) && Objects.equals(p.getLastName(), lastName)) {
                    p.setBirthdate("");
                    p.setAllergies(null);
                    p.setMedications(null);
                    }
                })
                .collect(Collectors.toList()));
    }
}
