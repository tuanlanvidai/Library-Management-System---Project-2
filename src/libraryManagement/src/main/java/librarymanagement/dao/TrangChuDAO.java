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
import librarymanagement.pojo.BorrowedBook;

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
        String sql = "select count(*) as total from ReturnFine where lateDays > 0 and isDeleted = 0";
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
    
     public List<BorrowedBook> getOverdueOrDamagedReaders() {
        List<BorrowedBook> overdueOrDamagedReaders = new ArrayList<>();
        String sql = "select bb.readerId, bb.bookId, bb.borrowId, rb.bookStatus, "
                   + "rf.lateDays, rf.FineMoney "
                   + "from BorrowBook bb "
                   + "join ReturnBook rb ON bb.borrowId = rb.borrowId "
                   + "left JOIN ReturnFine rf ON rb.returnId = rf.returnId "
                   + "where (rf.lateDays > 0 OR rb.bookStatus = 'Damaged') "
                   + "and bb.isDeleted = 0 AND rb.isDeleted = 0 AND rf.isDeleted = 0";

        try (Connection connection = DriverManager.getConnection(ConfigUtils.dbConnect, ConfigUtils.username, ConfigUtils.password);
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int readerId = rs.getInt("readerId");
                int bookId = rs.getInt("bookId");
                int borrowId = rs.getInt("borrowId");
                String bookStatus = rs.getString("bookStatus");
                int lateDays = rs.getInt("lateDays");
                int fineMoney = rs.getInt("FineMoney");
                BorrowedBook borrowedBook = new BorrowedBook(readerId, bookId, borrowId, bookStatus, lateDays, fineMoney);
                overdueOrDamagedReaders.add(borrowedBook);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return overdueOrDamagedReaders;
    }

}
