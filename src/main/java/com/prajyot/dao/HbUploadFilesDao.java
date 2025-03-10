package com.prajyot.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.mindrot.jbcrypt.BCrypt;

import com.prajyot.database.HibernateConnection;
import com.prajyot.interfaces.UploadFilesInterface;
import com.prajyot.entity.EmployeeEntity;
import com.prajyot.entity.UploadFilesEntity;
import com.prajyot.services.EmployeeService;
import com.prajyot.validate.Validator;

import jakarta.servlet.http.Part;

public class HbUploadFilesDao implements UploadFilesInterface {

	
	static final Logger logger = LogManager.getLogger(JdbcUploadFilesDao.class);
	EmployeeService dao = new EmployeeService();
	
	@Override
	public boolean uploadFile(Part filePart, String username2) {
		logger.info("HIbernate uploadFile() called");
		String line;
		int addData = 0;
		int notAddData = 0;
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
		LocalDate uploadDate = LocalDate.now();
		boolean isFirstLine = true;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String salt = BCrypt.gensalt();
        Date dob_date = null;
        Date join_date = null;
        InputStream fileContent = null;
		BufferedReader reader = null;
		Validator validate = new Validator();
		List<EmployeeEntity> notUploadList = new ArrayList<>();
		Session session = null;
//		UploadFiles uploadFiles1 = new UploadFiles();
		UploadFilesEntity uploadFiles = new UploadFilesEntity();
		
		
		try {
			session = HibernateConnection.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			Query<UploadFilesEntity> query = session.createQuery("from UploadFilesEntity where fileName = :filename", UploadFilesEntity.class);
			query.setParameter("filename", fileName);
			List<UploadFilesEntity> result = query.getResultList();  
			if(!result.isEmpty()) {
				session.delete(result.get(0));
			}			fileContent = filePart.getInputStream();
			reader = new  BufferedReader(new InputStreamReader(fileContent));	
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
							String hashedPassword = BCrypt.hashpw(password, salt);
							
				        	 dob_date =  simpleDateFormat.parse(dob);
				        	 join_date =  simpleDateFormat.parse(joiningDate);
					         java.sql.Date sqldobDate = null;
					       	 java.sql.Date sqljoinDate = null;
				       		sqldobDate = new  java.sql.Date(dob_date.getTime());
				       		sqljoinDate = new  java.sql.Date(join_date.getTime());
					     	
				       		if(dao.employeeIdExist(("CT"+employeeId))) {
								logger.info("uploadFile() Employee id already exist");
								notAddData++;
								ifNotInsert(ename, email, gender, mobilenumber, address, adminUserName, username, password, employeeId, department, join_date, dob_date, salary, notUploadList);
								continue;
							}
				       		if(ename.isEmpty() || gender.isEmpty() || email.isEmpty() || mobilenumber.isEmpty() || address.isEmpty() || employeeId.isEmpty() || department.isEmpty() || joiningDate.isEmpty() || dob.isEmpty() ) {
								logger.info("uploadFile() Something is empty!!");
								notAddData++;
								ifNotInsert(ename, email, gender, mobilenumber, address, adminUserName, username, password, employeeId, department, join_date, dob_date, salary, notUploadList);
								continue;
							}
							if(!validate.nameValidator(ename.trim())){
								logger.info("Name Invalid");
								notAddData++;
								ifNotInsert(ename, email, gender, mobilenumber, address, adminUserName, username, password, employeeId, department, join_date, dob_date, salary, notUploadList);
								continue;
							}
							if(!validate.mailValidator(email)) {
								notAddData++;
								ifNotInsert(ename, email, gender, mobilenumber, address, adminUserName, username, password, employeeId, department, join_date, dob_date, salary, notUploadList);
								continue;
							}
							if(!validate.genderValidator(gender)) {
								notAddData++;
								ifNotInsert(ename, email, gender, mobilenumber, address, adminUserName, username, password, employeeId, department, join_date, dob_date, salary, notUploadList);
								continue;
							}
							if(!validate.employeeIdValidator(employeeId)) {
								notAddData++;
								ifNotInsert(ename, email, gender, mobilenumber, address, adminUserName, username, password, employeeId, department, join_date, dob_date, salary, notUploadList);
								continue;
							}
							if(!validate.mobileValidator(mobilenumber)) {
								notAddData++;
								ifNotInsert(ename, email, gender, mobilenumber, address, adminUserName, username, password, employeeId, department, join_date, dob_date, salary, notUploadList);
								continue;
							}
							if(!validate.salaryValidator(salary)) {
								notAddData++;
								ifNotInsert(ename, email, gender, mobilenumber, address, adminUserName, username, password, employeeId, department, join_date, dob_date, salary, notUploadList);
								continue;
							}
							if(!validate.passwordValidator(password)) {
								notAddData++;
								ifNotInsert(ename, email, gender, mobilenumber, address, adminUserName, username, password, employeeId, department, join_date, dob_date, salary, notUploadList);
								continue;
							}	
							EmployeeEntity employee = new EmployeeEntity();
							employee.seteName(ename);
							employee.setGender(gender);
							employee.setEmail(email);
							employee.setMobileNumber(mobilenumber);
							employee.setAddress(address);
							employee.setAdmin(adminUserName);
							employee.setUsername(username);
							employee.setPassword(hashedPassword);
							employee.setEmployeeId("CT"+employeeId);
							employee.setDepartment(department);
							employee.setDob(sqldobDate);
							employee.setJoinDate(sqljoinDate);
							employee.setSalary(Float.parseFloat(salary));
							
							session.save(employee);
							addData++;
						}else {
							logger.error("Data Missing ");		
							return false;
						}	
			}
			uploadFiles.setUploadDate(java.sql.Date.valueOf(uploadDate));
			uploadFiles.setFileName(fileName);
			uploadFiles.setAdmin(username2);
			uploadFiles.setAddData(addData);
			uploadFiles.setNotAddData(notAddData);
			session.save(uploadFiles);
			
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
			transaction.commit();
			return true;
		} catch (Exception e) {
			logger.error("Connection error HbUploadFilesDao uploadFile() "+e);
			e.printStackTrace();
		}finally {
			if(session != null)
				session.close();
		}
		return false;
	}

	@Override
	public void ifNotInsert(String ename, String email, String gender, String mobilenumber, String address,
			String adminUserName, String username, String password, String employeeId, String department,
			Date join_date, Date dob_date, String salary, List<EmployeeEntity> notUploadList) {
		logger.info("HIbernate uploadFile() called");
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
		logger.info("HIbernate uploadFile() called");
		Session session = null;
		List<UploadFilesEntity> list = null;
		try {
			session = HibernateConnection.getSessionFactory().openSession();
			Query<UploadFilesEntity> query = session.createQuery("from UploadFilesEntity ",UploadFilesEntity.class);
			list = query.getResultList();
			return list;
		} catch (Exception e) {
			logger.error("Connection error UploadFileDao allUploadFiles() "+e);
		}finally{
			if(session != null)
				session.close();
		}
		return list;
	}

}
