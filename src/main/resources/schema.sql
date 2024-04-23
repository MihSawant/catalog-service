DROP TABLE IF EXISTS book;
CREATE TABLE book(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    isbn varchar(255) NOT NULL,
    title varchar(255) NOT NULL,
    author varchar(255) NOT NULL,
    price integer NOT NULL
)