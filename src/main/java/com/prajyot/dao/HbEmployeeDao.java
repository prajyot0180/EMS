package com.prajyot.dao;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.mindrot.jbcrypt.BCrypt;

import com.prajyot.database.HibernateConnection;
import com.prajyot.interfaces.EmployeeInterface;
import com.prajyot.entity.EmployeeEntity;

public class HbEmployeeDao implements EmployeeInterface{

	static final Logger logger = LogManager.getLogger(HbEmployeeDao.class);

	@Override
	public List<EmployeeEntity> viewAllEmployee(int offSet) {
		logger.info("Hibernate viewAllEmployee() called");
		Session session = null;
		List<EmployeeEntity> list = null;
		try {
			int recoordPerPage = 11;
			session = HibernateConnection.getSessionFactory().openSession(); //factory.openSession();
			Query<EmployeeEntity> query = session.createQuery("from EmployeeEntity order by employeeId", EmployeeEntity.class);
			query.setFirstResult(offSet);
			query.setMaxResults(recoordPerPage);
			list = query.getResultList();
			logger.info(list.size());
			
		}catch(Exception e) {
			logger.info("Catch called viewAllEmployee()" + e);
		}
		finally {
			if(session != null)
				session.close();
		}
		return list;
	}

	@Override
	public List<EmployeeEntity> allEmployee() {
		logger.info("Hibernate allEmployee() called");
		Session session = null;
		List<EmployeeEntity> list = null;
		try {
			session = HibernateConnection.getSessionFactory().openSession(); //factory.openSession();
			Query<EmployeeEntity> query = session.createQuery("from EmployeeEntity order by employeeId", EmployeeEntity.class);	
			list = query.getResultList();
		
		}catch(Exception e) {
			logger.info("Catch called allEmployee()" + e);

		}finally {
			if(session != null)
				session.close();
		}
		return list;
	}

	@Override
	public void addEmployee(String employeeId, String ename, String gender, String dob, String email,
			String mobilenumber, String address, String joiningDate, String department, Float salary, String username,
			String password, String adminUserName) {
		logger.info("Hibernate addEmployee() called");
		Session session = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String salt = BCrypt.gensalt();
	    String hashedPassword = BCrypt.hashpw(password==null?"Pass@111":password , salt);
	    Date dob_date = null;
		Date join_date = null;
		try {
			dob_date = simpleDateFormat.parse(dob);
			join_date = simpleDateFormat.parse(joiningDate);
			java.sql.Date sqldobDate = new  java.sql.Date(dob_date.getTime());
			java.sql.Date sqljoinDate = new  java.sql.Date(join_date.getTime());
			session = HibernateConnection.getSessionFactory().openSession(); //factory.openSession();
			Transaction transaction = session.beginTransaction();
			EmployeeEntity employee = new EmployeeEntity();
			employee.setEmployeeId("CT"+employeeId);
			employee.seteName(ename);
			employee.setGender(gender);
			employee.setEmail(email);
			employee.setMobileNumber(mobilenumber);
			employee.setAddress(address);
			employee.setAdmin(adminUserName);
			employee.setDepartment(department);
			employee.setDob(sqldobDate);
			employee.setJoinDate(sqljoinDate);
			employee.setUsername(username);
			employee.setPassword(hashedPassword);
			employee.setSalary(salary);
			session.save(employee);
			transaction.commit();
		} catch (Exception e) {
			logger.info("Catch called addEmployee()" + e);
		}finally {
			if(session != null)
				session.close();
		}
	}

	@Override
	public void updateEmployee(String ename, String gender, String dob, String email, String mobilenumber,
			String address, String joiningDate, String department, Float salary, String adminUserName,
			String employeeId) {
		logger.info("Hibernate updateEmployee() called");	
		Session session = null;
		EmployeeEntity employee = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
        Date dob_date = null;
        Date join_date = null; 
		try {
			dob_date =  simpleDateFormat.parse(dob);
			join_date =  simpleDateFormat.parse(joiningDate);
			java.sql.Date sqldobDate = new  java.sql.Date(dob_date.getTime());
			java.sql.Date sqljoinDate = new  java.sql.Date(join_date.getTime());
			session = HibernateConnection.getSessionFactory().openSession(); //factory.openSession();
			Transaction transaction = session.beginTransaction();
			Query<EmployeeEntity> query = session.createQuery("from EmployeeEntity where employeeId = :employeeId", EmployeeEntity.class);
			query.setParameter("employeeId", employeeId);
			employee = query.uniqueResult();
			employee.seteName(ename);
			employee.setGender(gender);
			employee.setEmail(email);
			employee.setMobileNumber(mobilenumber);
			employee.setAddress(address);
			employee.setAdmin(adminUserName);
			employee.setDepartment(department);
			employee.setDob(sqldobDate);
			employee.setJoinDate(sqljoinDate);
			employee.setSalary(salary);
			session.update(employee); 
			transaction.commit();
		}catch (Exception e) {
			logger.info("Catch called updateEmployee()" + e);
		}finally {
			if(session != null)
				session.close();
		}
	}

	@Override
	public boolean emailExist(String email) {
		logger.info("Hibernate emailExist() called");
		Session session = null;
		EmployeeEntity employee = null;
		try {
			session = HibernateConnection.getSessionFactory().openSession(); //factory.openSession();
			Transaction transaction = session.beginTransaction();
			Query<EmployeeEntity> query = session.createQuery("from EmployeeEntity where email = :email", EmployeeEntity.class);
			query.setParameter("email", email);
			employee = query.uniqueResult();
			transaction.commit();
			if(employee != null ) {
				logger.info("EmployeeDao email Exist");
				return true;
			}
		}catch (Exception e) {
			logger.info("Catch called emailExist()" + e);
		}finally {
			if(session != null)
				session.close();
		}
		return false;
	}

	@Override
	public boolean usernameExist(String username) {
		logger.info("Hibernate usernameExist() called");	
		Session session = null;
		EmployeeEntity employee = null;
		try {
			session = HibernateConnection.getSessionFactory().openSession(); //factory.openSession();
			Transaction transaction = session.beginTransaction();
			Query<EmployeeEntity> query = session.createQuery("from EmployeeEntity where username = :username", EmployeeEntity.class);
			query.setParameter("username", username);
			employee = query.uniqueResult();
			transaction.commit();
			if(employee != null ) {
				logger.info("EmployeeDao username Exist");
				return true;
			}
		}catch (Exception e) {
			logger.info("Catch called usernameExist()" + e);
		}finally {
			if(session != null)
				session.close();
		}
		return false;
	}

	@Override
	public boolean employeeIdExist(String employeeId) {
		logger.info("Hibernate employeeIdExist() called");
		Session session = null;
		EmployeeEntity employee = null;
		try {
			session = HibernateConnection.getSessionFactory().openSession(); //factory.openSession();
			Transaction transaction = session.beginTransaction();
			Query<EmployeeEntity> query = session.createQuery("from EmployeeEntity where employeeId = :employeeId", EmployeeEntity.class);
			query.setParameter("employeeId", employeeId);
			employee = query.uniqueResult();
			transaction.commit();
			if(employee != null ) {
				logger.info("EmployeeDao username Exist");
				return true;
			}
		}catch (Exception e) {
			logger.info("Catch called employeeIdExist()" + e);
		}finally {
			if(session != null)
				session.close();
		}
		return false;
	}

	@Override
	public boolean deleteEmployee(int eid) {
		logger.info("Hibernate deleteEmployee() called");
		Session session = null;
		EmployeeEntity employee = null;
		try {
			session = HibernateConnection.getSessionFactory().openSession(); //factory.openSession();
			Transaction transaction = session.beginTransaction();
			Query<EmployeeEntity> query = session.createQuery("from EmployeeEntity where eId = :eid", EmployeeEntity.class);
			query.setParameter("eid", eid);
			employee = query.uniqueResult();
			session.delete(employee);
			transaction.commit();
			return true;
		}catch (Exception e) {
			logger.info("Catch called deleteEmployee()" + e);
		}finally {
			if(session != null)
				session.close();
		}
		return false;
	}

	@Override
	public boolean forgotPassword(String USERNAME, String PASSWORD) {
		logger.info("Hibernate forgotPassword() called");
		Session session = null;
		EmployeeEntity employee = null;
		try {
			session = HibernateConnection.getSessionFactory().openSession(); //factory.openSession();
			Transaction transaction = session.beginTransaction();
			Query<EmployeeEntity> query = session.createQuery("from EmployeeEntity where username = :username", EmployeeEntity.class);
			query.setParameter("username", USERNAME);
			employee = query.uniqueResult();
			if(employee == null) {
				return true;
			}
			employee.setPassword(PASSWORD);
			session.saveOrUpdate(employee);
			transaction.commit();
			return true;
		}catch (Exception e) {
			logger.info("Catch called forgotPassword()" + e);
		}finally {
			if(session != null)
				session.close();
		}
		return false;
	}

	@Override
	public boolean login(String USERNAME, String PASSWORD) {
		logger.info("Hibernate login() called");
		Session session = null;
		EmployeeEntity employee = null;
		try {
			session = HibernateConnection.getSessionFactory().openSession(); //factory.openSession();
			Query<EmployeeEntity> query = session.createQuery("from EmployeeEntity where username = :username ", EmployeeEntity.class);
			query.setParameter("username", USERNAME);
			employee = query.uniqueResult();			
			if(BCrypt.checkpw(PASSWORD, employee.getPassword())) {
				return true;
			}
		}catch (Exception e) {
			logger.info("Catch called login()" + e);
		}finally {
			if(session != null)
				session.close();
		}
		return false;
	}

	
}
