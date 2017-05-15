
-- // alter_issues_table
ALTER TABLE issues
  ADD subject_id   INTEGER;

UPDATE issues SET
  subject_id    = 0::INTEGER;

ALTER TABLE issues
  ALTER subject_id     SET NOT NULL;

ALTER TABLE issues
  DROP author_id;


-- //@UNDO
ALTER TABLE issues
  ADD author_id   SERIAL;
ALTER TABLE issues
  DROP subject_id;

