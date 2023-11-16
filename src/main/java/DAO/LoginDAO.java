package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Models.Account;
import Util.HandleExeption;
import Util.JDBCUtil;

public class LoginDAO {
	public Account onLogin(Account loginData) throws ClassNotFoundException {
		Account account= new Account();

		try {
			// Bước 1: Mở kết nối đến MySQL
			Connection conn = JDBCUtil.getConnection();
			
			// Bước 2: Khởi tạo Prepare Statement
			PreparedStatement preparedStatement = conn
					.prepareStatement("select * from account where username = ? and password = ? ");
			preparedStatement.setString(1, loginData.getUsername());
			preparedStatement.setString(2, loginData.getPassword());

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next())
			{
				account.setUsername(rs.getString(1));
//				account.setRole(rs.getString(3));
			}
			
			conn.close();

		} catch (SQLException e) {
			// process sql exception
			HandleExeption.printSQLException(e);
		}
		return account;
	}
}
