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
import java.util.*;

public class ReaderDAO {
    public static List<String[]> getOverdueBorrowDetails() {
        List<String[]> reader = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(ConfigUtils.dbConnect, ConfigUtils.username, ConfigUtils.password);
            String sql = "select rb.readerId, bb.bookId, bb.borrowId, rt.bookStatus, rf.lateDays, rf.fineMoney " +
                         "from BorrowBook bb " +
                         "join ReturnBook rt on bb.borrowId = rt.borrowId " +
                         "join ReturnFine rf on rt.returnId = rf.returnId " +
                         "where rf.lateDays > 0 and rf.isDeleted = 0";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String[] row = new String[5];
                row[0] = String.valueOf(rs.getInt("readerId"));
                row[1] = String.valueOf(rs.getInt("bookId"));
                row[2] = String.valueOf(rs.getInt("borrowId"));
                row[3] = rs.getString("bookStatus");
                row[4] = String.valueOf(rs.getInt("fineMoney"));
                reader.add(row);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return reader;
    }
}


