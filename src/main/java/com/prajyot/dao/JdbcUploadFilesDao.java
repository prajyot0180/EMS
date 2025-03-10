package com.prajyot.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import com.prajyot.database.DatabaseConnection;
import com.prajyot.interfaces.UploadFilesInterface;
import com.prajyot.entity.EmployeeEntity;
import com.prajyot.entity.UploadFilesEntity;
import com.prajyot.services.EmployeeService;
import com.prajyot.validate.Validator;

import jakarta.servlet.http.Part;

public class JdbcUploadFilesDao implements UploadFilesInterface {
	
	static final Logger logger = LogManager.getLogger(JdbcUploadFilesDao.class);
	EmployeeService dao = new EmployeeService();

	@Override
	public boolean uploadFile(Part filePart, String username2) {
		logger.info("JDBC uploadFile() called");
		String query = "insert into p_employee(employee_name,gender,email,mobile_number,address,admin,username,password,employee_id,department,join_date,dob,salary) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String query1 ="insert into p_uploaddata(upload_date,filename,admin,added,not_added) values (?, ?, ?, ?, ?)";
		String line;
		int addData = 0;
		int notAddData = 0;
		String remark = null;
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
		LocalDate uploadDate = LocalDate.now();
		boolean isFirstLine = true;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String salt = BCrypt.gensalt();
        Date dob_date = null;
        Date join_date = null;
		Connection con = null;
		PreparedStatement stmt = null,pstmt = null,stmt1 = null, stmt2 = null;
		ResultSet rs = null,rs1 = null;
		InputStream fileContent = null;
		BufferedReader reader = null;
		Validator validate = new Validator();
		List<EmployeeEntity> notUploadList = new ArrayList<>();
		try {
			con = DatabaseConnection.getConnection();
			
			stmt1 = con.prepareStatement("select count(*) from p_uploaddata where filename = ?");
			stmt1.setString(1, fileName);
			rs1 = stmt1.executeQuery();
			if(rs1.next() && rs1.getInt(1) > 0 ) {
				 stmt2 = con.prepareStatement("delete from p_uploaddata where filename = ?");
				 stmt2.setString(1, fileName);
				 stmt2.executeQuery();
				 
			}
			fileContent = filePart.getInputStream();
			reader = new  BufferedReader(new InputStreamReader(fileContent));	
			
			stmt = con.prepareStatement(query);
			while((line = reader.readLine()) != null) {	
				
				String[] data = line.split(",");	
				if(data.length >= 13) {	
					logger.info("data correct");		
					if (isFirstLine) { 
				        isFirstLine = false;
				        continue;
				    }	
					String ename = data[0].trim();
					String gender = data[1].trim();
					String email = data[2].trim();
					String mobilenumber = data[3].trim();
					String address = data[4].trim();
					String adminUserName = data[5].trim();
					String username = data[6].trim();
					String password = data[7].trim();
					String employeeId = data[8].trim();
					String department = data[9].trim();
					String joiningDate = data[10];
					String dob = data[11];
					String salary = data[12]; 
					
		        	 dob_date =  simpleDateFormat.parse(dob);
		        	 join_date =  simpleDateFormat.parse(joiningDate);
			         java.sql.Date sqldobDate = null;
			       	 java.sql.Date sqljoinDate = null;
		       		sqldobDate = new  java.sql.Date(dob_date.getTime());
		       		sqljoinDate = new  java.sql.Date(join_date.getTime());
			     		
					if(dao.employeeIdExist(("CT"+employeeId))) {
						logger.info("uploadFile() Employee id already exist");
						notAddData++;
						remark = "Employee ID already exist";
						ifNotInsert(ename, email, gender, mobilenumber, address, adminUserName, username, password, employeeId, department, join_date, dob_date, salary, notUploadList);
						continue;
					}
					
					if(ename.isEmpty() || gender.isEmpty() || email.isEmpty() || mobilenumber.isEmpty() || address.isEmpty() || employeeId.isEmpty() || department.isEmpty() || joiningDate.isEmpty() || dob.isEmpty() ) {
						logger.info("uploadFile() Something is empty!!");
						notAddData++;
						remark = "Something is empty";
						ifNotInsert(ename, email, gender, mobilenumber, address, adminUserName, username, password, employeeId, department, join_date, dob_date, salary, notUploadList);
						continue;
					}
					if(!validate.nameValidator(ename.trim())){
						logger.info("Name Invalid");
						notAddData++;
						remark = "Enter valid name";
						ifNotInsert(ename, email, gender, mobilenumber, address, adminUserName, username, password, employeeId, department, join_date, dob_date, salary, notUploadList);
						continue;
					}
					if(!validate.mailValidator(email)) {
						notAddData++;
						remark = "Enter valid mail";
						ifNotInsert(ename, email, gender, mobilenumber, address, adminUserName, username, password, employeeId, department, join_date, dob_date, salary, notUploadList);
						continue;
					}
					if(!validate.genderValidator(gender)) {
						notAddData++;
						remark = "Enter valid gender";
						ifNotInsert(ename, email, gender, mobilenumber, address, adminUserName, username, password, employeeId, department, join_date, dob_date, salary, notUploadList);
						continue;
					}
					if(!validate.employeeIdValidator(employeeId)) {
						notAddData++;
						remark = "Enter valid ID";
						ifNotInsert(ename, email, gender, mobilenumber, address, adminUserName, username, password, employeeId, department, join_date, dob_date, salary, notUploadList);
						continue;
					}
					if(!validate.mobileValidator(mobilenumber)) {
						notAddData++;
						remark = "Enter valid Mobile number";
						ifNotInsert(ename, email, gender, mobilenumber, address, adminUserName, username, password, employeeId, department, join_date, dob_date, salary, notUploadList);
						continue;
					}
					if(!validate.salaryValidator(salary)) {
						notAddData++;
						remark = "Enter valid salary";
						ifNotInsert(ename, email, gender, mobilenumber, address, adminUserName, username, password, employeeId, department, join_date, dob_date, salary, notUploadList);
						continue;
					}
					if(!validate.passwordValidator(password)) {
						notAddData++;
						remark = "Enter valid Password";
						ifNotInsert(ename, email, gender, mobilenumber, address, adminUserName, username, password, employeeId, department, join_date, dob_date, salary, notUploadList);
						continue;
					}	   
				       String hashedPassword = BCrypt.hashpw(password, salt);
				       logger.info("uploadFile() hashed pass "+gender+ " " +email+ " "+ address);
				       	stmt.setString(1, ename);
						stmt.setString(2, gender);
						stmt.setString(3, email);
						stmt.setString(4, mobilenumber);
						stmt.setString(5, address);
						stmt.setString(6, adminUserName);
						stmt.setString(7, username);
						stmt.setString(8, hashedPassword);
						stmt.setString(9, ("CT"+employeeId));
						stmt.setString(10, department);
						stmt.setDate(11, sqljoinDate);
						stmt.setDate(12, sqldobDate);      				
						stmt.setFloat(13, Float.parseFloat(salary));
						stmt.execute();
						addData++;
				}else {
					logger.error("Data Missing ");		
					return false;
				}			
			}
	
			pstmt = con.prepareStatement(query1);
			pstmt.setDate(1, java.sql.Date.valueOf(uploadDate));
			pstmt.setString(2, fileName);
			pstmt.setString(3, username2);
			pstmt.setInt(4, addData);
			pstmt.setInt(5, notAddData);
			pstmt.execute();
			try {
				logger.info("In rejected file creation");
				String directoryPath ="/home/credentek/Desktop/work/RejectedData/";
				File directory = new File(directoryPath);
				if(!directory.exists()) {
					directory.mkdirs();
				}
				try (FileWriter fileWriter = new FileWriter(directoryPath+fileName)) {
					
					fileWriter.write("employee_name,gender,email,mobile_number,address,admin,username,password,employee_id,department,join_date,dob,salary");
					fileWriter.write(System.lineSeparator());
					for(EmployeeEntity emp : notUploadList) {
						fileWriter.write(emp.geteName() + "," + emp.getGender() + "," + emp.getEmail() + "," + emp.getMobileNumber()+ "," + emp.getAddress() + "," + emp.getAdmin() + "," + emp.getUsername() + "," + emp.getPassword() + ","  + emp.getEmployeeId() + "," + emp.getDepartment()+ "," + simpleDateFormat.format(emp.getJoinDate()) + "," +simpleDateFormat.format(emp.getDob()) + "," + emp.getSalary( ));
						fileWriter.write(System.lineSeparator());	
					}
				}
			} catch (IOException e) {
				logger.error("Connection error rejected file creation "+e);
			}
			return true;
		} catch (Exception e) {
			logger.error("Connection error EmployeeDao uploadFile() "+e);
			e.printStackTrace();
		}finally {
			try {
				
				if(rs != null)
					rs.close();
				if(stmt != null)
					stmt.close();
				if(con != null)
					con.close();
				if(reader != null)
					reader.close();
				if(fileContent != null)
					fileContent.close();
			
			} catch (SQLException | IOException e) {
				logger.error("Connection error EmployeeDao emailExist() "+e);
			}
		}
		
		return false;
	}

	@Override
	public void ifNotInsert(String ename, String email, String gender, String mobilenumber, String address,
			String adminUserName, String username, String password, String employeeId, String department,
			Date join_date, Date dob_date, String salary, List<EmployeeEntity> notUploadList) {
		logger.info("JDBC in ifNotInsert");
		  
		EmployeeEntity emp = new EmployeeEntity();
		emp.seteName(ename);
		emp.setGender(gender);
		emp.setEmail(email);
		emp.setMobileNumber(mobilenumber);
		emp.setAddress(address);
		emp.setAdmin(adminUserName);
		emp.setUsername(username);
		emp.setPassword(password);
		emp.setEmployeeId(employeeId);
		emp.setDepartment(department);
		emp.setJoinDate( join_date);
		emp.setDob(dob_date);
		try {
			emp.setSalary(Float.parseFloat(salary));
		} catch (Exception e) {
			logger.info("salary exception " +e);
			emp.setSalary(Float.parseFloat("0"));
		}
		notUploadList.add(emp);
	}

	@Override
	public List<UploadFilesEntity> allUploadFiles() {
		logger.info("JDBC allUploadFiles() called");
		List<UploadFilesEntity> list = new ArrayList<>();
		String query="SELECT * FROM p_uploaddata ORDER BY id";
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = DatabaseConnection.getConnection();	
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();
			while(rs.next()) {
				UploadFilesEntity uploadFiles = new UploadFilesEntity(
					    rs.getInt("id"),
					    rs.getString("filename"),  
					    rs.getDate("upload_date"),
					    rs.getInt("added"),
					    rs.getInt("not_added"),
					    rs.getString("admin")
					);
				list.add(uploadFiles);
			}
			return list;
		} catch (SQLException e) {
			logger.error("Connection error UploadFileDao allUploadFiles() "+e);
		}finally{
			try {
				if(rs != null)
					rs.close();
				if(stmt != null)
					stmt.close();
				if(con != null)
					con.close();
			} catch (SQLException e) {
				logger.error("Connection error UploadFileDao allUploadFiles() "+e);
			}
		}
		return list;
	}

}
