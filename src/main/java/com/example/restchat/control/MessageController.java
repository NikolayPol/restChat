package com.example.restchat.control;

import com.example.restchat.model.Message;
import com.example.restchat.service.MessageService;
import lombok.AllArgsConstructor;
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
    public Message findById(@PathVariable Long id) {
        return messageService.findById(id).get();

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
