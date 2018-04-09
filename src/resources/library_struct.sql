DROP TABLE IF EXISTS authors;

CREATE TABLE authors (
  AuthorID int(11) NOT NULL AUTO_INCREMENT,
  AuthorName varchar(50) NOT NULL,
  PRIMARY KEY (AuthorID)
); 

DROP TABLE IF EXISTS books;

CREATE TABLE books (
  BookID int(11) NOT NULL AUTO_INCREMENT,
  BookTitle varchar(255) NOT NULL,
  AuthorID int(11) NOT NULL,
  Lent tinyint(4) DEFAULT 0,
  PRIMARY KEY (BookID),
  FOREIGN KEY (AuthorID) REFERENCES authors (AuthorID)
);

DROP TABLE IF EXISTS users;

CREATE TABLE users (
  UserID int(11) NOT NULL AUTO_INCREMENT,
  UserName varchar(50) NOT NULL,
  BorrowedBooks int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (UserID)
);

DROP TABLE IF EXISTS loans;

CREATE TABLE loans (
  BookID int(11) NOT NULL,
  UserID int(11) NOT NULL,
  LoanStartDate date NOT NULL,
  LoanEndDate date NOT NULL,
  PRIMARY KEY (BookID,UserID),
  FOREIGN KEY (BookID) REFERENCES books (BookID),
  FOREIGN KEY (UserID) REFERENCES users (UserID)
);

