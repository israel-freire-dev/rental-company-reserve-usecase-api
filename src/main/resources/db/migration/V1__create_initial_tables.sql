
CREATE SCHEMA IF NOT EXISTS rentalcompany;


CREATE TABLE IF NOT EXISTS rentalcompany.tb_customer (
    id BIGSERIAL PRIMARY KEY,
    public_id VARCHAR(36)  NOT NULL UNIQUE,
    name VARCHAR(150) NOT NULL,
    document VARCHAR(14) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(15) NOT NULL,
    street VARCHAR(150) NOT NULL,
    number VARCHAR(10) NOT NULL,
    complement VARCHAR(100),
    neighborhood VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(2) NOT NULL,
    zip_code VARCHAR(9) NOT NULL,
    is_suspended BOOLEAN NOT NULL,
    has_pendency BOOLEAN NOT NULL
);


CREATE TABLE IF NOT EXISTS rentalcompany.tb_vehicle (
    vehicle_id BIGSERIAL PRIMARY KEY,
    public_id VARCHAR(36) NOT NULL UNIQUE,
    vehicle_model_name VARCHAR(255) NOT NULL,
    vehicle_type VARCHAR(36) NOT NULL,
    vehicle_year INTEGER NOT NULL,
    door_quantity INTEGER NOT NULL,
    fuel_type VARCHAR(36) NOT NULL,
    license_plate VARCHAR(7) NOT NULL UNIQUE,
    color VARCHAR(30) NOT NULL,
    mileage VARCHAR(255) NOT NULL,
    renavam VARCHAR(11) NOT NULL UNIQUE,
    vin VARCHAR(17) NOT NULL UNIQUE,
    vehicle_status VARCHAR(30) NOT NULL DEFAULT 'AVAILABLE',
    fipe_code VARCHAR(20),
    daily_rate_in_cents INT,
    fipe_value_in_cents INT,
    image_url VARCHAR(255),
    creation_date TIMESTAMP NOT NULL,
    update_date TIMESTAMP
);


CREATE TABLE IF NOT EXISTS rentalcompany.tb_reserve (
    id BIGSERIAL PRIMARY KEY,
    public_id VARCHAR(36) NULL UNIQUE,
    customer_name VARCHAR(150) NOT NULL,
    customer_document VARCHAR(14) NOT NULL,
    vehicle_model_name VARCHAR(255) NOT NULL,
    vehicle_year INTEGER NOT NULL,
    vehicle_license_plate VARCHAR(7) NOT NULL,
    amount_in_cents BIGINT NOT NULL,
    rental_date DATE NOT NULL,
    return_date DATE NOT NULL,
    reservation_status VARCHAR(30) NOT NULL DEFAULT 'ACTIVE',
    payment_status VARCHAR(30) NOT NULL DEFAULT 'PENDING',
    creation_date TIMESTAMP NOT NULL,
    update_date TIMESTAMP
);