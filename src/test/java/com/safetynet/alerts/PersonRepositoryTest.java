package com.safetynet.alerts;

import com.safetynet.alerts.dal.repository.PersonRepository;
import com.safetynet.alerts.domain.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;


@SpringBootTest
public class PersonRepositoryTest {

    @Autowired
    PersonRepository personRepository;

    private final String firstNameTest = "firstname test";
    private final String lastNameTest = "lastname test";
    private final String addressTest = "address test";
    private final String cityTest = "city test";
    private final String zipTest = "zip test";
    private final String phoneTest = "phone test";
    private final String emailTest = "email test";

    @Test
    public void listPersonsTest () {
        //GIVEN WHEN
        List<Person> listPerson = personRepository.findAll();
        //THEN
        Assertions.assertEquals(personRepository.getListPersons().size(), listPerson.size());
    }

    @Test
    public void personByFirstNameAndLastNameTestOk () {
        Assertions.assertTrue(personRepository.findById(personRepository.getListPersons().get(0).getFirstName(),
                personRepository.getListPersons().get(0).getLastName()).isPresent());
    }

    @Test
    public void personByFirstNameAndLastNameTestKo () {
        Assertions.assertFalse(personRepository.findById("Test","TestKO").isPresent());
    }

    @Test
    public void deletePersonTestOk () {
        String firstNameToDelete = personRepository.getListPersons().get(0).getFirstName();
        String lastNameToDelete = personRepository.getListPersons().get(0).getLastName();
        int nbPersonsBeforeDelete = personRepository.getListPersons().size();
        personRepository.delete(firstNameToDelete, lastNameToDelete);
        Assertions.assertEquals(nbPersonsBeforeDelete-1, personRepository.getListPersons().size());
    }

    @Test
    public void deletePersonTestKo () {
        String firstNameToDelete = personRepository.getListPersons().get(0).getFirstName();
        String lastNameToDelete = personRepository.getListPersons().get(0).getLastName() + "_Test";
        int nbPersonsBeforeDelete = personRepository.getListPersons().size();
        Assertions.assertThrows(NoSuchElementException.class,() ->personRepository.delete(firstNameToDelete, lastNameToDelete));
        Assertions.assertEquals(nbPersonsBeforeDelete, personRepository.getListPersons().size());
    }

    @Test
    public void updatePersonTestKo () {
        Person personToUpdate = new Person(firstNameTest, lastNameTest, addressTest, cityTest, zipTest, phoneTest, emailTest);
        Assertions.assertThrows(NoSuchElementException.class,() ->personRepository.update(personToUpdate));
    }

    @Test
    public void updatePersonTestOk () {
        Person personToUpdate = personRepository.getListPersons().get(0);
        personToUpdate.setPhone(phoneTest);
        Person updatedPerson = personRepository.update(personToUpdate);
        Assertions.assertEquals(personToUpdate.getFirstName(), updatedPerson.getFirstName());
        Assertions.assertEquals(personToUpdate.getLastName(), updatedPerson.getLastName());
        Assertions.assertEquals(personToUpdate.getPhone(), updatedPerson.getPhone());
    }

    @Test
    public void savePersonTest () {
        //GIVEN
        int nbPersonsBeforeAdd = personRepository.getListPersons().size();
        Person person = new Person(firstNameTest, lastNameTest, addressTest, cityTest, zipTest, phoneTest, emailTest);
        //WHEN
        personRepository.save(person);
        //THEN
        Assertions.assertEquals(nbPersonsBeforeAdd + 1, personRepository.getListPersons().size());
        Assertions.assertTrue(personRepository.getListPersons().stream()
                .anyMatch(p -> Objects.equals(p.getFirstName(), person.getFirstName())
                        && Objects.equals(p.getLastName(), person.getLastName())
                        && Objects.equals(p.getZip(), person.getZip())));
    }

}
