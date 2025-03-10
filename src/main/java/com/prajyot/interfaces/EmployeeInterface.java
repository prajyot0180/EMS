package com.prajyot.interfaces;

import java.util.List;

import com.prajyot.entity.EmployeeEntity;
import com.prajyot.pojo.Employee;

public interface EmployeeInterface {
	public List<EmployeeEntity> viewAllEmployee(int offSet);
	public List<EmployeeEntity> allEmployee();
	public void addEmployee(String employeeId, String ename, String gender, String dob, String email, String mobilenumber, String address, String joiningDate, String department, Float salary, String username, String password, String adminUserName);
	public void updateEmployee(String ename, String gender,String dob, String email, String mobilenumber, String address, String joiningDate, String department, Float salary, String adminUserName,String employeeId);
	public boolean emailExist(String email);
	public boolean usernameExist(String username);
	public boolean employeeIdExist(String employeeId);
	public boolean deleteEmployee(int eid);
	public boolean forgotPassword(String USERNAME, String PASSWORD);
	public boolean login(String USERNAME, String PASSWORD);
}
