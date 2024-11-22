CREATE DATABASE QLTV_BETA
GO
USE QLTV_BETA
GO
SET DATEFORMAT DMY -- dateformat: dd/mm/yyyy
GO﻿

CREATE TABLE DOCGIA 
(
	MaDG		VARCHAR(5) NOT NULL PRIMARY KEY,
	HoTen		NVARCHAR(30),
	GioiTinh	NVARCHAR(3),
	NgaySinh	DATE,
	DiaChi		NVARCHAR(255),
	Email		VARCHAR(255) NOT NULL,
	SoDT		VARCHAR(10) NOT NULL,
	NgayLapThe	DATE,
	IsDeleted	BIT DEFAULT 0
);
GO

CREATE TABLE THELOAI
(
	TenTheLoai	NVARCHAR(225) PRIMARY KEY,
	IsDeleted	BIT DEFAULT 0
);
GO

CREATE TABLE SACH 
(
	MaSach		VARCHAR(5) NOT NULL PRIMARY KEY,
	TenSach		NVARCHAR(255),
	TheLoai	NVARCHAR(225) FOREIGN KEY REFERENCES THELOAI(TenTheLoai),
	TacGia		NVARCHAR(255),
	NamXB		SMALLINT,
	NhaXB		NVARCHAR(255),
	NgayNhap	DATE,
	TriGia		INT,
	TinhTrang	SMALLINT DEFAULT 1,
	IsDeleted	BIT DEFAULT 0
);
GO

CREATE TABLE PHIEUMUON
(
	MaPhMuon	VARCHAR(5) NOT NULL PRIMARY KEY,
	MaDG		VARCHAR(5) FOREIGN KEY REFERENCES DOCGIA(MaDG),
	MaSach		VARCHAR(5) FOREIGN KEY REFERENCES SACH(MaSach),
	NgayMuon	DATE,
	NgayPhTra	DATE,
	IsDeleted	BIT DEFAULT 0
);
GO

CREATE TABLE PHIEUTRA
(
	MaPhTra		VARCHAR(5) NOT NULL PRIMARY KEY,
	MaPhMuon	VARCHAR(5) FOREIGN KEY REFERENCES PHIEUMUON(MaPhMuon),
	NgayTra		DATE,
	IsDeleted	BIT DEFAULT 0
);
GO

CREATE TABLE PHIEUTHU
(
	ID			INT IDENTITY(1, 1) PRIMARY KEY,
	MaPhTra		VARCHAR(5) FOREIGN KEY REFERENCES PHIEUTRA(MaPhTra),
	SoNgayQHan	SMALLINT,
	SoTienThu	INT,
	IsDeleted	BIT DEFAULT 0
);
GO

CREATE TABLE THUTHU
(
	MaTT		VARCHAR(5) PRIMARY KEY,
	HoTen		NVARCHAR(30),
	GioiTinh	NVARCHAR(3),
	NgayVLam	DATE,
	NgaySinh	DATE,
	DiaChi		NVARCHAR(255),
	Email		VARCHAR(255) NOT NULL,
	SoDT		VARCHAR(10) NOT NULL,
	IsDeleted	BIT DEFAULT 0
)
GO

CREATE TABLE ACCOUNT
(
	TaiKhoan	VARCHAR(30) PRIMARY KEY,
	MatKhau		VARCHAR(255) NOT NULL,
	MaDG		VARCHAR(5) FOREIGN KEY REFERENCES DOCGIA(MaDG),
	MaTT		VARCHAR(5) FOREIGN KEY REFERENCES THUTHU(MaTT),
	VaiTro		SMALLINT NOT NULL,
	IsDeleted	BIT DEFAULT 0
);
GO

CREATE TABLE SETTING
(
	ID					INT PRIMARY KEY,
	TuoiToiTieuDocGia	INT	DEFAULT 13,
	TuoiToiDaDocGia		INT	DEFAULT 70,
	TuoiToiTieuThuThu	INT	DEFAULT 13,
	TuoiToiDaThuThu		INT	DEFAULT 70,
	ThoiHanThe			INT DEFAULT 24,
	SoNgayMuonToiDa		INT DEFAULT 30,
	SoTienNopTre		INT DEFAULT 2000,
	SoSachMuonToiDa		INT	DEFAULT 5,
	SoLuongTheLoaiToiDa INT DEFAULT 30,
	ThoiGianNhapSach	INT DEFAULT 5
)
GO

CREATE TABLE PARAMETERS
(
	ID 		INT PRIMARY KEY,
	IDDocGia	SMALLINT DEFAULT 6,
	IDSach		SMALLINT DEFAULT 8,
	IDPhieuMuon	SMALLINT DEFAULT 11,
	IDPhieuTra	SMALLINT DEFAULT 9,
	IDMaTT		SMALLINT DEFAULT 3
)

-- Luôn cần một dòng dữ liệu trong bảng SETTING và PARAMETERs để truy xuất thông tin sau này
INSERT INTO SETTING VALUES(1, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT);
GO
INSERT INTO PARAMETERS VALUES(1, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT);
GO

-- Trigger nếu muốn thêm sách với thể loại không phụ thuộc vào thể loại đã có
/*
CREATE TRIGGER THEM_SACH_VOI_THELOAI
ON SACH
FOR INSERT, UPDATE
AS
BEGIN
	DECLARE @MaSach VARCHAR(5), @TheLoai VARCHAR(225)

	SELECT @MaSach = MaSach from INSERTED
	SELECT @TheLoai = TheLoai from INSERTED

	IF NOT EXISTS (
		SELECT * FROM THELOAI
		WHERE LOWER(TenTheLoai) = LOWER(@TheLoai)
	)
	BEGIN
		INSERT INTO THELOAI VALUES (@TheLoai, 0)
	END
	ELSE IF NOT EXISTS (
		SELECT * FROM THELOAI
		WHERE TenTheLoai = @TheLoai
	)
	BEGIN
		UPDATE SACH
		SET TheLoai = (
			SELECT TenTheLoai
			FROM THELOAI
			WHERE LOWER(TenTheLoai) = LOWER(@TheLoai)
		)
		WHERE SACH.MaSach = @MaSach
	END
END*/
GO

-- Tạo TRIGGER để tính ngày phải trả của cuốn sách
CREATE TRIGGER NGAY_PHAI_TRA_PHIEUMUON
ON PHIEUMUON
FOR INSERT
AS
BEGIN
	DECLARE @MaPhMuon VARCHAR(5), @MAXDAY INT = 30
	DECLARE @MaSach VARCHAR(5), @TinhTrang SMALLINT
	
	SELECT @MaPhMuon = MaPhMuon FROM INSERTED
	SELECT @MaSach = MaSach FROM INSERTED
	SELECT @TinhTrang = TinhTrang FROM SACH WHERE MaSach = @MaSach

	IF (@TinhTrang = 0) 
	BEGIN
		ROLLBACK;
		RAISERROR('Cuốn sách này đã được mượn!', 10, 1)
	END
	
	-- Tính toán hạn trả trong phiếu mượn
	UPDATE PHIEUMUON 
	SET NgayPhTra = DATEADD(day, @MAXDAY, NgayMuon)
	WHERE PHIEUMUON.MaPhMuon = @MaPhMuon

	-- Điều chỉnh lại thuộc tính tình trạng của sách được mượn thành 0
	UPDATE SACH
	SET TinhTrang = 0
	WHERE SACH.MaSach = @MaSach
END;
GO

-- Tạo TRIGGER kiểm tra có trả sách quá hạn không, nếu có thì tạo phiếu thu trong bảng PHIEUTHU tương ứng với phiếu mượn, phiếu trả đó
CREATE TRIGGER KIEM_TRA_QUA_HAN_PHIEUTRA
ON PHIEUTRA
FOR INSERT, UPDATE
AS
BEGIN
	-- Lấy dữ liệu để kiểm tra điều kiện
	DECLARE @NgayPhTra DATE, @NgayTra DATE
	DECLARE @MaSach VARCHAR(5)

	SELECT @NgayPhTra = NgayPhTra
	FROM INSERTED INNER JOIN PHIEUMUON ON INSERTED.MaPhMuon = PHIEUMUON.MaPhMuon
	SELECT @NgayTra = NgayTra FROM INSERTED

	SELECT @MaSach = PHIEUMUON.MaSach
	FROM INSERTED 
	INNER JOIN PHIEUMUON ON INSERTED.MaPhMuon = PHIEUMUON.MaPhMuon
	INNER JOIN SACH ON SACH.MaSach = PHIEUMUON.MaSach

	UPDATE SACH
	SET TinhTrang = 1
	WHERE MaSach = @MaSach

	IF (@NgayTra > @NgayPhTra)
	BEGIN
		DECLARE @MaPhTra VARCHAR(5), @IDPhTra INT = 2000, @SoNgQuaHan INT

		SELECT @MaPhTra = MaPhTra FROM INSERTED
		SELECT @SoNgQuaHan = DATEDIFF(day, @NgayPhTra, @NgayTra)

		-- Tổng tiền = Số ngày * Tiền nộp 1 ngày
		INSERT INTO PHIEUTHU VALUES(@MaPhTra, @SoNgQuaHan, @SoNgQuaHan * @IDPhTra, DEFAULT);
	END
END;
GO

-- INSERT DATA: Dữ liệu để test các chức năng của app
-- DOCGIA TABLE
INSERT INTO DOCGIA VALUES ('DG001', N'Nguyễn Thanh Hưng', N'Nam', '2002-06-15', N'135 Nam Kỳ Khởi Nghĩa, Bến Nghé, Quận 1', 'hungnt@gmail.com', '0392511342', '2024-02-07', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG002', N'Trần Nguyễn Yến Nhi', N'Nữ', '2008-11-14', N'Đường Lê Lợi, phường Bến Thành, quận 1', 'nhitran08@gmail.com', '0867455258', '2024-03-07', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG003', N'Trần Lê Tuyết Mai', N'Nữ', '2004-10-14', N'số 2 Nguyễn Bỉnh Khiêm, Quận 1', 'lmaiq1@gmail.com', '0914567842', '2024-03-12', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG004', N'Lê Thị Ngọc Ánh', N'Nữ', '2004-12-02', N'số 2 Khu Him Lam, quận 7', 'anhngoc@gmail.com', '0392411748', '2024-04-14', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG005', N'Phan Minh Long', N'Nam', '2009-05-07', N'Số 3 Hòa Bình, phường 3, quận 11', 'longSia@gmail.com', '0974373212', '2024-05-07', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG006', N'Nguyễn Thị Lan', N'Nữ', '1995-08-19', N'25 Lê Văn Sỹ, Quận 3', 'lannguyen@gmail.com', '0987623451', '2024-02-17', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG007', N'Phạm Văn Hoàng', N'Nam', '2001-12-10', N'11 Trần Hưng Đạo, Quận 5', 'hoangpham@gmail.com', '0376542312', '2024-06-07', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG008', N'Trịnh Thị Hồng Nhung', N'Nữ', '1998-04-22', N'78 Nguyễn Trãi, Quận 1', 'nhungtrinh@gmail.com', '0909876543', '2024-03-19', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG009', N'Lê Anh Tú', N'Nam', '2003-07-15', N'12 Đặng Văn Bi, Thủ Đức', 'tule@gmail.com', '0356789123', '2024-04-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG010', N'Võ Thị Minh Hằng', N'Nữ', '2000-11-05', N'90 Võ Thị Sáu, Quận 3', 'hangvo@gmail.com', '0389876543', '2024-05-13', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG011', N'Nguyễn Văn Bình', N'Nam', '1996-02-12', N'45 Điện Biên Phủ, Quận 10', 'binhnguyen@gmail.com', '0934567891', '2024-06-20', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG012', N'Phạm Thị Bích Ngọc', N'Nữ', '1997-09-23', N'60 Nguyễn Huệ, Quận 1', 'ngocpham@gmail.com', '0945678912', '2024-07-14', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG013', N'Trần Minh Đức', N'Nam', '2002-10-18', N'32 Lý Thường Kiệt, Quận 10', 'ductran@gmail.com', '0967891234', '2024-08-07', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG014', N'Lê Thị Thanh Hà', N'Nữ', '2005-06-30', N'40 Nguyễn Du, Quận 1', 'hathanh@gmail.com', '0978912345', '2024-09-15', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG015', N'Nguyễn Minh Anh', N'Nam', '2001-04-21', N'120 Lê Quang Định, Bình Thạnh', 'minhanhnguyen@gmail.com', '0989123456', '2024-10-02', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG016', N'Phan Thị Hồng Loan', N'Nữ', '1999-03-14', N'100 Đinh Tiên Hoàng, Quận 1', 'loanphan@gmail.com', '0912345678', '2024-11-05', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG017', N'Trịnh Văn Hưng', N'Nam', '2007-07-22', N'200 Trần Bình Trọng, Quận 5', 'hungtrinh@gmail.com', '0923456789', '2024-12-07', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG018', N'Vũ Thị Thanh Mai', N'Nữ', '1998-11-30', N'45 Phạm Ngũ Lão, Quận 1', 'maivu@gmail.com', '0934567890', '2024-01-12', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG019', N'Nguyễn Văn Tuấn', N'Nam', '2004-02-27', N'33 Trương Định, Quận 3', 'tuannguyen@gmail.com', '0945678901', '2024-02-20', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG020', N'Hoàng Thị Mai Phương', N'Nữ', '2000-10-10', N'58 Cách Mạng Tháng 8, Quận 10', 'phuonghoang@gmail.com', '0956789012', '2024-03-25', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG021', N'Lê Văn An', N'Nam', '2003-05-05', N'72 Bùi Thị Xuân, Quận 1', 'anle@gmail.com', '0967890123', '2024-04-17', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG022', N'Trần Thị Bích Ngọc', N'Nữ', '1997-08-13', N'14 Nguyễn Thị Minh Khai, Quận 3', 'ngoctran@gmail.com', '0978901234', '2024-05-21', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG023', N'Nguyễn Văn Hoàng', N'Nam', '2002-11-11', N'21 Lê Lợi, Quận 1', 'hoangnguyen@gmail.com', '0989012345', '2024-06-13', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG024', N'Phạm Thị Thu Hằng', N'Nữ', '2005-09-19', N'67 Phan Đình Phùng, Phú Nhuận', 'hangpham@gmail.com', '0990123456', '2024-07-05', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG025', N'Trần Văn Bảo', N'Nam', '1996-12-12', N'22 Lê Văn Sỹ, Quận 3', 'baotran@gmail.com', '0901234567', '2024-08-14', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG026', N'Võ Thị Kim Liên', N'Nữ', '1999-07-25', N'11 Phạm Văn Đồng, Bình Thạnh', 'lienvo@gmail.com', '0912345679', '2024-09-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG027', N'Nguyễn Minh Thắng', N'Nam', '2001-06-28', N'101 Nguyễn Đình Chiểu, Quận 1', 'thangnguyen@gmail.com', '0923456791', '2024-10-09', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG028', N'Phạm Thị Minh Châu', N'Nữ', '1998-02-24', N'73 Lê Lợi, Quận 1', 'chaupham@gmail.com', '0934567910', '2024-11-11', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG029', N'Lê Văn Kiên', N'Nam', '2003-03-15', N'99 Võ Văn Tần, Quận 3', 'kienle@gmail.com', '0945678910', '2024-12-18', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG030', N'Trần Thị Thanh Mai', N'Nữ', '2000-04-18', N'35 Phan Văn Trị, Gò Vấp', 'maitran@gmail.com', '0956789102', '2024-01-24', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG031', N'Nguyễn Văn Lâm', N'Nam', '1996-06-20', N'12 Đinh Bộ Lĩnh, Bình Thạnh', 'lamnguyen@gmail.com', '0967891023', '2024-02-18', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG032', N'Phạm Thị Bảo Ngọc', N'Nữ', '2005-08-22', N'78 Nguyễn Văn Trỗi, Phú Nhuận', 'ngocbao@gmail.com', '0978910234', '2024-03-22', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG033', N'Trịnh Văn Hùng', N'Nam', '2002-10-23', N'90 Bạch Đằng, Bình Thạnh', 'hungtrinh@gmail.com', '0989102345', '2024-04-25', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG034', N'Vũ Thị Hồng Thắm', N'Nữ', '1997-12-25', N'102 Hai Bà Trưng, Quận 1', 'thamvu@gmail.com', '0991023456', '2024-05-25', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG035', N'Nguyễn Minh Tuấn', N'Nam', '2001-01-29', N'11 Nguyễn Thái Học, Quận 1', 'tuannguyen@gmail.com', '0902103456', '2024-06-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG036', N'Phạm Thị Minh Tâm', N'Nữ', '2003-11-14', N'150 Trần Quang Khải, Quận 1', 'tampham@gmail.com', '0910234567', '2024-07-28', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG037', N'Trần Văn Phú', N'Nam', '1999-03-13', N'66 Nguyễn Trãi, Quận 5', 'phutran@gmail.com', '0921034567', '2024-08-30', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG038', N'Lê Thị Thanh Hà', N'Nữ', '2000-04-17', N'14 Lê Hồng Phong, Quận 10', 'halethanh@gmail.com', '0932045678', '2024-09-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG039', N'Nguyễn Văn Hoàng', N'Nam', '1996-05-14', N'45 Nguyễn Tri Phương, Quận 10', 'hoangnguyen@gmail.com', '0943056789', '2024-10-25', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG040', N'Phạm Thị Thanh Tuyền', N'Nữ', '2003-06-17', N'33 Nguyễn Văn Thủ, Quận 1', 'tuyenpham@gmail.com', '0954067891', '2024-11-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG041', N'Trần Văn Hải', N'Nam', '2001-12-12', N'101 Lê Thánh Tôn, Quận 1', 'haitran@gmail.com', '0965078912', '2024-12-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG042', N'Lê Thị Thanh Mai', N'Nữ', '1999-08-23', N'99 Nguyễn Đình Chiểu, Quận 3', 'mailethanh@gmail.com', '0976089123', '2024-01-24', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG043', N'Nguyễn Minh Đức', N'Nam', '2004-09-22', N'11 Võ Văn Kiệt, Quận 1', 'ducnguyen@gmail.com', '0987091234', '2024-02-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG044', N'Phạm Văn Hùng', N'Nam', '2002-07-11', N'45 Đinh Tiên Hoàng, Bình Thạnh', 'hungpham@gmail.com', '0998012345', '2024-03-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG045', N'Trần Thị Mai Hương', N'Nữ', '1997-11-28', N'102 Lê Văn Sỹ, Quận 3', 'huongtran@gmail.com', '0909123456', '2024-04-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG046', N'Nguyễn Văn Thành', N'Nam', '2000-05-13', N'72 Phạm Ngũ Lão, Quận 1', 'thanhnguyen@gmail.com', '0911234567', '2024-05-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG047', N'Phạm Thị Hồng', N'Nữ', '2003-01-10', N'56 Đinh Tiên Hoàng, Quận 1', 'hongpham@gmail.com', '0922345678', '2024-06-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG048', N'Trần Văn Đức', N'Nam', '1998-04-11', N'88 Lý Tự Trọng, Quận 1', 'ductran@gmail.com', '0933456789', '2024-07-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG049', N'Lê Thị Thanh Hương', N'Nữ', '2001-06-14', N'60 Nguyễn Huệ, Quận 1', 'huongle@gmail.com', '0944567890', '2024-08-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG050', N'Nguyễn Văn Tuấn', N'Nam', '1996-09-09', N'44 Nguyễn Trãi, Quận 1', 'tuannguyen@gmail.com', '0955678901', '2024-09-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG051', N'Phạm Thị Thu Thủy', N'Nữ', '2002-12-12', N'12 Nguyễn Đình Chiểu, Quận 3', 'thuypham@gmail.com', '0966789012', '2024-10-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG052', N'Trần Văn An', N'Nam', '1999-03-15', N'100 Trần Hưng Đạo, Quận 1', 'antran@gmail.com', '0977890123', '2024-11-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG053', N'Lê Thị Hồng Nhung', N'Nữ', '2004-05-18', N'120 Nguyễn Văn Trỗi, Phú Nhuận', 'nhungle@gmail.com', '0988901234', '2024-12-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG054', N'Nguyễn Minh Tuấn', N'Nam', '2000-07-12', N'45 Lý Thường Kiệt, Quận 10', 'tuanminh@gmail.com', '0999012345', '2024-01-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG055', N'Phạm Văn Hiếu', N'Nam', '1997-11-28', N'56 Lê Hồng Phong, Quận 10', 'hieupham@gmail.com', '0901023456', '2024-02-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG056', N'Trần Thị Thanh Mai', N'Nữ', '2003-12-12', N'72 Nguyễn Huệ, Quận 1', 'maitran@gmail.com', '0912034567', '2024-03-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG057', N'Vũ Văn Nam', N'Nam', '1998-10-12', N'99 Lý Tự Trọng, Quận 1', 'namvu@gmail.com', '0923045678', '2024-04-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG058', N'Nguyễn Văn Hoàng', N'Nam', '2001-05-15', N'33 Nguyễn Trãi, Quận 1', 'hoangnguyen@gmail.com', '0934056789', '2024-05-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG059', N'Phạm Thị Mai Hương', N'Nữ', '2004-07-12', N'102 Đinh Tiên Hoàng, Bình Thạnh', 'huongpham@gmail.com', '0945067890', '2024-06-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG060', N'Trần Văn Tuấn', N'Nam', '1996-08-13', N'45 Nguyễn Văn Trỗi, Phú Nhuận', 'tuantran@gmail.com', '0956078901', '2024-07-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG061', N'Lê Thị Thanh Hằng', N'Nữ', '2002-12-22', N'33 Trần Bình Trọng, Quận 5', 'hangle@gmail.com', '0967089012', '2024-08-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG062', N'Nguyễn Minh Thắng', N'Nam', '1998-09-19', N'88 Lê Văn Sỹ, Quận 3', 'thangnguyen@gmail.com', '0978090123', '2024-09-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG063', N'Phạm Thị Hồng Nhung', N'Nữ', '2004-11-18', N'99 Nguyễn Đình Chiểu, Quận 3', 'nhungpham@gmail.com', '0989091234', '2024-10-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG064', N'Trần Văn Hùng', N'Nam', '2001-01-10', N'45 Nguyễn Văn Trỗi, Phú Nhuận', 'hungtran@gmail.com', '0990102345', '2024-11-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG065', N'Lê Thị Thanh Thủy', N'Nữ', '1996-12-12', N'33 Nguyễn Đình Chiểu, Quận 3', 'thuyle@gmail.com', '0901102345', '2024-12-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG066', N'Nguyễn Văn Tuấn', N'Nam', '2004-05-18', N'88 Nguyễn Trãi, Quận 1', 'tuannguyen@gmail.com', '0912103456', '2024-01-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG067', N'Phạm Thị Thanh Tâm', N'Nữ', '1999-03-13', N'72 Lý Tự Trọng, Quận 1', 'tampham@gmail.com', '0923104567', '2024-02-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG068', N'Trần Văn Hải', N'Nam', '2002-07-11', N'33 Nguyễn Thái Học, Quận 1', 'haitran@gmail.com', '0934105678', '2024-03-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG069', N'Lê Thị Thanh Hà', N'Nữ', '1997-11-28', N'102 Lê Văn Sỹ, Quận 3', 'halethanh@gmail.com', '0945106789', '2024-04-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG070', N'Nguyễn Minh Hoàng', N'Nam', '2000-05-13', N'45 Trần Hưng Đạo, Quận 1', 'hoangnguyen@gmail.com', '0956107890', '2024-05-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG071', N'Phạm Thị Minh Châu', N'Nữ', '2003-01-10', N'120 Nguyễn Đình Chiểu, Quận 3', 'chaupham@gmail.com', '0967108901', '2024-06-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG072', N'Trần Văn Hùng', N'Nam', '1998-04-11', N'99 Nguyễn Trãi, Quận 1', 'hungtran@gmail.com', '0978109012', '2024-07-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG073', N'Lê Thị Thanh Hà', N'Nữ', '2001-06-14', N'45 Đinh Tiên Hoàng, Bình Thạnh', 'halethanh@gmail.com', '0989100123', '2024-08-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG074', N'Nguyễn Văn Thắng', N'Nam', '1996-09-19', N'33 Lê Văn Sỹ, Quận 3', 'thangnguyen@gmail.com', '0991012345', '2024-09-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG075', N'Phạm Thị Mai', N'Nữ', '2002-12-12', N'88 Nguyễn Huệ, Quận 1', 'maipham@gmail.com', '0902012345', '2024-10-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG076', N'Trần Văn Hùng', N'Nam', '1999-03-13', N'45 Nguyễn Đình Chiểu, Quận 3', 'hungtran@gmail.com', '0913012345', '2024-11-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG077', N'Lê Thị Thanh Thủy', N'Nữ', '2004-05-18', N'33 Nguyễn Văn Trỗi, Phú Nhuận', 'thuypham@gmail.com', '0924012345', '2024-12-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG078', N'Nguyễn Văn Hải', N'Nam', '2000-07-12', N'88 Lê Thánh Tôn, Quận 1', 'hai.nguyen@gmail.com', '0935012345', '2024-01-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG079', N'Phạm Thị Hồng Nhung', N'Nữ', '1996-11-28', N'45 Nguyễn Văn Thủ, Quận 1', 'nhungpham@gmail.com', '0946012345', '2024-02-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG080', N'Trần Văn Tuấn', N'Nam', '2001-01-10', N'72 Trương Định, Quận 3', 'tuantran@gmail.com', '0957012345', '2024-03-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG081', N'Lê Thị Thanh Hà', N'Nữ', '2002-12-22', N'102 Nguyễn Văn Trỗi, Phú Nhuận', 'halethanh@gmail.com', '0968012345', '2024-04-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG082', N'Nguyễn Minh Đức', N'Nam', '1998-09-19', N'33 Võ Văn Kiệt, Quận 1', 'ducnguyen@gmail.com', '0979012345', '2024-05-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG083', N'Phạm Thị Hồng', N'Nữ', '2004-11-18', N'99 Lý Thường Kiệt, Quận 10', 'hongpham@gmail.com', '0980012345', '2024-06-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG084', N'Trần Văn Tuấn', N'Nam', '2001-01-10', N'45 Nguyễn Văn Trỗi, Phú Nhuận', 'tuantran@gmail.com', '0991012345', '2024-07-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG085', N'Lê Thị Thanh Hương', N'Nữ', '1996-12-12', N'33 Đinh Tiên Hoàng, Bình Thạnh', 'huongle@gmail.com', '0902012345', '2024-08-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG086', N'Nguyễn Minh Thắng', N'Nam', '2004-05-18', N'88 Nguyễn Huệ, Quận 1', 'thangnguyen@gmail.com', '0913012345', '2024-09-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG087', N'Phạm Thị Mai', N'Nữ', '1999-03-13', N'72 Nguyễn Đình Chiểu, Quận 3', 'maipham@gmail.com', '0924012345', '2024-10-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG088', N'Trần Văn Hùng', N'Nam', '2002-07-11', N'33 Trương Định, Quận 3', 'hungtran@gmail.com', '0935012345', '2024-11-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG089', N'Lê Thị Thanh Thủy', N'Nữ', '1997-11-28', N'45 Nguyễn Văn Trỗi, Phú Nhuận', 'thuypham@gmail.com', '0946012345', '2024-12-27', DEFAULT);
INSERT INTO DOCGIA VALUES ('DG090', N'Nguyễn Minh Hải', N'Nam', '2000-05-13', N'88 Lê Thánh Tôn, Quận 1', 'hai.nguyen@gmail.com', '0957012345', '2024-01-27', DEFAULT);
GO

-- THELOAI TABLE
INSERT INTO THELOAI VALUES (N'Tin học', DEFAULT);
INSERT INTO THELOAI VALUES (N'Vật lý', DEFAULT);
INSERT INTO THELOAI VALUES (N'Toán', DEFAULT);
INSERT INTO THELOAI VALUES (N'Y học', DEFAULT);
INSERT INTO THELOAI VALUES (N'Văn học', DEFAULT);
INSERT INTO THELOAI VALUES (N'Lịch sử', DEFAULT);
GO

-- SACH TABLE
INSERT INTO SACH VALUES ('SS001', N'Giáo trình toán cao cấp', N'Toán', N'Nhóm tác giả từ UIT', 2015, N'NXB Đại học quốc gia HCM', '2020-06-12', 60000, DEFAULT, DEFAULT);
INSERT INTO SACH VALUES ('SS002', N'Giáo trình Hệ điều hành', N'Tin học', N'Nhóm tác giả từ UIT', 2015, N'NXB Đại học quốc gia HCM', '2020-05-12', 53000, DEFAULT, DEFAULT);
INSERT INTO SACH VALUES ('SS003', N'Toán học và ứng dụng', N'Toán', N'Nhóm tác giả từ viện toán học Việt Nam', 2014, N'Viện toán học Việt Nam', '2020-07-12', 72000, DEFAULT, DEFAULT);
INSERT INTO SACH VALUES ('SS004', N'Cẩm nang chăm sóc sức khỏe', N'Y học', N'Nguyễn Ngọc Hân, Đặng Huy Hoàng', 2014, N'NXB thông tin và truyền thông', '2020-05-12', 50000, DEFAULT, DEFAULT);
INSERT INTO SACH VALUES ('SS005', N'Ngôn ngữ lập trình C#', N'Tin học', N'Phan Văn Thúc', 2010, N'NXB Giáo dục', '2020-05-12', 54000, DEFAULT, DEFAULT);
INSERT INTO SACH VALUES ('SS006', N'Đại số tuyến tính', N'Toán', N'Phạm Công Ngô', 2015, N'NXB Đại học quốc gia HCM', '2020-06-12', 65000, DEFAULT, DEFAULT);
INSERT INTO SACH VALUES ('SS007', N'Vật lý đại cương', N'Vật lý', N'Trần Văn Lượng', 2014, N'NXB Đại học quốc gia HCM', '2020-06-12', 65000, DEFAULT, DEFAULT);
GO

-- PHIEUMUON TABLE
INSERT INTO PHIEUMUON VALUES ('PM001', 'DG001', 'SS001', '7/05/2024', NULL, DEFAULT);
INSERT INTO PHIEUMUON VALUES ('PM002', 'DG005', 'SS004', '7/05/2024', NULL, DEFAULT);
INSERT INTO PHIEUMUON VALUES ('PM003', 'DG005', 'SS003', '7/05/2024', NULL, DEFAULT);
INSERT INTO PHIEUMUON VALUES ('PM004', 'DG003', 'SS006', '15/04/2024', NULL, DEFAULT);
INSERT INTO PHIEUMUON VALUES ('PM005', 'DG002', 'SS004', '5/04/2024', NULL, DEFAULT);
INSERT INTO PHIEUMUON VALUES ('PM006', 'DG002', 'SS003', '5/04/2024', NULL, DEFAULT);
INSERT INTO PHIEUMUON VALUES ('PM007', 'DG004', 'SS005', '25/04/2024', NULL, DEFAULT);
INSERT INTO PHIEUMUON VALUES ('PM008', 'DG001', 'SS002', '14/05/2024', NULL, DEFAULT);
INSERT INTO PHIEUMUON VALUES ('PM009', 'DG001', 'SS006', '14/05/2024', NULL, DEFAULT);
INSERT INTO PHIEUMUON VALUES ('PM010', 'DG002', 'SS005', '7/04/2024', NULL, DEFAULT);
GO

-- PHIEUTRA TABLE
INSERT INTO PHIEUTRA VALUES ('PT001', 'PM003', '21/05/2024', DEFAULT);
INSERT INTO PHIEUTRA VALUES ('PT002', 'PM005', '22/5/2024', DEFAULT);
INSERT INTO PHIEUTRA VALUES ('PT003', 'PM007', '10/5/2024', DEFAULT);
INSERT INTO PHIEUTRA VALUES ('PT004', 'PM002', '15/05/2024', DEFAULT);
INSERT INTO PHIEUTRA VALUES ('PT005', 'PM001', '18/05/2024', DEFAULT);
INSERT INTO PHIEUTRA VALUES ('PT006', 'PM004', '15/05/2024', DEFAULT);
INSERT INTO PHIEUTRA VALUES ('PT007', 'PM006', '14/05/2024', DEFAULT);
INSERT INTO PHIEUTRA VALUES ('PT008', 'PM009', '20/05/2024', DEFAULT);
GO

-- THUTHU TABLE
INSERT INTO THUTHU VALUES ('TT001', N'Lương Ngọc Huyền', N'Nữ', '5/01/2024', '12/03/2003', N'Phường Bến Thành, Quận 1, Thành phố Hồ Chí Minh', 'huyenlb@gmail.com', '0369442256', DEFAULT);
INSERT INTO THUTHU VALUES ('TT002', N'Cao Văn Thành', N'Nam', '12/01/2024', '22/05/2003', N'Đường Lê Duẩn, Bến Nghé, Quận 1, Thành phố Hồ Chí Minh', 'thanhcv03@gmail.com', '0973464756', DEFAULT);
GO

-- ACOUNT TABLE
INSERT INTO ACCOUNT VALUES ('TaiKhoan1', 'MatKhau1', 'DG001', NULL, 1, DEFAULT);
INSERT INTO ACCOUNT VALUES ('TaiKhoan2', 'MatKhau2', 'DG002', NULL, 1, DEFAULT);
INSERT INTO ACCOUNT VALUES ('TaiKhoan3', 'MatKhau3', 'DG003', NULL, 1, DEFAULT);
INSERT INTO ACCOUNT VALUES ('TaiKhoan4', 'MatKhau4', 'DG004', NULL, 1, DEFAULT);
INSERT INTO ACCOUNT VALUES ('TaiKhoan5', 'MatKhau5', 'DG005', NULL, 1, DEFAULT);
INSERT INTO ACCOUNT VALUES ('TaiKhoan6', 'MatKhau6', NULL, 'TT001', 2, DEFAULT);
INSERT INTO ACCOUNT VALUES ('TaiKhoan7', 'MatKhau7', NULL, 'TT002', 2, DEFAULT);