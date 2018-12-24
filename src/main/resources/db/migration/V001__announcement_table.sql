CREATE TABLE document
(
    id SERIAL PRIMARY KEY,
    date TIMESTAMP WITH TIME ZONE,
    info JSON,
    raw TEXT
);
