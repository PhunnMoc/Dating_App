package Models;

public class Hobby {
	private String iDhobby;
	private String hobbyName;
	public Hobby(String iDhobby, String hobbyName) {
		super();
		this.iDhobby = iDhobby;
		this.hobbyName = hobbyName;
	}
	public Hobby() {
		super();
	}
	public String getiDhobby() {
		return iDhobby;
	}
	public void setiDhobby(String iDhobby) {
		this.iDhobby = iDhobby;
	}
	public String getHobbyName() {
		return hobbyName;
	}
	public void setHobbyName(String hobbyName) {
		this.hobbyName = hobbyName;
	}
			
}