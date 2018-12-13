CREATE TABLE announcement
(
    id SERIAL PRIMARY KEY,
    number INT,
    date TIMESTAMP WITH TIME ZONE,
    info JSONB,
    raw_html TEXT
);
