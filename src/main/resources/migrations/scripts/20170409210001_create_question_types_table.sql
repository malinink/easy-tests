-- // create_question_types

CREATE TABLE question_types (
  id         INTEGER     NOT NULL,
  name       VARCHAR(30) NOT NULL,
  sort       INTEGER     NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO question_types VALUES
  (1, 'Один ответ', 1),
  (2, 'Много ответов', 2),
  (3, 'Нумерация', 3),
  (4, 'Текст', 4);

-- //@UNDO

DROP TABLE question_types;