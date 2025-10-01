--liquibase formatted sql
--changeset kkumar:business_service

CREATE TABLE IF NOT EXISTS business_service (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(100) NOT NULL,
    description VARCHAR(1000) NOT NULL,
    duration VARCHAR(10) NOT NULL,
    price VARCHAR(20) NOT NULL,
    category VARCHAR(50) NOT NULL,
    business_id VARCHAR(255) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Create indexes for performance
CREATE INDEX idx_business_service_business_id ON business_service (business_id);
CREATE INDEX idx_business_service_category ON business_service (category);
CREATE INDEX idx_business_service_name ON business_service (name);
