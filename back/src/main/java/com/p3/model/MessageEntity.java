package com.p3.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "MESSAGES")
public class MessageEntity extends ModelEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    private RentalEntity rental;

    @OneToOne(fetch = FetchType.EAGER)
    private UserEntity user;

    private String message;

    public MessageEntity() {
        this.createdAt = new Date();
    }

    public int getId() {
        return id;
    }

    public RentalEntity getRental() {
        return rental;
    }

    public MessageEntity setRental(RentalEntity rental) {
        this.rental = rental;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public MessageEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public MessageEntity setMessage(String message) {
        this.message = message;
        return this;
    }

    public MessageEntity setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
