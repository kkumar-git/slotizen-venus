--liquibase formatted sql
--changeset kkumar:business_profiles
CREATE TABLE business_profiles (
    business_id UUID PRIMARY KEY,
    business_name VARCHAR(255) NOT NULL,
    description VARCHAR(1000),
    address VARCHAR(255) NOT NULL,
    city VARCHAR(100),
    state VARCHAR(100),
    zip_code VARCHAR(20),
    phone VARCHAR(30),
    website VARCHAR(255),
    timezone VARCHAR(100),
    slug VARCHAR(255) NOT NULL UNIQUE,
    created_at TIMESTAMPTZ NOT NULL
);
