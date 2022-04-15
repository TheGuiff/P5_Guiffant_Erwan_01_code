package com.safetynet.alerts;

import com.safetynet.alerts.dal.json.processing.JsonFileLoader;
import com.safetynet.alerts.dal.json.data.JsonFile;
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
        jsonFile = fileLoader.fileLoader(dataFileTest);
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
        jsonFile = fileLoader.fileLoader("");
        //THEN
        assertNull(jsonFile);
    }
}
