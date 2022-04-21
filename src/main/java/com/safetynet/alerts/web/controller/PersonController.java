package com.safetynet.alerts.web.controller;

import com.safetynet.alerts.web.dto.PersonDto;
import com.safetynet.alerts.domain.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("/person")
    public List<PersonDto> getPersons() {
        return personService.listPersonToPersonDto(personService.getPersons());
    }

    @GetMapping("/person/{firstName},{lastName}")
    public PersonDto getPerson(@PathVariable("firstName") final String firstName,
                               @PathVariable("lastName") final String lastName) {
        return personService.personToPersonDto(personService.getPerson(firstName, lastName));
    }

    @DeleteMapping("/person/{firstName},{lastName}")
    public void deletePerson(@PathVariable("firstName") final String firstName,
                             @PathVariable("lastName") final String lastName) {
        personService.deletePerson(firstName, lastName);
    }

    @PostMapping("person/{firstName},{lastName},{address},{city},{zip},{phone},{email}")
    @PutMapping("person/{firstName},{lastName},{address},{city},{zip},{phone},{email}")
    public PersonDto savePerson(@PathVariable("firstName") final String firstName,
                                @PathVariable("lastName") final String lastName,
                                @PathVariable("address") final String address,
                                @PathVariable("city") final String city,
                                @PathVariable("zip") final String zip,
                                @PathVariable("phone") final String phone,
                                @PathVariable("email") final String email) {
        return personService.personToPersonDto(personService.savePerson(firstName, lastName, address, city, zip, phone, email));
    }

}
