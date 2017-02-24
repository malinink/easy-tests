----------------------
-- DROP FOREIGN KEYS
----------------------


----------------------
-- DROP TABLES
----------------------
DROP TABLE IF EXISTS users;

DROP TABLE IF EXISTS answers;

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

CREATE TABLE questions ( -- Feel free to delete, I needed it to test Answers table @rezenbekk
  id SERIAL NOT NULL,
  question VARCHAR(30) NOT NULL
);

CREATE TABLE answers (
  id SERIAL NOT NULL,
  txt VARCHAR(250) NOT NULL,
  question_id INTEGER NOT NULL,
  is_right BOOLEAN NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (question_id) REFERENCES questions(id)
);
----------------------
-- INSERT DATA
----------------------

INSERT INTO users (first_name, last_name, surname) VALUES
  ('FirstName1', 'LastName1', 'Surname1'),
  ('FirstName2', 'LastName2', 'Surname2'),
  ('FirstName3', 'LastName3', 'Surname3');

INSERT INTO questions(question) VALUES
  ('q1'),
  ('q2'),
  ('q3');

INSERT INTO answers(txt, question_id, is_right) VALUES
  ('Answer1', 1, TRUE),
  ('Answer2', 3, FALSE),
  ('Answer3', 2, TRUE);

