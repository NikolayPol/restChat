package com.example.restchat.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * Класс Role - модель роли
 *
 * @author Nikolay Polegaev
 * @version 1.1 07.01.2022
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

    @NotBlank(message = "Field must not be empty")
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
