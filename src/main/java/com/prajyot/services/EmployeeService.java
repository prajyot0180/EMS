package com.prajyot.services;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.prajyot.dao.HbEmployeeDao;
import com.prajyot.entity.EmployeeEntity;
import com.prajyot.interfaces.EmployeeInterface;
import com.prajyot.pojo.Employee;



public class EmployeeService {
	
	static final Logger logger = LogManager.getLogger(EmployeeService.class);
	private int noOfRecords ;// allEmployee().size();
	public int getNoOfRecords() {
		return noOfRecords;
	}
	public void setNoOfRecords(int noOfRecords) {
		this.noOfRecords = noOfRecords;
	}

//	EmployeeInterface employeeInterface = new JdbcEmployeeDao();
	EmployeeInterface employeeInterface = new HbEmployeeDao();

	public List<Employee> viewAllEmployee(int offSet){
		logger.info("EmployeeDao viewAllEmployee() called");
		List<Employee> list = allEmployee();
		List<EmployeeEntity> list1 = employeeInterface.viewAllEmployee(offSet);
		setNoOfRecords(list.size());
		List<Employee> emplist= new ArrayList<>();
		for(EmployeeEntity employeeEntity : list1) {
			Employee employee = new Employee();
			employee.setAddress(employeeEntity.getAddress());
			employee.setAdmin(employeeEntity.getAdmin());
			employee.setDepartment(employeeEntity.getDepartment());
			employee.setDob(employeeEntity.getDob());
			employee.seteId(employeeEntity.geteId());
			employee.setEmail(employeeEntity.getEmail());
			employee.setEmployeeId(employeeEntity.getEmployeeId());
			employee.seteName(employeeEntity.geteName());
			employee.setGender(employeeEntity.getGender());
			employee.setJoinDate(employeeEntity.getJoinDate());
			employee.setMobileNumber(employeeEntity.getMobileNumber());
			employee.setPassword(employeeEntity.getPassword());
			employee.setSalary(employeeEntity.getSalary());
			employee.setUsername(employeeEntity.getUsername());
			emplist.add(employee);
		}
		return emplist;
	}
		
	
	public List<Employee> allEmployee() {
		logger.info("EmployeeDao allEmployee() called");
		List<EmployeeEntity> list = employeeInterface.allEmployee();
		List<Employee> empList = new ArrayList<Employee>();
		
		for(EmployeeEntity employeeEntity : list) {
			Employee employee = new Employee();
			employee.setAddress(employeeEntity.getAddress());
			employee.setAdmin(employeeEntity.getAdmin());
			employee.setDepartment(employeeEntity.getDepartment());
			employee.setDob(employee.getDob());
			employee.seteId(employeeEntity.geteId());
			employee.setEmail(employeeEntity.getEmail());
			employee.setEmployeeId(employeeEntity.getEmployeeId());
			employee.seteName(employeeEntity.geteName());
			employee.setGender(employeeEntity.getGender());
			employee.setJoinDate(employeeEntity.getJoinDate());
			employee.setMobileNumber(employeeEntity.getMobileNumber());
			employee.setPassword(employeeEntity.getPassword());
			employee.setSalary(employeeEntity.getSalary());
			employee.setUsername(employeeEntity.getUsername());
			empList.add(employee);
		}
		
		return empList;
	}
	
	public void addEmployee(String employeeId, String ename, String gender, String dob, String email, String mobilenumber, String address, String joiningDate, String department, Float salary, String username, String password, String adminUserName) {
		logger.info("EmployeeDao addEmployee() called");
		employeeInterface.addEmployee(employeeId, ename, gender, dob, email, mobilenumber, address, joiningDate, department, salary, username, password, adminUserName);
	}
	
	
	
	public void updateEmployee(String ename, String gender,String dob, String email, String mobilenumber, String address, String joiningDate, String department, Float salary, String adminUserName,String employeeId){
		logger.info("EmployeeDao updateEmployee() called");
		employeeInterface.updateEmployee(ename, gender, dob, email, mobilenumber, address, joiningDate, department, salary, adminUserName, employeeId);
	}
	
	public boolean emailExist(String email) {
		logger.info("EmployeeDao emailExist() called");
		return employeeInterface.emailExist(email);
	}

	public boolean usernameExist(String username) {
		logger.info("EmployeeDao usernameExist() called");
		return employeeInterface.usernameExist(username);
	}

	public boolean employeeIdExist(String employeeId) {
		logger.info("EmployeeDao employeeIdExist() called");
		return employeeInterface.employeeIdExist(employeeId);
	}

	public boolean deleteEmployee(int eid) {
		logger.info("EmployeeDao deleteEmployee() called");
		return employeeInterface.deleteEmployee(eid);
	}

	public boolean forgotPassword(String USERNAME, String PASSWORD) {
		logger.info("EmployeeDao forgotPassword() called");
		return employeeInterface.forgotPassword(USERNAME, PASSWORD);
	}

	public boolean login(String USERNAME, String PASSWORD) {	
		logger.info("EmployeeDao login() called");
		return employeeInterface.login(USERNAME, PASSWORD);
	}  
}
