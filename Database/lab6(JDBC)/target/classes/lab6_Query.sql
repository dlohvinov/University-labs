DROP DATABASE IF EXISTS Library;
CREATE DATABASE Library DEFAULT  CHARACTER SET utf8;
USE Library;

CREATE TABLE book (
id		int		NOT NULL,
title	nvarchar(50) NOT NULL,
author	nvarchar(50) NULL,
genre nvarchar(20) NOT NULL,
publisher nvarchar(20) NULL,
availability bit NOT NULL,

CONSTRAINT PK_book_id
PRIMARY KEY NONCLUSTERED (id)
) ENGINE InnoDB;


CREATE TABLE genre (
genre nvarchar(20) NULL
) ENGINE InnoDB;


CREATE TABLE reader (
id	    int      NOT NULL,
surname nvarchar(20) NOT NULL,
name    nvarchar(20) NULL,
address nvarchar(50) NOT NULL,

CONSTRAINT PK_reader_id
PRIMARY KEY NONCLUSTERED (id)
) ENGINE InnoDB;


CREATE TABLE history (
book   int NULL,
reader int NULL
) ENGINE InnoDB;


/*
ALTER TABLE book 
ADD CONSTRAINT UQ_book_genre
UNIQUE(genre);

ALTER TABLE history 
ADD CONSTRAINT UQ_history_book
UNIQUE(book);

ALTER TABLE history 
ADD CONSTRAINT UQ_history_reader
UNIQUE(reader);
*/

ALTER TABLE genre 
ADD CONSTRAINT UQ_genre_genre
UNIQUE(genre);

ALTER TABLE book 
ADD CONSTRAINT FK_genre_book
FOREIGN KEY (genre) REFERENCES genre (genre)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE history 
ADD CONSTRAINT FK_history_book
FOREIGN KEY (book) REFERENCES book (id)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE history 
ADD CONSTRAINT FK_history_reader
FOREIGN KEY (reader) REFERENCES reader (id)
ON DELETE CASCADE
ON UPDATE CASCADE;


INSERT INTO genre VALUES('tales'),('detective'),('novel'),('history'), ('adventure');

INSERT INTO book VALUES(1, 'tales by Sharle Perro', 'Sharle Perro', 'tales', 'great_publisher', True);
INSERT INTO book VALUES(2, 'Sherlock Holmes', 'Sharle Perro', 'detective', 'nice_publisher', False);
INSERT INTO book VALUES(3, 'High Rock', 'Harry Rock', 'detective', 'jelous_publisher', True);
INSERT INTO book VALUES(4, 'Histury of IIWW', 'Jasmine Jane', 'history', 'kind_publisher', True);
INSERT INTO book VALUES(5, 'Aivengo', 'Vallter Scott', 'novel', 'neat_publisher', False);
INSERT INTO book VALUES(6, 'tales by Hrimm', 'Brothers Hrimm', 'tales', 'great_publisher', False);
INSERT INTO book VALUES(7, '12 chairs', 'Ilf and Pertov', 'adventure', 'nice_publisher', False);
INSERT INTO book VALUES(8, '13 chairs', 'Not Ilf and Not Petrov', 'novel', 'jelous_publisher', True);
INSERT INTO book VALUES(9, 'Lord of the Rings', 'Tolkien', 'tales', 'kind_publisher', False);
INSERT INTO book VALUES(10, 'Ubick', 'Dick', 'novel', 'neat_publisher', True);

INSERT INTO reader VALUES(1, 'Zubenko', 'Mykhailo', 'ZM Street 1');
INSERT INTO reader VALUES(2, 'Zubko', 'Mykhailo', 'ZM Street 2');
INSERT INTO reader VALUES(3, 'Zubov', 'Mykhailo', 'ZM Street 3');
INSERT INTO reader VALUES(4, 'Shirov', 'Andrii', 'ZM Street 4');
INSERT INTO reader VALUES(5, 'Shirenko', 'Andrii', 'ZM Street 5');
INSERT INTO reader VALUES(6, 'Shir', 'Mykhailo', 'ZM Street 6');
INSERT INTO reader VALUES(7, 'Jerov', 'Dmytro', 'ZM Street 7');
INSERT INTO reader VALUES(8, 'Jerko', 'Mykhailo', 'ZM Street 8');
INSERT INTO reader VALUES(9, 'Jerry', 'Tomm', 'ZM Street 9');
INSERT INTO reader VALUES(10, 'Haiev', 'Ivan', 'ZM Street 10');

INSERT INTO history VALUE(2,1), (5,1), (6,3), (7, 7), (9, 2);

DELIMITER //
CREATE PROCEDURE insert_genre
  (IN new_genre varchar(20))
  BEGIN
    DECLARE msg varchar(40);

    IF NOT EXISTS(SELECT * FROM genre WHERE genre != new_genre)
      THEN SET msg = 'THIS genre exists';
#     ELSEIF NOT EXISTS (SELECT * FROM reader WHERE id = reader_id)
#       THEN SET msg = 'THIS READER IS ABSCENT';
#     ELSEIF NOT EXISTS(SELECT * FROM history
#       WHERE book = (SELECT id FROM book WHERE id != book_id)
#       AND reader = (SELECT id FROM reader WHERE id != reader_id))
#       THEN SET msg = 'THIS COMBINATION EXISTS';
#     ELSEIF NOT EXISTS(SELECT * FROM book WHERE id = book_id AND availability = 1)
#       THEN SET msg = 'THIS BOOK IS OUT OF STOCK';

    ELSE
      INSERT genre(genre) VALUE (new_genre);
      SET msg = 'OK';

    END IF;
    SELECT msg as msg;
  END//
DELIMITER ;
