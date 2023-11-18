package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.time.format.DateTimeFormatter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import Handle.ImageHandle;
import Models.Account;
import Models.Message;
import Models.Profile;
import Util.JDBCUtil;
import Util.HandleExeption;

public class ChatDAO {

		private static final String INSERT_MESSAGE_SQL = "INSERT INTO message" +
	        " (ID_Receiver, ID_Sender, Time,  Content) VALUES " + " (?, ?, ?, N?);";

	    private static final String SELECT_USER_MESSAGE_BY_USERID = "SELECT p.userid, p.Name, p.Age,p.Gender,p.Birthday,p.Relationship,p.Height,p.Zodiac,p.Address,p.Introduce,p.ImageData, MAX(m.Time) AS LastMessageTime\r\n"
	    		+ "FROM Profile p\r\n"
	    		+ "LEFT JOIN Message m ON p.UserID = m.Id_Sender OR p.UserID = m.Id_Receiver\r\n"
	    		+ "WHERE (m.Id_Sender = ? OR m.Id_Receiver = ? ) and p.userid <> ? \r\n"
	    		+ "GROUP BY p.userid, p.Name, p.Age,p.Gender,p.Birthday,p.Relationship,p.Height,p.Zodiac,p.Address,p.Introduce,p.ImageData\r\n"
	    		+ "ORDER BY LastMessageTime DESC";
	    
	    
	    private static final String SELECT_MESSAGE_LAST_BY_USERID = "SELECT IDMessage, ID_Receiver, ID_Sender, Time, Content\r\n"
	    		+ "	FROM (\r\n"
	    		+ "		SELECT \r\n"
	    		+ "			IDMessage,\r\n"
	    		+ "			ID_Receiver,\r\n"
	    		+ "			ID_Sender,\r\n"
	    		+ "			Time,\r\n"
	    		+ "			Content,\r\n"
	    		+ "			ROW_NUMBER() OVER (PARTITION BY \r\n"
	    		+ "								CASE \r\n"
	    		+ "									WHEN ID_Receiver = ? THEN ID_Sender \r\n"
	    		+ "									WHEN ID_Sender = ? THEN ID_Receiver \r\n"
	    		+ "								END\r\n"
	    		+ "							  ORDER BY Time DESC) AS rn\r\n"
	    		+ "		FROM message\r\n"
	    		+ "		WHERE ID_Receiver = ? OR ID_Sender = ? \r\n"
	    		+ "	) AS ranked\r\n"
	    		+ "	WHERE rn = 1\r\n"
	    		+ "	ORDER BY Time DESC ;";
	    
	    private static final String SELECT_Profile_BY_UserID = "Select * from Profile where userID= ? ;";
	    
	    private static final String SELECT_MESSAGE_BY_USERID = "SELECT *\r\n"
	    		+ "FROM message\r\n"
	    		+ "WHERE (Id_Sender = ? or Id_Receiver = ?) \r\n"
	    		+ "ORDER BY Time asc;";
	    
	    
	    public List < Profile > select_other_user_message (String id_mainUser ) {  {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Profile > listPr = new ArrayList < > ();

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
                
//                if(UserID.equals(main.getUserID()))
                {
                	String UserID=rs.getString(1);
                	String Name=rs.getString(2);
					int Age = rs.getInt(3);
					String Gender = rs.getString(4);
					Date BirthDay = rs.getDate(5);
					String Relationship = rs.getString(6);
					int Height = rs.getInt(7);
					String Zodiac = rs.getString(8);
					String Address = rs.getString(9);
					String Introduce = rs.getString(10);
					byte[] Url_image = rs.getBytes(11);
					String imageURL = ImageHandle.byteArrayToImage(Url_image);
					
					listPr.add(new Profile(UserID, Name,Age,Gender,BirthDay,Relationship,Height,Zodiac,Address,Introduce, imageURL));
			}}}
             catch (SQLException exception) {
        	HandleExeption.printSQLException(exception);
        }
        return listPr;
        }
    }
	    
	    public List<Message> select_message_last(String id_mainUser ) {
	    	List < Message > message = new ArrayList < > ();        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtil.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MESSAGE_LAST_BY_USERID);) {
            preparedStatement.setString(1, id_mainUser);
            preparedStatement.setString(2, id_mainUser);
            preparedStatement.setString(3, id_mainUser);
            preparedStatement.setString(4,id_mainUser);
          //  System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {               
                Message lastMes = new Message();
                lastMes.setIdmessage(rs.getInt(1));
                lastMes.setId_Reciver(rs.getString(2));
                lastMes.setId_Sender(rs.getString(3));
                java.sql.Time sqlTime = rs.getTime(4);
                
                // Convert java.sql.Time to java.time.LocalTime
                LocalTime localTime = sqlTime.toLocalTime();
                lastMes.setTime(localTime);
                lastMes.setContent(rs.getString(5));
                message.add(lastMes);
            }
        } catch (SQLException exception) {
            HandleExeption.printSQLException(exception);
        }
        return message;
    }
	    
	    
	    public Profile Select_profile (String user_ID ) {
	        Profile pro  = null;
	        // Step 1: Establishing a Connection
	        try (Connection connection = JDBCUtil.getConnection();
	            // Step 2:Create a statement using connection object
	            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_Profile_BY_UserID);) {
	            preparedStatement.setString(1, user_ID );
	            
	          //  System.out.println(preparedStatement);
	            // Step 3: Execute the query or update query
	            ResultSet rs = preparedStatement.executeQuery();

	            // Step 4: Process the ResultSet object.
	            while (rs.next()) {
	                pro = new Profile();
	                pro.setName(rs.getString(2));
	                pro.setIntroduce(rs.getString(10));
	                pro.setImageData(rs.getBytes(11));
	            }
	        } catch (SQLException exception) {
	            HandleExeption.printSQLException(exception);
	        }
	        return pro;
	    }
	    public List < Message > select_message_by_UserID (String ID_Sender) {  {

	        // using try-with-resources to avoid closing resources (boiler plate code)
	        List < Message > message = new ArrayList < > ();

	        // Step 1: Establishing a Connection
	        try (Connection connection = JDBCUtil.getConnection();

	            // Step 2:Create a statement using connection object
	            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MESSAGE_BY_USERID);) {
	        	preparedStatement.setString(1, ID_Sender );
	        	preparedStatement.setString(2, ID_Sender );
	            //System.out.println(preparedStatement);
	            // Step 3: Execute the query or update query
	            ResultSet rs = preparedStatement.executeQuery();

	            // Step 4: Process the ResultSet object.
	            while (rs.next()) {
	                String IDReceiver = rs.getString(2);
	                String IDSender = rs.getString(3);
	 
	               	String Content = rs.getString(5);
	               	message.add(new Message(IDReceiver, IDSender, Content));
	            }
	        } catch (SQLException exception) {
	        	HandleExeption.printSQLException(exception);
	        }
	        return message;
	        }
	    }
    
	    public Boolean insertMessage(String ID_Receiver, String ID_Sender, String Content ) throws SQLException {
	    	boolean rowUpdated;
	        try (Connection connection = JDBCUtil.getConnection();
	            // Step 2:Create a statement using connection object
	            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MESSAGE_SQL);) {
	        	preparedStatement.setString(1, ID_Receiver);
	        	preparedStatement.setString(2, ID_Sender);
	        	preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
	        	preparedStatement.setString(4, Content);
	            //System.out.println(new Timestamp(System.currentTimeMillis()));
	          //  System.out.println(preparedStatement);
	            rowUpdated = preparedStatement.executeUpdate() > 0;
	        } 
	        return rowUpdated;
	    }
	    
}