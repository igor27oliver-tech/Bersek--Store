CREATE TABLE banda (
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    name          VARCHAR(150) NOT NULL,
    country       VARCHAR(100),
    description   VARCHAR(2000),
    year_start    INT
);