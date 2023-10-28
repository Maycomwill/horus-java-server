package com.example.horus.controllers;

import com.example.horus.entitites.User;
import com.example.horus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository repository;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = new ArrayList<User>();
        repository.findAll().forEach(userList::add);
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> findById(@PathVariable(value = "id") UUID id) {
        Optional<User> user = repository.findById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User entity = repository.save(user);

        return ResponseEntity.ok(entity);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") UUID id, @RequestBody User requestUser) {
        Optional<User> entity = repository.findById(id);

        if (entity.isPresent()) {
            User userEntity = entity.get();
            if(requestUser.getFirst_name() != null && requestUser.getLast_name() != null && requestUser.getEmail() != null){
                userEntity.setFirst_name(requestUser.getFirst_name());
                userEntity.setLast_name(requestUser.getLast_name());
                userEntity.setEmail(requestUser.getEmail());
            }
            repository.save(userEntity);
            return ResponseEntity.ok(userEntity);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") UUID id) {
        Optional<User> user = repository.findById(id);
        if(user.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.ok("Usuário deletado");
        }return null;
    }

}
