-- liquibase formatted sql

-- changeset slotizen:create-demo-requests-table

-- Create demo_requests table
CREATE TABLE demo_requests (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL,
    company VARCHAR(100) NOT NULL,
    industry VARCHAR(100),
    message TEXT,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Add indexes for performance
CREATE INDEX idx_demo_requests_email ON demo_requests(email);
CREATE INDEX idx_demo_requests_status ON demo_requests(status);
CREATE INDEX idx_demo_requests_created_at ON demo_requests(created_at);
CREATE INDEX idx_demo_requests_email_created ON demo_requests(email, created_at);

-- Add check constraint for status
ALTER TABLE demo_requests 
ADD CONSTRAINT chk_demo_request_status CHECK (status IN ('PENDING', 'CONTACTED', 'COMPLETED', 'CANCELLED'));

-- Add comments for documentation
COMMENT ON TABLE demo_requests IS 'Demo requests submitted by potential customers';
COMMENT ON COLUMN demo_requests.status IS 'Request status: PENDING, CONTACTED, COMPLETED, or CANCELLED';
COMMENT ON COLUMN demo_requests.created_at IS 'Timestamp when demo request was created';
COMMENT ON COLUMN demo_requests.updated_at IS 'Timestamp when demo request was last updated';