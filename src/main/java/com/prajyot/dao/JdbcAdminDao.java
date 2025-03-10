package com.prajyot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import com.prajyot.database.DatabaseConnection;
import com.prajyot.interfaces.AdminInterface;

public class JdbcAdminDao implements AdminInterface {

	static final Logger logger = LogManager.getLogger(JdbcAdminDao.class);

	@Override
	public boolean usernameExist(String username) {
		logger.info("JDBC  usernameExist() called");
		String queryToCheck="select count(*) from p_admin where username=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DatabaseConnection.getConnection();	
			pstmt = con.prepareStatement(queryToCheck);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			if(rs.next() && rs.getInt(1)>0) {
				logger.info("AdminDao username Exist");
				return true;
			}else {
				logger.info("AdminDao username not Exist");
				return false;
			}
		} catch (SQLException e) {
			logger.error("Connection error AdminDao usernameExist() "+e);
		}finally {
			try {
				if(rs != null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			} catch (SQLException e) {
				logger.error("Connection error AdminDao usernameExist() "+e);
			}
		}
		return false;
	}

	@Override
	public void addAdmin(String name, String gender, String username, String mobilenumber, String password) {
		logger.info("JDBC addAdmin() called");
		String salt = BCrypt.gensalt();
		String hashedPassword = BCrypt.hashpw(password, salt);
		String query="INSERT INTO p_admin (name, gender, username, mobile_number, password) VALUES (?,?,?,?,?)";
		Connection con = null;
		PreparedStatement stmt = null;	
		try {	
			con = DatabaseConnection.getConnection();	
			stmt = con.prepareStatement(query);
			stmt.setString(1, name);
			stmt.setString(2, gender);
			stmt.setString(3, username);
			stmt.setString(4, mobilenumber);
			stmt.setString(5, hashedPassword);
			stmt.execute();
			logger.info("Admin added : " + name + " " + gender + " " + username + " " + mobilenumber + " " +hashedPassword);
		} catch (SQLException e) {
			logger.error("Connection error AdminDao addAdmin() "+e);
		}finally {
			try {
				if(stmt != null)
					stmt.close();
				if(con != null)
					con.close();
			} catch (SQLException e) {
				logger.error("Connection error AdminDao addAdmin() "+e);
			}
		}
	}

	@Override
	public boolean forgotPassword(String USERNAME, String PASSWORD) {
		logger.info("JDBC forgotPassword() called");
		String salt = BCrypt.gensalt();
		String hashedPassword = BCrypt.hashpw(PASSWORD, salt);
		String queryToCheck="select count(*) from p_admin where username=?";
		Connection con = null;
		PreparedStatement pstmt = null,stmt = null;
		ResultSet rs = null;
		try {
			con = DatabaseConnection.getConnection();			
			pstmt = con.prepareStatement(queryToCheck);
			pstmt.setString(1, USERNAME);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getInt(1) == 0) {
					logger.error("AdminDao forgotPassword username not found");
					con.close();
					return true;
				}else {
					logger.info("AdminDao forgotPassword successful");
					String query="update p_admin set password = ? where username = ? ";
					stmt = con.prepareStatement(query);
					stmt.setString(1, hashedPassword);
					stmt.setString(2, USERNAME);
					stmt.execute();
					return false;
				}
			}
		} catch (SQLException e) {
			logger.error("Connection error AdminDao forgotPassword() "+e);
		}finally {
			try {
				if(rs != null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
				if(stmt != null)
					stmt.close();
				if(con != null)
					con.close();
			} catch (SQLException e) {
				logger.error("Connection error AdminDao forgotPassword() "+e);
			}
		}
		return true;
	}

	@Override
	public boolean login(String USERNAME, String PASSWORD) {
		logger.info("JDBC login() called");
		
		String hashedPassword = null;
		Connection con = null;
		PreparedStatement pstmt = null,stmt = null;
		ResultSet rs = null, rs1 = null;
		try {
			con = DatabaseConnection.getConnection();	
			pstmt = con.prepareStatement("SELECT password FROM p_admin WHERE username = ? ");
        	pstmt.setString(1, USERNAME);
        	rs1 = pstmt.executeQuery();
        	while(rs1.next()) {       	
        		if(BCrypt.checkpw(PASSWORD, rs1.getString(1))) {
        			logger.info("AdminDao login() hashedPassword and password matched");
            		 hashedPassword = rs1.getString(1);          	
            	}
        	}
        	stmt = con.prepareStatement("SELECT * FROM p_admin WHERE username = ? and password = ?");
            stmt.setString(1, USERNAME.trim());
            stmt.setString(2, hashedPassword);
            rs = stmt.executeQuery();
            if (rs.next()) {
            	logger.info("AdminDao login() username and password matched");    
        		return true;
        } else {
        	logger.info("AdminDao login() username and password  not matched");      
        		return false;
        }
		} catch (SQLException e) {
			logger.error("Connection error AdminDao login() "+e);
		}finally {
			try {
				if(rs != null)
					rs.close();
				if(rs1 != null)
					rs1.close();
				if(pstmt != null)
					pstmt.close();
				if(stmt != null)
					stmt.close();
				if(con != null)
					con.close();
			} catch (SQLException e) {
				logger.error("Connection error AdminDao login() "+e);
			}
		}
		return false;
	}

}
