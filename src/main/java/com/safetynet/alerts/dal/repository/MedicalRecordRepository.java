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

    public Person saveMedicalRecord(String firstName, String lastName, String birthdate, List<String> medications, List<String> allergies) {
        Optional<Person> optionalPerson = personRepository.findById(firstName, lastName);
        Person person = optionalPerson.orElseThrow(() -> new NoSuchElementException("No person with this firstname and lastname"));
        person.setBirthdate(birthdate);
        person.setMedications(medications);
        person.setAllergies(allergies);
        personRepository.delete(firstName, lastName);
        return personRepository.save(person);
    }

    public void delete(String firstName, String lastName) {
        personRepository.setListPersons(personRepository.getListPersons().stream()
                .peek(person -> {if (Objects.equals(person.getFirstName(), firstName) && Objects.equals(person.getLastName(), lastName)) {
                    person.setBirthdate("");
                    person.setAllergies(null);
                    person.setMedications(null);
                    }
                })
                .collect(Collectors.toList()));
    }
}
