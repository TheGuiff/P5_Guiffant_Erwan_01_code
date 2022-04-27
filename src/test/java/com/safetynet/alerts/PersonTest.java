package com.safetynet.alerts;

import com.safetynet.alerts.domain.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class PersonTest {

    static Person personTest;

    @Test
    public void getAgeTest() {
        personTest = new Person("test","test","","","","","");
        personTest.setBirthdate("08/13/1970");
        long ageTest = personTest.getAge();
        Assertions.assertEquals(51, ageTest);
    }
}
