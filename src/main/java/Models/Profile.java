package Models;

import java.sql.Date;

public class Profile {
	private String userID;
	private String name;
	private int age;
	private String gender;
	private Date birthDay;
	private String relationship;
	private int height;
	private String zodiac;
	private String address;
	private String introduce;
	private String url_image;
	public Profile(String userID, String name, int age, String gender, Date birthDay, String relationship, int height,
			String zodiac, String address, String introduce,String url_image) {
		super();
		this.userID = userID;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.birthDay = birthDay;
		this.relationship = relationship;
		this.height = height;
		this.zodiac = zodiac;
		this.address = address;
		this.introduce = introduce;
		this.url_image=url_image;
	}
	public Profile(String userID, String name, String url_image)
	{
		this.userID = userID;
		this.name =name;
		this.url_image = url_image;
	}
	
	public Profile() {
		super();
	}
	public String getUrl_image() {
		return url_image;
	}
	public void setUrl_image(String url_image) {
		this.url_image = url_image;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getZodiac() {
		return zodiac;
	}
	public void setZodiac(String zodiac) {
		this.zodiac = zodiac;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	
	
}