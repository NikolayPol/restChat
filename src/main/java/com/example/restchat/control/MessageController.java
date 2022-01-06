package com.example.restchat.control;

import com.example.restchat.model.Message;
import com.example.restchat.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Класс MessageController - контроллер для сообщений
 *
 * @author Nikolay Polegaev
 * @version 1.0 05.01.2022
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
    public Message create(@RequestBody Message message) {
        return messageService.save(message);
    }

    @PutMapping()
    public Message update(@RequestBody Message message) {
        return messageService.update(message);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return messageService.delete(id);
    }
}
