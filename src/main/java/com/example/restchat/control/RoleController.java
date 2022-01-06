package com.example.restchat.control;

import com.example.restchat.model.Role;
import com.example.restchat.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Класс RoleController  - контроллер для ролей
 *
 * @author Nikolay Polegaev
 * @version 1.0 05.01.2022
 */
@RestController
@RequestMapping("/roles")
@AllArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping
    public List<Role> findAll() {
        return roleService.findAll();
    }

    @GetMapping("/{id}")
    public Role findById(@PathVariable int id) {
        return roleService.findById(id).get();
    }

    @PostMapping()
    public Role create(@RequestBody Role role) {
        return roleService.save(role);
    }

    @PutMapping()
    public Role update(@RequestBody Role role) {
        return roleService.save(role);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable int id) {
        return roleService.delete(id);
    }
}
