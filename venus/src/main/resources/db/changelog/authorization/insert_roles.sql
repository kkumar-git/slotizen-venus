--liquibase formatted sql
--changeset kkumar:insert-roles

INSERT INTO roles (name, description) VALUES
('ROLE_SUPER_ADMIN', 'Super admin with full system access'),
('ROLE_BUSINESS_OWNER', 'Business owner with full control of their tenant'),
('ROLE_MANAGER', 'Manager with operational control'),
('ROLE_STAFF', 'Staff member handling services'),
('ROLE_RECEPTIONIST', 'Receptionist handling bookings & clients'),
('ROLE_CLIENT', 'Client accessing their own bookings');