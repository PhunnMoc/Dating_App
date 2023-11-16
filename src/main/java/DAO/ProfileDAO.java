package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.Account;
import Models.Hobby;
import Models.Profile;
import Models.UserHobby;
import Util.HandleExeption;
import Util.JDBCUtil;

public class ProfileDAO {

	private static final String SELECT_HOBBIES_BY_ID = "select UserID, NameHobby, IDHobby\r\n"
			+ "from userhobby inner join hobby \r\n"
			+ "on userhobby.IDHobby = hobby.IDHobby\r\n"
			+ "where userhobby.UserID = 'user2_id = ?";
	private static final String SELECT_ALL_HOBBIES = "select * from hobby";
	private static final String SELECT_ALL_PROFILE = "SELECT * FROM profile;";
    private static final String SELECT_IMAGES_BY_ID = "select imgID, url from Image where id = ?";
    private static final String UPDATE_PROFILE = "update profile set name = ?, age= ?, gender = ?, birthDay= ?, \r\n"
    		+ "relationship = ?, height = ?, zodiac = ?, address = ?, introduce = ?\r\n"
    		+ "where id = ?";
	
	public Profile GetProfile(Account accData) throws ClassNotFoundException {
		Profile profile= new Profile();

		try {
			// Bước 1: Mở kết nối đến MySQL
			Connection conn = JDBCUtil.getConnection();
			
			// Bước 2: Khởi tạo Prepare Statement
			PreparedStatement preparedStatement = conn
					.prepareStatement("select * from profile where userid = ?");
			preparedStatement.setString(1, accData.getUserid());

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next())
			{
				profile.setUserID(rs.getString(1));
				profile.setName(rs.getString(2));
				profile.setAge(rs.getInt(3));
				profile.setGender(rs.getString(4));
				profile.setBirthDay(rs.getDate(5));
				profile.setRelationship(rs.getString(6));
				profile.setHeight(rs.getInt(7));
				profile.setZodiac(rs.getString(8));
				profile.setAddress(rs.getString(9));
				profile.setIntroduce(rs.getString(10));
				profile.setUrl_image(rs.getString(11));
			}		
			conn.close();

		} catch (SQLException e) {
			// process sql exception
			HandleExeption.printSQLException(e);
		}
		return profile;
	}
	
	public List < UserHobby > GetHobby(Account accData) {
        // using try-with-resources to avoid closing resources (boiler plate code)
        List < UserHobby > hobbies = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection conn = JDBCUtil.getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_HOBBIES_BY_ID);) {
            // Step 3: Execute the query or update query
        	preparedStatement.setString(1, accData.getUserid());
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String userid = rs.getString(1);
                String hobbyName = rs.getString(2);
                String iDhobby = rs.getString(3);
                hobbies.add(new UserHobby(userid, hobbyName, iDhobby));
            }
        } catch (SQLException e) {
        	HandleExeption.printSQLException(e);
        }
        return hobbies;
    }
	
	public List < Hobby > GetAllHobbies() {
        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Hobby > hobbies = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection conn = JDBCUtil.getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ALL_HOBBIES);) {
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
            	String iDhobby = rs.getString(1);
                String hobbyName = rs.getString(2);
               
                hobbies.add(new Hobby(iDhobby, hobbyName));
            }
        } catch (SQLException e) {
        	HandleExeption.printSQLException(e);
        }
        return hobbies;
    }
	
	//phương
	public List < Profile > GeListProfile() {
        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Profile > listPr = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection conn = JDBCUtil.getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ALL_PROFILE);) {
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String UserID=rs.getString(1);
//                if(UserID.equals(main.getUserID()))
                {
                	String Name=rs.getString(2);
					int Age = rs.getInt(3);
					String Gender = rs.getString(4);
					Date BirthDay = rs.getDate(5);
					String Relationship = rs.getString(6);
					int Height = rs.getInt(7);
					String Zodiac = rs.getString(8);
					String Address = rs.getString(9);
					String Introduce = rs.getString(10);
					String Url_image = rs.getString(11);

					listPr.add(new Profile(UserID, Name,Age,Gender,BirthDay,Relationship,Height,Zodiac,Address,Introduce,Url_image));
                }
                
            }
        } catch (SQLException e) {
        	HandleExeption.printSQLException(e);
        }
        return listPr;
    }
	
	public Profile GetInfor(String id) throws ClassNotFoundException {
		Profile profile= new Profile();

		try {
			// Bước 1: Mở kết nối đến MySQL
			Connection conn = JDBCUtil.getConnection();
			
			// Bước 2: Khởi tạo Prepare Statement
			PreparedStatement preparedStatement = conn
					.prepareStatement("select * from profile where userid = ?");
			preparedStatement.setString(1, id);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next())
			{
				profile.setUserID(rs.getString(1));
				profile.setName(rs.getString(2));
				profile.setAge(rs.getInt(3));
				profile.setGender(rs.getString(4));
				profile.setBirthDay(rs.getDate(5));
				profile.setRelationship(rs.getString(6));
				profile.setHeight(rs.getInt(7));
				profile.setZodiac(rs.getString(8));
				profile.setAddress(rs.getString(9));
				profile.setIntroduce(rs.getString(10));
				profile.setUrl_image(rs.getString(11));
			}		
			conn.close();

		} catch (SQLException e) {
			// process sql exception
			HandleExeption.printSQLException(e);
		}
		return profile;
	}
	//phương
	
	
	/*
	 * public List < Image > GetImage(Account accData) { // using try-with-resources
	 * to avoid closing resources (boiler plate code) List < Image > images = new
	 * ArrayList < > (); // Step 1: Establishing a Connection try (Connection conn =
	 * JDBCUtil.getConnection();
	 * 
	 * // Step 2:Create a statement using connection object PreparedStatement
	 * preparedStatement = conn.prepareStatement(SELECT_IMAGES_BY_ID);) { // Step 3:
	 * Execute the query or update query preparedStatement.setString(1,
	 * accData.getUserid()); ResultSet rs = preparedStatement.executeQuery();
	 * 
	 * // Step 4: Process the ResultSet object. while (rs.next()) { String imgID =
	 * rs.getString(1); String url = rs.getString(2); images.add(new Image(imgID,
	 * url)); } } catch (SQLException e) { HandleExeption.printSQLException(e); }
	 * return images; }
	 */
	
	public boolean updateProfile(Profile profile) throws SQLException {
        boolean rowUpdated;
        try (Connection conn = JDBCUtil.getConnection();
        	PreparedStatement statement = conn.prepareStatement(UPDATE_PROFILE);) {
            statement.setString(1, profile.getName());
            statement.setInt(2, profile.getAge());
            statement.setString(3, profile.getGender());
            statement.setDate(4, profile.getBirthDay());
            statement.setString(5, profile.getRelationship());
            statement.setInt(6, profile.getHeight());
            statement.setString(7, profile.getZodiac());
            statement.setString(8, profile.getAddress());
            statement.setString(9, profile.getIntroduce());
            statement.setString(10, profile.getUserID());
            
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}