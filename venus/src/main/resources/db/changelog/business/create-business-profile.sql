--liquibase formatted sql
--changeset kkumar:business_profile

CREATE EXTENSION IF NOT EXISTS "pgcrypto";
CREATE TABLE IF NOT EXISTS business_profile (
    business_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    business_name VARCHAR(255) NOT NULL,
    logo_url VARCHAR(500),
    business_type VARCHAR(255) NOT NULL,
    description VARCHAR(1000),
    address VARCHAR(255) NOT NULL,
    city VARCHAR(255),
    state VARCHAR(255),
    zip_code VARCHAR(50),
    phone VARCHAR(50),
    website VARCHAR(255),
    timezone VARCHAR(100),
    slug VARCHAR(255) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    competition_level INT,
    completed BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX idx_business_profile_name ON business_profile (business_name);
CREATE INDEX idx_business_profile_city ON business_profile (city);
CREATE INDEX idx_business_profile_state ON business_profile (state);
CREATE INDEX idx_business_profile_zip ON business_profile (zip_code);
CREATE INDEX idx_business_profile_city_state ON business_profile (city, state);
CREATE INDEX idx_business_profile_active ON business_profile (active);