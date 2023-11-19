
CREATE TABLE IF NOT EXISTS author (
    author_id SERIAL PRIMARY KEY,
    author_name VARCHAR(255) NOT NULL,
    sex VARCHAR(1) CHECK (sex IN ('M', 'F'))

    );

INSERT INTO author (author_id, author_name, sex) VALUES
('1', 'Auteur 1', 'M'),
('2', 'Auteur 2', 'F'),
('3', 'Auteur 3', 'M');
