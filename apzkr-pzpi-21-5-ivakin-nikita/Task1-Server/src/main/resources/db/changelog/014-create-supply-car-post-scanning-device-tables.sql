-- Table for Post
CREATE TABLE project.post (
                              id SERIAL PRIMARY KEY,
                              location VARCHAR(255),
                              scanning_device_id INTEGER UNIQUE

);

-- Table for ScanningDevice
CREATE TABLE project.scanning_device (
                                         id SERIAL PRIMARY KEY,
                                         post_id INTEGER UNIQUE

);

-- Table for SupplyCar
CREATE TABLE project.supply_car (
                                    id SERIAL PRIMARY KEY,
                                    car_number VARCHAR(255),
                                    request_id INTEGER UNIQUE,
                                    logistic_company_id INTEGER UNIQUE

);