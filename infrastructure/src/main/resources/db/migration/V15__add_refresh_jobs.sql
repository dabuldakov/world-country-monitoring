CREATE TABLE refresh_job
(
    id            BIGSERIAL PRIMARY KEY,
    feature       VARCHAR(50)  NOT NULL,
    country_code  VARCHAR(3),
    status        VARCHAR(20)  NOT NULL,
    total         INT          NOT NULL DEFAULT 0,
    processed     INT          NOT NULL DEFAULT 0,
    failed        INT          NOT NULL DEFAULT 0,
    created_at    TIMESTAMPTZ  NOT NULL DEFAULT now(),
    started_at    TIMESTAMPTZ,
    finished_at   TIMESTAMPTZ,
    error_message TEXT
);

CREATE TABLE refresh_job_item
(
    id            BIGSERIAL PRIMARY KEY,
    job_id        BIGINT       NOT NULL REFERENCES refresh_job (id) ON DELETE CASCADE,
    country_code  VARCHAR(3)   NOT NULL,
    status        VARCHAR(20)  NOT NULL,
    error_message TEXT,
    updated_at    TIMESTAMPTZ
);

CREATE UNIQUE INDEX ux_refresh_job_active
    ON refresh_job (feature, COALESCE(country_code, ''))
    WHERE status IN ('QUEUED', 'RUNNING');

CREATE TABLE data_refresh_country_status
(
    id              BIGSERIAL PRIMARY KEY,
    feature         VARCHAR(50) NOT NULL,
    country_code    VARCHAR(3)  NOT NULL,
    last_updated_at BIGINT,
    status          VARCHAR(20),
    error_message   TEXT,
    UNIQUE (feature, country_code)
);