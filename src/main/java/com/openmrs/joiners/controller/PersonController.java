package com.openmrs.joiners.controller;

import com.openmrs.joiners.entity.Person;
import com.openmrs.joiners.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/persons")
    public ResponseEntity<List<Person>> getAllPersons(){
        return ResponseEntity.ok(personService.getAllPersons());
    }

    @PutMapping(value = "/update/department")
    public ResponseEntity <Person> updatePerson(@RequestBody Person updatedPerson){
        return ResponseEntity.ok(personService.updatePerson(updatedPerson));
    }
}
