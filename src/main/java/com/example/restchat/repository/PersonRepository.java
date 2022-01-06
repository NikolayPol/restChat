package com.example.restchat.repository;

import com.example.restchat.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Интерфейс PersonRepository - посредством Spring Data обеспечивает
 *  * выполнение crud-команд для объектов Person.
 *
 * @author Nikolay Polegaev
 * @version 1.0 05.01.2022
 */
public interface PersonRepository extends JpaRepository<Person, Long> {
}
