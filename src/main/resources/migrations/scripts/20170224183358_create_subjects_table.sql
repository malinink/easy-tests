-- // create_users_table

CREATE TABLE subjects (
  id        SERIAL       NOT NULL,
  name      VARCHAR(255) NOT NULL,
  description TEXT,
  user_id   INTEGER      NOT NULL,
  PRIMARY KEY (id)
);

-- //@UNDO

DROP TABLE subjects;