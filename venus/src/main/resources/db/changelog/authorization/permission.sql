--liquibase formatted sql
--changeset kkumar:permissions

CREATE TABLE permissions (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE, -- e.g. view_dashboard
    description VARCHAR(255)
);

-- Index
CREATE INDEX idx_permissions_name ON permissions(name);