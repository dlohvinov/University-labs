DROP DATABASE IF EXISTS russianibrary;
USE Library;
INSERT INTO genre VALUES ('tales'),('detective'),('novel'),('history'), ('adventure');

INSERT INTO book (id, title, author, genre, publisher, availability) VALUES(1, 'tales by Sharle Perro', 'Sharle Perro', 'tales', 'great_publisher', True);
INSERT INTO book (id, title, author, genre, publisher, availability) VALUES(2, 'Sherlock Holmes', 'Sharle Perro', 'detective', 'nice_publisher', False);
INSERT INTO book (id, title, author, genre, publisher, availability) VALUES(3, 'High Rock', 'Harry Rock', 'detective', 'jelous_publisher', True);
INSERT INTO book (id, title, author, genre, publisher, availability) VALUES(4, 'Histury of IIWW', 'Jasmine Jane', 'history', 'kind_publisher', True);
INSERT INTO book (id, title, author, genre, publisher, availability) VALUES(5, 'Aivengo', 'Vallter Scott', 'novel', 'neat_publisher', False);
INSERT INTO book (id, title, author, genre, publisher, availability) VALUES(6, 'tales by Hrimm', 'Brothers Hrimm', 'tales', 'great_publisher', False);
INSERT INTO book (id, title, author, genre, publisher, availability) VALUES(7, '12 chairs', 'Ilf and Pertov', 'adventure', 'nice_publisher', False);
INSERT INTO book (id, title, author, genre, publisher, availability) VALUES(8, '13 chairs', 'Not Ilf and Not Petrov', 'novel', 'jelous_publisher', True);
INSERT INTO book (id, title, author, genre, publisher, availability) VALUES(9, 'Lord of the Rings', 'Tolkien', 'tales', 'kind_publisher', False);
INSERT INTO book (id, title, author, genre, publisher, availability) VALUES(10, 'Ubick', 'Dick', 'novel', 'neat_publisher', True);

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