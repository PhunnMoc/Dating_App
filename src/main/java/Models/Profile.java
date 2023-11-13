package Models;

import java.sql.Date;

public class Profile {
	private String UserID;
	private String Name;
	private int Age;
	private Date BirthDay;
	private String Relationship;
	private int Height;
	private String Zodiac;
	private String Address;
	private String Introduce;
	
	public Profile() {
		super();
	}

	public Profile(String userID, String name, int age, Date birthDay, String relationship, int height, String zodiac,
			String address, String introduce) {
		super();
		UserID = userID;
		Name = name;
		Age = age;
		BirthDay = birthDay;
		Relationship = relationship;
		Height = height;
		Zodiac = zodiac;
		Address = address;
		Introduce = introduce;
	}

	public Profile(String userID, String name) {
		super();
		UserID = userID;
		Name = name;
	}

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getAge() {
		return Age;
	}

	public void setAge(int age) {
		Age = age;
	}

	public Date getBirthDay() {
		return BirthDay;
	}

	public void setBirthDay(Date birthDay) {
		BirthDay = birthDay;
	}

	public String getRelationship() {
		return Relationship;
	}

	public void setRelationship(String relationship) {
		Relationship = relationship;
	}

	public int getHeight() {
		return Height;
	}

	public void setHeight(int height) {
		Height = height;
	}

	public String getZodiac() {
		return Zodiac;
	}

	public void setZodiac(String zodiac) {
		Zodiac = zodiac;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getIntroduce() {
		return Introduce;
	}

	public void setIntroduce(String introduce) {
		Introduce = introduce;
	}
	
}
