package DAO;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Util.HandleExeption;
import Util.JDBCUtil;

public class RegisterDAO {
	public boolean checkEmailExists(String email) {
		boolean result = false;
		try {
			Connection conn = JDBCUtil.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement("select * from account where email = ?");
			preparedStatement.setString(1, email);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next())
			{
				result = true;
			}
			conn.close();
		}catch(SQLException e) {
			HandleExeption.printSQLException(e);
		}		
		return result;
	}
	
	public boolean checkUserIDExists(String UserID) {
		boolean result = false;
		try {
			Connection conn = JDBCUtil.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement("select * from account where UserID = ?");
			preparedStatement.setString(1, UserID);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next())
			{
				result = true;
			}
			conn.close();
		}catch(SQLException e) {
			HandleExeption.printSQLException(e);
		}		
		return result;
	}
	
	public String generateUserID() {
		//Tao ID 8 so ngau nhien
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            int digit = random.nextInt(10);
            sb.append(digit);
        }

        String newUserID = sb.toString();

        while (checkUserIDExists(newUserID)) {
            sb.setLength(0);
            for (int i = 0; i < 8; i++) {
                int digit = random.nextInt(10);
                sb.append(digit);
            }
            newUserID = sb.toString();
        }
        return newUserID;
    }
}
