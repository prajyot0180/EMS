package com.prajyot.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="p_employee")
public class EmployeeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_seq")
	@SequenceGenerator(name = "emp_seq", sequenceName = "emp_seq", allocationSize = 1)
	@Column(name="id")
	private Integer eId;
	@Column(name="employee_id")
	private String employeeId;
	@Column(name="employee_name")
	private String eName;
	@Column(name="gender")
	private String gender;
	@Column(name="email")
	private String email;
	@Column(name="mobile_number")
	private String mobileNumber;
	@Column(name="address")
	private String address;
	@Column(name="salary")
	private Float salary;
	@Column(name="department")
	private String department;
	@Column(name="join_date")
	private Date joinDate;
	@Column(name="dob")
	private Date dob;
	@Column(name="admin")
	private String admin;
	@Column(name="username")
	private String username;
	@Column(name="password")
	private String password;
	public EmployeeEntity() {
		
	}
	public EmployeeEntity(Integer eId, String eName, String gender, String email, String mobileNumber, String address, String admin, String username, String password) {
	
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
	public EmployeeEntity(Integer eId, String employeeId, String eName, String gender, String email, String mobileNumber,
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
	@Override
	public String toString() {
		return "Employee [eId=" + eId + ", employeeId=" + employeeId + ", eName=" + eName + ", gender=" + gender
				+ ", email=" + email + ", mobileNumber=" + mobileNumber + ", address=" + address + ", salary=" + salary
				+ ", department=" + department + ", joinDate=" + joinDate + ", dob=" + dob + ", admin=" + admin
				+ ", username=" + username + ", password=" + password + "]";
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	    public static void main(String[] args) {
	        Date currentDate = new Date();
	        System.out.println("Original Date: " + currentDate);

	        // Format the date to remove the time part
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Only date (no time)
	        String dateString = sdf.format(currentDate);
	        
	        try {
	            // Parse the string back to a Date object (this will only contain the date part)
	            Date truncatedDate = sdf.parse(dateString);
	            System.out.println("Truncated Date: " + truncatedDate);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
	
	
}
