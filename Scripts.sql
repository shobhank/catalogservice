u root p root

CREATE DATABASE catalog;
use catalog;
CREATE TABLE book (id INT NOT NULL, isbn VARCHAR(20) NOT NULL, author VARCHAR(100) NOT NULL, title VARCHAR(100) NOT NULL,language VARCHAR(10) NOT NULL,rating FLOAT(2,1) NOT NULL, PRIMARY KEY(id));
CREATE INDEX book_ix on book (id, title);
INSERT INTO book VALUES (1,"1338099132","Dan Brown","Digital Fortress","English",8.2);
INSERT INTO book VALUES (2,"1338099133","J.R.R. Tolkein","The Lord Of The Rings Trilogy","English",8.2);
INSERT INTO book VALUES (3,"1038049132","J.K. Rowling","Harry Potter And The Chamber Of Secrets","English",8.2);
INSERT INTO book VALUES (4,"1338299132","Harper Lee","To Kill A Mocking Bird","English",8.2);
INSERT INTO book VALUES (5,"1328099132","William Shakespeare","Hamlet","English",8.2);
