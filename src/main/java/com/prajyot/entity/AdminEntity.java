package com.prajyot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="p_admin")
public class AdminEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admin_seq")
	@SequenceGenerator(name = "admin_seq", sequenceName = "admin_seq", allocationSize = 1)
	@Column(name="id")
	private Integer adminID;
	
	@Column(name="name")
	private String name;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="username")
	private String userName;
	
	@Column(name="mobile_number")
	private String mobileNumber;
	
	@Column(name="password")
	private String upassword;

	public AdminEntity() {
		
	}
	
	public AdminEntity(Integer adminID, String name, String gender, String userName, String mobileNumber, String upassword) {
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
