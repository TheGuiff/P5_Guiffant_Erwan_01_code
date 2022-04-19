package com.safetynet.alerts.domain.service;

import com.safetynet.alerts.dal.repository.PersonRepository;
import com.safetynet.alerts.domain.model.Person;
import com.safetynet.alerts.web.dto.PersonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public List<Person> getPersons () {
        return personRepository.findAll();
    }

    public Person getPerson (String firsName, String lastName) {
        Optional<Person> optionalPerson = personRepository.findById(firsName, lastName);
        optionalPerson.orElseThrow(NoSuchElementException::new);
        return optionalPerson.get();
    }

    public void deletePerson(String firstName, String lastName) {
        personRepository.delete(firstName, lastName);
    }

    public Person savePerson (Person person) {
        return personRepository.save(person);
    }

    public PersonDto personToPersonDto (Person person) {
        PersonDto personDto = new PersonDto();
        personDto.setFirstName(person.getFirstName());
        personDto.setLastName(person.getLastName());
        personDto.setBirthdate(person.getBirthdate());
        personDto.setAddress(person.getAddress());
        personDto.setCity(person.getCity());
        personDto.setZip(person.getZip());
        personDto.setEmail(person.getEmail());
        personDto.setPhone(person.getPhone());
        personDto.setMedicalRecord(person.getMedicalRecord());
        return personDto;
    }

    public List<PersonDto> listPersonToPersonDto (List<Person> listPerson) {
        return listPerson.stream().map(this::personToPersonDto).collect(Collectors.toList());
    }

}
