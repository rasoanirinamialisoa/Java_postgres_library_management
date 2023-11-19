
CREATE TABLE IF NOT EXISTS subscribers (
    subscriber_id SERIAL PRIMARY KEY,
    subscriber_username VARCHAR(255) NOT NULL,
    subscriber_password VARCHAR(255) NOT NULL,
    subscription_start_date DATE,
    subscription_end_date DATE
    );

INSERT INTO subscribers (subscriber_username, subscriber_password, subscription_start_date, subscription_end_date)
VALUES
    ('John', 'john123', '2023-11-19', '2024-11-19'),
    ('Eddy', 'eddy123', '2023-11-20', '2024-11-20'),
    ('Hermione', 'hermione123', '2023-11-21', '2024-11-21');



