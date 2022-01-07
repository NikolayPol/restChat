package com.example.restchat.control;

import com.example.restchat.model.Message;
import com.example.restchat.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

/**
 * Класс MessageController - контроллер для сообщений
 *
 * @author Nikolay Polegaev
 * @version 2.1 07.01.2022
 */
@RestController
@RequestMapping("/messages")
@AllArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @GetMapping
    public List<Message> findAll() {
        return messageService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> findById(@PathVariable Long id) {
        var message = messageService.findById(id);
        return new ResponseEntity<>(
                message.orElse(new Message()),
                message.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @PostMapping()
    public Message create(@Valid @RequestBody Message message) {
        return messageService.save(message);
    }

    @PutMapping()
    public Message update(@Valid @RequestBody Message message) {
        return messageService.update(message);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return messageService.delete(id);
    }

    @RequestMapping(value = "", produces = "application/json", method = {RequestMethod.PATCH})
    public ResponseEntity<Message> patch(@Valid @RequestBody Message message)
            throws InvocationTargetException, IllegalAccessException {
        Message currentMessage = messageService.findById(message.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );

        var methods = currentMessage.getClass().getDeclaredMethods();

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
                var newValue = getMethod.invoke(message);
                if (newValue != null) {
                    setMethod.invoke(currentMessage, newValue);
                }
            }
        }
        return new ResponseEntity<>(messageService.update(currentMessage), HttpStatus.OK);
    }
}
