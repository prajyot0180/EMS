package com.prajyot.pojo;

import java.util.Date;

public class Employee {

	private Integer eId;
	private String employeeId;
	private String eName;
	private String gender;
	private String email;
	private String mobileNumber;
	private String address;
	private Float salary;
	private String department;
	private Date joinDate;
	private Date dob;
	private String admin;
	private String username;
	private String password;
	
	public Employee() {
		
	}
	public Employee(Integer eId, String eName, String gender, String email, String mobileNumber, String address, String admin, String username, String password) {
	
		this.eId = eId;
		this.eName = eName;
		this.gender = gender;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.address = address;
		this.admin = admin;
		this.username = username;
		this.password = password;
	}
	public Employee(Integer eId, String employeeId, String eName, String gender, String email, String mobileNumber,
			String address, Float salary, String department, Date joinDate, Date dob, String admin, String username,
			String password) {

		this.eId = eId;
		this.employeeId = employeeId;
		this.eName = eName;
		this.gender = gender;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.address = address;
		this.salary = salary;
		this.department = department;
		this.joinDate = joinDate;
		this.dob = dob;
		this.admin = admin;
		this.username = username;
		this.password = password;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public Float getSalary() {
		return salary;
	}
	public void setSalary(Float salary) {
		this.salary = salary;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Integer geteId() {
		return eId;
	}
	public String getAdmin() {
		return admin;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public void seteId(Integer eId) {
		this.eId = eId;
	}
	public String geteName() {
		return eName;
	}
	public void seteName(String eName) {
		this.eName = eName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
