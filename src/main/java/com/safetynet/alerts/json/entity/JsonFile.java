package com.safetynet.alerts.json.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.safetynet.alerts.json.JsonMedicalRecords;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JsonFile {

    public List<JsonPerson> persons;
    public List<JsonFirestations> firestations;
    @JsonProperty("medicalrecords")
    public List<JsonMedicalRecords> medicalRecords;

}
