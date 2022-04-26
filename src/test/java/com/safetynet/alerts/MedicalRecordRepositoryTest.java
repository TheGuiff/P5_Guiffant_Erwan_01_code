package com.safetynet.alerts;

import com.safetynet.alerts.dal.repository.MedicalRecordRepository;
import com.safetynet.alerts.dal.repository.PersonRepository;
import com.safetynet.alerts.domain.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
public class MedicalRecordRepositoryTest {

    @Autowired
    MedicalRecordRepository medicalRecordRepository;

    @Autowired
    PersonRepository personRepository;

    static final String birthdateTest1 = "birthdate";
    static final String medicationTest1 = "medication";
    static final String allergieTest1 = "allergie";
    static List<String> listMedications = new ArrayList<>();
    static List<String> listAllergies = new ArrayList<>();
    static int numberOfPersonsBeforeTest;

    @BeforeAll
    static void listOfMedicalRecordsForTest () {
        listMedications.add(medicationTest1);
        listAllergies.add(allergieTest1);
    }

    @Test
    public void deleteMedicalRecordTest () {
        numberOfPersonsBeforeTest = personRepository.getListPersons().size();
        medicalRecordRepository.delete(personRepository.getListPersons().get(0).getFirstName(),
                personRepository.getListPersons().get(0).getLastName());
        Assertions.assertEquals(numberOfPersonsBeforeTest, personRepository.getListPersons().size());
        Assertions.assertEquals("",personRepository.getListPersons().get(0).getBirthdate());
        Assertions.assertNull(personRepository.getListPersons().get(0).getMedications());
        Assertions.assertNull(personRepository.getListPersons().get(0).getAllergies());
    }

    @Test
    public void createMedicalRecordTest () {
        numberOfPersonsBeforeTest = personRepository.getListPersons().size();
        Person person = medicalRecordRepository.save(personRepository.getListPersons().get(0).getFirstName(),
                personRepository.getListPersons().get(0).getLastName(), birthdateTest1, listMedications, listAllergies);
        Assertions.assertEquals(numberOfPersonsBeforeTest, personRepository.getListPersons().size());
        Assertions.assertEquals(personRepository.getListPersons().get(0).getFirstName(), person.getFirstName());
        Assertions.assertEquals(personRepository.getListPersons().get(0).getLastName(), person.getLastName());
        Assertions.assertEquals(birthdateTest1, person.getBirthdate());
        Assertions.assertEquals(1, person.getMedications().size());
        Assertions.assertEquals(1, person.getAllergies().size());
        Assertions.assertEquals(medicationTest1, person.getMedications().get(0));
        Assertions.assertEquals(allergieTest1, person.getAllergies().get(0));
    }

    @Test
    public void updateMedicalRecordTest () {
        numberOfPersonsBeforeTest = personRepository.getListPersons().size();
        Person personTest = personRepository.getListPersons().get(0);
        Person person = medicalRecordRepository.save(personTest.getFirstName(), personTest.getLastName(), birthdateTest1, listMedications, listAllergies);
        Assertions.assertEquals(numberOfPersonsBeforeTest, personRepository.getListPersons().size());
        Assertions.assertEquals(personTest.getFirstName(), person.getFirstName());
        Assertions.assertEquals(personTest.getLastName(), person.getLastName());
        Assertions.assertEquals(birthdateTest1, person.getBirthdate());
        Assertions.assertEquals(1, person.getMedications().size());
        Assertions.assertEquals(1, person.getAllergies().size());
        Assertions.assertEquals(medicationTest1, person.getMedications().get(0));
        Assertions.assertEquals(allergieTest1, person.getAllergies().get(0));
    }
}
