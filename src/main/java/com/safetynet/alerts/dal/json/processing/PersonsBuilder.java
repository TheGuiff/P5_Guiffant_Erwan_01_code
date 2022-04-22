package com.safetynet.alerts.dal.json.processing;

import com.safetynet.alerts.dal.json.data.JsonFile;
import com.safetynet.alerts.dal.json.data.JsonMedicalRecords;
import com.safetynet.alerts.domain.model.Person;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
                    Optional<JsonMedicalRecords> optionalJsonMedicalRecords = jsonFile.getMedicalRecords().stream().
                            filter(mr -> Objects.equals(mr.getLastName(), pOut.getLastName()) && Objects.equals(mr.getFirstName(), pOut.getFirstName()))
                            .findFirst();
                    if (optionalJsonMedicalRecords.isPresent()) {
                        pOut.setBirthdate(optionalJsonMedicalRecords.get().getBirthdate());
                        pOut.setMedications(optionalJsonMedicalRecords.get().getMedications());
                        pOut.setAllergies(optionalJsonMedicalRecords.get().getAllergies());
                    }
                    return pOut;
                })
                .collect(Collectors.toList());

    }
}
