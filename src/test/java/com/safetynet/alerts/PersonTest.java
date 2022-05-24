package com.safetynet.alerts;

import com.safetynet.alerts.domain.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@SpringBootTest
public class PersonTest {

    static Person personTest;

    @Test
    public void getAgeTest() {
        personTest = new Person("test","test","","","","","");
        personTest.setBirthdate(LocalDate.ofYearDay(LocalDate.now().getYear() - 51,1).format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        long ageTest = personTest.getAge();
        Assertions.assertEquals(51, ageTest);
    }
}
