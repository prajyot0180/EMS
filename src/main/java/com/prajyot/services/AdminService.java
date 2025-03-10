package com.prajyot.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.prajyot.dao.HbAdminDao;
import com.prajyot.interfaces.AdminInterface;

public class AdminService {

	static final Logger logger = LogManager.getLogger(AdminService.class);

//	AdminInterface adminInterface = new JdbcAdminDao();
	AdminInterface adminInterface = new HbAdminDao();
	
	public boolean usernameExist(String username) {
		logger.info("AdminDao usernameExist() called");
		return adminInterface.usernameExist(username);
	}


	public void addAdmin(String name, String gender, String username, String mobilenumber, String password) {
		logger.info("AdminDao addAdmin() called");
		adminInterface.addAdmin(name, gender, username, mobilenumber, password);
	}


	public boolean forgotPassword(String USERNAME, String PASSWORD) {
		logger.info("AdminDao forgotPassword() called");
		return adminInterface.forgotPassword(USERNAME, PASSWORD);
	}

	public boolean login(String USERNAME, String PASSWORD) {
		logger.info("AdminDao login() called");
		return adminInterface.login(USERNAME, PASSWORD);
	}

}
