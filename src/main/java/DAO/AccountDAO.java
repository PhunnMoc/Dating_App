package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import Models.Account;
import Util.JDBCUtil;

public class AccountDAO {
	public void insertAccount(Account account) {
        try (Connection conn = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO account (Email, Password, UserID) VALUES (?, ?, ?)")) {
        	preparedStatement.setString(1, account.getEmail());
        	preparedStatement.setString(2, account.getPassword());   
            preparedStatement.setString(3, account.getUserID());
            
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
