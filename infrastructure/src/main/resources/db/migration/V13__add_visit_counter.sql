CREATE TABLE visit_counter
(
    id    BIGINT PRIMARY KEY,
    total BIGINT NOT NULL
);

INSERT INTO visit_counter (id, total)
VALUES (1, 0);
