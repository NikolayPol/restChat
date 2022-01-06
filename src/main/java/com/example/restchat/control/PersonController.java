package com.example.restchat.control;

import com.example.restchat.model.Person;
import com.example.restchat.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Класс PersonController  - контроллер для пользователей
 *
 * @author Nikolay Polegaev
 * @version 1.0 05.01.2022
 */
@RestController
@RequestMapping("/persons")
@AllArgsConstructor
public class PersonController {
    private final PersonService personService;

    @GetMapping
    public List<Person> findAll() {
        return personService.findAll();
    }

    @GetMapping("/{id}")
    public Person findById(@PathVariable Long id) {
        return personService.findById(id).get();
    }

    @PostMapping()
    public Person create(@RequestBody Person person) {
        return personService.save(person);
    }

    @PutMapping()
    public Person update(@RequestBody Person person) {
        return personService.save(person);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return personService.delete(id);
    }
}
