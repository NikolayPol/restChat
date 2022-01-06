package com.example.restchat.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

/**
 * Класс Role - модель роли
 *
 * @author Nikolay Polegaev
 * @version 1.0 05.01.2022
 */
@Entity
@Table(name = "role")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Role role = (Role) o;
        return id == role.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Role{"
                + "id=" + id
                + ", role='" + name + '\''
                + '}';
    }
}
