DROP DATABASE IF EXISTS family_tree;
CREATE DATABASE family_tree;

USE family_tree;

DROP TABLE IF EXISTS gender;
CREATE TABLE gender (
  gender VARCHAR(10) PRIMARY KEY NOT NULL
);

DROP TABLE IF EXISTS family_member;
CREATE TABLE family_member(
  member_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  birth_date DATE DEFAULT NULL,
  death_date DATE DEFAULT NULL,
  birth_place VARCHAR(80) DEFAULT 'unknown',
  death_place VARCHAR(80) DEFAULT 'unknown',
  marriage_date DATE DEFAULT NULL,
  gender VARCHAR(10) NOT NULL
);

DROP TABLE IF EXISTS family_tree;
CREATE TABLE family_tree (
  member_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  parent_id INT DEFAULT NULL,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  birth_date DATE DEFAULT NULL,
  death_date DATE DEFAULT NULL,
  birth_place VARCHAR(80) DEFAULT 'unknown',
  death_place VARCHAR(80) DEFAULT 'unknown',
  credit_card_number VARCHAR(50),
  gender VARCHAR(10) NOT NULL ,
  partner_id INT UNIQUE NOT NULL
);


DROP TABLE IF EXISTS family_asset;
CREATE TABLE family_asset(
  asset_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  asset_name VARCHAR(50) NOT NULL,
  price_estimate FLOAT DEFAULT 0.0,
  price_max FLOAT DEFAULT 0.0,
  price_min FLOAT DEFAULT 0.0,
  catalog_code VARCHAR(10) UNIQUE DEFAULT NULL
);

DROP TABLE IF EXISTS asset_member;
CREATE TABLE asset_member(
  asset_member_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  asset_id INT NOT NULL,
  member_id INT NOT NULL
);

-- TRIGGERS --
DELIMITER //
DROP TRIGGER IF EXISTS before_family_member_insert//
CREATE TRIGGER before_family_member_insert
  BEFORE INSERT ON family_member
  FOR EACH ROW

  BEGIN

    DECLARE msg VARCHAR(50);

    IF NEW.birth_date > current_date() OR NEW.death_date > current_date() THEN
      SET msg = 'Birth and death dates cannot be in the future';
      SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg;
    ELSEIF NOT exists(SELECT * FROM gender WHERE gender.gender = NEW.gender) THEN
      SET msg = 'No such gender';
      SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg;
    END IF;

  END// 
  

DROP TRIGGER IF EXISTS before_family_tree_insert//
/*
CREATE TRIGGER before_family_tree_insert
  BEFORE INSERT ON family_tree
  FOR EACH ROW

  BEGIN

    DECLARE msg VARCHAR(50);
    DECLARE auto_id INT;

    IF NOT exists(SELECT * FROM gender WHERE gender.gender = NEW.gender) THEN
      SET msg = 'No Such Gender';
      SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg;
    ELSEIF NOT exists(SELECT * FROM family_member WHERE family_member.member_id = NEW.partner_id) THEN
      SET msg = 'No such partner';
      SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg;
    ELSEIF NOT exists(SELECT * FROM family_tree WHERE member_id = NEW.parent_id) THEN
      IF NEW.parent_id IS NULL THEN SET msg = 'Warning: Null parent Id';
        ELSE
          SET msg = 'Wrong parent id';
          SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg;
      END IF;
    ELSEIF NEW.credit_card_number NOT REGEXP '\\d{4}\\s\\d{4}\\s\\d{4}\\s\\d{4}' THEN
      SET msg = 'Invalid format for card number';
      SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg;
    END IF;

    SELECT member_id FROM family_tree ORDER BY member_id DESC LIMIT 1 INTO auto_id;
    IF auto_id IS NULL THEN SET auto_id = 0;END IF;
    SET auto_id = auto_id + 1;
    SET NEW.last_name = concat(NEW.last_name, auto_id);

  END//
*/
DROP TRIGGER IF EXISTS before_family_asset_insert//
CREATE TRIGGER before_family_asset_insert
  BEFORE INSERT ON family_asset
  FOR EACH ROW

  BEGIN

    DECLARE msg VARCHAR(50);

    IF NEW.price_max < NEW.price_min THEN
      SET msg = 'Max price cannot be smaller than min';
      SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg;
    ELSEIF NOT (NEW.price_estimate >= NEW.price_min OR NEW.price_estimate <= NEW.price_max) THEN
      SET msg = 'Estimate price should be in between max abd min';
      SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg;
    END IF;

  END//

DROP TRIGGER IF EXISTS before_asset_member_insert//
CREATE TRIGGER before_asset_member_insert
  BEFORE INSERT ON asset_member
  FOR EACH ROW

  BEGIN

    DECLARE msg VARCHAR(50);

    IF NOT exists(SELECT * FROM family_tree WHERE family_tree.member_id = NEW.member_id) THEN
      SET msg = 'No such family member';
      SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg;
    ELSEIF NOT exists(SELECT * FROM family_asset WHERE family_asset.asset_id = NEW.asset_id) THEN
      SET msg = 'No such asset';
      SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg;
    ELSEIF exists(SELECT * FROM asset_member WHERE member_id = NEW.member_id AND asset_id = NEW.asset_id) THEN
      SET msg = 'Such combination already exists';
      SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg;
    END IF;

  END//

-- INSERTS --
INSERT INTO gender VALUES ('male'), ('female');

INSERT INTO family_member (first_name, last_name, birth_date, death_date,
                           birth_place, death_place, marriage_date, gender)
VALUES
       ('John', 'Miller', '1985-05-05', '2016-08-21', 'Venice', 'Seoul', '2016-07-19', 'male'),
       ('Olivia', 'Wright', '1987-05-05', '2016-08-21', 'London', 'Bangkok', '2015-07-19', 'female'),
       ('Harry', 'Watson', '1896-05-05', '2016-08-21', 'Tokyo', 'London', '2014-07-19', 'male'),
       ('Emily', 'Green', '1896-05-05', '2018-08-21', 'London', 'Istanbul', '2018-07-19', 'female'),
       ('George', 'Murphy', '1786-05-05', '2016-08-21', 'London', 'Brussels', '2005-07-19', 'male'),
       ('Sophia', 'Rivera', '2015-05-05', '2016-08-21', 'Budapest', 'Kuala Lumpur', '2008-07-19', 'female'),
       ('Jacob', 'Cooper', '2016-05-05', '2016-08-21', 'Singapore', 'London', '2007-07-19', 'male'),
       ('Ava', 'Richardson', '1998-05-05', '2016-08-21', 'New York', 'London', '2010-07-19', 'female'),
       ('Freddie', 'Cook', '1896-05-05', '2016-08-21', 'Sydney', 'Dubai', '2009-07-19', 'male'),
       ('Isabella', 'Morris', '1893-05-05', '2016-08-21', 'Paris', 'London', '1999-07-19', 'female');


INSERT INTO family_tree (parent_id, first_name, last_name, birth_date, death_date,
                         birth_place, death_place, credit_card_number, gender, partner_id)
VALUES
       (NUll ,'John', 'Walker', '1985-05-05', '2016-08-21', 'Venice', 'Seoul', '5698 9878 3245 6841', 'male', 6),
       (1 ,'Alma', 'Clark', '1996-05-05', '2016-08-21', 'Venice', 'Budapest', '5698 9878 3245 6841', 'female', 10),
       (1 ,'Arnold', 'Parker', '1915-05-05', '2016-08-21', 'Kuala Lumpur', 'Seoul', '3082 0597 0601 5675', 'male', 5),
       (2 ,'Amabel', 'Evans', '1945-05-05', '2016-08-21', 'Venice', 'Dubai', '4190 0248 3059 8228', 'female', 1),
       (3 ,'Arthur', 'Collins', '1965-05-05', '2016-08-21', 'New York', 'Seoul', '9324 0379 9354 7372', 'male', 3),
       (3 ,'Angela', 'Sanders', '1919-05-05', '2016-08-21', 'Tokyo', 'Singapore', '4939 1696 6739 9831', 'female', 4),
       (3 ,'George', 'Gray', '1917-05-05', '2016-08-21', 'Tokyo', 'Seoul', '4933 6172 1718 9352', 'male', 8),
       (5 ,'Arabella', 'Watson', '1973-05-05', '2016-08-21', 'Venice', 'Dubai', '7478 8626 1188 7037', 'female', 9),
       (3 ,'Jeremy', 'Ward', '1987-05-05', '2016-08-21', 'Bangkok', 'Seoul', '5496 2167 5326 6857', 'male', 2),
       (5 ,'Augusta', 'Howard', '1998-05-05', '2016-08-21', 'Singapore', 'Seoul', '3320 7737 5130 0739', 'female', 7);


INSERT INTO family_asset (asset_name, price_estimate, price_max, price_min, catalog_code)
VALUES
       ('The Dropa Stones', 54.4, 98.4, 29.5, 'A98453XS'),
       ('The Saqqara bird', 45.6, 105.8, 18.6, 'Z91547UE'),
       ('The Baghdad battery', 45.2, 122.6, 27.5, 'M62485DD'),
       ('The Piri Reis map', 65.1, 92.6, 55.4, 'A34598PO'),
       ('The Nazca drawings', 98.1, 293.4, 48.6, 'M31587OX'),
       ('The Sacsayhuaman walls', 23.0, 30.5, 18.9, 'Z25493IA'),
       ('The Antikythera mechanism', 450.0, 485.0, 400.2, 'A50486FY'),
       ('Relic from Egypt', 165.8, 185.9, 80.3, 'M01489PD'),
       ('Thor\'s Hammer', 44.8, 68.3, 12.6, 'Z98701UY');

INSERT INTO asset_member (asset_id, member_id)
VALUES
       (4, 5),
       (8, 1),
       (2, 7),
       (4, 3),
       (5, 8);


-- FUNCTIONS --
SET GLOBAL log_bin_trust_function_creators = 1;

DROP FUNCTION IF EXISTS get_max_max_price//
CREATE FUNCTION get_max_max_price() RETURNS FLOAT
  BEGIN
    RETURN (SELECT max(price_max) FROM family_asset);
  END//

SELECT get_max_max_price();

DROP FUNCTION IF EXISTS get_gender_by_key//
CREATE FUNCTION get_gender_by_key(val INT) RETURNS VARCHAR(10)
  BEGIN
    RETURN (SELECT gender FROM family_tree WHERE member_id = val);
  END//

SELECT get_gender_by_key(4);

-- STORED PROCEDURES --

DROP PROCEDURE IF EXISTS insert_to_family_member//
CREATE PROCEDURE insert_to_family_tree (
  IN first_name_in VARCHAR(50),
  IN last_name_in VARCHAR(50),
  IN birth_date_in DATE,
  IN death_date_in DATE,
  IN birth_place_in VARCHAR(80),
  IN death_place_in VARCHAR(80),
  IN credit_card_number_in VARCHAR(50),
  IN gender_in VARCHAR(10),
  IN parent_id_in INT,
  IN partner_id_in INT
)

BEGIN

  INSERT INTO family_tree (parent_id, first_name, last_name, birth_date, death_date,
                           birth_place, death_place, credit_card_number, gender, partner_id)
  VALUE
        (parent_id_in, first_name_in, last_name_in, birth_date_in, death_date_in,
            birth_place_in, death_place_in, credit_card_number_in, gender_in, partner_id_in);

END//

DROP FUNCTION IF EXISTS get_real_member_name//
CREATE FUNCTION get_real_member_name(member_id_in INT) RETURNS VARCHAR(50)
  BEGIN
    RETURN (SELECT first_name FROM family_tree WHERE member_id = member_id_in);
  END//

DROP FUNCTION IF EXISTS get_real_asset_name//
CREATE FUNCTION get_real_asset_name(asset_id_in INT) RETURNS VARCHAR(50)
  BEGIN
    RETURN (SELECT asset_name FROM family_asset WHERE asset_id = asset_id_in);
  END//

DROP PROCEDURE IF EXISTS get_asset_member//
CREATE PROCEDURE get_asset_member (
  IN asset_name_in VARCHAR(50)
)

  BEGIN
    IF asset_name_in IS NULL THEN
      SELECT get_real_member_name(member_id), get_real_asset_name(asset_id) FROM asset_member;
    ELSE
      SELECT get_real_asset_name(asset_id) FROM asset_member WHERE get_real_member_name(member_id) = asset_name_in;
    END IF;
  END//
-- CURSOR --
DROP PROCEDURE IF EXISTS create_tables//
CREATE PROCEDURE create_tables()
  BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE member_id_par INT;
    DECLARE parent_id_par INT;
    DECLARE first_name_par VARCHAR(50);
    DECLARE last_name_par VARCHAR(50);
    DECLARE birth_date_par DATE;
    DECLARE death_date_par DATE;
    DECLARE birth_place_par VARCHAR(80);
    DECLARE death_place_par VARCHAR(80);
    DECLARE credit_card_number_par VARCHAR(50);
    DECLARE gender_par VARCHAR(10);
    DECLARE partner_id_par INT;

    DECLARE tree_cursor CURSOR FOR
      SELECT 
		member_id,
		first_name,
		last_name,
		birth_date,
		death_date,
		birth_place,
		death_place,
		credit_card_number,
		gender,
		partner_id
      FROM family_tree;

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

	SET @now1 = cast(time(now()) as nchar(50));
	 SET @q = CONCAT('
        CREATE TABLE IF NOT EXISTS `' , @now1, '` (
            `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
            `value` VARCHAR(200) NOT NULL,
            PRIMARY KEY (`id`)
        ) ENGINE=MyISAM DEFAULT CHARSET=utf8
    ');
    PREPARE statement FROM @q;
    EXECUTE statement;
    DEALLOCATE PREPARE statement;
    
	SET @now2 = concat(cast(time(now()) as nchar(50)),' time2');
	 SET @q = CONCAT('
        CREATE TABLE IF NOT EXISTS `' , @now2, '` (
            `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
            `value` VARCHAR(200) NOT NULL,
            PRIMARY KEY (`id`)
        ) ENGINE=MyISAM DEFAULT CHARSET=utf8
    ');
    PREPARE statement FROM @q;
    EXECUTE statement;
    DEALLOCATE PREPARE statement;
    
    OPEN tree_cursor;
    myLoop: LOOP
    
      FETCH tree_cursor INTO member_id_par, first_name_par, last_name_par, birth_date_par, death_date_par,
        birth_place_par, death_place_par, credit_card_number_par, gender_par, partner_id_par;
        
    IF done = TRUE THEN LEAVE myLoop; END IF;
        
      SET @all_together = concat(" ",member_id_par," ", first_name_par," ", last_name_par, " ",birth_date_par, " ",death_date_par," ",
        birth_place_par, " ",death_place_par, " ",credit_card_number_par, " ",gender_par, " ",partner_id_par);
        
      SET @rand = floor(rand() * 2);
      IF (@rand = 0) THEN 
		SET @q = concat("INSERT INTO `", @now1, "` (value) VALUE ( '", @all_together, "' )");
		ELSE 
		SET @q = concat("INSERT INTO `", @now2, "` (value) VALUE ( '", @all_together, "' )");
		END IF;
    PREPARE statement FROM @q;
    EXECUTE statement;
    DEALLOCATE PREPARE statement;
    
    END LOOP;

    CLOSE tree_cursor;

  END//

DELIMITER ;
CALL create_tables;
