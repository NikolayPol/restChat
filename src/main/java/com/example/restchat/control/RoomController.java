package com.example.restchat.control;

import com.example.restchat.model.Room;
import com.example.restchat.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

/**
 * Класс RoomController - контроллер для комнат
 *
 * @author Nikolay Polegaev
 * @version 2.0 07.01.2022
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
        var room = roomService.findById(id);
        return new ResponseEntity<>(
                room.orElse(new Room()),
                room.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody Room room) {
        roomService.save(room);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody Room room) {
        roomService.save(room);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return new ResponseEntity<>(roomService.delete(id) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "", produces = "application/json", method = {RequestMethod.PATCH})
    public ResponseEntity<Room> patch(@RequestBody Room room)
            throws InvocationTargetException, IllegalAccessException {
        Room currentRoom = roomService.findById(room.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );

        var methods = currentRoom.getClass().getDeclaredMethods();

        var namePerMethod = new HashMap<String, Method>();

        for (var method : methods) {
            var name = method.getName();
            if (name.startsWith("get") || name.startsWith("set")) {
                namePerMethod.put(name, method);
            }
        }

        for (var name : namePerMethod.keySet()) {
            if (name.startsWith("get")) {
                var getMethod = namePerMethod.get(name);
                var setMethod = namePerMethod.get(name.replace("get", "set"));
                if (setMethod == null) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "Invalid properties mapping");
                }
                var newValue = getMethod.invoke(room);
                if (newValue != null) {
                    setMethod.invoke(currentRoom, newValue);
                }
            }
        }
        return new ResponseEntity<>(roomService.save(currentRoom), HttpStatus.OK);
    }
}
