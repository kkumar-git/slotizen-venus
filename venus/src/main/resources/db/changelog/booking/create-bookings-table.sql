--liquibase formatted sql
--changeset kkumar:bookings_table

CREATE TABLE IF NOT EXISTS bookings (
    id BIGSERIAL PRIMARY KEY,
    business_id BIGINT NOT NULL,
    client_id BIGINT NOT NULL,
    service_id BIGINT NOT NULL,
    staff_id BIGINT NOT NULL,
    booking_date DATE NOT NULL,
    booking_time TIME NOT NULL,
    duration_minutes INTEGER NOT NULL,
    status VARCHAR(20) NOT NULL,
    price DECIMAL(10,2),
    notes TEXT,
    created_date TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT NOT NULL,
    last_modified_by BIGINT NOT NULL,
    CONSTRAINT fk_bookings_business FOREIGN KEY (business_id) REFERENCES business_profile(id),
    CONSTRAINT fk_bookings_client FOREIGN KEY (client_id) REFERENCES clients(id),
    CONSTRAINT fk_bookings_service FOREIGN KEY (service_id) REFERENCES business_service(id),
    CONSTRAINT fk_bookings_staff FOREIGN KEY (staff_id) REFERENCES staff_members(id),
    CONSTRAINT fk_bookings_created_by FOREIGN KEY (created_by) REFERENCES users(id),
    CONSTRAINT fk_bookings_modified_by FOREIGN KEY (last_modified_by) REFERENCES users(id)
);

-- Create indexes for performance
CREATE INDEX idx_bookings_business_date ON bookings (business_id, booking_date);
CREATE INDEX idx_bookings_staff_date_time ON bookings (staff_id, booking_date, booking_time);
CREATE INDEX idx_bookings_status ON bookings (status);
CREATE INDEX idx_bookings_client ON bookings (client_id);
CREATE INDEX idx_bookings_service ON bookings (service_id);
CREATE INDEX idx_bookings_business_status ON bookings (business_id, status);
CREATE INDEX idx_bookings_business_staff ON bookings (business_id, staff_id);