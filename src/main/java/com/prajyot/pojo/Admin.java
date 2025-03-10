package com.prajyot.pojo;

public class Admin {
	
	private Integer adminID;
	private String name;
	private String gender;
	private String userName;
	private String mobileNumber;
	private String upassword;

	public Admin() {
		
	}
	
	public Admin(Integer adminID, String name, String gender, String userName, String mobileNumber, String upassword) {
		super();
		this.adminID = adminID;
		this.name = name;
		this.gender = gender;
		this.userName = userName;
		this.mobileNumber = mobileNumber;
		this.upassword = upassword;
	}
	
	public Integer getAdminID() {
		return adminID;
	}
	public void setAdminID(Integer adminID) {
		this.adminID = adminID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getUpassword() {
		return upassword;
	}
	public void setUpassword(String password) {
		this.upassword = password;
	}
}
