package com.postgresql.demo.adapters.web;


import com.postgresql.demo.domain.model.Person;
import com.postgresql.demo.domain.repo.PersonRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons") // RESTful e organizado
public class PersonController {

    private final PersonRepo personRepo;

    public PersonController(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    @GetMapping
    public List<Person> getAll() {
        return personRepo.findAll();
    }

    @PostMapping
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        Person saved = personRepo.save(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}


