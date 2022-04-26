package com.safetynet.alerts.dal.repository;

import com.safetynet.alerts.domain.model.Person;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Component
@Setter
@Getter
public class PersonRepository {

    public List<Person> listPersons;

    public List<Person> findAll() {
        return this.getListPersons();
    }

    public Optional<Person> findById (String firstName, String lastName) {
        return this.listPersons.stream()
                .filter(person -> Objects.equals(person.getFirstName(), firstName) && Objects.equals(person.getLastName(), lastName))
                .findFirst();
    }

    public void delete(String firsName, String lastName) {
        Optional<Person> optionalPerson = findById(firsName, lastName);
        optionalPerson.orElseThrow(() -> new NoSuchElementException("Unknown person with this firstname and lastname"));
        this.listPersons.remove(optionalPerson.get());
    }

    public Person update (Person person) {
        Optional<Person> oldPerson = findById(person.getFirstName(), person.getLastName());
        oldPerson.orElseThrow(() -> new NoSuchElementException("Unknown person with this firstname and lastname"));
        return save(person);
    }

    public Person save (Person person) {
        Optional<Person> oldPerson = findById(person.getFirstName(), person.getLastName());
        if (oldPerson.isPresent()){
            person.setBirthdate(oldPerson.get().getBirthdate());
            person.setMedications(oldPerson.get().getMedications());
            person.setAllergies(oldPerson.get().getAllergies());
            delete(person.getFirstName(), person.getLastName());
        }
        this.listPersons.add(person);
        return person;
    }
}
