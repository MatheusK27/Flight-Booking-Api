CREATE TABLE airports (
                          id BIGSERIAL PRIMARY KEY,
                          code VARCHAR(3) NOT NULL UNIQUE,
                          name VARCHAR(100) NOT NULL,
                          city VARCHAR(100) NOT NULL,
                          country VARCHAR(100) NOT NULL
);

CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(20) NOT NULL DEFAULT 'PASSENGER',
                       created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE flights (
                         id BIGSERIAL PRIMARY KEY,
                         flight_number VARCHAR(10) NOT NULL UNIQUE,
                         origin_id BIGINT NOT NULL REFERENCES airports(id),
                         destination_id BIGINT NOT NULL REFERENCES airports(id),
                         departure_time TIMESTAMP NOT NULL,
                         arrival_time TIMESTAMP NOT NULL,
                         total_seats INT NOT NULL,
                         available_seats INT NOT NULL,
                         price DECIMAL(10,2) NOT NULL,
                         status VARCHAR(20) NOT NULL DEFAULT 'SCHEDULED'
);

CREATE TABLE bookings (
                          id BIGSERIAL PRIMARY KEY,
                          user_id BIGINT NOT NULL REFERENCES users(id),
                          flight_id BIGINT NOT NULL REFERENCES flights(id),
                          seat_number VARCHAR(5) NOT NULL,
                          seat_class VARCHAR(20) NOT NULL,
                          status VARCHAR(20) NOT NULL DEFAULT 'CONFIRMED',
                          total_price DECIMAL(10,2) NOT NULL,
                          booked_at TIMESTAMP DEFAULT NOW(),
                          cancelled_at TIMESTAMP
);