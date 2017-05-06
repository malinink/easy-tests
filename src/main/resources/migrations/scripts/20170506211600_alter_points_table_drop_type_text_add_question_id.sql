-- // alter_points_table_drop_type_text_add_question_id
-- Migration SQL that makes the change goes here.

ALTER TABLE points
  ADD question_id INTEGER;

UPDATE points SET
  question_id = 1;

ALTER TABLE points
  ALTER question_id	SET NOT NULL;

ALTER TABLE points
  DROP text,
  DROP type;

-- //@UNDO
-- SQL to undo the change goes here.

ALTER TABLE points
  DROP question_id;

ALTER TABLE points
  ADD type VARCHAR(20),
  ADD text VARCHAR(300);

UPDATE points SET
  type = '',
  text = '';

ALTER TABLE points
  ALTER type SET NOT NULL,
  ALTER text SET NOT NULL;
