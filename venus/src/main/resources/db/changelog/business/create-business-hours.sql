--liquibase formatted sql
--changeset kkumar:business_hours
CREATE TABLE business_hours (
    id BIGSERIAL PRIMARY KEY,
    business_id BIGINT NOT NULL REFERENCES business_profile(business_id),

    monday_is_open BOOLEAN,
    monday_open_time VARCHAR(25),
    monday_close_time VARCHAR(25),

    tuesday_is_open BOOLEAN,
    tuesday_open_time VARCHAR(25),
    tuesday_close_time VARCHAR(25),

    wednesday_is_open BOOLEAN,
    wednesday_open_time VARCHAR(25),
    wednesday_close_time VARCHAR(25),

    thursday_is_open BOOLEAN,
    thursday_open_time VARCHAR(25),
    thursday_close_time VARCHAR(25),

    friday_is_open BOOLEAN,
    friday_open_time VARCHAR(25),
    friday_close_time VARCHAR(25),

    saturday_is_open BOOLEAN,
    saturday_open_time VARCHAR(25),
    saturday_close_time VARCHAR(25),

    sunday_is_open BOOLEAN,
    sunday_open_time VARCHAR(25),
    sunday_close_time VARCHAR(25)
);
-- Index to quickly find business hours by business_id
CREATE INDEX idx_business_hours_business_id ON business_hours(business_id);