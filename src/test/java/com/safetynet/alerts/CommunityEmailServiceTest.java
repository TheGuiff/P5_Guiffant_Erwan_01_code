package com.safetynet.alerts;

import com.safetynet.alerts.dal.repository.PersonRepository;
import com.safetynet.alerts.domain.model.Person;
import com.safetynet.alerts.domain.service.CommunityEmailService;
import com.safetynet.alerts.web.dto.CommunityEmailDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CommunityEmailServiceTest {

    @Autowired
    CommunityEmailService communityEmailService;

    @MockBean
    PersonRepository personRepository;

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
    public void communityEmailTest () {
        try {
            when(personRepository.getListPersons()).thenReturn(listPersonTest);
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException("Failed to set up test mock objects");
        }
        CommunityEmailDto communityEmailDto = communityEmailService.communityEmail(cityTest2);
        assertEquals(2, communityEmailDto.getListEmail().size());
        assertTrue(communityEmailDto.getListEmail().contains(emailTest2));
        assertTrue(communityEmailDto.getListEmail().contains(emailTest3));
    }
}
