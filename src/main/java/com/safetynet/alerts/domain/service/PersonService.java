package com.safetynet.alerts.domain.service;

import com.safetynet.alerts.dal.repository.PersonRepository;
import com.safetynet.alerts.web.dto.PersonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public List<PersonDto> getPersons () {
        return personRepository.findAll().stream().map(p -> {
            PersonDto personDto = new PersonDto();
            personDto.setFirstName(p.getFirstName());
            personDto.setLastName(p.getLastName());
            personDto.setBirthdate(p.getBirthdate());
            personDto.setAddress(p.getAddress());
            personDto.setCity(p.getCity());
            personDto.setZip(p.getZip());
            personDto.setEmail(p.getEmail());
            personDto.setPhone(p.getPhone());
            personDto.setMedicalRecord(p.getMedicalRecord());
            return personDto;
        }).collect(Collectors.toList());
    }

    public PersonDto getPerson (String firsName, String lastName) {
        return personRepository.findAll().stream()
                .filter(p -> Objects.equals(p.getFirstName(), firsName) && Objects.equals(p.getLastName(), lastName))
                .map(p -> {
                    PersonDto personDto = new PersonDto();
                    personDto.setFirstName(p.getFirstName());
                    personDto.setLastName(p.getLastName());
                    personDto.setBirthdate(p.getBirthdate());
                    personDto.setAddress(p.getAddress());
                    personDto.setCity(p.getCity());
                    personDto.setZip(p.getZip());
                    personDto.setEmail(p.getEmail());
                    personDto.setPhone(p.getPhone());
                    personDto.setMedicalRecord(p.getMedicalRecord());
                    return personDto;
                })
                .collect(Collectors.toList()).get(0);
    }

}
