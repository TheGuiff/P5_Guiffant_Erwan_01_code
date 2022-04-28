package com.safetynet.alerts.domain.service;

import com.safetynet.alerts.dal.repository.PersonRepository;
import com.safetynet.alerts.web.dto.PersonInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
public class PersonInfoService {

    @Autowired
    PersonRepository personRepository;

    public PersonInfoDto personInfo (String firstName, String lastName) {
        Optional<PersonInfoDto> optionalPersonInfoDto = personRepository.getListPersons().stream()
                .filter(person -> Objects.equals(person.getFirstName(), firstName) && Objects.equals(person.getLastName(), lastName))
                .map(person -> {
                    PersonInfoDto personInfoDto = new PersonInfoDto();
                    personInfoDto.setFirstName(person.getFirstName());
                    personInfoDto.setLastName(person.getLastName());
                    personInfoDto.setAge(person.getAge());
                    personInfoDto.setEmail(person.getEmail());
                    personInfoDto.setMedications(person.getMedications());
                    personInfoDto.setAllergies(person.getAllergies());
                    return personInfoDto;
                })
                .findFirst();
        return optionalPersonInfoDto.orElseThrow(() -> new NoSuchElementException("Unknown person with this firstname and lastname"));
    }
}
