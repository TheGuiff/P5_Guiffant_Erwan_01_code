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
        return this.listPersons;
    }

    public Optional<Person> findById (String firstName, String lastName) {
        return this.listPersons.stream()
                .filter(person -> Objects.equals(person.getFirstName(), firstName) && Objects.equals(person.getLastName(), lastName))
                .findFirst();
    }

    public void delete(String firsName, String lastName) {
        Optional<Person> optionalPerson = findById(firsName, lastName);
        optionalPerson.orElseThrow(NoSuchElementException::new);
        this.listPersons.remove(optionalPerson.get());
    }

    public Person save (Person person) {
        if (findById(person.getFirstName(), person.getLastName()).isPresent()) {
            delete(person.getFirstName(), person.getLastName());
        }
        this.listPersons.add(person);
        return person;
    }
}
