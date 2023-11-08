package Models;

public class Image {
	private String imgID;
	private String url;
	private String userID;
	public Image(String imgID, String url, String userID) {
		super();
		this.imgID = imgID;
		this.url = url;
		this.userID = userID;
	}
	
	public Image(String imgID, String url) {
		super();
		this.imgID = imgID;
		this.url = url;
	}


	public Image() {
		super();
	}
	public String getImgID() {
		return imgID;
	}
	public void setImgID(String imgID) {
		this.imgID = imgID;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
}
