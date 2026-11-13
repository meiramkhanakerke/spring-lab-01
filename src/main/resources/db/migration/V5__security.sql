CREATE TABLE app_user (
                          id            BIGSERIAL PRIMARY KEY,
                          username      VARCHAR(60)  NOT NULL UNIQUE,
                          password_hash VARCHAR(100) NOT NULL,
                          enabled       BOOLEAN      NOT NULL DEFAULT TRUE
);

CREATE TABLE user_role (
                           user_id BIGINT      NOT NULL REFERENCES app_user (id) ON DELETE CASCADE,
                           role    VARCHAR(20) NOT NULL,
                           PRIMARY KEY (user_id, role)
);

-- user -> user123 | admin -> admin123
INSERT INTO app_user (username, password_hash, enabled) VALUES
                                                            ('user',  '$2a$10$abcdefghijklmnopqrstuuqVwskozrMIYHaV2U8izbKS09gvJ9bm2', TRUE),
                                                            ('admin', '$2a$10$123456789012345678901u./zCLeARnBuyfsw17qkjYMfd7L4o2om', TRUE);

INSERT INTO user_role (user_id, role)
SELECT id, 'USER'  FROM app_user;
INSERT INTO user_role (user_id, role)
SELECT id, 'ADMIN' FROM app_user WHERE username = 'admin';