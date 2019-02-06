USE master
GO

IF DB_ID('Library_Lohvinov_8var') IS NOT NULL
DROP DATABASE Library
GO

CREATE DATABASE Library
ON PRIMARY
(NAME = 'Library', FILENAME='D:\DB_lab5\library.mdf', SIZE = 30MB, MAXSIZE = 100MB, FILEGROWTH = 5MB)
LOG ON
(NAME = 'Library_log',  FILENAME='D:\DB_lab5\library_log.ldf', SIZE = 8MB, MAXSIZE = 40MB, FILEGROWTH = 4MB)
GO
USE Library

CREATE TABLE book (
id		int		NOT NULL,
title	nvarchar(50) NOT NULL,
author	nvarchar(50) NULL,
genre nvarchar(20) NOT NULL,
publisher nvarchar(20) NULL,
publish_year int NULL,
price	int	    NOT NULL DEFAULT 0,	
income_date date NOT NULL DEFAULT GETDATE(),
income_amount int NOT NULL,
photo varbinary(max) NULL,
description varbinary(max) NULL,
availability bit NOT NULL

CONSTRAINT PK_book_id
PRIMARY KEY NONCLUSTERED (id)
)

CREATE TABLE reader (
id	    int      NOT NULL,
surname nvarchar(20) NOT NULL,
name    nvarchar(20) NULL,
address nvarchar(50) NOT NULL

CONSTRAINT PK_reader_id
PRIMARY KEY NONCLUSTERED (id)
)


CREATE TABLE history (
book   int NULL,
reader int NULL,
out_date date NULL,
back_date date NULL
)


ALTER TABLE book
ADD CONSTRAINT CK_book_publish_year
CHECK (publish_year > 1950)

ALTER TABLE book
ADD CONSTRAINT CK_book_incoume
CHECK (income_amount >= 1)

ALTER TABLE history 
ADD CONSTRAINT UQ_history_book
UNIQUE(book)

ALTER TABLE history 
ADD CONSTRAINT UQ_history_reader
UNIQUE(reader)


ALTER TABLE history 
ADD CONSTRAINT FK_history_book
FOREIGN KEY (book) REFERENCES book (id)
ON DELETE CASCADE
ON UPDATE CASCADE

ALTER TABLE history 
ADD CONSTRAINT FK_history_reader
FOREIGN KEY (reader) REFERENCES reader (id)
ON DELETE CASCADE
ON UPDATE CASCADE

GO


insert into book values(1,'tales','perro', 'tales', 'p1', 1992, 10, '2000-10-10', 1,
 (SELECT * FROM OPENROWSET(BULK N'D:\DB_lab5\ex.jpg', SINGLE_BLOB) IMG_DATA),
 (SELECT * FROM OPENROWSET(BULK N'D:\DB_lab5\txt.txt', SINGLE_BLOB) IMG_DATA), 'true');


