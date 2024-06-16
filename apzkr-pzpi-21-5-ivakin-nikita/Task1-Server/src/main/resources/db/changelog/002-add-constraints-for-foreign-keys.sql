-- Додавання зовнішнього ключа до таблиці project.brigade_commander
ALTER TABLE project.brigade_commander
    ADD CONSTRAINT fk_brigade_commander_group FOREIGN KEY (brigade_group_id) REFERENCES project.brigade_group(brigade_group_id);

-- Додавання зовнішнього ключа до таблиці project.brigade_group
ALTER TABLE project.brigade_group
    ADD CONSTRAINT fk_brigade_group_commander FOREIGN KEY (brigade_commander_id) REFERENCES project.brigade_commander(brigade_commander_id);

/*-- Додавання зовнішнього ключа до таблиці project.regiment_commander
ALTER TABLE project.regiment_commander
    ADD CONSTRAINT fk_regiment_commander_group FOREIGN KEY (regiment_group_id) REFERENCES project.regiment_group(regiment_group_id);

ALTER TABLE project.regiment_commander
    ADD CONSTRAINT fk_regiment_commander_brigade FOREIGN KEY (brigade_commander_id) REFERENCES project.brigade_commander(brigade_commander_id);

ALTER TABLE project.regiment_commander
    ADD CONSTRAINT fk_regiment_commander_brigade_group FOREIGN KEY (brigade_group_id) REFERENCES project.brigade_group(brigade_group_id);

-- Додавання зовнішнього ключа до таблиці project.regiment_group
ALTER TABLE project.regiment_group
    ADD CONSTRAINT fk_regiment_group_commander FOREIGN KEY (regiment_commander_id) REFERENCES project.regiment_commander(regiment_commander_id);

ALTER TABLE project.regiment_group
    ADD CONSTRAINT fk_regiment_group_brigade FOREIGN KEY (brigade_group_id) REFERENCES project.brigade_group(brigade_group_id);
*/
-- Додавання зовнішнього ключа до таблиці project.battalion_commander
ALTER TABLE project.battalion_commander
    ADD CONSTRAINT fk_battalion_commander_group FOREIGN KEY (battalion_group_id) REFERENCES project.battalion_group(battalion_group_id);

ALTER TABLE project.battalion_commander
    ADD CONSTRAINT fk_battalion_commander_brigade FOREIGN KEY (brigade_commander_id) REFERENCES project.brigade_commander(brigade_commander_id);

ALTER TABLE project.battalion_commander
    ADD CONSTRAINT fk_battalion_commander_brigade_group FOREIGN KEY (brigade_group_id) REFERENCES project.brigade_group(brigade_group_id);

-- Додавання зовнішнього ключа до таблиці project.battalion_group
ALTER TABLE project.battalion_group
    ADD CONSTRAINT fk_battalion_group_commander FOREIGN KEY (battalion_commander_id) REFERENCES project.battalion_commander(battalion_commander_id);

ALTER TABLE project.battalion_group
    ADD CONSTRAINT fk_battalion_group_brigade FOREIGN KEY (brigade_group_id) REFERENCES project.brigade_group(brigade_group_id);

-- Додавання зовнішнього ключа до таблиці project.company_commander
ALTER TABLE project.company_commander
    ADD CONSTRAINT fk_company_commander_group FOREIGN KEY (company_group_id) REFERENCES project.company_group(company_group_id);

ALTER TABLE project.company_commander
    ADD CONSTRAINT fk_company_commander_battalion FOREIGN KEY (battalion_commander_id) REFERENCES project.battalion_commander(battalion_commander_id);

ALTER TABLE project.company_commander
    ADD CONSTRAINT fk_company_commander_battalion_group FOREIGN KEY (battalion_group_id) REFERENCES project.battalion_group(battalion_group_id);

-- Додавання зовнішнього ключа до таблиці project.company_group
ALTER TABLE project.company_group
    ADD CONSTRAINT fk_company_group_commander FOREIGN KEY (company_commander_id) REFERENCES project.company_commander(company_commander_id);

ALTER TABLE project.company_group
    ADD CONSTRAINT fk_company_group_battalion FOREIGN KEY (battalion_group_id) REFERENCES project.battalion_group(battalion_group_id);

-- Додавання зовнішнього ключа до таблиці project.plat_commander
ALTER TABLE project.plat_commander
    ADD CONSTRAINT fk_plat_commander_group FOREIGN KEY (plat_group_id) REFERENCES project.plat_group(plat_group_id);

ALTER TABLE project.plat_commander
    ADD CONSTRAINT fk_plat_commander_company FOREIGN KEY (company_commander_id) REFERENCES project.company_commander(company_commander_id);

ALTER TABLE project.plat_commander
    ADD CONSTRAINT fk_plat_commander_company_group FOREIGN KEY (company_group_id) REFERENCES project.company_group(company_group_id);

-- Додавання зовнішнього ключа до таблиці project.plat_group
ALTER TABLE project.plat_group
    ADD CONSTRAINT fk_plat_group_commander FOREIGN KEY (plat_commander_id) REFERENCES project.plat_commander(plat_commander_id);

ALTER TABLE project.plat_group
    ADD CONSTRAINT fk_plat_group_company FOREIGN KEY (company_group_id) REFERENCES project.company_group(company_group_id);

-- Додавання зовнішнього ключа до таблиці project.regiment_commander
ALTER TABLE project.logistic_commander
    ADD CONSTRAINT fk_logistic_commander_company FOREIGN KEY (logistic_company_id) REFERENCES project.logistic_company(logistic_company_id);

ALTER TABLE project.logistic_commander
    ADD CONSTRAINT fk_logistic_commander_brigade FOREIGN KEY (brigade_commander_id) REFERENCES project.brigade_commander(brigade_commander_id);

ALTER TABLE project.logistic_commander
    ADD CONSTRAINT fk_logistic_commander_brigade_group FOREIGN KEY (brigade_group_id) REFERENCES project.brigade_group(brigade_group_id);

-- Додавання зовнішнього ключа до таблиці project.regiment_group
ALTER TABLE project.logistic_company
    ADD CONSTRAINT fk_logistic_group_commander FOREIGN KEY (logistic_commander_id) REFERENCES project.logistic_commander(logistic_commander_id);

ALTER TABLE project.logistic_company
    ADD CONSTRAINT fk_logistic_group_brigade FOREIGN KEY (brigade_group_id) REFERENCES project.brigade_group(brigade_group_id);
