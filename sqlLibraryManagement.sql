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

CREATE TABLE BookStatus (
    statusId INT PRIMARY KEY AUTO_INCREMENT,
    statusName VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE BorrowBook (
    borrowId INT PRIMARY KEY,
    readerId INT,
    bookId INT,
    borrowDate DATE NOT NULL,
    dueDate DATE NOT NULL,
    FOREIGN KEY (readerId) REFERENCES Reader(readerId),
    FOREIGN KEY (bookId) REFERENCES Book(bookId),
    isDeleted BIT DEFAULT 0
);

CREATE TABLE ReturnBook (
    returnId INT PRIMARY KEY,
    borrowId INT,
    returnDate DATE NOT NULL,
    statusId INT,
    FOREIGN KEY (borrowId) REFERENCES BorrowBook(borrowId),
    FOREIGN KEY (statusId) REFERENCES BookStatus(statusId),
    isDeleted BIT DEFAULT 0
);

CREATE TABLE ReturnFine (
    returnFineId INT PRIMARY KEY AUTO_INCREMENT,
    returnId INT,
    lateDays SMALLINT,
    statusId INT,
    FineMoney INT NOT NULL,
    FOREIGN KEY (returnId) REFERENCES ReturnBook(returnId),
    FOREIGN KEY (statusId) REFERENCES BookStatus(statusId),
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
    bookDamageFee INT UNSIGNED NOT NULL,
    lostBookFee INT UNSIGNED NOT NULL,
    maxBooksBorrowed INT UNSIGNED NOT NULL 
);

INSERT INTO Category (categoryName) VALUES 
('Toán học'),
('Vật lý'),
('Hóa học'),
('Văn học'),
('Lịch sử'),
('Địa lý');

INSERT INTO Book (title, author, category, publishYear, totalQuantity, availableQty) VALUES
('Toán Cao Cấp',  'Nguyễn Minh Hoàng', 'Toán học', 2022, 5, 4),
('Đại Số Tuyến Tính', 'Trần Thị Lan', 'Toán học', 2023, 6, 5),
('Phương Trình Vi Phân', 'Lê Văn Dũng', 'Toán học', 2023, 5, 4),
('Giải Tích: Hướng Dẫn Hoàn Chỉnh', 'Phạm Văn Tùng', 'Toán học', 2024, 5, 4),

('Vật Lý Căn Bản', 'Nguyễn Quốc Hùng', 'Vật lý', 2022, 6, 5),
('Nhập Môn Cơ Học Lượng Tử',  'Trần Văn Bảo', 'Vật lý', 2023, 4, 3),
('Cơ Học Cổ Điển', 'Phạm Hồng Phúc', 'Vật lý', 2024, 5, 4),

('Đại Gia Gatsby', 'F. Scott Fitzgerald (Dịch: Nguyễn Hồng Anh)', 'Văn học', 2023, 6, 5),
('Moby Dick', 'Herman Melville (Dịch: Trần Hữu Minh)', 'Văn học', 2023, 4, 3),
('Kiêu Hãnh Và Định Kiến',  'Jane Austen (Dịch: Lê Thu Hà)', 'Văn học', 2024, 5, 5),

('Lịch Sử Các Nền Văn Minh Cổ Đại', 'Nguyễn Quốc Thịnh', 'Lịch sử', 2022, 5, 5),
('Lịch Sử Thế Giới: Góc Nhìn Toàn Cầu', 'Phạm Minh Phương', 'Lịch sử', 2022, 5, 5),
('Sự Trỗi Dậy Và Suy Tàn Của Đế Chế La Mã', 'Lê Văn Cường', 'Lịch sử', 2024, 4, 3),

('Hóa Học Hữu Cơ', 'Nguyễn Thị Thu Hương', 'Hóa học',2023, 6, 5),
('Hóa Lý', 'Phan Văn Bình', 'Hóa học', 2024, 5, 5),

('Địa Lý Thế Giới', 'Lê Hồng Minh', 'Địa lý', 2022, 4, 3),
('Địa Lý Khu Vực Toàn Cầu', 'Nguyễn Thị Mai', 'Địa lý', 2023, 5, 4);

INSERT INTO Reader (name, phoneNumber, address, email, registerDay) VALUES
('Nguyễn Thị Lan Anh', '0345678911', '123 Đường Chính, Thành phố A', 'lananh.nguyen@gmail.com', '2024-10-20'),
('Trần Văn Minh', '0298765432', '456 Đường Sồi, Thành phố B', 'vanminh.tran@gmail.com', '2024-11-08'),
('Phạm Thị Thu Hà', '0214356873', '789 Đường Thông, Thành phố C', 'thuha.pham@gmail.com', '2024-03-08'),
('Lê Hoàng Nam', '0432123454', '321 Đường Bàng, Thành phố D', 'hoangnam.le@gmail.com', '2024-04-12'),
('Ngô Thị Hồng', '0756123455', '654 Đường Dương, Thành phố E', 'hong.ngo@gmail.com', '2024-07-08'),
('Đỗ Đức Anh', '0879654326', '987 Đường Tùng, Thành phố F', 'ducanh.do@gmail.com', '2024-06-18'),
('Bùi Thị Mai Hương', '0567891237', '159 Đường Phong, Thành phố G', 'maihuong.bui@gmail.com', '2024-07-23'),
('Phan Văn Quân', '0654321988', '753 Đường Tràm, Thành phố H', 'vanquan.phan@gmail.com', '2024-08-28'),
('Vũ Minh Hằng', '0987123459', '159 Đường Sồi, Thành phố I', 'minhhang.vu@gmail.com', '2024-11-08'),
('Nguyễn Văn Hùng', '0210987610', '963 Đường Thông, Thành phố J', 'vanhung.nguyen@gmail.com', '2024-10-27'),
('Trần Thị Hạnh', '0357918211', '852 Đường Tùng, Thành phố K', 'thihanh.tran@gmail.com', '2024-11-01'),
('Phạm Văn An', '0486123412', '741 Đường Dương, Thành phố L', 'vanan.pham@gmail.com', '2024-11-08'),
('Ngô Thị Mai', '0712345613', '963 Đường Phong, Thành phố M', 'thimai.ngo@gmail.com', '2024-11-12'),
('Hoàng Thị Lan', '0843456714', '741 Đường Sồi, Thành phố N', 'thilan.hoang@gmail.com', '2024-11-30'),
('Phạm Quốc Bảo', '0921345615', '852 Đường Thông, Thành phố O', 'quocbao.pham@gmail.com', '2024-12-08');

INSERT INTO BookStatus (statusName) VALUES
('Bình thường'),
('Hư hại'),
('Mất sách');

INSERT INTO BorrowBook (borrowId, readerId, bookId, borrowDate, dueDate, isDeleted) VALUES
(11111, 1, 1, '2024-11-01', '2024-11-15', 0),
(11112, 1, 2, '2024-11-02', '2024-11-16', 0),
(11113, 1, 3, '2024-11-05', '2024-11-19', 0),
(22221, 2, 4, '2024-11-07', '2024-11-21', 1),
(22222, 2, 5, '2024-11-10', '2024-11-24', 0),
(22223, 2, 6, '2024-11-12', '2024-11-26', 0),
(33331, 3, 7, '2024-11-15', '2024-11-29', 0),
(33332, 3, 8, '2024-11-16', '2024-11-30', 0),
(44441, 5, 9, '2024-11-18', '2024-12-02', 1),
(44442, 5, 10, '2024-11-20', '2024-12-04', 1),
(44443, 5, 11, '2024-11-25', '2024-12-09', 0),
(55551, 4, 12, '2024-11-27', '2024-12-11', 1),
(55552, 4, 13, '2024-11-30', '2024-12-14', 0),
(66661, 9, 14, '2024-12-01', '2024-12-15', 0),
(66662, 9, 15, '2024-12-02', '2024-12-16', 0),
(66663, 9, 16, '2024-12-03', '2024-12-17', 0);

INSERT INTO ReturnBook (returnId, borrowId, returnDate, statusId, isDeleted) VALUES
(20005, 11111, '2024-11-15', 1, 0), 
(20006, 11112, '2024-11-15', 3, 0), 
(20007, 11113, '2024-11-21', 2, 0),
(20001, 22221, '2024-11-22', 1, 0),
(20008, 22222, '2024-11-24', 1, 0), 
(20009, 22223, '2024-11-30', 1, 0),
(20010, 33331, '2024-11-30', 2, 0),
(20011, 33332, '2024-11-30', 1, 0), 
(20002, 44441, '2024-12-01', 2, 0),
(20003, 44442, '2024-12-02', 1, 0),
(20012, 44443, '2024-12-11', 2, 0),
(20004, 55551, '2024-12-11', 1, 0),
(20013, 55552, '2024-12-14', 1, 0);

INSERT INTO ReturnFine (returnId, lateDays, statusId, fineMoney) VALUES
(20005, 0, 1, 0),
(20006, 0, 3, 50000),
(20007, 3, 2, 11000),
(20001, 1, 1, 2000),
(20008, 0, 1, 0),
(20009, 4, 1, 8000),
(20010, 1, 2, 7000),
(20011, 0, 1, 0),  
(20002, 0, 2, 5000),  
(20003, 0, 1, 0),  
(20012, 2, 2, 9000),  
(20004, 0, 1, 0),
(20013, 0, 1, 0);



INSERT INTO Role (roleName) VALUES 
('Librarian'),
('Admin');

INSERT INTO Employee (name, role, phoneNumber, email, password) VALUES

('admin', 'Admin', '0123456789', 'admin@gmail.com', 'admin'),
('A', 'Librarian', '0372003788', 'a@gmail.com', '123'),
('B', 'Librarian', '0345018165', 'b@gmail.com', '123'),
('C', 'Librarian', '0988660609', 'c@gmail.com', '123');

INSERT INTO Setting (maxBorrowDays, lateFeePerDay, bookDamageFee, lostBookFee, maxBooksBorrowed)
VALUES 
(14, 2000, 10000, 50000, 3);

-- Trigger for random borrow id
DELIMITER $$
CREATE TRIGGER random_borrow_id
BEFORE INSERT ON BorrowBook
FOR EACH ROW
BEGIN
    DECLARE randomId INT;

    SET randomId = FLOOR(10000 + (RAND() * 89999));

    WHILE EXISTS (SELECT 1 FROM BorrowBook WHERE borrowId = randomId) DO
        SET randomId = FLOOR(10000 + (RAND() * 89999));
    END WHILE;
    SET NEW.borrowId = randomId;
END$$
DELIMITER ;
