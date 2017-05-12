--
--    Copyright 2010-2016 the original author or authors.
--
--    Licensed under the Apache License, Version 2.0 (the "License");
--    you may not use this file except in compliance with the License.
--    You may obtain a copy of the License at
--
--       http://www.apache.org/licenses/LICENSE-2.0
--
--    Unless required by applicable law or agreed to in writing, software
--    distributed under the License is distributed on an "AS IS" BASIS,
--    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
--    See the License for the specific language governing permissions and
--    limitations under the License.
--

-- // add_serial_number_to_Answer
-- Migration SQL that makes the change goes here.

ALTER TABLE answers
  ADD COLUMN serial_number INTEGER NOT NULL DEFAULT 0,
  ADD CONSTRAINT CHK_serial_number CHECK(serial_number>=0);

-- //@UNDO
-- SQL to undo the change goes here.

ALTER TABLE answers
  DROP CONSTRAINT CHK_serial_number,
  DROP COLUMN serial_number;
