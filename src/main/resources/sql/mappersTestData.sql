----------------------
-- DROP FOREIGN KEYS
----------------------
-- ALTER TABLE question_type_options DROP CONSTRAINT question_type_options_issue_standard_id_fkey;
-- ALTER TABLE topic_priorities DROP CONSTRAINT topic_priorities_issue_standard_id_fkey;

----------------------
-- TRUNCATE TABLES
----------------------

TRUNCATE TABLE question_type_options RESTART IDENTITY;
TRUNCATE TABLE topic_priorities RESTART IDENTITY;
TRUNCATE TABLE issue_standards RESTART IDENTITY;
TRUNCATE TABLE users RESTART IDENTITY;
TRUNCATE TABLE subjects RESTART IDENTITY;
TRUNCATE TABLE questions RESTART IDENTITY;
TRUNCATE TABLE question_types RESTART IDENTITY;
TRUNCATE TABLE answers RESTART IDENTITY;
TRUNCATE TABLE quizzes RESTART IDENTITY;
TRUNCATE TABLE solutions RESTART IDENTITY;
TRUNCATE TABLE testees RESTART IDENTITY;
TRUNCATE TABLE points RESTART IDENTITY;
TRUNCATE TABLE issues RESTART IDENTITY;
TRUNCATE TABLE topics RESTART IDENTITY;

----------------------
-- INSERT DATA
----------------------
INSERT INTO users (first_name, last_name, surname, email, password, is_admin, state) VALUES
  ('FirstName1', 'LastName1', 'Surname1', 'email1@gmail.com', 'hash1', TRUE, 1),
  ('FirstName2', 'LastName2', 'Surname2', 'email2@gmail.com', 'hash2', FALSE, 2),
  ('FirstName3', 'LastName3', 'Surname3', 'email3@gmail.com', 'hash3', TRUE, 3);

INSERT INTO issue_standards (time_limit, questions_number, subject_id) VALUES
  (300, 30, 1),
  (NULL, 15, 3);

INSERT INTO topic_priorities (topic_id, is_preferable, issue_standard_id) VALUES
  (1, TRUE, 1),
  (2, FALSE, 1),
  (3, TRUE, 2);

INSERT INTO question_type_options (question_type_id, min_number, max_number, time_limit, issue_standard_id) VALUES
  (1, 1, NULL, NULL, 1),
  (2, NULL, 5, NULL, 1),
  (3, 5, 10, NULL, 1),
  (1, 1, 5, 120, 2),
  (3, 5, NULL, 300, 2);

INSERT INTO subjects (name, description, user_id) VALUES
  ('Subject1', 'Subject Description 1', 2),
  ('Subject2', 'Subject Description 2', 2),
  ('Subject3', 'Subject Description 3', 3);

INSERT INTO topics (name, subject_id) VALUES
  ('Name1', 2),
  ('Name2', 2),
  ('Name3', 3);

INSERT INTO questions (text, question_type_id, topic_id) VALUES
  ('test1', 1, 1),
  ('test2', 2, 3),
  ('test3', 3, 2);

INSERT INTO question_types VALUES
  (1, 'Один ответ', 1),
  (2, 'Много ответов', 2),
  (3, 'Нумерация', 3),
  (4, 'Текст', 4);

INSERT INTO answers(txt, question_id, serial_number, is_right) VALUES
  ('Answer1', 1, 1, TRUE),
  ('Answer2', 2, 2, FALSE),
  ('Answer3', 3, 3, TRUE);

INSERT INTO quizzes (issue_id, invite_code,started_at,finished_at,code_expired) VALUES
 (1, 'test_invite_code1','2003-2-1'::timestamp,'2003-3-1'::timestamp,FALSE ),
 (2, 'test_invite_code2','2003-2-1'::timestamp,'2003-3-1'::timestamp,FALSE ),
 (3, 'test_invite_code3','2003-2-1'::timestamp,'2003-3-1'::timestamp,TRUE );

INSERT INTO solutions (answer_id, point_id) VALUES
  (10, 1), (20, 1), (11, 2), (21, 2), (12, 3);

INSERT INTO testees (first_name, last_name, surname, group_number, quiz_id) VALUES
  ('FirstName1', 'LastName1', 'Surname1', 301, 1),
  ('FirstName2', 'LastName2', 'Surname2', 302, 2),
  ('FirstName3', 'LastName3', 'Surname3', 303, 3);
  
INSERT INTO points (question_id, quiz_id) VALUES
	(1, 1),
	(2, 2),
	(3, 2);

INSERT INTO issues (name, subject_id) VALUES
  ('Name1', 1),
  ('Name2', 2),
  ('Name3', 3);
