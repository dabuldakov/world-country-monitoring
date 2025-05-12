CREATE TABLE country
(
    code VARCHAR(3) PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE international_reserve
(
    id                 BIGSERIAL PRIMARY KEY,
    amount             FLOAT,
    foreign_exchange   FLOAT,
    monetary_gold      FLOAT,
    monetary_gold_tonn FLOAT,
    country_code       VARCHAR(3) NOT NULL,
    date               DATE       NOT NULL,
    FOREIGN KEY (country_code) REFERENCES country (code),
    UNIQUE (date, country_code)
);

CREATE TABLE gross_domestic_product
(
    id                        BIGSERIAL PRIMARY KEY,
    absolut                   FLOAT,
    purchasing_power_parities FLOAT,
    current_amount            FLOAT,
    country_code              VARCHAR(3) NOT NULL,
    date                      DATE       NOT NULL,
    FOREIGN KEY (country_code) REFERENCES country (code),
    UNIQUE (date, country_code)
);

CREATE TABLE debt
(
    id                        BIGSERIAL PRIMARY KEY,
    foreign_amount            FLOAT,
    percentage_to_gdp         FLOAT,
    country_code              VARCHAR(3) NOT NULL,
    date                      DATE       NOT NULL,
    FOREIGN KEY (country_code) REFERENCES country (code),
    UNIQUE (date, country_code)
)