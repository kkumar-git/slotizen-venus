-- liquibase formatted sql

-- changeset slotizen:create-departments-table

-- Create departments table
CREATE TABLE departments (
    id BIGSERIAL PRIMARY KEY,
    business_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    color VARCHAR(7) NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Add indexes for performance
CREATE INDEX idx_departments_business_id ON departments(business_id);
CREATE INDEX idx_departments_active ON departments(business_id, is_active);
CREATE UNIQUE INDEX idx_departments_unique_name ON departments(business_id, name);

-- Add comments for documentation
COMMENT ON TABLE departments IS 'Business departments for organizing staff and services';
COMMENT ON COLUMN departments.color IS 'Hex color code for department identification';
COMMENT ON COLUMN departments.is_active IS 'Whether the department is currently active';