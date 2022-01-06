package com.example.restchat.service;

import com.example.restchat.model.Room;
import com.example.restchat.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Класс RoomService - обеспечивает взаимодействие Контроллера с Репозиторием.
 *
 * @author Nikolay Polegaev
 * @version 1.0 05.01.2022
 */
@Service
@AllArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    public Optional<Room> findById(Long id) {
        return roomRepository.findById(id);
    }

    public Room save(Room room) {
        return roomRepository.save(room);
    }

    public boolean delete(Long id) {
        boolean rsl = false;
        if (roomRepository.existsById(id)) {
            roomRepository.deleteById(id);
            rsl = true;
        }
        return rsl;
    }
}
