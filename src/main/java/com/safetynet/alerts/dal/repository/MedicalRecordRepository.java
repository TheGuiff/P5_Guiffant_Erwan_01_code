package com.safetynet.alerts.dal.repository;

import com.safetynet.alerts.domain.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MedicalRecordRepository {

    @Autowired
    PersonRepository personRepository;

    public Person save(String firstName, String lastName, String birthdate, List<String> medications, List<String> allergies) {
        Person person;
        Optional<Person> optionalPerson = personRepository.findById(firstName, lastName);
        if (optionalPerson.isPresent()){
            optionalPerson.get().setBirthdate(birthdate);
            personRepository.delete(firstName, lastName);
            person = optionalPerson.get();
        }else {
            person = new Person(firstName, lastName,"","","","","");
        }
        person.setAllergies(allergies);
        person.setMedications(medications);
        person.setBirthdate(birthdate);
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
