CREATE TABLE life_expectancy
(
    id           BIGSERIAL PRIMARY KEY,
    years        FLOAT,
    country_code VARCHAR(3) NOT NULL,
    date         DATE       NOT NULL,
    FOREIGN KEY (country_code) REFERENCES country (code),
    UNIQUE (date, country_code)
);