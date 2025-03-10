package com.prajyot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import com.prajyot.database.DatabaseConnection;
import com.prajyot.interfaces.EmployeeInterface;
import com.prajyot.entity.EmployeeEntity;

public class JdbcEmployeeDao implements EmployeeInterface {
	
	

	static final Logger logger = LogManager.getLogger(JdbcEmployeeDao.class);

	@Override
	public List<EmployeeEntity> viewAllEmployee(int offSet) {
		logger.info("JDBC viewAllEmployee() called");

		List<EmployeeEntity> list = new ArrayList<>();
		int lend = offSet + 11;
		String query="SELECT * FROM (SELECT p_employee.*, ROWNUM AS rnum FROM p_employee ORDER BY id) WHERE rnum >" + offSet + " AND rnum <= "+ lend;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = DatabaseConnection.getConnection();	
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();
			while(rs.next()) {
				EmployeeEntity employee = new EmployeeEntity(
					    rs.getInt("id"),
					    rs.getString("employee_id"),  
					    rs.getString("employee_name"),
					    rs.getString("gender")==null?"":rs.getString("gender"),
					    rs.getString("email")==null?"": rs.getString("email"),
					    rs.getString("mobile_number")==null?"":rs.getString("mobile_number"),
					    rs.getString("address")==null?"":rs.getString("address"),
					    rs.getFloat("salary"),
					    rs.getString("department")==null?"":rs.getString("department"),
					    rs.getDate("join_date"),
					    rs.getDate("dob"),
					    rs.getString("admin")==null?"":rs.getString("admin"),
					    rs.getString("username"),
					    rs.getString("password")
					);
				list.add(employee);
			}	
			
			 return list;
		} catch (SQLException e) {
			logger.error("Connection error employeeDao addEmployee() "+e);
		}finally {
			try {
				if(rs != null)
					rs.close();
				if(stmt != null)
					stmt.close();
				if(con != null)
					con.close();
			} catch (SQLException e) {
				logger.error("Connection error employeeDao 11Employee() "+e);
			}
		}
		return list;
	}

	@Override
	public List<EmployeeEntity> allEmployee() {
		logger.info("JDBC allEmployee() called");
		List<EmployeeEntity> list = new ArrayList<>();
		String query="SELECT * FROM p_employee ORDER BY employee_id";
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = DatabaseConnection.getConnection();	
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();
			while(rs.next()) {
				EmployeeEntity employee = new EmployeeEntity(
					    rs.getInt("id"),
					    rs.getString("employee_id"),  
					    rs.getString("employee_name"),
					    rs.getString("gender"),
					    rs.getString("email"),
					    rs.getString("mobile_number"),
					    rs.getString("address"),
					    rs.getFloat("salary"),
					    rs.getString("department"),
					    rs.getDate("join_date"),
					    rs.getDate("dob"),
					    rs.getString("admin"),
					    rs.getString("username"),
					    rs.getString("password")
					);
				list.add(employee);
			}
			return list;
		} catch (SQLException e) {
			logger.error("Connection error employeeDao allEmployee() "+e);
		}finally{
			try {
				if(rs != null)
					rs.close();
				if(stmt != null)
					stmt.close();
				if(con != null)
					con.close();
			} catch (SQLException e) {
				logger.error("Connection error employeeDao allEmployee() "+e);
			}
		}
		return list;
	}

	@Override
	public void addEmployee(String employeeId, String ename, String gender, String dob, String email,
			String mobilenumber, String address, String joiningDate, String department, Float salary, String username,
			String password, String adminUserName) {
		logger.info("JDBC addEmployee() called");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String salt = BCrypt.gensalt();
	    String hashedPassword = BCrypt.hashpw(password==null?"Pass@111":password , salt);
	    String query = "insert into p_employee(employee_name,gender,email,mobile_number,address,admin,username,password,employee_id,department,join_date,dob,salary) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	    Date dob_date = null;
	    Date join_date = null;
	    Connection con = null;
		PreparedStatement stmt = null;
        try {
			dob_date =  simpleDateFormat.parse(dob);
			join_date =  simpleDateFormat.parse(joiningDate);
			java.sql.Date sqldobDate = new  java.sql.Date(dob_date.getTime());
			java.sql.Date sqljoinDate = new  java.sql.Date(join_date.getTime());
  			con = DatabaseConnection.getConnection();
			stmt = con.prepareStatement(query);
			stmt.setString(1, ename);
			stmt.setString(2, gender);
			stmt.setString(3, email);
			stmt.setString(4, mobilenumber);
			stmt.setString(5, address);
			stmt.setString(6, adminUserName);
			stmt.setString(7, username);
			stmt.setString(8, hashedPassword);
			stmt.setString(9,("CT"+employeeId));
			stmt.setString(10, department);
			stmt.setDate(11, sqljoinDate);
			stmt.setDate(12, sqldobDate);
			stmt.setFloat(13, salary);
			stmt.execute();
			logger.info("EmployeeDao add employee :" +employeeId+" "+ename+" "+gender+" "+email+" "+mobilenumber+" "+address+" "+username+" "+department+" "+sqljoinDate+" "+sqldobDate+" "+salary);
		} catch (ParseException | SQLException e) {
			logger.error("Connection error employeeDao addEmployee() "+e);
		}finally {
			try {
				if(stmt != null)
					stmt.close();
				if(con != null)
					con.close();
			} catch (SQLException e) {
				logger.error("Connection error employeeDao allEmployee() "+e);
			}
		}		
		
	}

	@Override
	public void updateEmployee(String ename, String gender, String dob, String email, String mobilenumber,
			String address, String joiningDate, String department, Float salary, String adminUserName,
			String employeeId) {
		logger.info("JDBC updateEmployee() called");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		String queryToCheck="update p_employee set employee_name = ?, gender = ?, dob = ?, email = ?, mobile_number = ?, address = ?, join_date = ?, department = ?, salary = ?, admin = ? where employee_id = ? ";
        Date dob_date = null;
        Date join_date = null;
        Connection con = null;
		PreparedStatement stmt = null;
        	 try {
				dob_date =  simpleDateFormat.parse(dob);
				join_date =  simpleDateFormat.parse(joiningDate);
				java.sql.Date sqldobDate = new  java.sql.Date(dob_date.getTime());
				java.sql.Date sqljoinDate = new  java.sql.Date(join_date.getTime());
				con = DatabaseConnection.getConnection();
				stmt= con.prepareStatement(queryToCheck);
				stmt.setString(1, ename);
				stmt.setString(2, gender);
				stmt.setDate(3, sqldobDate);
				stmt.setString(4, email);
				stmt.setString(5, mobilenumber);
				stmt.setString(6, address);
				stmt.setDate(7, sqljoinDate);
				stmt.setString(8, department);
				stmt.setFloat(9, salary);
				stmt.setString(10, adminUserName);
				stmt.setString(11, employeeId);	
				stmt.execute();
				logger.info("EmployeeDao add employee :" +employeeId+" "+ename+" "+gender+" "+email+" "+mobilenumber+" "+address+" "+department+" "+sqljoinDate+" "+sqldobDate+" "+salary);
			} catch (ParseException | SQLException e) {
				logger.error("Connection error employeeDao updateEmployee() "+e);
			}finally {
				try {
					if(stmt != null)
						stmt.close();
					if(con != null)
						con.close();
				} catch (SQLException e) {
					logger.error("Connection error employeeDao allEmployee() "+e);
				}
			}		
		
	}

	@Override
	public boolean emailExist(String email) {
		logger.info("JDBC emailExist() called");
		String queryToCheck="select count(*) from p_employee where email = ? ";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DatabaseConnection.getConnection();
			pstmt = con.prepareStatement(queryToCheck);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if(rs.next() && rs.getInt(1) > 0) {
				logger.info("EmployeeDao email Exist");
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			logger.error("Connection error EmployeeDao emailExist() "+e);
		}finally {
			try {
				if(rs != null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			} catch (SQLException e) {
				logger.error("Connection error EmployeeDao emailExist() "+e);
			}
		}
		return false;
		
	}

	@Override
	public boolean usernameExist(String username) {
		logger.info("JDBC usernameExist() called");
		String queryToCheckUsername="select count(*) from p_employee where username = ? ";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DatabaseConnection.getConnection();
			pstmt = con.prepareStatement(queryToCheckUsername);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			if(rs.next() && rs.getInt(1) > 0) {
				logger.info("EmployeeDao username Exist ");
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			logger.error("Connection error EmployeeDao usernameExist() "+e);
		}finally {
			try {
				if(rs != null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			} catch (SQLException e) {
				logger.error("Connection error EmployeeDao usernameExist() "+e);
			}
		}
		return false;
	}

	@Override
	public boolean employeeIdExist(String employeeId) {
		logger.info("JDBC employeeIdExist() called");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {	
			con = DatabaseConnection.getConnection();
			String queryToCheckempId="select count(*) from p_employee where employee_id = ? ";
			pstmt = con.prepareStatement(queryToCheckempId);
			pstmt.setString(1, employeeId);
			rs = pstmt.executeQuery();
			if(rs.next() && rs.getInt(1) > 0) {
				logger.info("EmployeeDao id Exist ");
				return true;
			}else {
				logger.info("EmployeeDao id not Exist ");
				return false;
			}
		} catch (SQLException e) {
			logger.error("Connection error EmployeeDao employeeIdExist() "+e);
		}finally {
			try {
				if(rs != null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			} catch (SQLException e) {
				logger.error("Connection error EmployeeDao employeeIdExist() "+e);
			}
		}
		return false;
	}

	@Override
	public boolean deleteEmployee(int eid) {
		logger.info("JDBC deleteEmployee() called");
		String query = "delete from p_employee where id=?";
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = DatabaseConnection.getConnection();
			stmt = con.prepareStatement(query);
			stmt.setInt(1, eid);
			stmt.execute();
			return true;
		} catch (SQLException e) {
			logger.error("Connection error EmployeeDao deleteEmployee() "+e);
		}finally {
			try {
				if(rs != null)
					rs.close();
				if(stmt != null)
					stmt.close();
				if(con != null)
					con.close();
			} catch (SQLException e) {
				logger.error("Connection error EmployeeDao deleteEmployee() "+e);
			}
		}
		return false;
	}

	@Override
	public boolean forgotPassword(String USERNAME, String PASSWORD) {
		logger.info("JDBC forgotPassword() called");
		String salt = BCrypt.gensalt();
		String hashedPassword = BCrypt.hashpw(PASSWORD, salt);
		String queryToCheck="select count(*) from p_employee where username=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DatabaseConnection.getConnection();
			pstmt = con.prepareStatement(queryToCheck);
			pstmt.setString(1, USERNAME);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getInt(1) == 0) {
					logger.error("EmployeeDao forgotPassword username not found");
					return true;
				}else {
					logger.info("EmployeeDao forgotPassword successful");
					String query="update p_employee set password = ? where username = ? ";
					PreparedStatement stmt = con.prepareStatement(query);
					stmt.setString(1, hashedPassword);
					stmt.setString(2, USERNAME);
					stmt.execute();
					return false;
				}
			}
		} catch (SQLException e) {
			logger.error("Connection error EmployeeDao forgotPassword() "+e);
		}finally {
			try {
				if(rs != null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			} catch (SQLException e) {
				logger.error("Connection error EmployeeDao forgotPassword() "+e);
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
			pstmt = con.prepareStatement("SELECT password FROM p_employee WHERE username = ? ");
        	pstmt.setString(1, USERNAME);
        	rs1 = pstmt.executeQuery();
        	while(rs1.next()) {       	
        		if(BCrypt.checkpw(PASSWORD, rs1.getString(1))) {
        			logger.info("EmployeeDao login() hashedPassword and password matched");
            		hashedPassword = rs1.getString(1);   
            	}
        	}
            stmt = con.prepareStatement("SELECT * FROM p_employee WHERE username = ? and password = ?");
            stmt.setString(1, USERNAME.trim());
            stmt.setString(2, hashedPassword);
            rs = stmt.executeQuery();
            if (rs.next()) {
            	logger.info("EmployeeDao login() username and password matched");
        		return true;
        } else {
        		logger.info("EmployeeDao login() username and password not matched");
        		return false;
        }
		} catch (SQLException e) {
			logger.error("Connection error EmployeeDao login() "+e);
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
				logger.error("Connection error EmployeeDao emailExist() "+e);
			}
		}
		return false;
	}

}
