-- // create_answers_table
-- Migration SQL that makes the change goes here.

CREATE TABLE answers (
  id SERIAL NOT NULL,
  txt VARCHAR(255) NOT NULL,
  question_id INTEGER NOT NULL,
  is_right BOOLEAN NOT NULL,
  PRIMARY KEY (id)
  --FOREIGN KEY (question_id) REFERENCES questions(id)
);

-- //@UNDO
-- SQL to undo the change goes here.

DROP TABLE answers;
