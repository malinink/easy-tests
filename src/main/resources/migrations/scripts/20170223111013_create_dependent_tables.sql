-- // create_dependent_tables
-- Migration SQL that makes the change goes here.

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

-- //@UNDO
-- SQL to undo the change goes here.

DROP TABLE question_type_options;
DROP TABLE topic_priorities;
DROP TYPE TOPIC_PRIORITY;
