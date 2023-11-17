package Models;

public class UserHobby {
	private String userID;
	private String hobbyName;
	private String iDhobby;
	public UserHobby(String userID, String hobbyName) {
		super();
		this.userID = userID;
		this.hobbyName = hobbyName;
	}
	
	public String getiDhobby() {
		return iDhobby;
	}

	public void setiDhobby(String iDhobby) {
		this.iDhobby = iDhobby;
	}

	public UserHobby(String userID, String hobbyName, String iDhobby) {
		super();
		this.userID = userID;
		this.hobbyName = hobbyName;
		this.iDhobby = iDhobby;
	}

	public UserHobby() {
		super();
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getHobbyName() {
		return hobbyName;
	}
	public void setHobbyName(String hobbyName) {
		this.hobbyName = hobbyName;
	}
	
	
}
