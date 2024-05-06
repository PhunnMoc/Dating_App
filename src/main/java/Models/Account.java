package Models;

public class Account {

	private String email;
	private String password;
	private String UserID;
	private String role;
	
	public Account() {
		super();
	}

	public Account(String email, String password, String userID) {
		super();
		this.email = email;
		this.password = password;
		this.UserID = userID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}