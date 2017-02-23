-- // create_issue_standard_table
-- Migration SQL that makes the change goes here.

CREATE TABLE issue_standard (
  id                SERIAL      NOT NULL,
  time_limit        INTEGER,
  questions_number  INTEGER     CHECK (questions_number > 0),
  subject_id        INTEGER     NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (subject_id)
        REFERENCES subjects (id)
        ON DELETE CASCADE
);

-- //@UNDO
-- SQL to undo the change goes here.

DROP TABLE issue_standard;
