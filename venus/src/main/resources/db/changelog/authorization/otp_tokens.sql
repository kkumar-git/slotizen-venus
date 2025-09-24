--liquibase formatted sql
--changeset kkumar:otp_tokens

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
-- Lookup OTPs quickly by user
CREATE INDEX idx_otp_tokens_user ON otp_tokens(user_id);

-- Lookup active (unused and unexpired) OTPs quickly
CREATE INDEX idx_otp_tokens_active ON otp_tokens(user_id, used, expiry);
