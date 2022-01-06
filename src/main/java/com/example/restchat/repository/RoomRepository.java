package com.example.restchat.repository;

import com.example.restchat.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Интерфейс RoomRepository - посредством Spring Data обеспечивает
 *  * выполнение crud-команд для объектов Room.
 *
 * @author Nikolay Polegaev
 * @version 1.0 05.01.2022
 */
public interface RoomRepository extends JpaRepository<Room, Long> {
}
