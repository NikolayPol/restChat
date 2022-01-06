package com.example.restchat.repository;

import com.example.restchat.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Интерфейс RoleRepository - посредством Spring Data обеспечивает
 *  * выполнение crud-команд для объектов Role.
 *
 * @author Nikolay Polegaev
 * @version 1.0 05.01.2022
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
