CREATE TABLE IF NOT EXISTS rent_office(
    id SERIAL PRIMARY KEY,
    title TEXT NOT NULL,
    address TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS car(
  id SERIAL PRIMARY KEY,
  vin TEXT NOT NULL,
  model TEXT NOT NULL,
  year INT NOT NULL,
  rent_office_id INT NOT NULL,
  foreign key (rent_office_id) references rent_office(id) ON DELETE RESTRICT
                                                          ON UPDATE NO ACTION
);