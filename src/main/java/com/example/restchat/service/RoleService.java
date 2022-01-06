package com.example.restchat.service;

import com.example.restchat.model.Role;
import com.example.restchat.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Класс RoleService - обеспечивает взаимодействие Контроллера с Репозиторием.
 *
 * @author Nikolay Polegaev
 * @version 1.0 05.01.2022
 */
@Service
@AllArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Optional<Role> findById(int id) {
        return roleRepository.findById(id);
    }

    public Role save(Role role) {
        return roleRepository.save(role);
    }

    public boolean delete(int id) {
        boolean rsl = false;
        if (roleRepository.existsById(id)) {
            roleRepository.deleteById(id);
            rsl = true;
        }
        return rsl;
    }
}
