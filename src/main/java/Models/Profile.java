package Models;

public class Profile {
	private String userID;
	private String name;
	private String url_image ;
	
	public Profile(String userID, String name, String url_image)
	{
		this.userID = userID;
		this.name =name;
		this.url_image = url_image;
	}
	public String getUserID( ){
		return this.userID;
	}
	
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	

}
