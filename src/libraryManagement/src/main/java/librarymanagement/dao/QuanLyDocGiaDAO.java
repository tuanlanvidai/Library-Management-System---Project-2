package librarymanagement.dao;

import librarymanagement.pojo.QuanLyDocGia;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuanLyDocGiaDAO {

    // Khai báo ConfigUtils như một lớp tĩnh và sử dụng trực tiếp các trường tĩnh
    private static final ConfigUtils util = new ConfigUtils();

    // Lấy danh sách tất cả độc giả
    public List<QuanLyDocGia> getAllReaders() {
        List<QuanLyDocGia> readers = new ArrayList<>();
        String sql = "SELECT * FROM reader WHERE isDeleted = 0";  // Lọc bỏ độc giả đã xóa
        try (Connection con = DriverManager.getConnection(util.dbConnect, util.username, util.password);
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                QuanLyDocGia reader = new QuanLyDocGia(
                        rs.getInt("readerId"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("phoneNumber"),
                        rs.getString("email"),
                        rs.getString("registerDay"),
                        rs.getBoolean("isDeleted")
                );
                readers.add(reader);
            }
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        return readers;
    }

    // Thêm độc giả mới
    public boolean addReader(QuanLyDocGia reader) {
        boolean isSuccess = false;
        String sql = "INSERT INTO reader (name, address, phoneNumber, email, registerDay) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(util.dbConnect, util.username, util.password);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, reader.getTenDocGia());
            stmt.setString(2, reader.getDiaChi());
            stmt.setString(3, reader.getSoDienThoai());
            stmt.setString(4, reader.getEmail());
            stmt.setString(5, reader.getNgayDangKy());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                isSuccess = true;
            }
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        return isSuccess;
    }

    // Sửa thông tin độc giả
    public boolean editReader(QuanLyDocGia reader) {
        boolean isSuccess = false;
        String sql = "UPDATE reader SET name = ?, address = ?, phoneNumber = ?, email = ?, registerDay = ? WHERE readerId = ?";
        try (Connection con = DriverManager.getConnection(util.dbConnect, util.username, util.password);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, reader.getTenDocGia());
            stmt.setString(2, reader.getDiaChi());
            stmt.setString(3, reader.getSoDienThoai());
            stmt.setString(4, reader.getEmail());
            stmt.setString(5, reader.getNgayDangKy());
            stmt.setInt(6, reader.getMaDocGia());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                isSuccess = true;
            }
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        return isSuccess;
    }

    // Xóa độc giả (đánh dấu là đã xóa)
    public boolean deleteReader(int maDocGia) {
        boolean isSuccess = false;
        String sql = "UPDATE reader SET isDeleted = 1 WHERE readerId = ?";
        try (Connection con = DriverManager.getConnection(util.dbConnect, util.username, util.password);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, maDocGia);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                isSuccess = true;
            }
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        return isSuccess;
    }

    // Tìm kiếm độc giả theo tên hoặc mã độc giả
    public List<QuanLyDocGia> searchReader(String keyword) {
    List<QuanLyDocGia> readers = new ArrayList<>();
    String sql = "SELECT * FROM reader WHERE (name LIKE ?) AND isDeleted = 0";  // Tìm kiếm theo tên
    try (Connection con = DriverManager.getConnection(util.dbConnect, util.username, util.password);
         PreparedStatement stmt = con.prepareStatement(sql)) {
        
        stmt.setString(1, "%" + keyword + "%");  // Tìm kiếm theo tên độc giả

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                QuanLyDocGia reader = new QuanLyDocGia(
                         rs.getInt("readerId"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("phoneNumber"),
                        rs.getString("email"),
                        rs.getString("registerDay"),
                        rs.getBoolean("isDeleted")
                );
                readers.add(reader);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();  // Log đầy đủ lỗi
    }
    return readers;
}

    // Tìm kiếm độc giả theo mã độc giả (ID)
    public List<QuanLyDocGia> searchReaderById(int id) {
    List<QuanLyDocGia> readers = new ArrayList<>();
    String sql = "SELECT * FROM reader WHERE readerId = ? AND isDeleted = 0";  // Lọc bỏ độc giả đã xóa
    try (Connection con = DriverManager.getConnection(util.dbConnect, util.username, util.password);
         PreparedStatement stmt = con.prepareStatement(sql)) {
        
        stmt.setInt(1, id);  // Tìm theo mã độc giả

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                QuanLyDocGia reader = new QuanLyDocGia(
                        rs.getInt("readerId"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("phoneNumber"),
                        rs.getString("email"),
                        rs.getString("registerDay"),
                        rs.getBoolean("isDeleted")
                );
                readers.add(reader);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();  // Log đầy đủ lỗi
    }
    return readers;
}

    // Thêm dữ liệu vào bảng
    public void addDataToTable(DefaultTableModel model, JTable table) {
    List<QuanLyDocGia> readers = this.getAllReaders();
    model.setRowCount(0);  // Clear existing data
    for (QuanLyDocGia reader : readers) {
        model.addRow(new Object[]{
                reader.getMaDocGia(),
                reader.getTenDocGia(),
                reader.getDiaChi(),
                reader.getSoDienThoai(),
                reader.getEmail(),
                reader.getNgayDangKy()
        });
    }
}

    // Thêm dữ liệu từ tìm kiếm vào bảng
    public void addDataFromSearch(List<QuanLyDocGia> readers, DefaultTableModel model, JTable table) {
        model.setRowCount(0);  // Xóa các dòng hiện tại trong bảng
        for (QuanLyDocGia reader : readers) {
            model.addRow(new Object[]{
                reader.getMaDocGia(),
                reader.getTenDocGia(),
                reader.getDiaChi(),
                reader.getSoDienThoai(),
                reader.getEmail(),
                reader.getNgayDangKy()
            });
        }
    }
}
