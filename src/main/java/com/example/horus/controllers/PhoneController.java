package com.example.horus.controllers;

import com.example.horus.DTO.PhonesDTO;
import com.example.horus.entitites.Phones;
import com.example.horus.entitites.User;
import com.example.horus.repository.PhoneRepository;
import com.example.horus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/phones")
public class PhoneController {
    @Autowired
    private PhoneRepository repository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<Phones>> getAllPhones() {
        List<Phones> phonesList = new ArrayList<Phones>();
        repository.findAll().forEach(phonesList::add);
        return ResponseEntity.ok(phonesList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Phones>> findById(@PathVariable(value = "id") UUID id) {
        Optional<Phones> phone = repository.findById(id);
        return ResponseEntity.ok(phone);
    }

    @PostMapping
    public ResponseEntity<Phones> createPhone(@RequestBody PhonesDTO phoneDTO) {

        Optional<User> user = userRepository.findById(UUID.fromString(phoneDTO.getUser_id()));
        if (user.isPresent()) {
            Phones phone = new Phones();
            phone.setNumber(phoneDTO.getNumber());
            phone.setUser(user.get());
            Phones entity = repository.save(phone);
            return ResponseEntity.ok(entity);
        }

        return null;

    }

    @PutMapping("/{id}")
    public ResponseEntity<Phones> updateUser(@PathVariable(value = "id") UUID id, @RequestBody Phones requestPhones) {
        Optional<Phones> entity = repository.findById(id);

        if (entity.isPresent()) {
            Phones phonesEntity = entity.get();
            User user = requestPhones.getUser();
            if (requestPhones.getNumber() != null && requestPhones.getUser() != null) {
                phonesEntity.setNumber(requestPhones.getNumber());
                phonesEntity.setUser(requestPhones.getUser());
            }
            repository.save(phonesEntity);
            return ResponseEntity.ok(phonesEntity);
        }
        return null;
    }
}
