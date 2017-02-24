-- // create_questions_table

CREATE TABLE questions (
  id          SERIAL      NOT NULL,
  text        VARCHAR(80) NOT NULL,
  type        INTEGER     NOT NULL,
  topic_id    INTEGER     NOT NULL,
  PRIMARY KEY (id)
);

-- //@UNDO

DROP TABLE questions;