CREATE TABLE population
(
    id          BIGSERIAL PRIMARY KEY,
    population  FLOAT,
    country_code VARCHAR(3) NOT NULL,
    date         DATE       NOT NULL,
    FOREIGN KEY (country_code) REFERENCES country (code),
    UNIQUE (date, country_code)
);
