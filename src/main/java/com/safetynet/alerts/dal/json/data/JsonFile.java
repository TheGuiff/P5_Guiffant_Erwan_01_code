package com.safetynet.alerts.dal.json.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JsonFile {

    public List<JsonPerson> persons;
    public List<JsonFireStation> firestations;
    @JsonProperty("medicalrecords")
    public List<JsonMedicalRecords> medicalRecords;

}
