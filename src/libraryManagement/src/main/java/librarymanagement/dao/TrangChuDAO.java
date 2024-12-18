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
import librarymanagement.pojo.OverdueBook;

public class TrangChuDAO {
    ConfigUtils util = new ConfigUtils();
    
    public int getTotalBorrowedBooks() {
    int totalBorrowedBooks = 0;
    try {
        Connection con = DriverManager.getConnection(ConfigUtils.dbConnect, ConfigUtils.username, ConfigUtils.password);
        String sql = "select count(*) as total from BorrowBook where isDeleted = 0";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            totalBorrowedBooks = rs.getInt("total");
        }
        con.close();
    } catch (Exception e) {
        System.out.println(e);
    }
    return totalBorrowedBooks;
    }

    public int getBooksBorrowedByDay(String date) {
        int tTBrred = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(util.dbConnect, util.username, util.password);
            String sql = "select count(*) as ttB from BorrowBook where borrowDate = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, date);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                tTBrred = rs.getInt("ttB");
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return tTBrred;
    }
    public int getBooksReturnedByDay(String date) {
        int tTReturned = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(util.dbConnect, util.username, util.password);
            String sql = "select count(*) as ttR from ReturnBook where returnDate = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, date);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                tTReturned = rs.getInt("ttR");
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return tTReturned;
    }
    public List<Integer> getDailyRp(String date) {
        List<Integer> rp = new ArrayList<>();
        int ttB = getBooksBorrowedByDay(date);
        int ttR = getBooksReturnedByDay(date);

        rp.add(ttB);
        rp.add(ttR);
        return rp;
    }
    
    public int getBooksBorrowedByWeek(String sDate, String eDate) {
        int tTBrred = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(util.dbConnect, util.username, util.password);
            String sql = "select count(*) as ttB from BorrowBook where borrowDate between ? and ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, sDate);
            stmt.setString(2, eDate);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                tTBrred = rs.getInt("ttB");
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return tTBrred;
    }
    public int getBooksReturnedByWeek(String sDate, String eDate) {
        int tTReturned = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(util.dbConnect, util.username, util.password);
            String sql = "select count(*) as ttR from ReturnBook where returnDate between ? and ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, sDate);
            stmt.setString(2, eDate);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                tTReturned = rs.getInt("ttR");
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return tTReturned;
    }
    public List<Integer> getWeeklyRp(String sDate, String eDate) {
        List<Integer> rp = new ArrayList<>();
        int ttB = getBooksBorrowedByWeek(sDate, eDate);
        int ttR = getBooksBorrowedByWeek(sDate, eDate);
        rp.add(ttB);
        rp.add(ttR);
        return rp;
    }
    
    public int getBooksBorrowedByMonth(String sDate, String eDate) {
        return getBooksBorrowedByWeek(sDate, eDate);
    }
    public int getBooksReturnedByMonth(String sDate, String eDate) {
        return getBooksReturnedByWeek(sDate, eDate);
    }
    public List<Integer> getMonthlyRp(String sDate, String eDate) {
        List<Integer> rp = new ArrayList<>();
        int ttB = getBooksBorrowedByMonth(sDate, eDate);
        int ttR = getBooksReturnedByMonth(sDate, eDate);
        rp.add(ttB);
        rp.add(ttR);
        return rp;
    }
    
    public int getBooksBorrowedByYear(String sDate, String eDate) {
        return getBooksBorrowedByWeek(sDate, eDate);
    }
    public int getBooksReturnedByYear(String sDate, String eDate) {
        return getBooksReturnedByWeek(sDate, eDate);
    }
    public List<Integer> getYearlyRp(String sDate, String eDate) {
        List<Integer> rp = new ArrayList<>();
        int ttB = getBooksBorrowedByYear(sDate, eDate);
        int ttR = getBooksReturnedByYear(sDate, eDate);
        rp.add(ttB);
        rp.add(ttR);
        return rp;
    }
    
    public int getTotalBooks() {
    int totalBooksInStock = 0;
    try {
        Connection con = DriverManager.getConnection(ConfigUtils.dbConnect, ConfigUtils.username, ConfigUtils.password);
        String sql = "select sum(totalQuantity) as total from Book where isDeleted = 0";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            totalBooksInStock = rs.getInt("total");
        }
        con.close();
    } catch (Exception e) {
        System.out.println(e);
    }
    return totalBooksInStock;
}
    
    public List<String[]> getInventoryReport() {
    List<String[]> inventory = new ArrayList<>();
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(util.dbConnect, util.username, util.password);
        String sql = "select bookId, title, author, totalQuantity, availableQty from Book where isDeleted = 0";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            String[] row = new String[5];
            row[0] = String.valueOf(rs.getInt("bookId"));
            row[1] = rs.getString("title");
            row[2] = rs.getString("author");
            row[3] = String.valueOf(rs.getInt("totalQuantity"));
            row[4] = String.valueOf(rs.getInt("availableQty"));
            inventory.add(row);
        }
        con.close();
    } catch (Exception e) {
        System.out.println(e);
    }return inventory;}
    
    public int getOverdueBooks() {
    int overdueBooks = 0;
    try {
        Connection con = DriverManager.getConnection(ConfigUtils.dbConnect, ConfigUtils.username, ConfigUtils.password);
        
        String sql = "SELECT COUNT(*) AS total " +
                     "FROM BorrowBook bb " +
                     "WHERE bb.isDeleted = 0 " +
                     "AND DATEDIFF(CURRENT_DATE, bb.dueDate) > 0 ";

        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            overdueBooks = rs.getInt("total"); 
        }
        con.close();
    } catch (Exception e) {
        System.out.println(e);
    }
    return overdueBooks;
}

    public int getTotalReaders() {
        int totalReaders = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(util.dbConnect, util.username, util.password);
            String sql = "select count(*) as totalReaders from Reader where isDeleted = 0";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                totalReaders = rs.getInt("totalReaders");
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return totalReaders;
    }
    
    public List<OverdueBook> getListOverDueReaders() {
        List<OverdueBook> overdueBooks = new ArrayList<>();
        String query = "SELECT " +
                       "    bb.readerId AS readerId, " +
                       "    bb.bookId AS bookId, " +
                       "    bb.borrowId AS borrowId, " +
                       "    DATEDIFF(CURRENT_DATE, bb.dueDate) AS overdueDays, " +
                       "    COALESCE(rf.fineMoney, DATEDIFF(CURRENT_DATE, bb.dueDate) * 2000) AS fineMoney " +
                       "FROM BorrowBook bb " +
                       "LEFT JOIN ReturnBook rb ON rb.borrowId = bb.borrowId " +
                       "LEFT JOIN ReturnFine rf ON rf.returnId = rb.returnId " +
                       "WHERE bb.isDeleted = 0 " +
                       "AND DATEDIFF(CURRENT_DATE, bb.dueDate) > 0 " +
                       "AND (rb.returnId IS NULL OR rb.isDeleted = 0)";

        try (Connection connection = DriverManager.getConnection(ConfigUtils.dbConnect, ConfigUtils.username, ConfigUtils.password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int readerId = resultSet.getInt("readerId");
                int bookId = resultSet.getInt("bookId");
                int borrowId = resultSet.getInt("borrowId");
                int overdueDays = resultSet.getInt("overdueDays");
                int fineMoney = resultSet.getInt("fineMoney");

                overdueBooks.add(new OverdueBook(readerId, bookId, borrowId, overdueDays, fineMoney));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return overdueBooks;
    }


}
