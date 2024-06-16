ALTER TABLE project.supply_request
    ADD COLUMN commander_id INTEGER NOT NULL,
    ADD COLUMN military_group_id INTEGER NOT NULL,
    ADD COLUMN role_of_commander VARCHAR(255) NOT NULL;