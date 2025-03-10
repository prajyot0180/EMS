package com.prajyot.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.mindrot.jbcrypt.BCrypt;

import com.prajyot.database.HibernateConnection;
import com.prajyot.interfaces.AdminInterface;
import com.prajyot.entity.AdminEntity;

public class HbAdminDao implements AdminInterface {
	
	static final Logger logger = LogManager.getLogger(HbAdminDao.class);

	@Override
	public boolean usernameExist(String username) {
		logger.info("Hibernate  usernameExist() called");
		Session session = null;
		AdminEntity admin = null;
		try {
			session = HibernateConnection.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			Query<AdminEntity> query = session.createQuery("from AdminEntity where userName = :username", AdminEntity.class);
			query.setParameter("username", username);
			admin = query.uniqueResult();
			transaction.commit();
			if(admin != null ) {
				logger.info("AdminDao username Exist");
				return true;
			}
		}catch(Exception e) {
			logger.info("Catch called usernameExist()" + e);
		} finally {
				if(session != null)
					session.close();
		}
		return false;
	}

	@Override
	public void addAdmin(String name, String gender, String username, String mobilenumber, String password) {
		logger.info("Hibernate addAdmin() called");
		Session session = null;
		AdminEntity admin = new AdminEntity();
		String salt = BCrypt.gensalt();
		String hashedPassword = BCrypt.hashpw(password, salt);
		try {
			session = HibernateConnection.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			admin.setName(name);
			admin.setGender(gender);
			admin.setMobileNumber(mobilenumber);
			admin.setUserName(username);
			admin.setUpassword(hashedPassword);
			session.save(admin);
			transaction.commit();
		}catch(Exception e) {
			logger.info("Catch called usernameExist()" + e);
		} finally {
				if(session != null)
					session.close();
		}
	}

	@Override
	public boolean forgotPassword(String USERNAME, String PASSWORD) {
		logger.info("Hibernate forgotPassword() called");
		Session session = null;
		AdminEntity admin = null;
		String salt = BCrypt.gensalt();
		String hashedPassword = BCrypt.hashpw(PASSWORD, salt);
		try {
			session = HibernateConnection.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			Query< AdminEntity> query = session.createQuery("from AdminEntity where userName = :username", AdminEntity.class);
			query.setParameter("username", USERNAME);
			admin = query.uniqueResult();
			if(admin == null ) {
				return false;
			}
			admin.setUpassword(hashedPassword);
			session.saveOrUpdate(admin);
			transaction.commit();
			return true;
		}catch(Exception e) {
			logger.info("Catch called usernameExist()" + e);
		} finally {
				if(session != null)
					session.close();
		}
		return false;
	}

	@Override
	public boolean login(String USERNAME, String PASSWORD) {
		logger.info("Hibernate login() called");
		Session session = null;
		AdminEntity admin = null;
		try {
			session = HibernateConnection.getSessionFactory().openSession();
			Query<AdminEntity> query = session.createQuery("from AdminEntity where userName = :username ", AdminEntity.class);
			query.setParameter("username", USERNAME);
			admin = query.uniqueResult();
			if(BCrypt.checkpw(PASSWORD, admin.getUpassword())) {
				return true;
        	}

		}catch(Exception e) {
			logger.info("Catch called usernameExist()" + e);
		} finally {
				if(session != null)
					session.close();
		}
		return false;
	}

}
