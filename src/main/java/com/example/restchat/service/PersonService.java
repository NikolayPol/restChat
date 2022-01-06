package com.example.restchat.service;

import com.example.restchat.model.Person;
import com.example.restchat.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Класс PersonService - обеспечивает взаимодействие Контроллера с Репозиторием.
 *
 * @author Nikolay Polegaev
 * @version 1.0 05.01.2022
 */
@Service
@AllArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Optional<Person> findById(Long id) {
        return personRepository.findById(id);
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public boolean delete(Long id) {
        boolean rsl = false;
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
            rsl = true;
        }
        return rsl;
    }
}
