package com.example.horus.repository;

import com.example.horus.entitites.Phones;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PhoneRepository extends CrudRepository<Phones, UUID> {
}
