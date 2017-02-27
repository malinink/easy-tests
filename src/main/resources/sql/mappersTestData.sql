----------------------
-- DROP FOREIGN KEYS
----------------------


----------------------
-- DROP TABLES
----------------------
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS issues;


----------------------
-- CREATE TABLES
----------------------

CREATE TABLE users (
  id         SERIAL      NOT NULL,
  first_name VARCHAR(30) NOT NULL,
  last_name  VARCHAR(30) NOT NULL,
  surname    VARCHAR(30) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE issues (
  id          SERIAL        NOT NULL,
  name        VARCHAR(100)  NOT NULL,
  author_id   SERIAL        NOT NULL,
  PRIMARY KEY (id)
)


----------------------
-- INSERT DATA
----------------------

INSERT INTO users (first_name, last_name, surname) VALUES
  ('FirstName1', 'LastName1', 'Surname1'),
  ('FirstName2', 'LastName2', 'Surname2'),
  ('FirstName3', 'LastName3', 'Surname3');

INSERT INTO issues (name, author_id) VALUES
  ('Name1', 11),
  ('Name2', 12),
  ('Name3', 13);

