package com.example.restchat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * Класс Person - модель пользователя
 *
 * @author Nikolay Polegaev
 * @version 1.2 07.01.2022
 */
@Entity
@Table(name = "person")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Field must not be empty")
    private String login;

    private String email;

    @NotBlank
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Message> messages = new ArrayList<>();

    @ManyToMany()
    @JoinTable(
            name = "person_room",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "room_id"))
    private List<Room> rooms = new ArrayList<>();

    public Person(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return id == person.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Person{"
                + "id=" + id
                + ", login='" + login + '\''
                + ", email='" + email + '\''
                + ", password='" + password + '\''
                + ", roles=" + role
                + '}';
    }
}
