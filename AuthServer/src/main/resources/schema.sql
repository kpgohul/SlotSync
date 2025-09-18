-- Optional: Drop existing tables (only if you're recreating from scratch)
--DROP TABLE IF EXISTS customer_authority;
--DROP TABLE IF EXISTS customer;
--DROP TABLE IF EXISTS authority;

-- Create 'authority' table
CREATE TABLE IF NOT EXISTS authority (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description VARCHAR(255) UNIQUE
);

-- Create 'customer' table
CREATE TABLE IF NOT EXISTS customer (
    email VARCHAR(255) PRIMARY KEY,
    id BIGINT UNIQUE,
    password VARCHAR(255) NOT NULL,
    sync_status VARCHAR(50),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

-- Create join table 'customer_authority' for many-to-many relationship
CREATE TABLE IF NOT EXISTS customer_authority (
    customer_id VARCHAR(255) NOT NULL,
    authority_id BIGINT NOT NULL,
    PRIMARY KEY (customer_id, authority_id),
    FOREIGN KEY (customer_id) REFERENCES customer(email) ON DELETE CASCADE,
    FOREIGN KEY (authority_id) REFERENCES authority(id) ON DELETE CASCADE
);
