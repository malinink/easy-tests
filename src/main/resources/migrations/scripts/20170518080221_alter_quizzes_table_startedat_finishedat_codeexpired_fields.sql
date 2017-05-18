
-- // alter_quizzes_table_startedat_finishedat_codeexpired_fields
-- Migration SQL that makes the change goes here.
ALTER TABLE quizzes
  ADD started_at  TIMESTAMP,
  ADD finished_at TIMESTAMP,
  ADD code_expired BOOLEAN;
UPDATE quizzes SET
  code_expired  = FALSE::BOOLEAN;



-- //@UNDO
-- SQL to undo the change goes here.
ALTER TABLE quizzes
  DROP started_at,
  DROP finished_at,
  DROP code_expired;

