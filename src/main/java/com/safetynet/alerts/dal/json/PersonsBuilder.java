package com.safetynet.alerts.dal.json;

import com.safetynet.alerts.domain.model.MedicalRecord;
import com.safetynet.alerts.domain.model.Person;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PersonsBuilder {

    public List<Person> GetPersons (JsonFile jsonFile) {

        return jsonFile.getPersons().stream()
                .map(temp -> {
                    Person pOut = new Person();
                    MedicalRecord mrOut = new MedicalRecord();
                    pOut.setFirstName(temp.getFirstName());
                    pOut.setLastName(temp.getLastName());
                    pOut.setAddress(temp.getAddress());
                    pOut.setCity(temp.getCity());
                    pOut.setEmail(temp.getEmail());
                    pOut.setZip(temp.getZip());
                    pOut.setPhone(temp.getPhone());
                    List<JsonMedicalRecords> jsonMR = jsonFile.getMedicalRecords().stream().
                            filter(mr -> Objects.equals(mr.getLastName(), pOut.getLastName()) && Objects.equals(mr.getFirstName(), pOut.getFirstName())).
                            collect(Collectors.toList());
                    pOut.setBirthdate(jsonMR.get(0).getBirthdate());
                    mrOut.setAllergies(jsonMR.get(0).getAllergies());
                    mrOut.setMedications(jsonMR.get(0).getMedications());
                    pOut.setMedicalRecord(mrOut);
                    return pOut;
                })
                .collect(Collectors.toList());

    }
}
