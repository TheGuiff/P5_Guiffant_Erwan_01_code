package com.safetynet.alerts;

import com.safetynet.alerts.dal.repository.PersonRepository;
import com.safetynet.alerts.domain.model.Person;
import com.safetynet.alerts.domain.service.PersonService;
import com.safetynet.alerts.web.dto.PersonDto;
import net.bytebuddy.dynamic.DynamicType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    static final Person personTest1 = new Person("Test","Test1","","","","","");
    static final Person personTest2 = new Person("Test","Test2","","","","","");
    static final Person personTestSave = new Person("Test","Test3","","","","","");
    static List<Person> listPerson = new ArrayList<>();

    @BeforeAll
    static void listOfPersonsTest () {
        listPerson.add(personTest1);
        listPerson.add(personTest2);
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
        List<PersonDto> personDtoList = personService.listPersonToPersonDto(listPerson);
        assertEquals(2, personDtoList.size());
    }

    @Test
    public void getListPersonOk () {
        //Given
        try {
            when(personRepository.findAll()).thenReturn(listPerson);
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException("Failed to set up test mock objects");
        }
        //WHEN
        List<Person> listPerson = personService.getPersons();
        //THEN
        assertEquals(2,listPerson.size());
    }

    @Test
    public void getPersonByIdOk () {
        //Given
        try {
            when(personRepository.findById(personTest1.getFirstName(),personTest1.getLastName())).thenReturn(Optional.of(personTest1));
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException("Failed to set up test mock objects");
        }
        //WHEN
        Person person = personService.getPerson(personTest1.getFirstName(), personTest1.getLastName());
        //THEN
        assertEquals(personTest1.getFirstName(), person.getFirstName());
        assertEquals(personTest1.getLastName(),person.getLastName());
    }

    @Test
    public void deletePersonOk () {
        personService.deletePerson(personTest1.getFirstName(), personTest1.getLastName());
        verify(personRepository, Mockito.times(1)).delete(personTestSave.getFirstName(), personTestSave.getLastName());
    }

    @Test
    public void savePersonOk () {
        personService.savePerson(personTestSave);
        verify(personRepository, Mockito.times((1))).save(personTestSave);
    }

}
