-- Table for CarCheck
CREATE TABLE project.car_check (
                                   id SERIAL PRIMARY KEY,
                                   scanning_device_id INTEGER NOT NULL ,
                                   supply_car_id INTEGER NOT NULL,
                                   car_status VARCHAR(255),
                                   local_date_time TIMESTAMP,
                                   CONSTRAINT fk_car_check_scanning_device
                                       FOREIGN KEY (scanning_device_id)
                                           REFERENCES project.scanning_device(id),
                                   CONSTRAINT fk_car_check_supply_car
                                       FOREIGN KEY (supply_car_id)
                                           REFERENCES project.supply_car(id)
);