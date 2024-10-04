package Model;

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

    private Date created_at;

    private Date updated_at;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity owner;

    private String picture;

    private String description;

    public RentalEntity() {
        this.created_at = new Date();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getSurface() {
        return surface;
    }

    public void setSurface(float surface) {
        this.surface = surface;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getCreatedAt() {
        return created_at;
    }

    public Date getUpdatedAt() {
        return updated_at;
    }

    public RentalEntity setUpdatedAt(Date updated_at) {
        this.updated_at = updated_at;
        return this;
    }
}
