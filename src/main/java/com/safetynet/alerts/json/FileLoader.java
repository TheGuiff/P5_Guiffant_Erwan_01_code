package com.safetynet.alerts.json;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class FileLoader {

    public JsonFile DataToPersons (String dataFile) {

        InputStream inputStream;
        JsonFile result = null;

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            inputStream = getClass().getResourceAsStream(dataFile);
            result = mapper.readValue(inputStream, JsonFile.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
