package com.safetynet.alerts;

import com.safetynet.alerts.dal.repository.PersonRepository;
import com.safetynet.alerts.domain.model.Person;
import com.safetynet.alerts.domain.service.PersonService;
import com.safetynet.alerts.web.dto.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Autowired
    PersonService personService;

    @MockBean
    private PersonRepository personRepository;

    static final String firsNameTest = "Test";
    static final String lastNameTest1 = "Test1";
    static final String lastNameTest2 = "Test2";
    static final String lastNameTest3 = "Test3;";
    static final String addressTest1 = "address1";
    static final String addressTest2 = "address2";
    static final String cityTest1 = "city1";
    static final String cityTest2 = "city2";
    static final String zipTest = "zip";
    static final String phoneTest = "phone";
    static final String emailTest1 = "email1";
    static final String emailTest2 = "email2";
    static final String emailTest3 = "email3";
    static Person personTest1 = new Person(firsNameTest,lastNameTest1,addressTest1, cityTest1, zipTest, phoneTest, emailTest1);
    static Person personTest2 = new Person(firsNameTest,lastNameTest2,addressTest2, cityTest2, zipTest, phoneTest, emailTest2);
    static Person personTest3 = new Person(firsNameTest,lastNameTest3,addressTest2, cityTest2, zipTest, phoneTest, emailTest3);
    static List<Person> listPersonTest = new ArrayList<>();

    @BeforeAll
    static void listOfPersonsTest () {
        personTest1.setBirthdate(LocalDate.ofYearDay(LocalDate.now().getYear() - 51,1).format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        personTest2.setBirthdate(LocalDate.ofYearDay(LocalDate.now().getYear() - 8,1).format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        personTest3.setBirthdate(LocalDate.ofYearDay(LocalDate.now().getYear() - 35,1).format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        listPersonTest.add(personTest1);
        listPersonTest.add(personTest2);
        listPersonTest.add(personTest3);
    }

    @Test
    public void getListPersonTest () {
        //Given
        try {
            when(personRepository.findAll()).thenReturn(listPersonTest);
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException("Failed to set up test mock objects");
        }
        //WHEN
        List<Person> listPerson = personService.getListPersons();
        //THEN
        assertEquals(3,listPerson.size());
    }

    @Test
    public void getPersonByFirstNameAndLastNameTestOk () {
        //Given
        try {
            when(personRepository.findById(personTest1.getFirstName(),personTest1.getLastName())).thenReturn(Optional.of(personTest1));
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException("Failed to set up test mock objects");
        }
        //WHEN
        Person person = personService.getPersonByFirstNameAndLastName(personTest1.getFirstName(), personTest1.getLastName());
        //THEN
        assertEquals(personTest1.getFirstName(), person.getFirstName());
        assertEquals(personTest1.getLastName(),person.getLastName());
    }

    @Test
    public void getPersonByFirstNameAndLastNameTestKo () {
        //Given
        try {
            when(personRepository.findById(personTest1.getFirstName(),personTest1.getLastName())).thenReturn(Optional.empty());
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException("Failed to set up test mock objects");
        }
        assertThrows(NoSuchElementException.class,()->personService.getPersonByFirstNameAndLastName(personTest1.getFirstName(),
                personTest1.getLastName()));
    }

    @Test
    public void deletePersonTest () {
        personService.deletePerson(personTest1.getFirstName(), personTest1.getLastName());
        verify(personRepository, Mockito.times(1)).delete(personTest1.getFirstName(), personTest1.getLastName());
    }

    @Test
    public void addPersonTest () {
        personService.addPerson(firsNameTest, lastNameTest1, addressTest1, cityTest1, zipTest, phoneTest, emailTest1);
        verify(personRepository, Mockito.times(1)).save(any(Person.class));
    }

    @Test
    public void updatePersonTest () {
        personService.updatePerson(firsNameTest, lastNameTest1, addressTest1, cityTest1, zipTest, phoneTest, emailTest1);
        verify(personRepository, Mockito.times(1)).update(any(Person.class));
    }

    @Test
    public void personToPersonDtoTest() {
        //When
        PersonDto personDto = personService.personToPersonDto(personTest1);
        //Then
        assertEquals(personTest1.getFirstName(),personDto.getFirstName());
        assertEquals(personTest1.getLastName(), personDto.getLastName());
        assertEquals(personTest1.getAddress(), personDto.getAddress());
        assertEquals(personTest1.getCity(), personDto.getCity());
        assertEquals(personTest1.getZip(), personDto.getZip());
        assertEquals(personTest1.getPhone(), personDto.getPhone());
        assertEquals(personTest1.getEmail(), personDto.getEmail());
    }

    @Test
    public void listPersonToPersonDtoTest() {
        List<PersonDto> personDtoList = personService.listPersonToPersonDto(listPersonTest);
        assertEquals(3, personDtoList.size());
    }

    @Test
    public void listPersonsByListAddressesTest() {
        List<String> listAddresses = new ArrayList<>();
        listAddresses.add(addressTest1);
        when(personRepository.getListPersons()).thenReturn(listPersonTest);
        List<Person> personList = personService.listPersonsByListAddresses(listAddresses);
        assertEquals(1,personList.size());
        assertEquals(addressTest1,personList.get(0).getAddress());
    }

    @Test
    public void listPersonsOfAFireStationDtoTest () {
        ListPersonsOfAFireStationDto listPersonsOfAFireStationDto = personService.listPersonsByAFireSation(listPersonTest);
        assertEquals(3, listPersonsOfAFireStationDto.getPersonsCovered().size());
        assertEquals(1,listPersonsOfAFireStationDto.getNumberOfChilds());
        assertEquals(2,listPersonsOfAFireStationDto.getNumberOfAdults());
    }

}
