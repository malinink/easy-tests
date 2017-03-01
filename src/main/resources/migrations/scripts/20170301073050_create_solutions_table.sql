-- // create_solutions_table
-- Migration SQL that makes the change goes here.

CREATE TABLE solutions (
  id        SERIAL  NOT NULL,
  answer_id INTEGER,
  point_id  INTEGER NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (answer_id, point_id)
);


-- //@UNDO
-- SQL to undo the change goes here.

DROP TABLE solutions;
