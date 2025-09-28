--liquibase formatted sql
--changeset kkumar:business_hours
CREATE TABLE business_hours (
    id UUID PRIMARY KEY,
    business_id UUID NOT NULL REFERENCES business_profile(business_id),

    monday_is_open BOOLEAN,
    monday_open_time TIME,
    monday_close_time TIME,

    tuesday_is_open BOOLEAN,
    tuesday_open_time TIME,
    tuesday_close_time TIME,

    wednesday_is_open BOOLEAN,
    wednesday_open_time TIME,
    wednesday_close_time TIME,

    thursday_is_open BOOLEAN,
    thursday_open_time TIME,
    thursday_close_time TIME,

    friday_is_open BOOLEAN,
    friday_open_time TIME,
    friday_close_time TIME,

    saturday_is_open BOOLEAN,
    saturday_open_time TIME,
    saturday_close_time TIME,

    sunday_is_open BOOLEAN,
    sunday_open_time TIME,
    sunday_close_time TIME
);
-- Index to quickly find business hours by business_id
CREATE INDEX idx_business_hours_business_id ON business_hours(business_id);