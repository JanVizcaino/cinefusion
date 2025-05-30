package com.example_pgr.demo_pgr.controller;

import com.example_pgr.demo_pgr.dto.UserDTO;
import com.example_pgr.demo_pgr.model.User;
import com.example_pgr.demo_pgr.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // OBTENER TODOS LOS USUARIOS
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = userService.listUsers();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<UserDTO> userDTOS = users.stream().map(user -> {
            UserDTO dto = new UserDTO();
            dto.setId_user(user.getId_user());
            dto.setName(user.getName());
            dto.setEmail(user.getEmail());
            dto.setPassword(user.getPassword());
            dto.setAddress(user.getAddress());
            dto.setPhone(user.getPhone());
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(userDTOS);
    }

    // OBTENER USUARIO POR ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int id) {
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        UserDTO dto = new UserDTO();
        dto.setId_user(user.getId_user());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setAddress(user.getAddress());
        dto.setPhone(user.getPhone());

        return ResponseEntity.ok(dto);
    }

    // OBTENER USUARIO POR NOMBRE
    @GetMapping("/name/{name}")
    public ResponseEntity<UserDTO> getUserByName(@PathVariable String name) {
        User user = userService.findByName(name);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        UserDTO dto = mapToDTO(user);
        return ResponseEntity.ok(dto);
    }

    private UserDTO mapToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId_user(user.getId_user());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setAddress(user.getAddress());
        dto.setPhone(user.getPhone());
        return dto;
    }

    // CREAR UN NUEVO USUARIO
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setAddress(userDTO.getAddress());
        user.setPhone(userDTO.getPhone());

        userService.saveUser(user);

        // Actualizamos el ID generado en el DTO para devolverlo con el código 201
        userDTO.setId_user(user.getId_user());

        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    // ACTUALIZAR UN USUARIO EXISTENTE
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable int id, @RequestBody UserDTO userDTO) {
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setAddress(userDTO.getAddress());
        user.setPhone(userDTO.getPhone());

        userService.saveUser(user);

        return ResponseEntity.ok(userDTO);
    }

    // ELIMINAR UN USUARIO POR ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
