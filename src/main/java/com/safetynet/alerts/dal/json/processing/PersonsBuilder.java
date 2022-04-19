package com.safetynet.alerts.dal.json.processing;

import com.safetynet.alerts.dal.json.data.JsonFile;
import com.safetynet.alerts.dal.json.data.JsonMedicalRecords;
import com.safetynet.alerts.domain.model.MedicalRecord;
import com.safetynet.alerts.domain.model.Person;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PersonsBuilder {

    public List<Person> getPersons(JsonFile jsonFile) {

        return jsonFile.getPersons().stream()
                .map(temp -> {
                    Person pOut = new Person(temp.getFirstName(),
                            temp.getLastName(),
                            temp.getAddress(),
                            temp.getCity(),
                            temp.getZip(),
                            temp.getPhone(),
                            temp.getEmail());
                    List<JsonMedicalRecords> jsonMR = jsonFile.getMedicalRecords().stream().
                            filter(mr -> Objects.equals(mr.getLastName(), pOut.getLastName()) && Objects.equals(mr.getFirstName(), pOut.getFirstName())).
                            collect(Collectors.toList());
                    pOut.setBirthdate(jsonMR.get(0).getBirthdate());
                    MedicalRecord mrOut = new MedicalRecord();
                    mrOut.setAllergies(jsonMR.get(0).getAllergies());
                    mrOut.setMedications(jsonMR.get(0).getMedications());
                    pOut.setMedicalRecord(mrOut);
                    return pOut;
                })
                .collect(Collectors.toList());

    }
}
