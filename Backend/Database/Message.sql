CREATE TABLE MESSAGE (
    id INT PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1),
    sender INT,
    FOREIGN KEY (sender) REFERENCES Account,
    receiver INT,
    FOREIGN KEY (receiver) REFERENCES Account,
    content VARCHAR(4096),
    time_sent VARCHAR(255),
    is_received BOOLEAN,
    is_read BOOLEAN
);

INSERT INTO
    MESSAGE (
        sender,
        receiver,
        content,
        time_sent,
        is_received,
        is_read
    )
VALUES
    (
        0,
        1,
        'Hello World',
        '2021-01-01 00:00:00',
        true,
        true
    ),
    (
        1,
        0,
        'Hello World',
        '2021-01-01 00:00:00',
        true,
        true
    );