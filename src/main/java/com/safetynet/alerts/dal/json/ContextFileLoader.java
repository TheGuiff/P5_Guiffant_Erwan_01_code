package com.safetynet.alerts.dal.json;

import com.safetynet.alerts.dal.data.FileConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ContextFileLoader implements CommandLineRunner {

    @Autowired
    JsonFileLoader fileLoader;

    @Autowired
    DataLoader dataLoader;

    @Override
    public void run(String... args) {
        dataLoader.JsonToData(fileLoader.DataToPersons(FileConstants.dataFile));
        System.out.println("file loaded!");
    }
}

