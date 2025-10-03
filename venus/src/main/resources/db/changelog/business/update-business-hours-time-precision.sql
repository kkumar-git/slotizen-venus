--liquibase formatted sql

--changeset kkumar:update-business-hours-time-precision
ALTER TABLE business_hours 
ALTER COLUMN monday_open_time TYPE VARCHAR(25),
ALTER COLUMN monday_close_time TYPE VARCHAR(25),
ALTER COLUMN tuesday_open_time TYPE VARCHAR(25),
ALTER COLUMN tuesday_close_time TYPE VARCHAR(25),
ALTER COLUMN wednesday_open_time TYPE VARCHAR(25),
ALTER COLUMN wednesday_close_time TYPE VARCHAR(25),
ALTER COLUMN thursday_open_time TYPE VARCHAR(25),
ALTER COLUMN thursday_close_time TYPE VARCHAR(25),
ALTER COLUMN friday_open_time TYPE VARCHAR(25),
ALTER COLUMN friday_close_time TYPE VARCHAR(25),
ALTER COLUMN saturday_open_time TYPE VARCHAR(25),
ALTER COLUMN saturday_close_time TYPE VARCHAR(25),
ALTER COLUMN sunday_open_time TYPE VARCHAR(25),
ALTER COLUMN sunday_close_time TYPE VARCHAR(25);

--rollback ALTER TABLE business_hours ALTER COLUMN monday_open_time TYPE TIME, ALTER COLUMN monday_close_time TYPE TIME, ALTER COLUMN tuesday_open_time TYPE TIME, ALTER COLUMN tuesday_close_time TYPE TIME, ALTER COLUMN wednesday_open_time TYPE TIME, ALTER COLUMN wednesday_close_time TYPE TIME, ALTER COLUMN thursday_open_time TYPE TIME, ALTER COLUMN thursday_close_time TYPE TIME, ALTER COLUMN friday_open_time TYPE TIME, ALTER COLUMN friday_close_time TYPE TIME, ALTER COLUMN saturday_open_time TYPE TIME, ALTER COLUMN saturday_close_time TYPE TIME, ALTER COLUMN sunday_open_time TYPE TIME, ALTER COLUMN sunday_close_time TYPE TIME;