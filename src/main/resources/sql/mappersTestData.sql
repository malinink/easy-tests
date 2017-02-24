----------------------
-- DROP FOREIGN KEYS
----------------------


----------------------
-- DROP TABLES
----------------------
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS topics;


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


CREATE TABLE topics (
  id         SERIAL      NOT NULL,
  name VARCHAR(60) NOT NULL,
  questions_id  INTEGER NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (name)
);


----------------------
-- INSERT DATA
----------------------

INSERT INTO users (first_name, last_name, surname) VALUES
  ('FirstName1', 'LastName1', 'Surname1'),
  ('FirstName2', 'LastName2', 'Surname2'),
  ('FirstName3', 'LastName3', 'Surname3');

INSERT INTO topics (name, question_id) VALUES
  ('Web', '1'),
  ('Math', '2'),
  ('Database', '3');

