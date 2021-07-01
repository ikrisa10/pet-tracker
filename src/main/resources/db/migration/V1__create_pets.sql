DROP TABLE IF EXISTS species;

CREATE TABLE species (
  id SERIAL PRIMARY KEY,
  species_name VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS pets;

CREATE TABLE pets (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  species_id INTEGER NOT NULL REFERENCES species(id),
  breed VARCHAR(255) NOT NULL,
  age INTEGER NOT NULL,
  neutered BOOLEAN NOT NULL
);

CREATE TABLE dummies (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  species VARCHAR(255) NOT NULL,
  breed VARCHAR(255) NOT NULL,
  age INTEGER NOT NULL,
  neutered BOOLEAN NOT NULL
);