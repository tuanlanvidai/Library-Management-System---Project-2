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

public class TrangChuDAO {
    ConfigUtils util = new ConfigUtils();
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
}
