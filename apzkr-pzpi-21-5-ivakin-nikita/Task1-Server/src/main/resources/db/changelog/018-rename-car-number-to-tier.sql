ALTER TABLE project.supply_car RENAME COLUMN car_number TO tier;
ALTER TABLE project.supply_car ALTER COLUMN tier TYPE INT USING tier::integer;
