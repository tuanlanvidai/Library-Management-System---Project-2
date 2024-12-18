/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import librarymanagement.pojo.BaoCa0;

/**
 *
 * @author CuongVu
 */
public class BaoCaoDAO {    //Khai báo phương thức. Ngày cụ thể hoặc Tháng cụ thể  để lọc theo ngày trả sách
    public List<BaoCa0> getBaoCaoByDate(java.sql.Date selectedDate, int selectedMonth) {
    List<BaoCa0> baoCa0List = new ArrayList<>();
    
    String sql = "SELECT b.title AS 'BookName', " + 
                 "       r.name AS 'Name', " +
                 "       s.statusName AS 'Status', " + 
                 "       rf.lateDays AS 'ExDates', " + 
                 "       rf.fineMoney AS 'Values' " + 
                 "FROM Book b " + 
                 "JOIN BorrowBook bb ON b.bookId = bb.bookId " +
                 "JOIN ReturnBook rt ON bb.borrowId = rt.borrowId " + 
                 "JOIN BookStatus s ON rt.statusId = s.statusId " + 
                 "JOIN ReturnFine rf ON rt.returnId = rf.returnId " + 
                 "JOIN Reader r ON bb.readerId = r.readerId " + 
                 "WHERE rf.isDeleted = 0 AND rt.isDeleted = 0 ";

    // Nếu có ngày cụ thể, thêm điều kiện lọc ngày trả sách
    if (selectedDate != null) {
        sql += " AND rf.returnDate = ? ";
    } else if (selectedMonth > 0) {
        sql += " AND MONTH(rf.returnDate) = ? ";
    }

    sql += "ORDER BY r.name ASC;";  // Đảm bảo truy vấn luôn sắp xếp theo tên người mượn

    try (Connection con = DriverManager.getConnection(ConfigUtils.dbConnect, ConfigUtils.username, ConfigUtils.password);
         PreparedStatement stmt = con.prepareStatement(sql)) {
        if (selectedDate != null) {
            stmt.setDate(1, selectedDate); 
        } else if (selectedMonth > 0) {
            stmt.setInt(1, selectedMonth);  
        }

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String bookName = rs.getString("BookName");
            String name = rs.getString("Name");
            String status = rs.getString("Status");
            int exDates = rs.getInt("ExDates");
            double values = rs.getDouble("Values");  // Sử dụng double đúng cách

            BaoCa0 baoCa0 = new BaoCa0(name, bookName, status, exDates, (int) values);
            baoCa0List.add(baoCa0);
        }
    } catch (SQLException e) {
            System.out.println(e);
    }

    return baoCa0List;
}
    
    public int getTotalBooksBorrowed() {
        String sql = "SELECT SUM(totalQuantity) AS totalBooksBorrowed " +
                     "FROM Book " +
                     "JOIN BorrowBook ON Book.bookId = BorrowBook.bookId " +
                     "WHERE BorrowBook.isDeleted = 0";
        int totalBooks = 0;

        try (Connection connection = DriverManager.getConnection(
                ConfigUtils.dbConnect, ConfigUtils.username, ConfigUtils.password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            if (resultSet.next()) {
                totalBooks = resultSet.getInt("totalBooksBorrowed");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return totalBooks;
    }
}