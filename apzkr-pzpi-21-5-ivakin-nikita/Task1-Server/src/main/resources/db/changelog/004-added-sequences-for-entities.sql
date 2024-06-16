-- BattalionCommander Entity
CREATE SEQUENCE project.battalion_commander_id_seq START WITH 1 INCREMENT BY 1;
ALTER TABLE project.battalion_commander ALTER COLUMN battalion_commander_id SET DEFAULT nextval('project.battalion_commander_id_seq');

-- BrigadeCommander Entity
CREATE SEQUENCE project.brigade_commander_id_seq START WITH 1 INCREMENT BY 1;
ALTER TABLE project.brigade_commander ALTER COLUMN brigade_commander_id SET DEFAULT nextval('project.brigade_commander_id_seq');

-- CompanyCommander Entity
CREATE SEQUENCE project.company_commander_id_seq START WITH 1 INCREMENT BY 1;
ALTER TABLE project.company_commander ALTER COLUMN company_commander_id SET DEFAULT nextval('project.company_commander_id_seq');

-- LogisticCommander Entity
CREATE SEQUENCE project.logistic_commander_id_seq START WITH 1 INCREMENT BY 1;
ALTER TABLE project.logistic_commander ALTER COLUMN logistic_commander_id SET DEFAULT nextval('project.logistic_commander_id_seq');

-- PlatCommander Entity
CREATE SEQUENCE project.plat_commander_id_seq START WITH 1 INCREMENT BY 1;
ALTER TABLE project.plat_commander ALTER COLUMN plat_commander_id SET DEFAULT nextval('project.plat_commander_id_seq');

/*-- RegimentCommander Entity
CREATE SEQUENCE project.regiment_commander_id_seq START WITH 1 INCREMENT BY 1;
ALTER TABLE project.regiment_commander ALTER COLUMN regiment_commander_id SET DEFAULT nextval('project.regiment_commander_id_seq');
*/
-- BattalionGroup Entity
CREATE SEQUENCE project.battalion_group_id_seq START WITH 1 INCREMENT BY 1;
ALTER TABLE project.battalion_group ALTER COLUMN battalion_group_id SET DEFAULT nextval('project.battalion_group_id_seq');

-- BrigadeGroup Entity
CREATE SEQUENCE project.brigade_group_id_seq START WITH 1 INCREMENT BY 1;
ALTER TABLE project.brigade_group ALTER COLUMN brigade_group_id SET DEFAULT nextval('project.brigade_group_id_seq');

-- CompanyGroup Entity
CREATE SEQUENCE project.company_group_id_seq START WITH 1 INCREMENT BY 1;
ALTER TABLE project.company_group ALTER COLUMN company_group_id SET DEFAULT nextval('project.company_group_id_seq');

-- LogisticCompany Entity
CREATE SEQUENCE project.logistic_company_id_seq START WITH 1 INCREMENT BY 1;
ALTER TABLE project.logistic_company ALTER COLUMN logistic_company_id SET DEFAULT nextval('project.logistic_company_id_seq');

-- PlatGroup Entity
CREATE SEQUENCE project.plat_group_id_seq START WITH 1 INCREMENT BY 1;
ALTER TABLE project.plat_group ALTER COLUMN plat_group_id SET DEFAULT nextval('project.plat_group_id_seq');

/*-- RegimentGroup Entity
CREATE SEQUENCE project.regiment_group_id_seq START WITH 1 INCREMENT BY 1;
ALTER TABLE project.regiment_group ALTER COLUMN regiment_group_id SET DEFAULT nextval('project.regiment_group_id_seq');*/