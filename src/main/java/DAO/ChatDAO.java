package DAO;

import java.sql.Connection;
import java.time.format.DateTimeFormatter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Models.Account;
import Models.Message;
import Models.Profile;
import Util.JDBCUtil;
import Util.HandleExeption;

public class ChatDAO {

	private static final String INSERT_MESSAGE_SQL = "INSERT INTO message" +
	        "  (IDMessage, ID_Receiver, ID_Sender, Time,  Content) VALUES " + " (?, ?, ?, ?, ?);";

	    private static final String SELECT_USER_MESSAGE_BY_USERID = "SELECT DISTINCT p.userid,p.Name , p.url_image "
	    		+ " FROM Profile p "
	    		+ " JOIN Message m ON p.UserID = m.Id_Sender OR p.UserID = m.Id_Receiver "
	    		+ " WHERE (m.Id_Sender = ? OR m.Id_Receiver = ?) AND p.UserID <> ? ;";
	    
	    private static final String SELECT_MESSAGE_LAST_BY_USERID = "SELECT Time, content\r\n"
	    		+ "FROM message\r\n"
	    		+ "WHERE (Id_Sender = ? AND Id_Receiver = ?) \r\n"
	    		+ "   OR (Id_Sender = ? AND Id_Receiver = ?)\r\n"
	    		+ "ORDER BY Time DESC\r\n"
	    		+ "LIMIT 1;";
	    
	    private static final String SELECT_ACCOUNT_BY_Username = "select UserID from account where username = ?;";

	    
	    public List < Profile > select_other_user_message (String id_mainUser ) {  {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Profile > profile = new ArrayList < > ();

        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtil.getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_MESSAGE_BY_USERID);) {
        	preparedStatement.setString(1, id_mainUser );
        	preparedStatement.setString(2, id_mainUser );
        	preparedStatement.setString(3, id_mainUser );
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String UserID = rs.getString("userid");
                String Name = rs.getString("Name");
                String url_image = rs.getString("url_image");
                profile.add(new Profile(UserID, Name, url_image));
            }
        } catch (SQLException exception) {
        	HandleExeption.printSQLException(exception);
        }
        return profile;
        }
    }
	    
	    public Message select_message_last(String id_mainUser, String id_otherUser ) {
        Message message = null;
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtil.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MESSAGE_LAST_BY_USERID);) {
            preparedStatement.setString(1, id_mainUser);
            preparedStatement.setString(2, id_otherUser);
            preparedStatement.setString(3, id_otherUser);
            preparedStatement.setString(4,id_mainUser);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                LocalDate Time = rs.getDate("Time").toLocalDate();
                String content = rs.getString("content");
                message = new Message(Time,content);
            }
        } catch (SQLException exception) {
            HandleExeption.printSQLException(exception);
        }
        return message;
    }
    

	    
}