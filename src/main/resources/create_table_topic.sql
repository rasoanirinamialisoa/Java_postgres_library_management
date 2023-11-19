
CREATE TABLE IF NOT EXISTS topic (
        topic_id SERIAL PRIMARY KEY,
        topic_name VARCHAR(20) NOT NULL CHECK (topic_name IN ('COMEDY', 'ROMANCE', 'OTHER'))
    );

INSERT INTO topic (topic_name) VALUES
    ('COMEDY'),
    ('ROMANCE'),
    ('OTHER');
