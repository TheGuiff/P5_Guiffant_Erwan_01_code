package com.safetynet.alerts.dal.json;

import com.safetynet.alerts.dal.json.processing.DataLoader;
import com.safetynet.alerts.dal.json.processing.JsonFileLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ContextFileLoader implements CommandLineRunner {

    public static final String dataFile = "/data.json";

    @Autowired
    JsonFileLoader fileLoader;

    @Autowired
    DataLoader dataLoader;

    @Override
    public void run(String... args) {
        dataLoader.jsonToData(fileLoader.fileLoader(dataFile));
        System.out.println("file loaded!");
    }
}

