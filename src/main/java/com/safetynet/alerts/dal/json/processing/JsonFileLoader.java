package com.safetynet.alerts.dal.json.processing;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.dal.json.data.JsonFile;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class JsonFileLoader {

    public JsonFile fileLoader (String dataFile) {

        InputStream inputStream;
        JsonFile jsonFile = null;

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            inputStream = getClass().getResourceAsStream(dataFile);
            jsonFile = mapper.readValue(inputStream, JsonFile.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonFile;
    }
}
