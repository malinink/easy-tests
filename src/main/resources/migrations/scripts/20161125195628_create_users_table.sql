-- // create_users_table

CREATE TABLE users (
  id         SERIAL  NOT NULL,
  first_name VARCHAR NOT NULL,
  last_name  VARCHAR NOT NULL,
  surname    VARCHAR NOT NULL,
  PRIMARY KEY (id)
);

-- //@UNDO

DROP TABLE users;