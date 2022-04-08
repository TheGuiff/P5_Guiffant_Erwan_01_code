package com.safetynet.alerts;

import com.safetynet.alerts.json.FileLoader;
import com.safetynet.alerts.json.JsonFile;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FileLoaderTest {

    FileLoader fileLoader = new FileLoader();
    final String dataFileTest = "/dataTest.json";

    @Test
    public void loadFileIsOkOnGoodFile () {
        //GIVEN
        JsonFile jsonFile;
        //WHEN
        jsonFile = fileLoader.DataToPersons(dataFileTest);
        //THEN
        assertNotNull(jsonFile);
        assertNotNull(jsonFile.getPersons());
        assertNotNull(jsonFile.getFirestations());
        assertNotNull(jsonFile.getMedicalRecords());
        assertEquals(3,jsonFile.getPersons().size());
        assertEquals("3",jsonFile.getFirestations().get(0).getStation());
        assertEquals("Zemicks",jsonFile.getPersons().get(2).getLastName());
        assertEquals("03/06/1984",jsonFile.getMedicalRecords().get(0).getBirthdate());
    }

    @Test
    public void loadFileIsKoWhenNoFile () {
        //GIVEN
        JsonFile jsonFile;
        //WHEN
        jsonFile = fileLoader.DataToPersons("");
        //THEN
        assertNull(jsonFile);
    }
}
