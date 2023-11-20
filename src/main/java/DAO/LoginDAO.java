package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Models.Account;
import Util.JDBCUtil;

public class LoginDAO {
    public Account validate(String email, String password) throws ClassNotFoundException {
    	Account acc = new Account();
        

        try (Connection connection = JDBCUtil.getConnection();
                PreparedStatement preparedStatement = connection
                        .prepareStatement("select * from account where email = ? and password = ? ")) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
           if(rs.next()) {
            	acc.setEmail(rs.getString(1));
                acc.setUserID(rs.getString(3));
            }
           else {
        	   acc = null;
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return acc;
    }
    public void ChangePassword(String newPass, String email) throws ClassNotFoundException {

        try (Connection connection = JDBCUtil.getConnection();
                PreparedStatement preparedStatement = connection
                        .prepareStatement("update account set password = ? where email = ?")) {
            preparedStatement.setString(1, newPass);
            preparedStatement.setString(2, email);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}