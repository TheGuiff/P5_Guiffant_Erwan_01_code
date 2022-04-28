package com.safetynet.alerts;

import com.safetynet.alerts.dal.repository.PersonRepository;
import com.safetynet.alerts.domain.model.Person;
import com.safetynet.alerts.domain.service.PersonInfoService;
import com.safetynet.alerts.web.dto.PersonInfoDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PersonInfoServiceTest {

    @Autowired
    PersonInfoService personInfoService;

    @MockBean
    private PersonRepository personRepository;

    static final String firsNameTest = "Test";
    static final String lastNameTest1 = "Test1";
    static final String addressTest1 = "address1";
    static final String cityTest1 = "city1";
    static final String zipTest = "zip";
    static final String phoneTest = "phone";
    static final String emailTest1 = "email1";
    static final String medication = "med:100";
    static final String allergie = "allergie";
    static final String birthdate = "01/01/1980";
    static Person personTest1 = new Person(firsNameTest,lastNameTest1,addressTest1, cityTest1, zipTest, phoneTest, emailTest1);
    static List<Person> listPersonTest = new ArrayList<>();

    @BeforeAll
    static void beforePersonInfoTests() {
        personTest1.setBirthdate(birthdate);
        List<String> medications = new ArrayList<>();
        medications.add(medication);
        personTest1.setMedications(medications);
        List<String> allergies = new ArrayList<>();
        allergies.add(allergie);
        personTest1.setAllergies(allergies);
        listPersonTest.add(personTest1);
    }

    @Test
    public void personInfoTest () {
        try {
            when(personRepository.getListPersons()).thenReturn(listPersonTest);
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException("Failed to set up test mock objects");
        }
        PersonInfoDto personInfoDto = personInfoService.personInfo(firsNameTest, lastNameTest1);
        Assertions.assertEquals(firsNameTest, personInfoDto.getFirstName());
        Assertions.assertEquals(lastNameTest1,personInfoDto.getLastName());
        Assertions.assertEquals(personTest1.getAge(), personInfoDto.getAge());
        Assertions.assertEquals(1, personInfoDto.getMedications().size());
        Assertions.assertEquals(1, personInfoDto.getAllergies().size());
    }

    @Test
    public void personInfoTestKo () {
        try {
            when(personRepository.getListPersons()).thenReturn(listPersonTest);
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException("Failed to set up test mock objects");
        }
        Assertions.assertThrows(NoSuchElementException.class,() -> personInfoService.personInfo(firsNameTest, lastNameTest1+"KO"));
    }
}
