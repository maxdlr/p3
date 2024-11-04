package com.p3.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "RENTALS")
public class RentalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private float surface;

    private float price;

    private Date createdAt;

    private Date updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity owner;

    private String picture;

    @Column(length = 2000)
    private String description;

    public RentalEntity() {
        this.createdAt = new Date();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public RentalEntity setName(String name) {
        this.name = name;
        return this;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public RentalEntity setOwner(UserEntity owner) {
        this.owner = owner;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public RentalEntity setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RentalEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public float getSurface() {
        return surface;
    }

    public RentalEntity setSurface(float surface) {
        this.surface = surface;
        return this;
    }

    public float getPrice() {
        return price;
    }

    public RentalEntity setPrice(float price) {
        this.price = price;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public RentalEntity setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
