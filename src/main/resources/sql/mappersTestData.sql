----------------------
-- DROP FOREIGN KEYS
----------------------
-- ALTER TABLE question_type_options DROP CONSTRAINT question_type_options_issue_standard_id_fkey;
-- ALTER TABLE topic_priorities DROP CONSTRAINT topic_priorities_issue_standard_id_fkey;

----------------------
-- DROP TABLES
----------------------
DROP TABLE IF EXISTS question_type_options;
DROP TABLE IF EXISTS topic_priorities;
DROP  TYPE IF EXISTS TOPIC_PRIORITY;
DROP TABLE IF EXISTS issue_standard;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS subjects;
DROP TABLE IF EXISTS questions;
DROP TABLE IF EXISTS answers;
DROP TABLE IF EXISTS quizzes;
DROP TABLE IF EXISTS solutions;
DROP TABLE IF EXISTS testees;
DROP TABLE IF EXISTS points;


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
    PRIMARY KEY (id),
    UNIQUE (subject_id)
);

CREATE TYPE TOPIC_PRIORITY AS ENUM ('LOW', 'HIGH');

CREATE TABLE topic_priorities (
  id                 SERIAL          NOT NULL,
  topic_id           INTEGER         NOT NULL,
  priority           TOPIC_PRIORITY  NOT NULL,
  issue_standard_id  INTEGER         NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (topic_id, issue_standard_id),
    FOREIGN KEY (issue_standard_id)
        REFERENCES issue_standard (id)
        ON DELETE CASCADE
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
    UNIQUE (question_type_id, issue_standard_id),
    FOREIGN KEY (issue_standard_id)
        REFERENCES issue_standard (id)
        ON DELETE CASCADE
);

CREATE TABLE subjects (
  id        SERIAL        NOT NULL,
  name      VARCHAR(255)  NOT NULL,
  user_id   INTEGER       NOT NULL,
  issue_standard_id     INTEGER,
  PRIMARY KEY (id)
);

CREATE TABLE questions (
  id          SERIAL      NOT NULL,
  text        VARCHAR(80) NOT NULL,
  type        INTEGER     NOT NULL,
  topic_id    INTEGER     NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE answers (
  id SERIAL NOT NULL,
  txt VARCHAR(250) NOT NULL,
  question_id INTEGER NOT NULL,
  is_right BOOLEAN NOT NULL,
    PRIMARY KEY (id)--,
    --FOREIGN KEY (question_id) REFERENCES questions(id)
);

CREATE TABLE quizzes (
  id         SERIAL      NOT NULL,
  issue_id  INTEGER NOT NULL,
  invite_code VARCHAR(32),
  PRIMARY KEY (id)
);

CREATE TABLE solutions (
  id        SERIAL  NOT NULL,
  answer_id INTEGER,
  point_id  INTEGER NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (answer_id, point_id)
);

CREATE TABLE testees (
  id           SERIAL      NOT NULL,
  first_name   VARCHAR(30) NOT NULL,
  last_name    VARCHAR(30) NOT NULL,
  surname      VARCHAR(30) NOT NULL,
  group_number INTEGER     NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE points (
  id      SERIAL  		  NOT NULL,
  type		VARCHAR(20) 	NOT NULL,
  text		VARCHAR(300)	NOT NULL,
  quiz_id	INTEGER			  NOT NULL,
  	PRIMARY KEY (id)  
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

INSERT INTO subjects (name, user_id, issue_standard_id) VALUES
  ('test1', 2, 1),
  ('test2', 2, 1),
  ('test3', 3, 1);

INSERT INTO questions (text, type, topic_id) VALUES
  ('test1', 1, 1),
  ('test2', 2, 1),
  ('test3', 3, 1);

INSERT INTO answers(txt, question_id, is_right) VALUES
  ('Answer1', 1, TRUE),
  ('Answer2', 3, FALSE),
  ('Answer3', 2, TRUE);

INSERT INTO quizzes (issue_id, invite_code) VALUES
 (1, 'test_invite_code1'),
 (2, 'test_invite_code2'),
 (3, 'test_invite_code3');

INSERT INTO solutions (answer_id, point_id) VALUES
  (10, 1), (20, 1), (11, 2), (21, 2), (12, 3);

INSERT INTO testees (first_name, last_name, surname, group_number) VALUES
  ('FirstName1', 'LastName1', 'Surname1', 301),
  ('FirstName2', 'LastName2', 'Surname2', 302),
  ('FirstName3', 'LastName3', 'Surname3', 303);
  
INSERT INTO points (type, text, quiz_id) VALUES
	('type1', 'text1', 1),
	('type2', 'text2', 2),
	('type3', 'text3', 3);

