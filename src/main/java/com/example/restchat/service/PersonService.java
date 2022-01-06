package com.example.restchat.service;

import com.example.restchat.model.Person;
import com.example.restchat.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

/**
 * Класс PersonService - обеспечивает взаимодействие Контроллера с Репозиторием.
 *
 * @author Nikolay Polegaev
 * @version 1.1 06.01.2022
 */
@Service
@AllArgsConstructor
public class PersonService implements UserDetailsService {
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person user = personRepository.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(user.getLogin(), user.getPassword(), emptyList());
    }
}
