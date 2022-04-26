package com.safetynet.alerts.dal.repository;

import com.safetynet.alerts.domain.model.Person;
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

    public Person save(String firstName, String lastName, String birthdate, List<String> medications, List<String> allergies) {
        Optional<Person> person = personRepository.findById(firstName, lastName);
        person.orElseThrow(() -> new NoSuchElementException(("Unknown person for creation ou update medical record")));
        personRepository.setListPersons(personRepository.getListPersons().stream()
                .peek(p -> {if (Objects.equals(p.getFirstName(), firstName) && Objects.equals(p.getLastName(), lastName)) {
                    p.setBirthdate(person.get().getBirthdate());
                    p.setAllergies(person.get().getAllergies());
                    p.setMedications(person.get().getMedications());
                }
                })
                .collect(Collectors.toList()));
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
