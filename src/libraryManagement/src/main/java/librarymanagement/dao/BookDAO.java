/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagement.dao;

/**
 *
 * @author CuongVu
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import librarymanagement.pojo.Book;

public class BookDAO {
    public static List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(ConfigUtils.dbConnect, ConfigUtils.username, ConfigUtils.password);
            String sql = "select * from Book where isDeleted = 0";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getInt("bookId"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setCategory(rs.getString("category"));
                book.setPublishYear(rs.getInt("publishYear"));
                book.setTotalQuantity(rs.getInt("totalQuantity"));
                book.setAvailableQty(rs.getInt("availableQty"));
                book.setIsDeleted(rs.getBoolean("isDeleted"));
                books.add(book);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return books;
    }
    public static List<Book> getBooksEarlyPublishYear() {
        List<Book> books = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(ConfigUtils.dbConnect, ConfigUtils.username, ConfigUtils.password);
            String sql = "select * from Book where isDeleted = 0 and publishYear >=2023 order by publishYear ASC;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getInt("bookId"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setCategory(rs.getString("category"));
                book.setPublishYear(rs.getInt("publishYear"));
                book.setTotalQuantity(rs.getInt("totalQuantity"));
                book.setAvailableQty(rs.getInt("availableQty"));
                book.setIsDeleted(rs.getBoolean("isDeleted"));
                books.add(book);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return books;
    }
}

