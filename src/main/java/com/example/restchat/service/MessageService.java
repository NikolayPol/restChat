package com.example.restchat.service;

import com.example.restchat.model.Message;
import com.example.restchat.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Класс MessageService - обеспечивает взаимодействие Контроллера с Репозиторием.
 *
 * @author Nikolay Polegaev
 * @version 1.0 05.01.2022
 */
@Service
@AllArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    public Optional<Message> findById(Long id) {
        return messageRepository.findById(id);
    }

    public Message save(Message message) {
        message.setCreated(LocalDateTime.now());
        return messageRepository.save(message);
    }

    public Message update(Message message) {
        Optional<Message> messageFromDB = messageRepository.findById(message.getId());
        if (messageFromDB.isPresent()) {
            message.setCreated(messageFromDB.get().getCreated());
        } else {
            message.setCreated(LocalDateTime.now());
        }
        return messageRepository.save(message);
    }

    public boolean delete(Long id) {
        boolean rsl = false;
        if (messageRepository.existsById(id)) {
            messageRepository.deleteById(id);
            rsl = true;
        }
        return rsl;
    }
}
