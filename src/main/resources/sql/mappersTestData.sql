----------------------
-- DROP FOREIGN KEYS
----------------------


----------------------
-- DROP TABLES
----------------------
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS questions;


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

CREATE TABLE questions (
  id          SERIAL      NOT NULL,
  text        VARCHAR(80) NOT NULL,
  type        INTEGER     NOT NULL,
  topic_id    INTEGER     NOT NULL,
  PRIMARY KEY (id)
);


----------------------
-- INSERT DATA
----------------------

INSERT INTO users (first_name, last_name, surname) VALUES
  ('FirstName1', 'LastName1', 'Surname1'),
  ('FirstName2', 'LastName2', 'Surname2'),
  ('FirstName3', 'LastName3', 'Surname3');
  
INSERT INTO questions (text, type, topic_id) VALUES
  ('test1', 1, 1),
  ('test2', 2, 1),
  ('test3', 3, 1);

