CREATE SCHEMA my_schema;
GRANT ALL ON SCHEMA my_schema TO docker_user;
ALTER USER docker_user SET search_path TO my_schema;

CREATE TABLE IF NOT EXISTS my_schema.rent_office(
    id SERIAL PRIMARY KEY,
    title TEXT NOT NULL,
    address TEXT NOT NULL
)

