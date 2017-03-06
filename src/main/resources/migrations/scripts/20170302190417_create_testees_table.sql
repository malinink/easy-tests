-- // create_testees_table

CREATE TABLE testees (
  id           SERIAL      NOT NULL,
  first_name   VARCHAR(30) NOT NULL,
  last_name    VARCHAR(30) NOT NULL,
  surname      VARCHAR(30) NOT NULL,
  group_number INTEGER     NOT NULL,
  PRIMARY KEY (id)
);

-- //@UNDO

DROP TABLE testees;