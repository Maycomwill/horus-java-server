package com.example.horus.repository;

import com.example.horus.entitites.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<Users, UUID> {
}
