-- liquibase formatted sql

-- changeset kkumar:create-staff-tables

-- Create business_staff_members table
CREATE TABLE business_staff_members (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    role VARCHAR(50) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    business_id BIGINT NOT NULL,
    department_id BIGINT,
    hire_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    avatar VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create business_staff_services table for many-to-many relationship
CREATE TABLE business_staff_services (
    staff_id BIGINT NOT NULL,
    service_id BIGINT NOT NULL,
    PRIMARY KEY (staff_id, service_id),
    FOREIGN KEY (staff_id) REFERENCES business_staff_members(id) ON DELETE CASCADE
);



-- Add indexes for performance
CREATE INDEX idx_staff_business_id ON business_staff_members(business_id);
CREATE INDEX idx_staff_email_business ON business_staff_members(email, business_id);
CREATE INDEX idx_staff_status ON business_staff_members(status);
CREATE INDEX idx_staff_created_at ON business_staff_members(created_at);
CREATE INDEX idx_staff_services_staff_id ON business_staff_services(staff_id);
CREATE INDEX idx_staff_services_service_id ON business_staff_services(service_id);
CREATE INDEX idx_staff_department_id ON business_staff_members(department_id);

-- Add unique constraint on email per business
ALTER TABLE business_staff_members 
ADD CONSTRAINT uk_staff_email_business UNIQUE (email, business_id);

-- Add check constraint for status
ALTER TABLE business_staff_members 
ADD CONSTRAINT chk_staff_status CHECK (status IN ('ACTIVE', 'INACTIVE', 'ON_LEAVE'));

ALTER TABLE business_staff_members 
ADD CONSTRAINT fk_staff_department 
FOREIGN KEY (department_id) REFERENCES departments(id) ON DELETE SET NULL;

-- Add comments for documentation
COMMENT ON TABLE business_staff_members IS 'Staff members associated with businesses';
COMMENT ON TABLE business_staff_services IS 'Many-to-many relationship between staff and services';
COMMENT ON COLUMN business_staff_members.status IS 'Staff status: ACTIVE, INACTIVE, or ON_LEAVE';
COMMENT ON COLUMN business_staff_members.hire_date IS 'Date when staff member was hired';
COMMENT ON COLUMN business_staff_members.avatar IS 'URL or path to staff member avatar image';