-- // alter_testees_table_add_quiz_id_field

ALTER TABLE testees
  ADD quiz_id  INTEGER;

UPDATE testees SET
  quiz_id = id;

ALTER TABLE testees
  ALTER quiz_id SET NOT NULL,
  ADD UNIQUE (quiz_id);

-- //@UNDO

ALTER TABLE testees
  DROP quiz_id;