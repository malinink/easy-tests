-- // add_serial_number_to_Answer

ALTER TABLE answers
  ADD COLUMN serial_number INTEGER NOT NULL DEFAULT 0,
  ADD CONSTRAINT CHK_serial_number CHECK(serial_number>=0);

-- //@UNDO
-- SQL to undo the change goes here.

ALTER TABLE answers
  DROP COLUMN serial_number;
