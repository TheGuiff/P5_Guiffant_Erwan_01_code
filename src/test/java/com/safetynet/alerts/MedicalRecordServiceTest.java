package com.safetynet.alerts;

import com.safetynet.alerts.dal.repository.MedicalRecordRepository;
import com.safetynet.alerts.domain.model.Person;
import com.safetynet.alerts.domain.service.MedicalRecordService;
import com.safetynet.alerts.web.dto.MedicalRecordDto;
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
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class MedicalRecordServiceTest {

    @Autowired
    MedicalRecordService medicalRecordService;

    @MockBean
    MedicalRecordRepository medicalRecordRepository;

    static final String firsNameTest = "Test";
    static final String lastNameTest1 = "Test1";
    static final String lastNameTest2 = "Test2";
    static final String lastNameTest3 = "Test3";
    static final String addressTest = "address";
    static final String cityTest = "city";
    static final String zipTest = "zip";
    static final String phoneTest = "phone";
    static final String emailTest = "email";
    static final String birthdateTest1 = "birthdate";
    static final String medicationTest1 = "medication";
    static final String allergieTest1 = "allergie";
    static Person personTest1 = new Person(firsNameTest,lastNameTest1,addressTest, cityTest, zipTest, phoneTest, emailTest);
    static Person personTest2 = new Person(firsNameTest,lastNameTest2,addressTest, cityTest, zipTest, phoneTest, emailTest);
    static List<Person> listPerson = new ArrayList<>();
    static List<String> listMedications = new ArrayList<>();
    static List<String> listAllergies = new ArrayList<>();
    static MedicalRecordDto medicalRecordTest = new MedicalRecordDto();

    @BeforeAll
    static void listOfPersonsTest () {
        personTest1.setBirthdate(birthdateTest1);
        personTest2.setBirthdate(birthdateTest1);
        listMedications.add(medicationTest1);
        listAllergies.add(allergieTest1);
        personTest1.setMedications(listMedications);
        personTest1.setAllergies(listAllergies);
        personTest2.setAllergies(listAllergies);
        personTest2.setMedications(listMedications);
        listPerson.add(personTest1);
        listPerson.add(personTest2);
        medicalRecordTest.setFirstName(firsNameTest);
        medicalRecordTest.setLastName(lastNameTest1);
        medicalRecordTest.setBirthdate(birthdateTest1);
        medicalRecordTest.setMedications(listMedications);
        medicalRecordTest.setAllergies(listAllergies);
    }

    @Test
    public void getMedicalRecordByFirstNameAndLastNameTestOk () {
        //Given
        try {
            when(medicalRecordRepository.findById(firsNameTest, lastNameTest1)).thenReturn(Optional.of(medicalRecordTest));
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException("Failed to set up test mock objects");
        }
        //WHEN
        MedicalRecordDto medicalRecordDto = medicalRecordService.getMedicalRecordByFirstNameAndLastName(firsNameTest, lastNameTest1);
        //THEN
        assertEquals(firsNameTest, medicalRecordDto.getFirstName());
        assertEquals(lastNameTest1,medicalRecordDto.getLastName());
    }

    @Test
    public void getMedicalRecordByFirstNameAndLastNameTestKo () {
        try {
            when(medicalRecordRepository.findById(firsNameTest, lastNameTest1)).thenReturn(Optional.empty());
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException("Failed to set up test mock objects");
        }
        assertThrows(NoSuchElementException.class,()->medicalRecordService.getMedicalRecordByFirstNameAndLastName(firsNameTest, lastNameTest1));
    }

    @Test
    public void personToMedicalRecordDtoTest() {
        //When
        MedicalRecordDto medicalRecordDto = medicalRecordService.personToMedicalRecordDto(personTest1);
        //Then
        assertEquals(personTest1.getFirstName(),medicalRecordDto.getFirstName());
        assertEquals(personTest1.getLastName(), medicalRecordDto.getLastName());
        assertEquals(personTest1.getBirthdate(), medicalRecordDto.getBirthdate());
        assertEquals(1, medicalRecordDto.getMedications().size());
        assertEquals(1, medicalRecordDto.getAllergies().size());
        assertEquals(medicationTest1, medicalRecordDto.getMedications().get(0));
        assertEquals(allergieTest1, medicalRecordDto.getAllergies().get(0));
    }

    @Test
    public void deleteMedicalRecordTest () {
        medicalRecordService.deleteMedicalRecord(personTest1.getFirstName(), personTest1.getLastName());
        verify(medicalRecordRepository, Mockito.times(1)).delete(personTest1.getFirstName(), personTest1.getLastName());
    }

    @Test
    public void saveMedicalRecordTest () {
        medicalRecordService.saveMedicalRecord(firsNameTest, lastNameTest3,birthdateTest1,listMedications,listAllergies);
        verify(medicalRecordRepository, Mockito.times(1)).save(firsNameTest,lastNameTest3,birthdateTest1,listMedications,listAllergies);
    }
}
