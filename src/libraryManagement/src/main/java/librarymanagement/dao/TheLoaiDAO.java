package librarymanagement.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TheLoaiDAO {
    private Connection connection;

    public TheLoaiDAO(Connection connection) {
        this.connection = connection;
    }

    // Lấy danh sách tất cả thể loại
    public List<String> getAllTheLoai() throws SQLException {
        List<String> theLoaiList = new ArrayList<>();
        String query = "SELECT categoryName FROM Category WHERE isDeleted = 0";
        PreparedStatement stmt = connection.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            theLoaiList.add(rs.getString("categoryName"));
        }
        return theLoaiList;
    }

    // Thêm thể loại mới
    public void addTheLoai(String theLoaiName) throws SQLException {
        String query = "INSERT INTO Category (categoryName) VALUES (?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, theLoaiName);
        stmt.executeUpdate();
    }

    // Xóa thể loại (cập nhật isDeleted = 1)
    public void deleteTheLoai(String theLoaiName) throws SQLException {
        String query = "UPDATE Category SET isDeleted = 1 WHERE categoryName = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, theLoaiName);
        stmt.executeUpdate();
    }

    // Sửa tên thể loại
    public void updateTheLoai(String oldName, String newName) throws SQLException {
        String query = "UPDATE Category SET categoryName = ? WHERE categoryName = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, newName);
        stmt.setString(2, oldName);
        stmt.executeUpdate();
    }
}
