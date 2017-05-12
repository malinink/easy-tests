-- // alter_dependent_tables_alter_unique_keys

CREATE UNIQUE INDEX topic_id_issue_standard_id_idx ON topic_priorities (topic_id, issue_standard_id);
ALTER TABLE topic_priorities DROP CONSTRAINT topic_priorities_topic_id_issue_standard_id_key,
    ADD CONSTRAINT topic_priorities_topic_id_issue_standard_id_key UNIQUE USING INDEX topic_id_issue_standard_id_idx
    DEFERRABLE INITIALLY IMMEDIATE;

CREATE UNIQUE INDEX question_type_id_issue_standard_id_idx ON question_type_options (question_type_id, issue_standard_id);
ALTER TABLE question_type_options DROP CONSTRAINT question_type_options_question_type_id_issue_standard_id_key,
    ADD CONSTRAINT question_type_options_question_type_id_issue_standard_id_key UNIQUE USING INDEX question_type_id_issue_standard_id_idx
    DEFERRABLE INITIALLY IMMEDIATE;

-- //@UNDO

ALTER TABLE topic_priorities DROP CONSTRAINT topic_priorities_topic_id_issue_standard_id_key,
    ADD CONSTRAINT topic_priorities_topic_id_issue_standard_id_key UNIQUE (topic_id, issue_standard_id);
ALTER TABLE question_type_options DROP CONSTRAINT question_type_options_question_type_id_issue_standard_id_key,
    ADD CONSTRAINT question_type_options_question_type_id_issue_standard_id_key UNIQUE (question_type_id, issue_standard_id);
