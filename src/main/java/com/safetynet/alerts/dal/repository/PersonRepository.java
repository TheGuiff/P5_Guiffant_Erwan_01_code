package com.safetynet.alerts.dal.repository;

import com.safetynet.alerts.domain.model.Person;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@Setter
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
}
