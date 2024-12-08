/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagement.dao;

import librarymanagement.pojo.Borrower;
import librarymanagement.pojo.ReturnBook;

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
}
