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

public class TrangChuDAO {
    private Connection connection;

    public TrangChuDAO(String string, int aInt) throws SQLException {
        try {
            this.connection = DriverManager.getConnection(
                    ConfigUtils.dbConnect, ConfigUtils.username, ConfigUtils.password);
        } catch (SQLException e) {
            System.out.println("Error to connect database!");
        }
    }

public List<TrangChuDAO> getBooksByDate(int date) throws SQLException {
        List<TrangChuDAO> kq = new ArrayList<>();
        String query = "select count(*) as totalBooks, borrowDate from BorrowBook where borrowDate = ? group by borrowDate";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, date);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            kq.add(new TrangChuDAO(rs.getString("borrowDate"), rs.getInt("totalBooks")));
        }
        return kq;
    }

    public List<TrangChuDAO> getBooksByWeek(int week, int year) throws SQLException {
        List<TrangChuDAO> kq = new ArrayList<>();
        String query = "select(*) as totalBooks, week(borrowDate) as weekNumber from BorrowBook where year(borrowDate) = ? and week(borrowDate) = ? group by week(borrowDate)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, year);
        ps.setInt(2, week);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            kq.add(new TrangChuDAO("Week " + rs.getInt("weekNumber"), rs.getInt("totalBooks")));
        }
        return kq;
    }

    public List<TrangChuDAO> getBooksByMonth(int month, int year) throws SQLException {
        List<TrangChuDAO> kq = new ArrayList<>();
        String query = "select(*) as totalBooks, month(borrowDate) as monthNumber from BorrowBook where year(borrowDate) = ? and month(borrowDate) = ? group by month(borrowDate)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, year);
        ps.setInt(2, month);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
             kq.add(new TrangChuDAO("monthNumber" + rs.getInt("weekNumber"), rs.getInt("totalBooks")));
        }
        return kq;
    }

    public List<TrangChuDAO> getBooksByYear(int year) throws SQLException {
        List<TrangChuDAO> kq = new ArrayList<>();
        String query = "select(*) as totalBooks, year(borrowDate) as yearNumber from BorrowBook where year(borrowDate) = ? group by year(borrowDate)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, year);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            kq.add(new TrangChuDAO("Year " + rs.getInt("yearNumber"), rs.getInt("totalBooks")));
        }
        return kq;
    }

    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
