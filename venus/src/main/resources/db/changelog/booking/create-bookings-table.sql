--liquibase formatted sql
--changeset kkumar:bookings_table

CREATE TABLE bookings (
    id BIGSERIAL PRIMARY KEY,
    business_id BIGINT NOT NULL,
    client_id BIGINT NOT NULL,
    service_id BIGINT NOT NULL,
    staff_id BIGINT NOT NULL,
    booking_date DATE NOT NULL,
    booking_time TIME NOT NULL,
    duration_minutes INTEGER NOT NULL,
    status VARCHAR(50) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    notes TEXT,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT,
    
    CONSTRAINT fk_bookings_business FOREIGN KEY (business_id) REFERENCES business_profile(business_id),
    CONSTRAINT fk_bookings_client FOREIGN KEY (client_id) REFERENCES clients(id),
    CONSTRAINT fk_bookings_service FOREIGN KEY (service_id) REFERENCES business_service(id),
    CONSTRAINT fk_bookings_staff FOREIGN KEY (staff_id) REFERENCES business_staff_members(id),
    CONSTRAINT fk_bookings_created_by FOREIGN KEY (created_by) REFERENCES users(id)
);

-- Create indexes for performance
CREATE INDEX idx_bookings_business_date ON bookings(business_id, booking_date);
CREATE INDEX idx_bookings_client ON bookings(client_id);
CREATE INDEX idx_bookings_staff ON bookings(staff_id);
CREATE INDEX idx_bookings_status ON bookings(status);
CREATE INDEX idx_bookings_staff_date_time ON bookings(staff_id, booking_date, booking_time);
