-- SupplyResources Entity
CREATE SEQUENCE project.supply_request_id_seq START WITH 1 INCREMENT BY 1;
ALTER TABLE project.supply_request ALTER COLUMN request_id SET DEFAULT nextval('project.supply_request_id_seq');

-- RequestResources Entity
CREATE SEQUENCE project.resources_request_id_seq START WITH 1 INCREMENT BY 1;
ALTER TABLE project.resources_request ALTER COLUMN resources_request_id SET DEFAULT nextval('project.resources_request_id_seq');
