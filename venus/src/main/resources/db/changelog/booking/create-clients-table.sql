--liquibase formatted sql
--changeset kkumar:clients_table

CREATE TABLE IF NOT EXISTS clients (
    id BIGSERIAL PRIMARY KEY,
    business_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(50),
    avatar VARCHAR(500),
    status VARCHAR(20) DEFAULT 'active',
    total_spent DECIMAL(10,2) DEFAULT 0.00,
    total_appointments INT DEFAULT 0,
    last_visit DATE,
    next_appointment TIMESTAMP,
    tags JSON,  -- Array of strings
    favorite_services JSON,  -- Array of service IDs
    notes TEXT,
    date_of_birth DATE,
    join_date DATE DEFAULT CURRENT_DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT,
    last_modified_by BIGINT,
    
    CONSTRAINT fk_clients_business FOREIGN KEY (business_id) REFERENCES business_profile(business_id),
    CONSTRAINT fk_clients_created_by FOREIGN KEY (created_by) REFERENCES users(id),
    CONSTRAINT fk_clients_modified_by FOREIGN KEY (last_modified_by) REFERENCES users(id),
    
    CONSTRAINT unique_business_email UNIQUE (business_id, email)
);

-- Create indexes for performance
CREATE INDEX idx_clients_business_id ON clients (business_id);
CREATE INDEX idx_clients_email ON clients (email);
CREATE INDEX idx_clients_name ON clients (name);
CREATE INDEX idx_clients_status ON clients (status);
CREATE INDEX idx_clients_business_status ON clients (business_id, status);
CREATE INDEX idx_clients_created_by ON clients (created_by);