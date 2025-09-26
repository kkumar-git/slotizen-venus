--liquibase formatted sql
--changeset kkumar:insert-permissions

INSERT INTO permissions (name, description) VALUES
('view_dashboard', 'Access dashboard'),
('view_analytics', 'Access analytics'),

('view_bookings', 'View bookings'),
('create_booking', 'Create a new booking'),
('edit_booking', 'Edit existing booking'),
('delete_booking', 'Delete booking'),

('view_clients', 'View clients'),
('create_client', 'Add a new client'),
('edit_client', 'Edit client details'),
('delete_client', 'Delete client'),

('view_staff', 'View staff list'),
('create_staff', 'Add staff'),
('edit_staff', 'Edit staff details'),
('delete_staff', 'Remove staff'),

('view_services', 'View services'),
('create_service', 'Add service'),
('edit_service', 'Edit service'),
('delete_service', 'Remove service'),

('view_calendar', 'View calendar'),
('manage_calendar', 'Manage calendar'),

('view_payments', 'View payments'),
('process_payments', 'Process payments'),

('view_notifications', 'View notifications'),
('manage_notifications', 'Manage notifications'),

('manage_business', 'Manage own business settings'),
('manage_all_businesses', 'Super admin manage all businesses');
