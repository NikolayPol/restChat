package com.example.restchat.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Класс Message - модель сообщения
 *
 * @author Nikolay Polegaev
 * @version 1.1 07.01.2022
 */
@Entity
@Table(name = "message")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Field must not be empty")
    private String text;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Person author;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Message message = (Message) o;
        return id == message.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Message{"
                + "id=" + id
                + ", text='" + text + '\''
                + ", author=" + author
                + ", created=" + created
                + ", room=" + room
                + '}';
    }
}
