----------------------
-- DROP FOREIGN KEYS
----------------------


----------------------
-- DROP TABLES
----------------------
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS quizzes;


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

CREATE TABLE quizzes (
  id         SERIAL      NOT NULL,
  issue_id  INTEGER NOT NULL,
  invite_code VARCHAR(32),
  PRIMARY KEY (id)
);

----------------------
-- INSERT DATA
----------------------

INSERT INTO users (first_name, last_name, surname) VALUES
  ('FirstName1', 'LastName1', 'Surname1'),
  ('FirstName2', 'LastName2', 'Surname2'),
  ('FirstName3', 'LastName3', 'Surname3');


INSERT INTO quizzes (issue_id, invite_code) VALUES
 (1, 'test_invite_code1'),
 (2, 'test_invite_code2'),
 (3, 'test_invite_code3');