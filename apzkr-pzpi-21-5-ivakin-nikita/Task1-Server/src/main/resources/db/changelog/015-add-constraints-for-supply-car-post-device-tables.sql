-- Додавання зовнішнього ключа до таблиці post
ALTER TABLE project.post
    ADD CONSTRAINT fk_post_scanning_device
        FOREIGN KEY (scanning_device_id)
            REFERENCES project.scanning_device(id);

-- Додавання зовнішнього ключа до таблиці scanning_device
ALTER TABLE project.scanning_device
    ADD CONSTRAINT fk_scanning_device_post
        FOREIGN KEY (post_id)
            REFERENCES project.post(id);

-- Додавання зовнішнього ключа до таблиці supply_car
ALTER TABLE project.supply_car
    ADD CONSTRAINT fk_supply_car_supply_request
        FOREIGN KEY (request_id)
            REFERENCES project.supply_request(request_id);

-- Додавання зовнішнього ключа до таблиці supply_car
ALTER TABLE project.supply_car
    ADD CONSTRAINT fk_supply_car_logistic_company
        FOREIGN KEY (logistic_company_id)
            REFERENCES project.logistic_company(logistic_company_id);
