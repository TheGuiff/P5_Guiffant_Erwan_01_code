package com.safetynet.alerts.json;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ContextFileLoader implements CommandLineRunner {

    @Autowired
    FileLoader fileLoader;

    @Override
    public void run(String... args) {
        JsonFile jsonFile = fileLoader.DataToPersons(FileConstants.dataFile);
        System.out.println("file loaded!");
    }

    }