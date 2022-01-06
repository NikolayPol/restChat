package com.example.restchat.repository;

import com.example.restchat.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Интерфейс MessageRepository - посредством Spring Data обеспечивает
 * выполнение crud-команд для объектов Message.
 *
 * @author Nikolay Polegaev
 * @version 1.0 05.01.2022
 */
public interface MessageRepository extends JpaRepository<Message, Long> {
}
