-- Таблицы из Лабораторной 8
CREATE TABLE author (
                        id BIGSERIAL PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        country VARCHAR(255)
);

CREATE TABLE tag (
                     id BIGSERIAL PRIMARY KEY,
                     name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE book (
                      id BIGSERIAL PRIMARY KEY,
                      title VARCHAR(120) NOT NULL,
                      genre VARCHAR(20) NOT NULL,
                      year INT NOT NULL,
                      archived BOOLEAN NOT NULL DEFAULT FALSE,
                      created_at TIMESTAMP WITHOUT TIME ZONE,
                      version BIGINT NOT NULL DEFAULT 0,
                      author_id BIGINT REFERENCES author(id)
);

CREATE TABLE book_tag (
                          book_id BIGINT REFERENCES book(id) ON DELETE CASCADE,
                          tag_id BIGINT REFERENCES tag(id) ON DELETE CASCADE,
                          PRIMARY KEY (book_id, tag_id)
);

-- Таблица для Лабораторной 9
CREATE TABLE accounts (
                          id BIGSERIAL PRIMARY KEY,
                          owner_name VARCHAR(120) NOT NULL,
                          balance DECIMAL(19, 2) NOT NULL,
                          version BIGINT NOT NULL DEFAULT 0
);

INSERT INTO accounts (owner_name, balance, version) VALUES
                                                        ('Alice', 1000.00, 0),
                                                        ('Bob', 500.00, 0);