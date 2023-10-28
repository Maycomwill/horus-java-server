package com.example.horus.entitites;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Phones {

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true)
    private UUID id_phone;

    public UUID getId_phone() {
        return id_phone;
    }

    @Column(unique = true)
    private String number;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true)
    private User user;

}
