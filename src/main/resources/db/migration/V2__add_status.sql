ALTER TABLE accounts
    ADD COLUMN status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE';

CREATE TABLE audit_logs (
                            id BIGSERIAL PRIMARY KEY,
                            action VARCHAR(255) NOT NULL,
                            created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP
);