--liquibase formatted sql
--changeset kkumar:create-users

CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT false,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
-- Index for first_name
CREATE INDEX idx_users_first_name ON users(first_name);

-- Index for last_name
CREATE INDEX idx_users_last_name ON users(last_name);

-- Composite index for first_name + last_name
CREATE INDEX idx_users_full_name ON users(first_name, last_name);