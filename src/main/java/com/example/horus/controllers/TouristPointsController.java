package com.example.horus.controllers;

import com.example.horus.entitites.TouristPoints;
import com.example.horus.entitites.Users;
import com.example.horus.repository.TouristPointsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/points")
public class TouristPointsController {

    @Autowired
    private TouristPointsRepository repository;

    @GetMapping
    public ResponseEntity<List<TouristPoints>> getAllPoints() {
        List<TouristPoints> touristPointsList = new ArrayList<TouristPoints>();
        repository.findAll().forEach(touristPointsList::add);
        return ResponseEntity.ok(touristPointsList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<TouristPoints>> findById(@PathVariable(value = "id") UUID id) {
        Optional<TouristPoints> touristPoints = repository.findById(id);
        return ResponseEntity.ok(touristPoints);
    }

    @PostMapping
    public ResponseEntity<TouristPoints> createPoint(@RequestBody TouristPoints point) {
        TouristPoints entity = repository.save(point);

        return ResponseEntity.ok(entity);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TouristPoints> updatePoint(@PathVariable(value = "id") UUID id, @RequestBody TouristPoints requestPoint) {
        Optional<TouristPoints> entity = repository.findById(id);

        if (entity.isPresent()) {
            TouristPoints pointEntity = entity.get();
            if (requestPoint.getAddress() != null) {
                pointEntity.setAddress(requestPoint.getAddress());
            }
            if (requestPoint.getCity() != null) {
                pointEntity.setCity(requestPoint.getCity());
            }
            if (requestPoint.getCountry() != null) {
                pointEntity.setCountry(requestPoint.getCountry());
            }
            if (requestPoint.getState() != null) {
                pointEntity.setState(requestPoint.getState());
            }
            if (requestPoint.getNeighborhood() != null) {
                pointEntity.setNeighborhood(requestPoint.getNeighborhood());
            }
            if (requestPoint.getNumber() != null) {
                pointEntity.setNumber(requestPoint.getNumber());
            }
            if (requestPoint.getName() != null) {
                pointEntity.setName(requestPoint.getName());
            }
            if (requestPoint.getLat() != null) {
                pointEntity.setLat(requestPoint.getLat());
            }
            if (requestPoint.getLng() != null) {
                pointEntity.setLng(requestPoint.getLng());
            }
            if (requestPoint.getZip() != null) {
                pointEntity.setZip(requestPoint.getZip());
            }
            repository.save(pointEntity);
            return ResponseEntity.ok(pointEntity);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePoint(@PathVariable(value = "id") UUID id) {
        Optional<TouristPoints> point = repository.findById(id);
        if (point.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.ok("Ponto turístico deletado");
        }
        return null;
    }
}
