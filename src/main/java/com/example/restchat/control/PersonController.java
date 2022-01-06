package com.example.restchat.control;

import com.example.restchat.model.Person;
import com.example.restchat.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;


/**
 * Класс PersonController  - контроллер для пользователей
 *
 * @author Nikolay Polegaev
 * @version 1.1 06.01.2022
 */
@RestController
@RequestMapping("/persons")
@AllArgsConstructor
public class PersonController {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(PersonController.class.getSimpleName());
    private final PersonService personService;
    private final BCryptPasswordEncoder encoder;
    private final ObjectMapper objectMapper;

    @GetMapping
    public List<Person> findAll() {
        return personService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable Long id) {
        var person = personService.findById(id);
        return new ResponseEntity<>(
                person.orElse(new Person()),
                person.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
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

    @PostMapping("/sign-up")
    public void signUp(@RequestBody Person person) {
        var login = person.getLogin();
        var password = person.getPassword();
        if (login == null || password == null) {
            throw new NullPointerException("Username and password mustn't be empty");
        }
        if (password.length() < 6) {
            throw new IllegalArgumentException("Invalid password. "
                    + "Password length must be more than 5 characters.");
        }
        person.setPassword(encoder.encode(person.getPassword()));
        personService.save(person);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public void exceptionHandler(Exception e, HttpServletRequest request,
                                 HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() {
            {
                put("message", e.getMessage());
                put("type", e.getClass());
            }
        }));
        LOGGER.error(e.getLocalizedMessage());
    }
}
