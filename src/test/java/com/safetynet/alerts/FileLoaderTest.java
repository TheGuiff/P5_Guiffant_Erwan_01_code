package com.safetynet.alerts;

import com.safetynet.alerts.json.JsonFileLoader;
import com.safetynet.alerts.json.entity.JsonFile;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FileLoaderTest {

    JsonFileLoader fileLoader = new JsonFileLoader();
    final String dataFileTest = "/dataTest.json";

    @Test
    public void loadFileIsOkOnGoodFile () {
        //GIVEN
        JsonFile jsonFile;
        //WHEN
        jsonFile = fileLoader.DataToPersons(dataFileTest);
        //THEN
        assertNotNull(jsonFile);
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
