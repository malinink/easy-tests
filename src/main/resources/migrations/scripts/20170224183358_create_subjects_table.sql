-- // create_users_table

CREATE TABLE subjects (
  id         SERIAL      NOT NULL,
  name VARCHAR(255) NOT NULL,
  user_id  INTEGER NOT NULL,
  issue_standard_id INTEGER,
  PRIMARY KEY (id)
);

-- //@UNDO

DROP TABLE subjects;