/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import librarymanagement.pojo.QuanLySachPOJO;

public class QuanLySachDAO {

    List<QuanLySachPOJO> bookList = new ArrayList<>();
    ConfigUtils util = new ConfigUtils();

    // Lấy danh sách thể loại sách
    public List<String> getCategoryNames() {
        List<String> result = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    util.dbConnect, util.username, util.password);
            String sql = "select categoryName from category";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(rs.getString(1));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    // Lấy thông tin sách theo ID
    public QuanLySachPOJO getBookById(int bookId) {
        QuanLySachPOJO book = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    util.dbConnect, util.username, util.password);
            String sql = "select * from book where bookId = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, bookId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                book = new QuanLySachPOJO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
                                      rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getBoolean(8));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return book;
    }

    // Lấy danh sách tất cả sách
    public List<QuanLySachPOJO> getAllBooks() {
        bookList = new ArrayList<>();
        QuanLySachPOJO book;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    util.dbConnect, util.username, util.password);
            String sql = "select * from book";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                book = new QuanLySachPOJO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
                                      rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getBoolean(8));
                bookList.add(book);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return bookList;
    }

    // Thêm sách mới
    public Boolean addBook(QuanLySachPOJO book) {
        Boolean isSuccess = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    util.dbConnect, util.username, util.password);
            String sql = "insert into book(title, author, category, publishYear, totalQuantity, availableQty) values(?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getCategory());
            stmt.setInt(4, book.getPublishYear());
            stmt.setInt(5, book.getTotalQuantity());
            stmt.setInt(6, book.getAvailableQty());
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                isSuccess = true;
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return isSuccess;
    }

    // Sửa thông tin sách
    public Boolean editBook(QuanLySachPOJO book) {
        Boolean isSuccess = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    util.dbConnect, util.username, util.password);
            String sql = "update book set title=?, author=?, category=?, publishYear=?, totalQuantity=?, availableQty=? where bookId=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getCategory());
            stmt.setInt(4, book.getPublishYear());
            stmt.setInt(5, book.getTotalQuantity());
            stmt.setInt(6, book.getAvailableQty());
            stmt.setInt(7, book.getBookId());
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                isSuccess = true;
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return isSuccess;
    }

    // Xóa sách (đánh dấu là đã xóa)
    public Boolean deleteBook(int bookId) {
        Boolean isSuccess = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    util.dbConnect, util.username, util.password);
            String sql = "update book set isDeleted = 1 where bookId = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, bookId);
            int row = stmt.executeUpdate();
            if (row > 0) {
                isSuccess = true;
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return isSuccess;
    }

    // Tìm kiếm sách theo ID
    public List<QuanLySachPOJO> searchBookById(int bookId) {
        bookList = new ArrayList<>();
        QuanLySachPOJO book;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    util.dbConnect, util.username, util.password);
            String sql = "select * from book where bookId=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, bookId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                book = new QuanLySachPOJO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
                                      rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getBoolean(8));
                bookList.add(book);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return bookList;
    }

    // Thêm dữ liệu vào bảng
    public void addDataToTable(DefaultTableModel model, JTable table) {
        bookList = this.getAllBooks();
        model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        Object columns[] = new Object[6];
        for (QuanLySachPOJO book : bookList) {
            if (!book.isDeleted()) {
                columns[0] = book.getBookId();
                columns[1] = book.getTitle();
                columns[2] = book.getAuthor();
                columns[3] = book.getCategory();
                columns[4] = book.getPublishYear();
                columns[5] = book.getTotalQuantity();
                model.addRow(columns);
            }
        }
    }
    
  
    
     // Tìm kiếm sách theo tên (sử dụng LIKE để tìm tên gần giống)
public List<QuanLySachPOJO> searchBookByName(String bookName) {
    List<QuanLySachPOJO> bookList = new ArrayList<>();
    String sql = "SELECT * FROM book WHERE title LIKE ?";
    
    // Sử dụng kết nối từ ConfigUtils
    try (Connection con = DriverManager.getConnection(util.dbConnect, util.username, util.password);
         PreparedStatement stmt = con.prepareStatement(sql)) {
        stmt.setString(1, "%" + bookName + "%");
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                QuanLySachPOJO book = new QuanLySachPOJO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getBoolean(8));
                bookList.add(book);
            }
        }
    } catch (Exception e) {
        System.out.println(e);
    }

    return bookList;
}


    // Thêm dữ liệu từ tìm kiếm vào bảng
    public void addDataFromSearch(List<QuanLySachPOJO> books, DefaultTableModel model, JTable table) {
        model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        Object columns[] = new Object[6];
        for (QuanLySachPOJO book : books) {
            if (!book.isDeleted()) {
                columns[0] = book.getBookId();
                columns[1] = book.getTitle();
                columns[2] = book.getAuthor();
                columns[3] = book.getCategory();
                columns[4] = book.getPublishYear();
                columns[5] = book.getTotalQuantity();
                model.addRow(columns);
            }
        }
    }
}
	