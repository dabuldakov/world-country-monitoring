CREATE TABLE money_supply
(
    id                                BIGSERIAL PRIMARY KEY,
    amount_local_currency             FLOAT,
    amount_usd                        FLOAT,
    country_code                      VARCHAR(3) NOT NULL,
    date                              DATE       NOT NULL,
    FOREIGN KEY (country_code) REFERENCES country (code),
    UNIQUE (date, country_code)
);