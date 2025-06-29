DROP TABLE IF EXISTS transaction_jeonse_apartment;
DROP TABLE IF EXISTS transaction_jeonse_officetel;
DROP TABLE IF EXISTS transaction_jeonse_rowhouse;

CREATE TABLE transaction_jeonse_apartment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    address VARCHAR(50) NOT NULL,
    bun VARCHAR(4) NOT NULL,
    ji VARCHAR(4) NOT NULL,
    floor VARCHAR(4) NOT NULL,
    area VARCHAR(10) NOT NULL,
    contract_year_month VARCHAR(10) NOT NULL,
    price VARCHAR(20) NOT NULL,
    housing_type VARCHAR(10) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE transaction_jeonse_officetel (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    address VARCHAR(50) NOT NULL,
    bun VARCHAR(4) NOT NULL,
    ji VARCHAR(4) NOT NULL,
    floor VARCHAR(4) NOT NULL,
    area VARCHAR(10) NOT NULL,
    contract_year_month VARCHAR(10) NOT NULL,
    price VARCHAR(20) NOT NULL,
    housing_type VARCHAR(10) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE transaction_jeonse_rowhouse (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    address VARCHAR(50) NOT NULL,
    bun VARCHAR(4) NOT NULL,
    ji VARCHAR(4) NOT NULL,
    floor VARCHAR(4) NOT NULL,
    area VARCHAR(10) NOT NULL,
    contract_year_month VARCHAR(10) NOT NULL,
    price VARCHAR(20) NOT NULL,
    housing_type VARCHAR(10) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);