package Models;

public class Hobby {
	private String iDhobby;
	private String hobbyName;
	private byte[] imageData;
	private String imageURL;
	
	public Hobby(String iDhobby, String hobbyName, String imageURL) {
		super();
		this.iDhobby = iDhobby;
		this.hobbyName = hobbyName;
		this.imageURL = imageURL;
	}
	
	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

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