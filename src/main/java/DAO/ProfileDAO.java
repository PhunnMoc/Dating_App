package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Models.Profile;
import Util.JDBCUtil;

public class ProfileDAO {
	public void insertProfile(Profile profile) {
        try (Connection conn = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO Profile (UserId, Name) VALUES (?, ?)")) {
            preparedStatement.setString(1, profile.getUserID());
            preparedStatement.setString(2, profile.getName());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
