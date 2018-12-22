CREATE TABLE document
(
    id SERIAL PRIMARY KEY,
    date TIMESTAMP WITH TIME ZONE,
    info JSONB,
    raw_html TEXT
);
