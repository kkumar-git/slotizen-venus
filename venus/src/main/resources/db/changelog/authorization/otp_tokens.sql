--liquibase formatted sql
--changeset kkumar:auth
CREATE TABLE IF NOT EXISTS otp_tokens (
    id BIGSERIAL PRIMARY KEY,
    otp VARCHAR(20) NOT NULL,
    expiry TIMESTAMP NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    used BOOLEAN NOT NULL DEFAULT false,
    type VARCHAR(50) NOT NULL DEFAULT 'REGISTRATION',
    user_id BIGINT NOT NULL,
    CONSTRAINT fk_otp_user FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE
);
