package com.example.restchat.control;

import com.example.restchat.model.Role;
import com.example.restchat.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

/**
 * Класс RoleController  - контроллер для ролей
 *
 * @author Nikolay Polegaev
 * @version 2.1 07.01.2022
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
    public Role create(@Valid @RequestBody Role role) {
        return roleService.save(role);
    }

    @PutMapping()
    public Role update(@Valid @RequestBody Role role) {
        return roleService.save(role);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable int id) {
        return roleService.delete(id);
    }

    @RequestMapping(value = "", produces = "application/json", method = {RequestMethod.PATCH})
    public ResponseEntity<Role> patch(@Valid @RequestBody Role role)
            throws InvocationTargetException, IllegalAccessException {
        Role currentRole = roleService.findById(role.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );

        var methods = currentRole.getClass().getDeclaredMethods();

        var namePerMethod = new HashMap<String, Method>();

        for (var method : methods) {
            var name = method.getName();
            if (name.startsWith("get") || name.startsWith("set")) {
                namePerMethod.put(name, method);
            }
        }

        for (var name : namePerMethod.keySet()) {
            if (name.startsWith("get")) {
                var getMethod = namePerMethod.get(name);
                var setMethod = namePerMethod.get(name.replace("get", "set"));
                if (setMethod == null) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "Invalid properties mapping");
                }
                var newValue = getMethod.invoke(role);
                if (newValue != null) {
                    setMethod.invoke(currentRole, newValue);
                }
            }
        }
        return new ResponseEntity<>(roleService.save(currentRole), HttpStatus.OK);
    }
}
