package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Models.Account;
import Util.JDBCUtil;

public class LoginDAO {
	public boolean validate(Account account) throws ClassNotFoundException {
        boolean status = false;

        try (Connection connection = JDBCUtil.getConnection();
            PreparedStatement preparedStatement = connection
            .prepareStatement("select * from account where email = ? and password = ? ")) {
            preparedStatement.setString(1, account.getEmail());
            preparedStatement.setString(2, account.getPassword());

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            status = rs.next();

        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return status;
    }
}
