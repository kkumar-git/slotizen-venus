--liquibase formatted sql
--changeset kkumar:insert-role_permissions

--Super Admin - Has all permissions.
INSERT INTO role_permissions (role_id, permission_id)
SELECT 1, id FROM permissions;

--Business Owner - Full control of their own business (but not cross-tenant).
INSERT INTO role_permissions (role_id, permission_id)
SELECT 2, id FROM permissions WHERE name NOT IN ('manage_all_businesses');

--Manager - Can handle bookings, clients, staff, services, calendar, payments.
INSERT INTO role_permissions (role_id, permission_id)
SELECT 3, id FROM permissions WHERE name IN (
  'view_dashboard','view_analytics',
  'view_bookings','create_booking','edit_booking','delete_booking',
  'view_clients','create_client','edit_client','delete_client',
  'view_staff','create_staff','edit_staff','delete_staff',
  'view_services','create_service','edit_service','delete_service',
  'view_calendar','manage_calendar',
  'view_payments','process_payments',
  'view_notifications','manage_notifications'
);

--Staff - Can manage bookings, services, calendar.
INSERT INTO role_permissions (role_id, permission_id)
SELECT 4, id FROM permissions WHERE name IN (
  'view_bookings','create_booking','edit_booking',
  'view_services','view_calendar','manage_calendar',
  'view_notifications'
);

--Client - Only sees own bookings, calendar, notifications.
INSERT INTO role_permissions (role_id, permission_id)
SELECT 6, id FROM permissions WHERE name IN (
  'view_bookings','create_booking','view_calendar','view_notifications'
);