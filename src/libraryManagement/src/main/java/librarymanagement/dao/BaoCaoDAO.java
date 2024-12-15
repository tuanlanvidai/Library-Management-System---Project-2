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
import java.util.ArrayList;
import java.util.List;
import librarymanagement.pojo.BaoCa0;

/**
 *
 * @author CuongVu
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import librarymanagement.pojo.BaoCa0;

public class BaoCaoDAO {
    ConfigUtils util = new ConfigUtils();

    public List<BaoCa0> getBaoCaoByDate(java.sql.Date selectedDate, int selectedMonth) {
        List<BaoCa0> baoCa0List = new ArrayList<>();
        String sql = "SELECT b.bookName AS 'BookName', " + 
             "r.name AS 'Name', " +
             "s.statusName AS 'Status', " + 
             "rf.lateDays AS 'ExDates', " + 
             "rf.fineMoney AS 'Values' " + 
             "FROM Book b " + 
             "JOIN ReturnBook rt ON b.bookId = rt.bookId " + 
             "JOIN BookStatus s ON rt.statusId = s.statusId " + 
             "JOIN ReturnFine rf ON rt.returnId = rf.returnId " + 
             "JOIN Reader r ON rt.readerId = r.readerId " +
             "WHERE rf.isDeleted = 0 AND rt.isDeleted = 0";


        if (selectedDate != null) {
            sql += "AND rf.returnDate = ? ";
        } else if (selectedMonth > 0) {
            sql += "AND MONTH(rf.returnDate) = ? ";
        }

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
                String status = rs.getString("Status");
                int exDates = rs.getInt("ExDates");
                int values = rs.getInt("Values");
                String name = rs.getString("Name");

                BaoCa0 baoCa0 = new BaoCa0(name, bookName, status, exDates, values);
                baoCa0List.add(baoCa0);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return baoCa0List;
    }
}



