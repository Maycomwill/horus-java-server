package com.example.horus.repository;

import com.example.horus.entitites.TouristPoints;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TouristPointsRepository extends CrudRepository<TouristPoints, UUID> {
}
