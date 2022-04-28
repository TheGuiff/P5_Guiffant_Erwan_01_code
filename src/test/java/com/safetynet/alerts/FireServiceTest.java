package com.safetynet.alerts;

import com.safetynet.alerts.dal.repository.PersonRepository;
import com.safetynet.alerts.domain.model.Person;
import com.safetynet.alerts.domain.service.FireService;
import com.safetynet.alerts.domain.service.FireStationService;
import com.safetynet.alerts.web.dto.ListFireDto;
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
public class FireServiceTest {

    @Autowired
    FireService fireService;

    @MockBean
    FireStationService fireStationService;

    @MockBean
    PersonRepository personRepository;

    static final String firsNameTest = "Test";
    static final String lastNameTest1 = "Test1";
    static final String lastNameTest2 = "Test2";
    static final String addressTest1 = "address1";
    static final String cityTest1 = "city1";
    static final String zipTest = "zip";
    static final String phoneTest = "phone";
    static final String emailTest1 = "email1";
    static final String medication = "med:100";
    static final String allergie = "allergie";
    static final String birthdate = "01/01/1980";
    static Person personTest1 = new Person(firsNameTest,lastNameTest1,addressTest1, cityTest1, zipTest, phoneTest, emailTest1);
    static Person personTest2 = new Person(firsNameTest,lastNameTest2,addressTest1, cityTest1, zipTest, phoneTest, emailTest1);
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
        personTest2.setBirthdate(birthdate);
        personTest2.setMedications(medications);
        personTest2.setAllergies(allergies);
        listPersonTest.add(personTest2);
    }

    @Test
    public void fireTest () {
        try {
            when(personRepository.getListPersons()).thenReturn(listPersonTest);
            when(fireStationService.fireStationByAddress(addressTest1)).thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException("Failed to set up test mock objects");
        }
        ListFireDto listFireDto = fireService.fire(addressTest1);
        Assertions.assertEquals(1, listFireDto.getStation());
        Assertions.assertEquals(2, listFireDto.getInhabitantsList().size());
    }

    @Test
    public void fireTestKo () {
        try {
            when(personRepository.getListPersons()).thenReturn(listPersonTest);
            when(fireStationService.fireStationByAddress(addressTest1)).thenThrow(NoSuchElementException.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException("Failed to set up test mock objects");
        }
        Assertions.assertThrows(NoSuchElementException.class,() -> fireService.fire(addressTest1));
    }
}
