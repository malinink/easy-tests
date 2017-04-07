-- // create_topics_table

CREATE TABLE topics (
  id         SERIAL      NOT NULL,
  name       VARCHAR(30) NOT NULL,
  subject_id INTEGER     NOT NULL,
  PRIMARY KEY (id)
);

-- //@UNDO

DROP TABLE topics;