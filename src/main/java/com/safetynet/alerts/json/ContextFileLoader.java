package com.safetynet.alerts.json;

import com.safetynet.alerts.json.entity.FileConstants;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ContextFileLoader implements CommandLineRunner {

    JsonFileLoader fileLoader = new JsonFileLoader();
    DataLoader dataLoader;

    @Override
    public void run(String... args) {
        dataLoader.JsonToData(fileLoader.DataToPersons(FileConstants.dataFile));
        System.out.println("file loaded!");
    }

    }