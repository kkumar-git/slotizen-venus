--liquibase formatted sql
--changeset kkumar:user_business

CREATE TABLE IF NOT EXISTS user_business (
    user_id BIGINT NOT NULL,
    business_id UUID NOT NULL,
    PRIMARY KEY (user_id, business_id),
    CONSTRAINT fk_user_business FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_user_business_profile FOREIGN KEY(business_id) REFERENCES business_profile(business_id) ON DELETE CASCADE
);

-- indexes
CREATE INDEX idx_user_business_user ON user_business(user_id);
CREATE INDEX idx_user_business_business ON user_business(business_id);