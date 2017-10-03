-- // create_points_table
-- Migration SQL that makes the change goes here.

CREATE TABLE points (
  id      SERIAL  		  NOT NULL,
  type		VARCHAR(20) 	NOT NULL,
  text		VARCHAR(300)	NOT NULL,
  quiz_id	INTEGER			  NOT NULL,
  	PRIMARY KEY (id)  
);

-- //@UNDO
-- SQL to undo the change goes here.
DROP TABLE points