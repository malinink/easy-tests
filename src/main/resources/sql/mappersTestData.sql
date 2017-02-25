----------------------
-- DROP FOREIGN KEYS
----------------------


----------------------
-- DROP TABLES
----------------------
DROP TABLE IF EXISTS question_type_options;
DROP TABLE IF EXISTS topic_priorities;
DROP  TYPE IF EXISTS TOPIC_PRIORITY;
DROP TABLE IF EXISTS issue_standard;
DROP TABLE IF EXISTS users;

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

CREATE TABLE issue_standard (
  id                SERIAL   NOT NULL,
  time_limit        INTEGER,
  questions_number  INTEGER,
  subject_id        INTEGER  NOT NULL,
    CONSTRAINT positiveness CHECK (questions_number > 0 AND time_limit > 0),
    PRIMARY KEY (id)
);

CREATE TYPE TOPIC_PRIORITY AS ENUM ('LOW', 'HIGH');

CREATE TABLE topic_priorities (
  id                 SERIAL          NOT NULL,
  topic_id           INTEGER         NOT NULL,
  priority           TOPIC_PRIORITY  NOT NULL,
  issue_standard_id  INTEGER         NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (topic_id, issue_standard_id)
);

CREATE TABLE question_type_options (
  id                 SERIAL   NOT NULL,
  question_type_id   INTEGER  NOT NULL,
  min_number         INTEGER,
  max_number         INTEGER,
  time_limit         INTEGER,
  issue_standard_id  INTEGER  NOT NULL,
    CONSTRAINT positiveness CHECK (min_number >= 0 AND max_number > 0 AND time_limit > 0),
    CONSTRAINT check_limits CHECK (max_number >= min_number),
    PRIMARY KEY (id),
    UNIQUE (question_type_id, issue_standard_id)
);

----------------------
-- INSERT DATA
----------------------

INSERT INTO users (first_name, last_name, surname) VALUES
  ('FirstName1', 'LastName1', 'Surname1'),
  ('FirstName2', 'LastName2', 'Surname2'),
  ('FirstName3', 'LastName3', 'Surname3');

INSERT INTO issue_standard (time_limit, questions_number, subject_id) VALUES
  (300, 30, 1),
  (NULL, 15, 3);

INSERT INTO topic_priorities (topic_id, priority, issue_standard_id) VALUES
  (1, 'HIGH', 1),
  (2, 'LOW', 1),
  (3, 'HIGH', 2);

INSERT INTO question_type_options (question_type_id, min_number, max_number, time_limit, issue_standard_id) VALUES
  (1, 1, NULL, NULL, 1),
  (2, NULL, 5, NULL, 1),
  (3, 5, 10, NULL, 1),
  (1, 1, 5, 120, 2),
  (3, 5, NULL, 300, 2);
