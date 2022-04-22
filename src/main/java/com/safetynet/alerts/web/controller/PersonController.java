package com.safetynet.alerts.web.controller;

import com.safetynet.alerts.web.dto.PersonDto;
import com.safetynet.alerts.domain.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@Slf4j
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("/person")
    public List<PersonDto> getPersons() {
        return personService.listPersonToPersonDto(personService.getPersons());
    }

    @GetMapping("/person/{firstName}/{lastName}")
    public PersonDto getPerson(@PathVariable("firstName") final String firstName,
                               @PathVariable("lastName") final String lastName) {
        return personService.personToPersonDto(personService.getPerson(firstName, lastName));
    }

    @DeleteMapping("/person") //Idem au-dessus
    public ResponseEntity<?> deletePerson(@RequestParam("firstName") final String firstName,
                                       @RequestParam("lastName") final String lastName) {
        log.info("");
        try {
            personService.deletePerson(firstName, lastName);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            log.error("DELETE /person error:{}",e.getMessage());
            return ResponseEntity.notFound().build();
        }
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
