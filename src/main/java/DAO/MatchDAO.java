package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Models.Account;
import Models.Match;
import Util.HandleExeption;
import Util.JDBCUtil;

public class MatchDAO {
	private static final String INSERT_MATCH_SQL = "INSERT INTO `match` "
			+ "  (matchID, MatchStatus, daymatch, userID1, userID2) VALUES " + " (?, N?, ? , ? , ?);";
	private static final String SELECT_MAX_ID = "SELECT MAX(CAST(matchID AS SIGNED)) AS max_id FROM `match`;";
	private static final String SQL_SAFE_UPDATES = "SET SQL_SAFE_UPDATES = 0;";
	private static final String SQL_UPDATE_ALL = "UPDATE `datingapp`.`match`  AS a\r\n"
			+ "JOIN `datingapp`.`match`  AS b ON a.userID1 = b.userID2 AND a.userID2 = b.userID1\r\n"
			+ "SET a.`MatchStatus` = 'Match' \r\n" + "WHERE a.`MatchStatus` = 'Chưa Match' AND a.matchID > 0;    ";
	private static final String DELETE_MATCH = "DELETE FROM datingapp.match \r\n" + "WHERE userID1=? and  userID2=?";
	private static final String SQL_UPDATE_ALLNoMatch ="UPDATE `datingapp`.`match` AS a\r\n"
			+ "LEFT JOIN `datingapp`.`match` AS b ON a.userID1 = b.userID2 AND a.userID2 = b.userID1\r\n"
			+ "SET a.`MatchStatus` = 'Chưa Match'\r\n"
			+ "WHERE b.userID1 IS NULL ;";

	public void updateMatchAll() throws SQLException {
		try {
			Connection connection = JDBCUtil.getConnection();
			PreparedStatement safe = connection.prepareStatement(SQL_SAFE_UPDATES);
			safe.executeUpdate();
			PreparedStatement updateAll = connection.prepareStatement(SQL_UPDATE_ALL);
			updateAll.executeUpdate();
		} catch (SQLException e) {
			HandleExeption.printSQLException(e);
		}
	}

	public boolean deleteMatch(String user1, String user2) throws SQLException {
		boolean rowUpdated;
		try (Connection conn = JDBCUtil.getConnection();
				PreparedStatement statement = conn.prepareStatement(DELETE_MATCH);) {
			statement.setString(1, user1);
			statement.setString(2, user2);
			rowUpdated = statement.executeUpdate() > 0;
			PreparedStatement safe = conn.prepareStatement(SQL_SAFE_UPDATES);
			safe.executeUpdate();
			PreparedStatement updateAllNoMatch=conn.prepareStatement(SQL_UPDATE_ALLNoMatch);
			updateAllNoMatch.executeUpdate();
		}
		return rowUpdated;
	}

	public void insertMatch(Match match) throws SQLException {
		try {
			Connection connection = JDBCUtil.getConnection();
			PreparedStatement maxID = connection.prepareStatement(SELECT_MAX_ID);
			ResultSet resultSet = maxID.executeQuery();
			int newID = 0;
			if (resultSet.next()) {
				int maxIDValue = resultSet.getInt("max_id");
				newID = maxIDValue + 1;
			}
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MATCH_SQL);
			String NewID = String.valueOf(newID);
			preparedStatement.setString(1, NewID);
			preparedStatement.setString(2, "Chưa Match");
			preparedStatement.setDate(3, JDBCUtil.getSQLDate(match.getDaymatch()));
			preparedStatement.setString(4, match.getUserID1());
			preparedStatement.setString(5, match.getUserID2());
//            System.out.println(NewID);
//            System.out.println(match.getDaymatch());
//            System.out.println(match.getUserID1());
//            System.out.println(match.getUserID2());
			preparedStatement.executeUpdate();

			connection.close();
		} catch (SQLException e) {
			HandleExeption.printSQLException(e);
		}
	}


}
