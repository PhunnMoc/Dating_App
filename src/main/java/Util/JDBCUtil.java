package Util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

public class JDBCUtil {

	public static Connection getConnection() {
		Connection conn = null;
		try {

			DriverManager.registerDriver(new com.mysql.jdbc.Driver());

<<<<<<< HEAD
			String url = "jdbc:mysql://localhost:3306/datingapp?useSSL=false";
			String username = "root";
			String password = "trinhthuphuong";

=======
			String url = "jdbc:mysql://teenther.ckck8862sjqz.us-east-1.rds.amazonaws.com:3306/datingapp";
			String username = "admin";
			String password = "12345678";
>>>>>>> main
			conn = DriverManager.getConnection(url, username, password);

			System.out.println("Connection Successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Connection error");
			e.printStackTrace();
		}

		return conn;
	}

	public static void closeConnection(Connection conn) {
		try {
			if (conn != null) {
				System.out.println("Connection close");
				conn.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void main() {
		System.out.println(" aaaa!");
		Connection a= getConnection();
		System.out.println(" cfcfcccf!");
		
	}
	public static Date getSQLDate(LocalDate date) {
        return java.sql.Date.valueOf(date);
    }
}