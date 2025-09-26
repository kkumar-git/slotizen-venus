--liquibase formatted sql
--changeset kkumar:roles
CREATE TABLE roles (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE, -- e.g. ROLE_SUPER_ADMIN
    description VARCHAR(255)
);

-- Index
CREATE INDEX idx_roles_name ON roles(name);