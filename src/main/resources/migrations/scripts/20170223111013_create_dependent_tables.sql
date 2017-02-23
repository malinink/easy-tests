-- // create_dependent_tables
-- Migration SQL that makes the change goes here.

CREATE TYPE PRIORITY AS ENUM ('low', 'high');

CREATE TABLE topics_priorities (
  id              SERIAL      NOT NULL,
  topic_id        INTEGER     NOT NULL,
  topic_priority  PRIORITY    NOT NULL,
  is_id           INTEGER     NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (topic_id, is_id),
    FOREIGN KEY (topic_id)
        REFERENCES topics (id)
        ON DELETE CASCADE,
    FOREIGN KEY (is_id)
        REFERENCES issue_standard (id)
        ON DELETE CASCADE
);

CREATE TABLE question_types_limits (
  id                SERIAL      NOT NULL,
  question_type_id  INTEGER     NOT NULL,
  min_number        INTEGER,
  max_number        INTEGER,
  time_limit        INTEGER,
  is_id             INTEGER     NOT NULL,
    CONSTRAINT positiveness CHECK (min_number >= 0 AND max_number > 0 AND time_limit > 0),
    CONSTRAINT check_limits CHECK (max_number >= min_number),
    PRIMARY KEY (id),
    UNIQUE (question_type_id, is_id),
    FOREIGN KEY (question_type_id)
        REFERENCES question_types (id)
        ON DELETE CASCADE,
    FOREIGN KEY (is_id)
        REFERENCES issue_standard (id)
        ON DELETE CASCADE
);

-- //@UNDO
-- SQL to undo the change goes here.

DROP TABLE question_types_limits;
DROP TABLE topics_priorities;
DROP TYPE PRIORITY;
