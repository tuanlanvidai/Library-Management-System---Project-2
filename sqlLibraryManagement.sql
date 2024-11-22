CREATE DATABASE AptLibrary;
GO

USE AptLibrary;
GO

CREATE TABLE Category(
    categoryName VARCHAR(255) PRIMARY KEY,
    isDeleted BIT DEFAULT 0
);
GO

CREATE TABLE Book (
    bookId INT PRIMARY KEY IDENTITY(1,1),
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255),
    category VARCHAR(255),
    publishYear SMALLINT,
    totalQuantity INT NOT NULL,
    availableQty INT NOT NULL,
    isDeleted BIT DEFAULT 0,
    FOREIGN KEY (category) REFERENCES Category(categoryName)
);
GO

CREATE TABLE Role (
    roleName VARCHAR(255) PRIMARY KEY,
    isDeleted BIT DEFAULT 0
);
GO

CREATE TABLE Reader (
    readerId INT PRIMARY KEY IDENTITY(1,1),
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    phoneNumber VARCHAR(15),
    email VARCHAR(255),
    registerDay DATE NOT NULL,
    isDeleted BIT DEFAULT 0
);
GO

CREATE TABLE BorrowBook (
    borrowId INT PRIMARY KEY IDENTITY(1,1),
    readerId INT,
    bookId INT,
    borrowDate DATE NOT NULL,
    dueDate DATE NOT NULL,
    FOREIGN KEY (readerId) REFERENCES Reader(readerId),
    FOREIGN KEY (bookId) REFERENCES Book(bookId),
    isDeleted BIT DEFAULT 0
);
GO

CREATE TABLE ReturnBook (
    returnId INT PRIMARY KEY IDENTITY(1,1),
    borrowId INT,
    returnDate DATE NOT NULL,
    bookStatus VARCHAR(255),
    FOREIGN KEY (borrowId) REFERENCES BorrowBook(borrowId),
    isDeleted BIT DEFAULT 0
);
GO

CREATE TABLE ReturnFine(
    returnFineId INT PRIMARY KEY IDENTITY(1,1),
    returnId INT,
    lateDays SMALLINT,
    bookStatus VARCHAR(255),
    FineMoney INT NOT NULL,
    FOREIGN KEY (returnId) REFERENCES ReturnBook(returnId),
    isDeleted BIT DEFAULT 0
);
GO

CREATE TABLE Employee (
    employeeId INT PRIMARY KEY IDENTITY(1,1),
    name VARCHAR(255) NOT NULL,
    role VARCHAR(255),
    phoneNumber VARCHAR(15),
    email VARCHAR(255),
    password VARCHAR(255) NOT NULL,
    FOREIGN KEY (role) REFERENCES Role(roleName),
    isDeleted BIT DEFAULT 0
);
GO
