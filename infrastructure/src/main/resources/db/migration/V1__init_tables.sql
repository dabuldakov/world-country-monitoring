-- Создание таблицы country
CREATE TABLE country (
                         code VARCHAR(3) PRIMARY KEY,  -- Код страны (ISO 3166-1 alpha-3)
                         name VARCHAR(100) NOT NULL     -- Название страны
);

-- Создание таблицы международных резервов
CREATE TABLE international_reserve (
                                       id BIGSERIAL PRIMARY KEY,
                                       amount BIGINT NOT NULL,
                                       foreign_exchange BIGINT NOT NULL,
                                       monetary_gold BIGINT NOT NULL,
                                       country_code VARCHAR(3) NOT NULL,
                                       date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                       FOREIGN KEY (country_code) REFERENCES country(code), -- Объявление внешнего ключа
                                       UNIQUE (date, country_code));  -- Уникальное ограничение для сочетания date и country_code