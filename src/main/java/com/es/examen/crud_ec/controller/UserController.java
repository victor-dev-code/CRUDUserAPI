package com.es.examen.crud_ec.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.es.examen.crud_ec.dto.RequestUserDTO;
import com.es.examen.crud_ec.dto.ResponseUserDTO;
import com.es.examen.crud_ec.service.UserServiceImpl;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ResponseUserDTO> save(@RequestBody RequestUserDTO userDTO) {
        ResponseUserDTO saved = userService.saveUser(userDTO);
        return ResponseEntity.ok(saved);        
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseUserDTO> findById(@PathVariable Long id) {
        return userService.getById(id).map(
            ResponseEntity::ok
        ).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (userService.deleteById(id))
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }
    
    @PutMapping
    public ResponseEntity<ResponseUserDTO> update(@RequestBody RequestUserDTO userDTO) {
        return userService.updateUser(userDTO).map(
            ResponseEntity::ok
        ).orElseGet(() -> ResponseEntity.notFound().build());
    }
}