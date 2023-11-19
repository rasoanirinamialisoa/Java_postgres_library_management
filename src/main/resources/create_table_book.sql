
CREATE TABLE IF NOT EXISTS book (
        book_id SERIAL PRIMARY KEY,
        book_name VARCHAR(255) NOT NULL,
        page_numbers INTEGER,
        release_date DATE,
        author_id INT,
        topic_id INT,
        FOREIGN KEY (author_id) REFERENCES author (author_id),
        FOREIGN KEY (topic_id) REFERENCES topic (topic_id),
        book_status VARCHAR(20) DEFAULT 'available' CHECK (book_status IN ('available', 'borrowed'))
);

INSERT INTO book (book_name, page_numbers, release_date, author_id, topic_id, book_status) VALUES
('Book1', 200, '2022-01-01', '1', 2, 'available'),
('Book2', 150, '2022-02-01', '2', 1, 'available'),
('Book3', 300, '2022-03-01', '3', 3, 'borrowed');