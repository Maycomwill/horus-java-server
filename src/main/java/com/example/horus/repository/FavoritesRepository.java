package com.example.horus.repository;

import com.example.horus.entitites.Favorites;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FavoritesRepository extends CrudRepository<Favorites, UUID> {
    Optional<List<Favorites>> findByUserId(UUID userId);
}
