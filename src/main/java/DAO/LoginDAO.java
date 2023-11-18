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
            while(rs.next()) {
            	acc.setEmail(rs.getString(1));
                acc.setUserID(rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return acc;
    }
}
