CREATE DATABASE AptLibrary;

USE AptLibrary;

CREATE TABLE Category(
    categoryName VARCHAR(255) PRIMARY KEY,
    isDeleted BIT DEFAULT 0
);

CREATE TABLE Book (
    bookId INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255),
    category VARCHAR(255),
    publishYear SMALLINT,
    totalQuantity INT NOT NULL,
    availableQty INT NOT NULL,
    isDeleted BIT DEFAULT 0,
    FOREIGN KEY (category) REFERENCES Category(categoryName)
);

CREATE TABLE Role (
    roleName VARCHAR(255) PRIMARY KEY,
    isDeleted BIT DEFAULT 0
);

CREATE TABLE Reader (
    readerId INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    phoneNumber VARCHAR(15),
    email VARCHAR(255),
    registerDay DATE NOT NULL,
    isDeleted BIT DEFAULT 0
);

CREATE TABLE BorrowBook (
    borrowId INT PRIMARY KEY AUTO_INCREMENT,
    readerId INT,
    bookId INT,
    borrowDate DATE NOT NULL,
    dueDate DATE NOT NULL,
    FOREIGN KEY (readerId) REFERENCES Reader(readerId),
    FOREIGN KEY (bookId) REFERENCES Book(bookId),
    isDeleted BIT DEFAULT 0
);

CREATE TABLE ReturnBook (
    returnId INT PRIMARY KEY AUTO_INCREMENT,
    borrowId INT,
    returnDate DATE NOT NULL,
    bookStatus VARCHAR(255),
    FOREIGN KEY (borrowId) REFERENCES BorrowBook(borrowId),
    isDeleted BIT DEFAULT 0
);

CREATE TABLE ReturnFine(
    returnFineId INT PRIMARY KEY AUTO_INCREMENT,
    returnId INT,
    lateDays SMALLINT,
    bookStatus VARCHAR(255),
    FineMoney INT NOT NULL,
    FOREIGN KEY (returnId) REFERENCES ReturnBook(returnId),
    isDeleted BIT DEFAULT 0
);

CREATE TABLE Employee (
    employeeId INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    role VARCHAR(255),
    phoneNumber VARCHAR(15),
    email VARCHAR(255),
    password VARCHAR(255) NOT NULL,
    FOREIGN KEY (role) REFERENCES Role(roleName),
    isDeleted BIT DEFAULT 0
);

CREATE TABLE Setting (
	settingId INT AUTO_INCREMENT PRIMARY KEY,
    maxBorrowDays INT UNSIGNED NOT NULL,
    lateFeePerDay INT UNSIGNED NOT NULL,
    maxBooksBorrowed INT UNSIGNED NOT NULL
);

INSERT INTO Category (categoryName) VALUES 
('Mathematics'),
('Physics'),
('Chemistry'),
('Literature'),
('History'),
('Geography');

INSERT INTO Book (title, author, category, publishYear, totalQuantity, availableQty) VALUES
('Advanced Mathematics',  'John Doe', 'Mathematics', 2022, 5, 4),
('Linear Algebra', 'Jane Smith', 'Mathematics', 2023, 6, 5),
('Differential Equations', 'Albert Johnson', 'Mathematics', 2023, 5, 4),
('Calculus: A Complete Guide', 'Emily Davis', 'Mathematics', 2024, 5, 4),

('Fundamentals of Physics', 'Richard Feynman', 'Physics', 2022, 6, 5),
('Introduction to Quantum Mechanics',  'Jane Smith', 'Physics', 2023, 4, 3),
('Classical Mechanics', 'Herbert Goldstein', 'Physics', 2024, 5, 4),

('The Great Gatsby', 'F. Scott Fitzgerald', 'Literature', 2023, 6, 5),
('Moby Dick', 'Herman Melville', 'Literature', 2023, 4, 3),
('Pride and Prejudice',  'Jane Austen', 'Literature', 2024, 5, 5),

('A History of Ancient Civilizations', 'William Thompson', 'History', 2022, 5, 5),
('World History: A Global Perspective', 'Anna Roberts', 'History', 2022, 5, 5),
('The Rise and Fall of the Roman Empire', 'Edward Gibbon', 'History', 2024, 4, 3),

('Organic Chemistry', 'Paula Bruice', 'Chemistry',2023, 6, 5),
('Physical Chemistry', 'Peter Atkins', 'Chemistry', 2024, 5, 5),

('Geography of the World', 'Michael Smith', 'Geography', 2022, 4, 3),
('World Regional Geography', 'James Peterson', 'Geography', 2023, 5, 4);


INSERT INTO Reader (name, phoneNumber, address, email, registerDay) VALUES
('Alice Johnson', '0345678911', '123 Main St, City A', 'alice.johnson@gmail.com', '2024-10-20'),
('Bob Smith', '0298765432', '456 Oak St, City B', 'bob.smith@gmail.com', '2024-11-08'),
('Charlie Brown', '0214356873', '789 Pine St, City C', 'charlie.brown@gmail.com', '2024-03-08'),
('David Lee', '0432123454', '321 Elm St, City D', 'david.lee@gmail.com', '2024-04-12'),
('Eva Green', '0756123455', '654 Birch St, City E', 'eva.green@gmail.com', '2024-07-08'),
('Fay Miller', '0879654326', '987 Cedar St, City F', 'fay.miller@gmail.com', '2024-06-18'),
('George King', '0567891237', '159 Maple St, City G', 'george.king@gmail.com', '2024-07-23'),
('Hannah Scott', '0654321988', '753 Fir St, City H', 'hannah.scott@gmail.com', '2024-08-28'),
('Ian Wright', '0987123459', '159 Oak St, City I', 'ian.wright@gmail.com', '2024-11-08'),
('Jack White', '0210987610', '963 Pine St, City J', 'jack.white@gmail.com', '2024-10-27'),
('Kim Adams', '0357918211', '852 Cedar St, City K', 'kim.adams@gmail.com', '2024-11-01'),
('Laura Taylor', '0486123412', '741 Birch St, City L', 'laura.taylor@gmail.com', '2024-11-08'),
('Mandy Phillips', '0712345613', '963 Maple St, City M', 'mandy.phillips@gmail.com', '2024-11-12'),
('Nancy Davis', '0843456714', '741 Oak St, City N', 'nancy.davis@gmail.com', '2024-11-31'),
('Oscar Wilson', '0921345615', '852 Pine St, City O', 'oscar.wilson@gmail.com', '2024-12-08');

INSERT INTO BorrowBook (readerId, bookId, borrowDate, dueDate) VALUES
(1, 1, '2024-11-02', '2024-11-09'),
(2, 2, '2024-11-05', '2024-11-12'),
(3, 3, '2024-11-07', '2024-11-14'),
(4, 4, '2024-11-08', '2024-11-15'),
(5, 5, '2024-11-10', '2024-11-17'),
(6, 6, '2024-11-12', '2024-11-19'),
(7, 7, '2024-11-15', '2024-11-22'),
(8, 8, '2024-11-18','2024-11-25'),
(9, 9, '2024-11-20', '2024-11-27'),
(10, 10, '2024-11-22', '2024-11-29'),
(11, 11, '2024-11-24', '2024-12-01'),
(12, 12, '2024-11-24', '2024-12-01'),
(13, 13, '2024-11-27','2024-12-04'),
(14, 14, '2024-12-01', '2024-12-08'),
(15, 15, '2024-12-03', '2024-12-10');

INSERT INTO ReturnBook (borrowId, returnDate, bookStatus) VALUES
(1, '2024-11-11', 'Damaged'),  
(2, '2024-11-17', 'Good'),     
(3, '2024-11-15', 'Good'),     
(4, '2024-11-17', 'Good'),     
(5, '2024-11-18', 'Good'),     
(6, '2024-11-22', 'Good'),     
(7, '2024-11-20', 'Good'),    
(8, '2024-11-23', 'Good'),    
(9, '2024-11-27', 'Good'),     
(10, '2024-11-25', 'Damaged'),   
(11, '2024-11-30', 'Good'),    
(12, '2024-12-01', 'Good'),    
(13, '2024-11-30', 'Good'),    
(14, '2024-12-07', 'Good'),    
(15, '2024-12-10', 'Good');   


INSERT INTO ReturnFine (returnId, lateDays, bookStatus, FineMoney) VALUES
-- fee = 10k/day, damaged = 40k
(1, 2, 'Damaged', 60),   
(2, 5, 'Good', 50),      
(3, 1, 'Good', 10),      
(4, 2, 'Good', 20),      
(5, 1, 'Good', 10),
(6, 3, 'Good', 30),      
(7, 0, 'Good', 0),      
(8, 0, 'Good', 0),       
(9, 0, 'Good', 0),       
(10, 0, 'Damaged', 40),  
(11, 0, 'Good', 0),      
(12, 0, 'Good', 0),     
(13, 0, 'Good', 0),     
(14, 0, 'Good', 0),    
(15, 0, 'Good', 0);      


INSERT INTO Role (roleName) VALUES 
('Librarian'),
('Admin');

INSERT INTO Employee (name, role, phoneNumber, email, password) VALUES

('admin', 'Admin', '0123456789', 'admin@gmail.com', 'admin'),
('A', 'Librarian', '0372003788', 'a@gmail.com', '123'),
('B', 'Librarian', '0345018165', 'b@gmail.com', '123'),
('C', 'Librarian', '0988660609', 'c@gmail.com', '123');

INSERT INTO Setting (maxBorrowDays, lateFeePerDay, maxBooksBorrowed)
VAlUES (14, 2000, 3);
