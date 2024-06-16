
CREATE TABLE project._user (
   id SERIAL PRIMARY KEY,
   first_name VARCHAR(255),
   last_name VARCHAR(255),
   second_name VARCHAR(255),
   email VARCHAR(255) UNIQUE,
   passport_number VARCHAR(255) UNIQUE,
   password VARCHAR(255),
   rank VARCHAR(255),
   post VARCHAR(255),
   role VARCHAR(255)
);


CREATE TABLE project.token (
   id SERIAL PRIMARY KEY,
   token VARCHAR(255) UNIQUE,
   token_type VARCHAR(255),
   revoked BOOLEAN,
   expired BOOLEAN,
   user_id INTEGER REFERENCES project._user(id)
);


