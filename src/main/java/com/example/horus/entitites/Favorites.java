package com.example.horus.entitites;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Favorites {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true)
    private UUID id_fav;
    @JoinColumn(name="id_point")
    private UUID id_point;
    @JoinColumn(name="id")
    private UUID id_user;
}
