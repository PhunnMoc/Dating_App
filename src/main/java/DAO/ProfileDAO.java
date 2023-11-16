package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import Models.Account;
import Models.Hobby;
import Models.Profile;
import Models.UserHobby;
import Util.HandleExeption;
import Util.JDBCUtil;
import Handle.ImageHandle;
public class ProfileDAO {
    private static final String SELECT_HOBBIES_BY_ID = "select UserID, NameHobby, hobby.IDHobby\r\n"
            + "from userhobby inner join hobby \r\n"
            + "on userhobby.IDHobby = hobby.IDHobby\r\n"
            + "where userhobby.UserID = ?";
    private static final String SELECT_ALL_HOBBIES = "select * from hobby";
    private static final String UPDATE_PROFILE_IMAGE = "update profile set name = N?, age= ?, gender = N?, birthDay= ?, \r\n"
            + "relationship = N?, height = ?, zodiac = N?, address = N?, introduce = N?, imageData =?\r\n"
            + "where Userid = ?";
    private static final String UPDATE_PROFILE = "update profile set name = N?, age= ?, gender = N?, birthDay= ?, \r\n"
            + "relationship = N?, height = ?, zodiac = N?, address = N?, introduce = N?\r\n"
            + "where Userid = ?";
    private static final String DELETE_USERHOBBY_BY_ID = "delete from userHobby where userid = ?";
    private static final String INSERT_USERHOBBY_BY_ID = "insert into userHobby (`IDHobby`, `UserID`) VALUES (?, ?)";
    private static final String SELECT_CARD_PROFILE = "select *from profile \r\n"
			+ "where profile.UserId <> ?\r\n"
			+ "					AND profile.UserId NOT IN (select  B.userID2 as NO\r\n"
			+ "				from  profile A LEFT JOIN datingapp.match B ON A.UserId = B.userID1 AND B.userID1 = ?\r\n"
			+ "					where  B.matchID IS NOT NULL)\r\n"
			+ "			    ";
	private static final String SELECT_MATCH_PROFILE= "select *from profile \r\n"
			+ "		where  profile.UserId <> ?\r\n"
			+ "			AND profile.UserId IN (select  B.userID2 as NO\r\n"
			+ "								from  profile A LEFT JOIN datingapp.match B ON A.UserId = B.userID1 AND B.userID1 = ?\r\n"
			+ "									where  B.matchID IS NOT NULL)";

    public Profile GetProfile(Account accData) throws ClassNotFoundException {
        Profile profile = new Profile();

        try {
            // Bước 1: Mở kết nối đến MySQL
            Connection conn = JDBCUtil.getConnection();

            // Bước 2: Khởi tạo Prepare Statement
            PreparedStatement preparedStatement = conn
                    .prepareStatement("select * from profile where userid = ?");
            preparedStatement.setString(1, accData.getUserID());

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
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
                profile.setImageData(rs.getBytes(11));

            }
            conn.close();

        } catch (SQLException e) {
            // process sql exception
            HandleExeption.printSQLException(e);
        }
        return profile;
    }

    public List<UserHobby> GetHobby(Account accData) {
        // using try-with-resources to avoid closing resources (boiler plate code)
        List<UserHobby> hobbies = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection conn = JDBCUtil.getConnection();

                // Step 2:Create a statement using connection object
                PreparedStatement preparedStatement = conn.prepareStatement(SELECT_HOBBIES_BY_ID);) {
            // Step 3: Execute the query or update query
            preparedStatement.setString(1, accData.getUserID());
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

    public List<Hobby> GetAllHobbies() {
        // using try-with-resources to avoid closing resources (boiler plate code)
        List<Hobby> hobbies = new ArrayList<>();
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

    public boolean updateProfileImage(Profile profile) throws SQLException {
        boolean rowUpdated;
        try (Connection conn = JDBCUtil.getConnection();
                PreparedStatement statement = conn.prepareStatement(UPDATE_PROFILE_IMAGE);) {
            statement.setString(1, profile.getName());
            statement.setInt(2, profile.getAge());
            statement.setString(3, profile.getGender());
            statement.setDate(4, profile.getBirthDay());
            statement.setString(5, profile.getRelationship());
            statement.setInt(6, profile.getHeight());
            statement.setString(7, profile.getZodiac());
            statement.setString(8, profile.getAddress());
            statement.setString(9, profile.getIntroduce());
            statement.setBytes(10, profile.getImageData());
            statement.setString(11, profile.getUserID());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public boolean DeleteUserHobby(Account acc) throws SQLException {
        boolean rowUpdated;
        try (Connection conn = JDBCUtil.getConnection();
                PreparedStatement statement = conn.prepareStatement(DELETE_USERHOBBY_BY_ID);) {
            statement.setString(1, acc.getUserID());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public boolean UpdateUserHobby(String iDhobby, Account acc) throws SQLException {
        boolean rowUpdated;
        try (Connection conn = JDBCUtil.getConnection();
                PreparedStatement statement = conn.prepareStatement(INSERT_USERHOBBY_BY_ID);) {
            statement.setString(1, iDhobby);
            statement.setString(2, acc.getUserID());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
    public void insertProfile(Profile profile) {
    	 try (Connection conn = JDBCUtil.getConnection();
    	 PreparedStatement preparedStatement = conn
    	 .prepareStatement("INSERT INTO Profile (UserId, Name) VALUES (?, N?)")) {
    	 preparedStatement.setString(1, profile.getUserID());
    	 preparedStatement.setString(2, profile.getName());
    	 System.out.println(preparedStatement);
    	 preparedStatement.executeUpdate();
    	 } catch (SQLException e) {
    	 e.printStackTrace();
    	 }
   }

	
	//phương
	public List < Profile > GeListProfile(String userID) {
        List < Profile > listPr = new ArrayList < > ();
// Step 1: Establishing a Connection
        try  {
        	Connection conn = JDBCUtil.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_CARD_PROFILE);
            preparedStatement.setString(1, userID);
            preparedStatement.setString(2, userID);
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
					byte[] Url_image = rs.getBytes(11);
					String imageURL = ImageHandle.byteArrayToImage(Url_image);
					
					listPr.add(new Profile(UserID, Name,Age,Gender,BirthDay,Relationship,Height,Zodiac,Address,Introduce, imageURL));
                }
                
            }
        } catch (SQLException e) {
        	HandleExeption.printSQLException(e);
        }
        return listPr;
    }
	public List < Profile > GeListProfileMatch(String userID) {
        List < Profile > listPr = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try  {
        	Connection conn = JDBCUtil.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_MATCH_PROFILE);
            preparedStatement.setString(1, userID);
            preparedStatement.setString(2, userID);
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
					byte[] Url_image = rs.getBytes(11);
					String imageURL = ImageHandle.byteArrayToImage(Url_image);
					
					listPr.add(new Profile(UserID, Name,Age,Gender,BirthDay,Relationship,Height,Zodiac,Address,Introduce, imageURL));
                }
                
            }
        } catch (SQLException e) {
        	HandleExeption.printSQLException(e);
        }
        return listPr;
    }
	}