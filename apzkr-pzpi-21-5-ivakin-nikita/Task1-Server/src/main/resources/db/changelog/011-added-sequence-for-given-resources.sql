CREATE SEQUENCE project.given_resources_id_seq START WITH 1 INCREMENT BY 1;
ALTER TABLE project.given_resources ALTER COLUMN given_resources_id SET DEFAULT nextval('project.given_resources_id_seq');
