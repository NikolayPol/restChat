package com.example.restchat.control;

import com.example.restchat.model.Role;
import com.example.restchat.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Role> findById(@PathVariable int id) {
        var role =  roleService.findById(id);
        return new ResponseEntity<>(
                role.orElse(new Role()),
                role.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
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
