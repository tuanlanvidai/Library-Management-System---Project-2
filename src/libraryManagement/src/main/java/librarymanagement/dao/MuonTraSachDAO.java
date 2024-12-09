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
                b.title AS bookTitle, 
                bb.borrowDate, 
                bb.dueDate, 
                CASE 
                    WHEN CURDATE() > bb.dueDate THEN 
                        (DATEDIFF(CURDATE(), bb.dueDate) * s.lateFeePerDay)
                    ELSE 
                        0
                END AS fineAmount
            FROM BorrowBook bb
            JOIN Book b ON bb.bookId = b.bookId
            CROSS JOIN Setting s
            WHERE bb.readerId = ? 
              AND bb.isDeleted = 0;
            """;

        try (Connection con = DriverManager.getConnection(util.dbConnect, util.username, util.password); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, readerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ReturnBook book = new ReturnBook(
                        rs.getInt("borrowId"),
                        rs.getString("bookTitle"),
                        rs.getDate("borrowDate"),
                        rs.getDate("dueDate"),
                        rs.getInt("fineAmount")
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

}
