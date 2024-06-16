ALTER TABLE project.given_resources
    ADD COLUMN issue_date DATE;

ALTER TABLE project.given_resources
    ADD COLUMN allocation_of_resources VARCHAR(255);
