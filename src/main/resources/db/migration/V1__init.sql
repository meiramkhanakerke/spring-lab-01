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