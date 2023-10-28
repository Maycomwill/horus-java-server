package com.example.horus.controllers;

import com.example.horus.entitites.Users;
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
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> userList = new ArrayList<Users>();
        repository.findAll().forEach(userList::add);
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Users>> findById(@PathVariable(value = "id") UUID id) {
        Optional<Users> user = repository.findById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
        Users entity = repository.save(user);

        return ResponseEntity.ok(entity);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable(value = "id") UUID id, @RequestBody Users requestUser) {
        Optional<Users> entity = repository.findById(id);

        if (entity.isPresent()) {
            Users userEntity = entity.get();
            if (requestUser.getFirst_name() != null) {
                userEntity.setFirst_name(requestUser.getFirst_name());
            }
            if (requestUser.getLast_name() != null) {
                userEntity.setLast_name(requestUser.getLast_name());
            }
            if (requestUser.getEmail() != null) {
                userEntity.setEmail(requestUser.getEmail());
            }
            if(requestUser.getPhone() != null){
                userEntity.setPhone(requestUser.getPhone());
            }
            repository.save(userEntity);
            return ResponseEntity.ok(userEntity);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") UUID id) {
        Optional<Users> user = repository.findById(id);
        if (user.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.ok("Usuário deletado");
        }
        return null;
    }

}
