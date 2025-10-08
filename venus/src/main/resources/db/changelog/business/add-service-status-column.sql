--liquibase formatted sql
--changeset kkumar:add_service_status_column

-- Add status column to business_service table
ALTER TABLE business_service 
ADD COLUMN status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE';

-- Create index for status column
CREATE INDEX idx_business_service_status ON business_service (status);

-- Update any existing services to have ACTIVE status
UPDATE business_service SET status = 'ACTIVE' WHERE status IS NULL;