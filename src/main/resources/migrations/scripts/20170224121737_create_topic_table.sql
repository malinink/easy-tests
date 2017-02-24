-- // create_topic_table

CREATE TABLE topics (
  id         SERIAL      NOT NULL,
  name VARCHAR(60) NOT NULL,
  questions_id  INTEGER NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (name)
);



-- //@UNDO

DROP TABLE topics;

