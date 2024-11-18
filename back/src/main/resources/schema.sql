-- Sequences
CREATE OR REPLACE SEQUENCE messages_seq START WITH 4 INCREMENT BY 1;
CREATE OR REPLACE SEQUENCE rentals_seq START WITH 4 INCREMENT BY 1;
CREATE OR REPLACE SEQUENCE roles_seq START WITH 4 INCREMENT BY 1;
CREATE OR REPLACE SEQUENCE users_seq START WITH 4 INCREMENT BY 1;

-- Drop existing tables if they exist
DROP TABLE IF EXISTS users_roles;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS messages;
DROP TABLE IF EXISTS rentals;
DROP TABLE IF EXISTS users;

-- Roles table
CREATE TABLE roles (
                       id INT NOT NULL PRIMARY KEY,
                       name VARCHAR(255) NULL
);

-- Users table
CREATE TABLE users (
                       id BIGINT NOT NULL PRIMARY KEY,
                       updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       email VARCHAR(255) NULL,
                       name VARCHAR(255) NULL,
                       password VARCHAR(255) NULL
);

-- Users_Roles table
CREATE TABLE users_roles (
                             role_id INT NOT NULL,
                             user_id BIGINT NOT NULL,
                             CONSTRAINT FK_users_roles_user_id FOREIGN KEY (user_id) REFERENCES users (id),
                             CONSTRAINT FK_users_roles_role_id FOREIGN KEY (role_id) REFERENCES roles (id)
);

-- Rentals table
CREATE TABLE rentals (
                         id INT NOT NULL PRIMARY KEY,
                         price FLOAT NOT NULL,
                         surface FLOAT NOT NULL,
                         created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         owner_id BIGINT NULL,
                         updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         description VARCHAR(2000) NULL,
                         name VARCHAR(255) NULL,
                         picture VARCHAR(255) NULL,
                         CONSTRAINT FK_rentals_owner_id FOREIGN KEY (owner_id) REFERENCES users (id)
);

-- Messages table
CREATE TABLE messages (
                          id INT NOT NULL PRIMARY KEY,
                          rental_id INT NULL,
                          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          user_id BIGINT NULL,
                          message VARCHAR(255) NULL,
                          CONSTRAINT FK_messages_user_id FOREIGN KEY (user_id) REFERENCES users (id),
                          CONSTRAINT FK_messages_rental_id FOREIGN KEY (rental_id) REFERENCES rentals (id)
);
