package com.safetynet.alerts;

import com.safetynet.alerts.dal.repository.PersonRepository;
import com.safetynet.alerts.domain.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Objects;


@SpringBootTest
public class PersonRepositoryTest {

    @Autowired
    PersonRepository personRepository;

    @Test
    public void testNumberOfPersonsInListPerson () {
        //GIVEN WHEN
        List<Person> listPerson = personRepository.findAll();
        //THEN
        Assertions.assertEquals(23, listPerson.size());
    }

    @Test
    public void testPersonOfListIsOk () {
        Assertions.assertTrue(personRepository.findById("John","Boyd").isPresent());
    }

    @Test
    public void testPersonNotInListIsKo () {
        Assertions.assertFalse(personRepository.findById("Test","KO").isPresent());
    }

    @Test
    public void testDeletePerson () {
        personRepository.delete("John","Boyd");
        Assertions.assertEquals(22, personRepository.getListPersons().size());
    }

    @Test
    public void testCreatePerson () {
        //GIVEN
        Person person = new Person("Test","OK","22 rue du test",
                "Paris","75012","0606060606","email@test.com");
        //WHEN
        personRepository.save(person);
        //THEN
        Assertions.assertEquals(24, personRepository.getListPersons().size());
    }

    @Test
    public void testUpdatePerson () {
        //GIVEN
        Person person = new Person("John","Boyd","1509 Culver St",
                "Culvert","99999","841-874-6512","jaboyd@email.com");
        //WHEN
        personRepository.save(person);
        //THEN
        Assertions.assertEquals(23, personRepository.getListPersons().size());
        Assertions.assertTrue(personRepository.getListPersons().stream()
                .anyMatch(p -> Objects.equals(p.getFirstName(), person.getFirstName())
                        && Objects.equals(p.getLastName(), person.getLastName())
                        && Objects.equals(p.getZip(), person.getZip())));
    }

}
