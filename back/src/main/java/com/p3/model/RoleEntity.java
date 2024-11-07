package com.p3.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ROLES")
public class RoleEntity extends ModelEntity {
    @Id
    @GeneratedValue
    private int id;
    private String name;

    public String getName() {
        return name;
    }
}
