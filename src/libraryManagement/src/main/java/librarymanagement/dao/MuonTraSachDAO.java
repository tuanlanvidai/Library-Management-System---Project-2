/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagement.dao;

import librarymanagement.pojo.Borrower;
import librarymanagement.pojo.ReturnBook;
import librarymanagement.pojo.Reader;
import librarymanagement.pojo.Book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lantr
 */
public class MuonTraSachDAO {

    ConfigUtils util = new ConfigUtils();

    private int calculateFine(java.sql.Date dueDate) {
        String sql = "SELECT lateFeePerDay FROM Setting LIMIT 1";
        int fine = 0;

        try (Connection con = DriverManager.getConnection(util.dbConnect, util.username, util.password); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            if (rs.next() && dueDate != null) {
                int lateFeePerDay = rs.getInt("lateFeePerDay");
                long daysLate = java.time.LocalDate.now().toEpochDay() - dueDate.toLocalDate().toEpochDay();
                if (daysLate > 0) {
                    fine = (int) daysLate * lateFeePerDay;
                }
            }
        } catch (Exception e) {
            System.out.println("Error calculating fine: " + e.getMessage());
        }

        return fine;
    }

    // Take all people list who still borrow book
    //if one person borrow more than one book, then just show that person once
    public List<Borrower> getBorrowers() {
        List<Borrower> borrowers = new ArrayList<>();
        String sql = """
                SELECT 
                    r.readerId, 
                    r.name AS readerName, 
                    r.registerDay, 
                    COUNT(bb.bookId) AS totalBooksBorrowed
                FROM Reader r
                JOIN BorrowBook bb ON r.readerId = bb.readerId
                WHERE bb.isDeleted = 0
                GROUP BY r.readerId, r.name, r.registerDay;
                """;

        try (Connection con = DriverManager.getConnection(util.dbConnect, util.username, util.password); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Borrower borrower = new Borrower(
                        rs.getInt("readerId"),
                        rs.getString("readerName"),
                        rs.getDate("registerDay"),
                        rs.getInt("totalBooksBorrowed")
                );
                borrowers.add(borrower);
            }

        } catch (Exception e) {
            System.out.println("Error fetching borrowers: " + e.getMessage());
        }

        return borrowers;
    }

    //Get all the book a person borrowed, when select a borrower
    public List<ReturnBook> getBorrowedBooksWithFine(int readerId) {
        List<ReturnBook> borrowedBooks = new ArrayList<>();
        String sql = """
        SELECT 
            bb.borrowId, 
            r.name AS readerName, 
            b.title AS bookTitle, 
            bb.borrowDate, 
            bb.dueDate
        FROM BorrowBook bb
        JOIN Reader r ON bb.readerId = r.readerId
        JOIN Book b ON bb.bookId = b.bookId
        WHERE bb.readerId = ? 
          AND bb.isDeleted = 0; 
        """;

        try (Connection con = DriverManager.getConnection(util.dbConnect, util.username, util.password); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, readerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int borrowId = rs.getInt("borrowId");
                String readerName = rs.getString("readerName");
                String bookTitle = rs.getString("bookTitle");
                java.sql.Date borrowDate = rs.getDate("borrowDate");
                java.sql.Date dueDate = rs.getDate("dueDate");
                int fineAmount = calculateFine(dueDate);

                ReturnBook book = new ReturnBook(
                        borrowId, readerName, bookTitle, borrowDate, dueDate, fineAmount
                );
                borrowedBooks.add(book);
            }

        } catch (Exception e) {
            System.out.println("Error fetching borrowed books with fine for readerId " + readerId + ": " + e.getMessage());
        }

        return borrowedBooks;
    }

    public List<Reader> getAllReaders() {
        List<Reader> readers = new ArrayList<>();
        String sql = """
            SELECT readerId, name, address, phoneNumber, email, registerDay
            FROM Reader
            WHERE isDeleted = 0;
            """;

        try (Connection con = DriverManager.getConnection(util.dbConnect, util.username, util.password); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Reader reader = new Reader();
                reader.setReaderId(rs.getInt("readerId"));
                reader.setName(rs.getString("name"));
                reader.setAddress(rs.getString("address"));
                reader.setPhoneNumber(rs.getString("phoneNumber"));
                reader.setEmail(rs.getString("email"));
                reader.setRegisterDay(rs.getString("registerDay"));
                reader.setIsDeleted(false);
                readers.add(reader);
            }

        } catch (Exception e) {
            System.out.println("Error fetching readers: " + e.getMessage());
        }

        return readers;
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = """
        SELECT bookId, title, author, category, publishYear, totalQuantity, availableQty
        FROM Book
        WHERE isDeleted = 0 AND availableQty > 0;
        """;

        try (Connection con = DriverManager.getConnection(util.dbConnect, util.username, util.password); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getInt("bookId"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setCategory(rs.getString("category"));
                book.setPublishYear(rs.getInt("publishYear"));
                book.setTotalQuantity(rs.getInt("totalQuantity"));
                book.setAvailableQty(rs.getInt("availableQty"));
                book.setIsDeleted(false);
                books.add(book);
            }

        } catch (Exception e) {
            System.out.println("Error fetching books: " + e.getMessage());
        }

        return books;
    }

    public int getMaxBorrowDays() {
        String sql = "SELECT maxBorrowDays FROM Setting LIMIT 1";

        try (Connection con = DriverManager.getConnection(util.dbConnect, util.username, util.password); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("maxBorrowDays");
            }
        } catch (Exception e) {
            System.out.println("Error fetching maxBorrowDays: " + e.getMessage());
        }
        return 14; // default if fail to get max borrow day
    }

    public int getMaxBooksBorrowed() {
        String sql = "SELECT maxBooksBorrowed FROM Setting LIMIT 1";

        try (Connection con = DriverManager.getConnection(util.dbConnect, util.username, util.password); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("maxBooksBorrowed");
            }
        } catch (Exception e) {
            System.out.println("Error fetching maxBooksBorrowed: " + e.getMessage());
        }
        return 3; // default if fail to get max book borrowed
    }

    public int getCurrentBooksBorrowed(int readerId) {
        String sql = """
        SELECT COUNT(*) AS borrowedCount
        FROM BorrowBook
        WHERE readerId = ? AND isDeleted = 0;
        """;

        try (Connection con = DriverManager.getConnection(util.dbConnect, util.username, util.password); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, readerId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("borrowedCount");
            }
        } catch (Exception e) {
            System.out.println("Error fetching current books borrowed: " + e.getMessage());
        }
        return 0;
    }

    public boolean addBorrowRecord(int readerId, int bookId, java.sql.Date borrowDate, java.sql.Date dueDate) {
        String insertBorrowSQL = """
        INSERT INTO BorrowBook (readerId, bookId, borrowDate, dueDate, isDeleted)
        VALUES (?, ?, ?, ?, 0);
        """;

        String updateBookSQL = """
        UPDATE Book
        SET availableQty = availableQty - 1
        WHERE bookId = ? AND availableQty > 0;
        """;

        try (Connection con = DriverManager.getConnection(util.dbConnect, util.username, util.password); PreparedStatement insertStmt = con.prepareStatement(insertBorrowSQL); PreparedStatement updateStmt = con.prepareStatement(updateBookSQL)) {

            con.setAutoCommit(false);

            insertStmt.setInt(1, readerId);
            insertStmt.setInt(2, bookId);
            insertStmt.setDate(3, borrowDate);
            insertStmt.setDate(4, dueDate);
            int rowsInserted = insertStmt.executeUpdate();

            if (rowsInserted == 0) {
                con.rollback();
                return false;
            }
            updateStmt.setInt(1, bookId);
            int rowsUpdated = updateStmt.executeUpdate();

            if (rowsUpdated == 0) {
                con.rollback();
                return false;
            }
            con.commit();
            return true;

        } catch (Exception e) {
            System.out.println("Error adding borrow record: " + e.getMessage());
            return false;
        }
    }

    public boolean readerExists(int readerId) {
        String sql = "SELECT 1 FROM Reader WHERE readerId = ? AND isDeleted = 0";
        try (Connection con = DriverManager.getConnection(util.dbConnect, util.username, util.password); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, readerId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            System.out.println("Error checking reader existence: " + e.getMessage());
        }
        return false;
    }

    public boolean bookExists(int bookId) {
        String sql = "SELECT 1 FROM Book WHERE bookId = ? AND isDeleted = 0";
        try (Connection con = DriverManager.getConnection(util.dbConnect, util.username, util.password); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, bookId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            System.out.println("Error checking book existence: " + e.getMessage());
        }
        return false;
    }

    public List<ReturnBook> getBorrowRecordsWithFine() {
        List<ReturnBook> borrowRecords = new ArrayList<>();
        String sql = """
        SELECT 
            bb.borrowId, 
            r.name AS readerName, 
            b.title AS bookTitle, 
            bb.borrowDate, 
            bb.dueDate
        FROM BorrowBook bb
        JOIN Reader r ON bb.readerId = r.readerId
        JOIN Book b ON bb.bookId = b.bookId
        WHERE bb.isDeleted = 0;
        """;

        try (Connection con = DriverManager.getConnection(util.dbConnect, util.username, util.password); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int borrowId = rs.getInt("borrowId");
                String readerName = rs.getString("readerName");
                String bookTitle = rs.getString("bookTitle");
                java.sql.Date borrowDate = rs.getDate("borrowDate");
                java.sql.Date dueDate = rs.getDate("dueDate");
                int fineAmount = calculateFine(dueDate);

                ReturnBook record = new ReturnBook(
                        borrowId, readerName, bookTitle, borrowDate, dueDate, fineAmount
                );
                borrowRecords.add(record);
            }

        } catch (Exception e) {
            System.out.println("Error fetching borrow records: " + e.getMessage());
        }

        return borrowRecords;
    }

    public List<String> getStatusNames() {
        List<String> statusNames = new ArrayList<>();
        String sql = "SELECT statusName FROM BookStatus";

        try (Connection con = DriverManager.getConnection(util.dbConnect, util.username, util.password); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                statusNames.add(rs.getString("statusName"));
            }

        } catch (Exception e) {
            System.out.println("Error fetching status names: " + e.getMessage());
        }

        return statusNames;
    }

    public int calculateTotalFine(java.sql.Date dueDate, int statusId) {
        String sql = "SELECT lateFeePerDay, bookDamageFee, lostBookFee FROM Setting LIMIT 1";
        int totalFine = 0;

        try (Connection con = DriverManager.getConnection(util.dbConnect, util.username, util.password); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                int lateFeePerDay = rs.getInt("lateFeePerDay");
                int damageFee = rs.getInt("bookDamageFee");
                int lostFee = rs.getInt("lostBookFee");

                // Tính tiền phạt trễ hạn
                if (dueDate != null) {
                    long daysLate = java.time.LocalDate.now().toEpochDay() - dueDate.toLocalDate().toEpochDay();
                    if (daysLate > 0) {
                        totalFine += (int) daysLate * lateFeePerDay;
                    }
                }

                // Thêm tiền phạt do hư hại hoặc mất sách
                if (statusId == 2) {
                    totalFine += damageFee;
                } else if (statusId == 3) {
                    totalFine += lostFee;
                }
            }

        } catch (Exception e) {
            System.out.println("Error calculating total fine: " + e.getMessage());
        }

        return totalFine;
    }

    public int createReturnBook(int borrowId, java.sql.Date returnDate, int statusId) {
        String sql = "INSERT INTO ReturnBook (borrowId, returnDate, statusId, isDeleted) VALUES (?, ?, ?, 0)";
        int returnId = -1;

        try (Connection con = DriverManager.getConnection(util.dbConnect, util.username, util.password); PreparedStatement stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, borrowId);
            stmt.setDate(2, returnDate);
            stmt.setInt(3, statusId);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                returnId = rs.getInt(1);
            }

        } catch (Exception e) {
            System.out.println("Error creating return book: " + e.getMessage());
        }

        return returnId;
    }

    public void createReturnFine(int returnId, int totalFine, int statusId, java.sql.Date dueDate) {
        String sql = "INSERT INTO ReturnFine (returnId, lateDays, statusId, FineMoney, isDeleted) VALUES (?, ?, ?, ?, 0)";

        try (Connection con = DriverManager.getConnection(util.dbConnect, util.username, util.password); PreparedStatement stmt = con.prepareStatement(sql)) {

            long daysLate = 0;
            if (dueDate != null) {
                daysLate = java.time.LocalDate.now().toEpochDay() - dueDate.toLocalDate().toEpochDay();
            }

            stmt.setInt(1, returnId);
            stmt.setInt(2, (int) Math.max(daysLate, 0));
            stmt.setInt(3, statusId);
            stmt.setInt(4, totalFine);
            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error creating return fine: " + e.getMessage());
        }
    }

    public void softDeleteBorrowBook(int borrowId) {
        String sql = "UPDATE BorrowBook SET isDeleted = 1 WHERE borrowId = ?";

        try (Connection con = DriverManager.getConnection(util.dbConnect, util.username, util.password); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, borrowId);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                System.out.println("Borrow book with ID " + borrowId + " not found or already deleted.");
            }

        } catch (Exception e) {
            System.out.println("Error updating isDeleted for borrow book: " + e.getMessage());
        }
    }

    public boolean borrowRecordExists(int borrowId) {
        String sql = "SELECT 1 FROM BorrowBook WHERE borrowId = ? AND isDeleted = 0";

        try (Connection con = DriverManager.getConnection(util.dbConnect, util.username, util.password); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, borrowId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            System.out.println("Error checking borrow record existence: " + e.getMessage());
        }
        return false;
    }
}
