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
import librarymanagement.pojo.Setting;

/**
 *
 * @author quoct
 */
public class CaiDatDAO {
    private Connection connection;

    public CaiDatDAO(Connection connection) {
        this.connection = connection;
    }

    public Setting getSetting() throws SQLException {
        String query = "SELECT * FROM Setting LIMIT 1";
        PreparedStatement stmt = connection.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return new Setting(
                rs.getInt("maxBorrowDays"),
                rs.getInt("lateFeePerDay"),
                rs.getInt("bookDamageFee"),
                rs.getInt("lostBookFee"),
                rs.getInt("maxBooksBorrowed")
            );
        }
        return null;
    }

    public void updateSetting(int maxBorrowDays, int lateFeePerDay, int maxBooksBorrowed, int bookDamageFee, int lostBookFee) throws SQLException {
        String query = "UPDATE Setting SET maxBorrowDays = ?, lateFeePerDay = ?, bookDamageFee = ?, lostBookFee = ?, maxBooksBorrowed = ? WHERE settingId = 1";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, maxBorrowDays);
        stmt.setInt(2, lateFeePerDay);
        stmt.setInt(3, bookDamageFee);
        stmt.setInt(4, lostBookFee);
        stmt.setInt(5, maxBooksBorrowed);
        stmt.executeUpdate();
    }

}


