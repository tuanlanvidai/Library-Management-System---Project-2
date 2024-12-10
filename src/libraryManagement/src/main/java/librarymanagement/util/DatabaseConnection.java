package librarymanagement.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import librarymanagement.dao.ConfigUtils;

public class DatabaseConnection {
    public static Connection getConnection() throws SQLException {
        // Sử dụng ConfigUtils để lấy thông tin kết nối
        return DriverManager.getConnection(
            ConfigUtils.dbConnect,
            ConfigUtils.username,
            ConfigUtils.password
        );
    }
}
