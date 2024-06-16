-- Sequence for post table
CREATE SEQUENCE project.post_id_seq START WITH 1 INCREMENT BY 1;
ALTER TABLE project.post ALTER COLUMN id SET DEFAULT nextval('project.post_id_seq');

-- Sequence for scanning_device table
CREATE SEQUENCE project.scanning_device_id_seq START WITH 1 INCREMENT BY 1;
ALTER TABLE project.scanning_device ALTER COLUMN id SET DEFAULT nextval('project.scanning_device_id_seq');

-- Sequence for supply_car table
CREATE SEQUENCE project.supply_car_id_seq START WITH 1 INCREMENT BY 1;
ALTER TABLE project.supply_car ALTER COLUMN id SET DEFAULT nextval('project.supply_car_id_seq');
