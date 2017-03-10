-- //create issues table

CREATE TABLE issues (
  id          SERIAL        NOT NULL,
  name        VARCHAR(100)  NOT NULL,
  author_id   SERIAL        NOT NULL,
  PRIMARY KEY (id)
);
-- //@UNDO

DROP TABLE issues;
