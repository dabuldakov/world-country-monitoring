-- Создание базы данных
CREATE DATABASE wcmdb;

-- Подключение к новой базе данных
\c wcmdb;

CREATE SCHEMA IF NOT EXISTS public;

CREATE TABLE public.international_reserve (
                                                   id BIGSERIAL PRIMARY KEY,
                                                   amount BIGINT NOT NULL,
                                                   foreign_exchange BIGINT NOT NULL,
                                                   monetary_gold BIGINT NOT NULL,
                                                   country_code TEXT NOT NULL,
                                                   date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);