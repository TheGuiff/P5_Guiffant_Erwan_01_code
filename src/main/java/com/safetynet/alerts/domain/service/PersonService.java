package com.safetynet.alerts.domain.service;

import com.safetynet.alerts.dal.repository.PersonRepository;
import com.safetynet.alerts.domain.model.Person;
import com.safetynet.alerts.web.dto.ListPersonsOfAFireStationDto;
import com.safetynet.alerts.web.dto.PersonDto;
import com.safetynet.alerts.web.dto.PersonsCoveredByAFireStationDto;
import com.safetynet.alerts.web.dto.PhoneAlertDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public List<Person> getListPersons() {
        return personRepository.findAll();
    }

    public Person getPersonByFirstNameAndLastName(String firsName, String lastName) {
        return personRepository.findById(firsName, lastName).orElseThrow(() -> new NoSuchElementException("Unknown person with this firstname and lastname"));
    }

    public void deletePerson(String firstName, String lastName) {
        personRepository.delete(firstName, lastName);
    }

    public Person addPerson (String firstName,
                              String lastName,
                              String address,
                              String city,
                              String zip,
                              String phone,
                              String email) {
        return personRepository.save(new Person(firstName, lastName, address, city, zip, phone, email));
    }

    public Person updatePerson (String firstName,
                             String lastName,
                             String address,
                             String city,
                             String zip,
                             String phone,
                             String email) {
        return personRepository.update(new Person(firstName, lastName, address, city, zip, phone, email));
    }

    public PersonDto personToPersonDto (Person person) {
        PersonDto personDto = new PersonDto();
        personDto.setFirstName(person.getFirstName());
        personDto.setLastName(person.getLastName());
        personDto.setAddress(person.getAddress());
        personDto.setCity(person.getCity());
        personDto.setZip(person.getZip());
        personDto.setEmail(person.getEmail());
        personDto.setPhone(person.getPhone());
        return personDto;
    }

    public List<PersonDto> listPersonToPersonDto (List<Person> listPerson) {
        return listPerson.stream().map(this::personToPersonDto).collect(Collectors.toList());
    }

    public PhoneAlertDto listPersonToPhoneAlertDto(List<Person> listPerson) {
        PhoneAlertDto phoneAlertDto = new PhoneAlertDto();
        phoneAlertDto.setListPhones(listPerson.stream().map(Person::getPhone).collect(Collectors.toList()));
        return phoneAlertDto;
    }

    public List<Person> listPersonsByListAddresses (List<String> addresses) {
        return personRepository.getListPersons().stream()
                .filter(person -> addresses.contains(person.getAddress()))
                .collect(Collectors.toList());
    }

    public ListPersonsOfAFireStationDto listPersonsByAFireSation(List<Person> personList) {
        ListPersonsOfAFireStationDto listPersonsOfAFireStationDto = new ListPersonsOfAFireStationDto();
        listPersonsOfAFireStationDto.setNumberOfAdults(personList.stream()
                .filter(p -> p.getAge() > 18)
                .count());
        listPersonsOfAFireStationDto.setNumberOfChilds(personList.size()-listPersonsOfAFireStationDto.getNumberOfAdults());
        listPersonsOfAFireStationDto.setPersonsCovered(personList.stream()
                .map(person -> {
                PersonsCoveredByAFireStationDto personsCoveredByAFireStationDto = new PersonsCoveredByAFireStationDto();
                personsCoveredByAFireStationDto.setFirstName(person.getFirstName());
                personsCoveredByAFireStationDto.setLastName(person.getLastName());
                personsCoveredByAFireStationDto.setAddress(person.getAddress());
                personsCoveredByAFireStationDto.setPhone(person.getPhone());
                return personsCoveredByAFireStationDto;})
                .collect(Collectors.toList()));
        return listPersonsOfAFireStationDto;
    }

}
