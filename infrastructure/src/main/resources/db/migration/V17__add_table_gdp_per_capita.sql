CREATE TABLE gross_domestic_product_per_capita
(
    id           BIGSERIAL PRIMARY KEY,
    amount       FLOAT,
    country_code VARCHAR(3) NOT NULL,
    date         DATE       NOT NULL,
    FOREIGN KEY (country_code) REFERENCES country (code),
    UNIQUE (date, country_code)
);