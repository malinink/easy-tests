-- // create_issue_standard_table
-- Migration SQL that makes the change goes here.

CREATE TABLE issue_standard (
  id                SERIAL   NOT NULL,
  time_limit        INTEGER,
  questions_number  INTEGER,
  subject_id        INTEGER  NOT NULL,
    CONSTRAINT positiveness CHECK (questions_number > 0 AND time_limit > 0),
    PRIMARY KEY (id),
    UNIQUE (subject_id)
);

-- //@UNDO
-- SQL to undo the change goes here.

DROP TABLE issue_standard;
