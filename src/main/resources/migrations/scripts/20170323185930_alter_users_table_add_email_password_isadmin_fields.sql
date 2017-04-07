-- // create_users_table

ALTER TABLE users
  ADD email    VARCHAR(30),
  ADD password VARCHAR(60),
  ADD is_admin BOOLEAN,
  ADD state    SMALLINT;

UPDATE users SET
  email     = id::VARCHAR,
  password  = '',
  is_admin  = FALSE,
  state     = 1;

ALTER TABLE users
  ALTER email     SET NOT NULL,
  ALTER password  SET NOT NULL,
  ALTER is_admin  SET NOT NULL,
  ALTER state     SET NOT NULL,
  ADD   UNIQUE (email);

-- //@UNDO

ALTER TABLE users
  DROP email,
  DROP password,
  DROP is_admin,
  DROP state;
