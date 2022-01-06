package com.example.restchat.control;

import com.example.restchat.model.Room;
import com.example.restchat.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Класс RoomController - контроллер для комнат
 *
 * @author Nikolay Polegaev
 * @version 1.0 05.01.2022
 */
@RestController
@RequestMapping("/rooms")
@AllArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @GetMapping
    public List<Room> findAll() {
        return roomService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> findById(@PathVariable Long id) {
        var room =  roomService.findById(id);
        return new ResponseEntity<>(
                room.orElse(new Room()),
                room.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @PostMapping()
    public Room create(@RequestBody Room room) {
        return roomService.save(room);
    }

    @PutMapping()
    public Room update(@RequestBody Room room) {
        return roomService.save(room);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return roomService.delete(id);
    }
}
