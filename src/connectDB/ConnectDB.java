package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	private static ConnectDB connectDB = new ConnectDB();
	private static Connection connection = null;

	public static Connection getConnection() {
		return connection;
	}

	public static ConnectDB getInstance() {
		return connectDB;
	}

	public void connect() throws SQLException {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=PTUD_QuanLyKaraokeNice";
		String username = "sa";
		String password = "sapassword";
		connection = DriverManager.getConnection(url, username, password);
	}

	public void disconnect() {
		if (connection != null)
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}