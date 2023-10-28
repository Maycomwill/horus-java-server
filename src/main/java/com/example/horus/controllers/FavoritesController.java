package com.example.horus.controllers;

import com.example.horus.entitites.Favorites;
import com.example.horus.entitites.Users;
import com.example.horus.repository.FavoritesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/favorites")
public class FavoritesController {

    @Autowired
    private FavoritesRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<List<Favorites>>> getUserFavorites(@PathVariable(value = "id") UUID userId){
        Optional<List<Favorites>> favorites = repository.findByUserId(userId);
        return ResponseEntity.ok(favorites);
    }

    @PostMapping("/{userId}/{pointId}")
    public ResponseEntity<Favorites> createFavorite(@PathVariable(value = "userId") UUID userId, @PathVariable(value = "pointId") UUID pointId, @RequestBody Favorites favorite) {
        Favorites entity = repository.save(favorite);

        return ResponseEntity.ok(entity);
    }

}
