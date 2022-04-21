package com.safetynet.alerts;

import com.safetynet.alerts.dal.repository.MedicalRecordRepository;
import com.safetynet.alerts.dal.repository.PersonRepository;
import com.safetynet.alerts.domain.model.MedicalRecord;
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
    static final String firstNameTest = "Test";
    static final String lastNameTest = "Test1";
    static List<String> listMedications = new ArrayList<>();
    static List<String> listAllergies = new ArrayList<>();
    static MedicalRecord medicalRecord = new MedicalRecord();

    @BeforeAll
    static void listOfMedicalRecordsForTest () {
        listMedications.add(medicationTest1);
        listAllergies.add(allergieTest1);
        medicalRecord.setMedications(listMedications);
        medicalRecord.setAllergies(listAllergies);
    }

    @Test
    public void deleteMedicalRecordTest () {
        medicalRecordRepository.delete(personRepository.getListPersons().get(0).getFirstName(),
                personRepository.getListPersons().get(0).getLastName());
        Assertions.assertEquals(23, personRepository.getListPersons().size());
        Assertions.assertEquals("",personRepository.getListPersons().get(0).getBirthdate());
        Assertions.assertNull(personRepository.getListPersons().get(0).getMedicalRecord());
    }

    @Test
    public void createMedicalRecordTest () {
        Person person = medicalRecordRepository.save(firstNameTest, lastNameTest, birthdateTest1, listMedications, listAllergies);
        Assertions.assertEquals(24, personRepository.getListPersons().size());
        Assertions.assertEquals(firstNameTest, person.getFirstName());
        Assertions.assertEquals(lastNameTest, person.getLastName());
        Assertions.assertEquals(birthdateTest1, person.getBirthdate());
        Assertions.assertEquals(1, person.getMedicalRecord().getMedications().size());
        Assertions.assertEquals(1, person.getMedicalRecord().getAllergies().size());
        Assertions.assertEquals(medicationTest1, person.getMedicalRecord().getMedications().get(0));
        Assertions.assertEquals(allergieTest1, person.getMedicalRecord().getAllergies().get(0));
    }

    @Test
    public void updateMedicalRecordTest () {
        Person personTest = personRepository.getListPersons().get(0);
        Person person = medicalRecordRepository.save(personTest.getFirstName(), personTest.getLastName(), birthdateTest1, listMedications, listAllergies);
        Assertions.assertEquals(23, personRepository.getListPersons().size());
        Assertions.assertEquals(personTest.getFirstName(), person.getFirstName());
        Assertions.assertEquals(personTest.getLastName(), person.getLastName());
        Assertions.assertEquals(birthdateTest1, person.getBirthdate());
        Assertions.assertEquals(1, person.getMedicalRecord().getMedications().size());
        Assertions.assertEquals(1, person.getMedicalRecord().getAllergies().size());
        Assertions.assertEquals(medicationTest1, person.getMedicalRecord().getMedications().get(0));
        Assertions.assertEquals(allergieTest1, person.getMedicalRecord().getAllergies().get(0));
    }
}
