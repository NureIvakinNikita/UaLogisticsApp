ALTER TABLE project.company_commander
    ADD COLUMN brigade_commander_id INTEGER;

ALTER TABLE project.plat_commander
    ADD COLUMN brigade_commander_id INTEGER;

ALTER TABLE project.supply_request
    ADD COLUMN brigade_commander_id INTEGER;