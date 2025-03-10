package com.prajyot.database;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class DatabaseConnection {

	static BasicDataSource dataSource = new BasicDataSource();

	static final Logger logger = LogManager.getLogger(DatabaseConnection.class);
	static {
		logger.info("DatabaseConnection static block called");
		dataSource.setUrl("jdbc:oracle:thin:@192.168.0.252:1521:orcl");
        dataSource.setUsername("SME_DEV_V24");
        dataSource.setPassword("password");
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dataSource.setMaxTotal(20); 
        dataSource.setMaxIdle(20);
        dataSource.setMinIdle(5); 
        dataSource.setInitialSize(5); 
	}
	
	public static Connection getConnection() {
		Connection con = null;
		try {
			logger.info("getConnection Called");
			con = dataSource.getConnection();
		} catch (SQLException e) {
			logger.error("Connection error DatabaseConnection "+e);
			e.printStackTrace();
		}
			return con;
	
	}
}
