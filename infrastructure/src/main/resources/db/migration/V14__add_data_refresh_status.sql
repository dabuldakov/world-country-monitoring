CREATE TABLE data_refresh_status
(
    feature         VARCHAR(50) PRIMARY KEY,
    last_updated_at BIGINT,
    status          VARCHAR(20),
    processed_count INT,
    error_message   TEXT
);