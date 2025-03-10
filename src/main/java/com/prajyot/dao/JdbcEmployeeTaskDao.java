package com.prajyot.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.prajyot.database.DatabaseConnection;
import com.prajyot.interfaces.EmployeeTaskInterface;
import com.prajyot.entity.EmployeeTaskEntity;

public class JdbcEmployeeTaskDao implements EmployeeTaskInterface {
	private int noOfRecords;
	public int getNoOfRecords() {
		return noOfRecords;
	}
	static final Logger logger = LogManager.getLogger(JdbcEmployeeTaskDao.class);

	@Override
	public List<EmployeeTaskEntity> viewAllEmployeeTask(int offSet, String employeeUserName) {
		logger.info("JDBC viewAllEmployeeTask() called");
		List<EmployeeTaskEntity> list = new ArrayList<>();
		int lend = offSet + 12;
		String query="SELECT * FROM (SELECT employee_task.*, ROWNUM AS rnum FROM employee_task where admin_name = ? ORDER BY id) WHERE rnum >" + offSet + " AND rnum <= "+ lend ;
		String query1="select count(*) from employee_task where admin_name = ?";

		Connection con = null;
		PreparedStatement stmt = null,stmt1 = null;
		ResultSet rs = null,rs1 = null;;
		try {
			con = DatabaseConnection.getConnection();		
			stmt = con.prepareStatement(query);
			stmt.setString(1, employeeUserName);
			rs = stmt.executeQuery();
			while(rs.next()) {
				EmployeeTaskEntity employeeTask = new EmployeeTaskEntity(
						rs.getInt("id"), 
						rs.getString("admin_name"), 
						rs.getString("task_name"),
						rs.getString("status"),
						rs.getString("note"), 
						rs.getDate("task_date"),
						rs.getDate("last_update"));
				list.add(employeeTask);
			}
			stmt1 = con.prepareStatement(query1);
			stmt1.setString(1, employeeUserName);
			rs1 = stmt1.executeQuery();	
			 if(rs1.next()) {
				 this.noOfRecords = rs1.getInt(1); 
			 }
			 logger.info("EmployeeTaskDao tasks :" +list.size());
			return list;
		} catch (SQLException e) {
			logger.error("Connection error EmployeeTaskDao viewAllEmployeeTask() "+e);		
		}finally {
			try {
				if(rs != null)
					rs.close();
				if(rs1 != null)
					rs1.close();
				if(stmt1 != null)
					stmt1.close();
				if(stmt != null)
					stmt.close();
				if(con != null)
					con.close();
			} catch (SQLException e) {
				logger.error("Connection error EmployeeTaskDao viewAllEmployeeTask() "+e);
			}
		}
		return list;
	}

	@Override
	public void addEmployeeTask(String taskname, String status, String note, Date sqlDate, String employeeUserName) {
		logger.info("JDBC addEmployeeTask() called");
		String query="insert into employee_task(task_name, status, note, task_date, admin_name, last_update) values (?, ?, ?, ?, ?, ?)";

		Connection con = null;
		PreparedStatement pstmt = null;
		try {	
			con = DatabaseConnection.getConnection();	
			pstmt = con.prepareStatement(query);
			pstmt.setString(1,taskname);
			pstmt.setString(2,status);
			pstmt.setString(3,note);
			pstmt.setDate(4,sqlDate);
			pstmt.setString(5, employeeUserName);
			pstmt.setDate(6,sqlDate);
			pstmt.execute();
			logger.info("EmployeeTaskDao add task :" +taskname+" "+status+" "+note+" "+sqlDate+" "+employeeUserName+" "+sqlDate);
		} catch (SQLException e) {
			logger.error("Connection error EmployeeTaskDao addEmployeeTask() "+e);
		}finally {
			try {		
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			} catch (SQLException e) {
				logger.error("Connection error EmployeeTaskDao addEmployeeTask() "+e);
			}
		}

	}

	@Override
	public void updateEmployeeTask(String updatetask, String updatestatus, String updatenote, Date sqlDate,
			Integer idvalue, Date sqlLastUpdate) {
		logger.info("JDBC updateEmployeeTask() called");
		String queryToCheck="update employee_task set task_name = ?, status = ?, note = ?, task_date = ?,  last_update = ? where id = ? ";
		Connection con = null;
		PreparedStatement stmt = null;
		try {	
			con = DatabaseConnection.getConnection();	
			stmt = con.prepareStatement(queryToCheck);
			stmt.setString(1, updatetask);
			stmt.setString(2, updatestatus);
			stmt.setString(3, updatenote);
			stmt.setDate(4, sqlDate);
			stmt.setDate(5, sqlLastUpdate);
			stmt.setInt(6, idvalue);
			stmt.execute();
			logger.info("EmployeeTaskDao update task :" + updatetask+" "+updatestatus+" "+updatenote+" "+sqlDate+" "+sqlLastUpdate);
		} catch (SQLException e) {
			logger.error("Connection error EmployeeTaskDao updateEmployeeTask() "+e);
		}finally {
			try {
				if(stmt != null)
					stmt.close();
				if(con != null)
					con.close();
			} catch (SQLException e) {
				logger.error("Connection error EmployeeTaskDao updateEmployeeTask() "+e);
			}
		}
	}

	@Override
	public void deleteEmployeeTask(Integer taskId) {
		logger.info("JDBC deleteEmployeeTask() called");
		String query = "delete from employee_task where id=?";
		Connection con = null;
		PreparedStatement stmt = null;	
		try {
			con = DatabaseConnection.getConnection();
			stmt = con.prepareStatement(query);
			stmt.setInt(1, taskId);
			stmt.execute();
			con.close();
		} catch (SQLException e) {
			logger.error("Connection error EmployeeTaskDao deleteEmployeeTask() "+e);
		}finally {
			try {
				if(stmt != null)
					stmt.close();
				if(con != null)
					con.close();
			} catch (SQLException e) {
				logger.error("Connection error EmployeeTaskDao deleteEmployeeTask() "+e);
			}
		}
	}

	@Override
	public int getTotalTask(String employeeUserName) {
		logger.info("JDBC getTotalTask() called");
		String queryTotal = "select count(*) from employee_task where admin_name = ?";
		int totalTask=0;
		Connection con = null;
		PreparedStatement stmt = null;	
		ResultSet rs = null;
		try {
			con = DatabaseConnection.getConnection();	
			stmt = con.prepareStatement(queryTotal);
			stmt.setString(1, employeeUserName);
			rs = stmt.executeQuery();
			while(rs.next()) {	
				logger.info("EmployeeTaskDao Total task : "+rs.getInt(1));
				totalTask = rs.getInt(1);
			}
			return totalTask;
		} catch (SQLException e) {
			logger.error("Connection error EmployeeTaskDao getTotalTask() "+e);
		}finally {
			try {
				if(rs != null)
					rs.close();
				if(stmt != null)
					stmt.close();
				if(con != null)
					con.close();
			} catch (SQLException e) {
				logger.error("Connection error EmployeeTaskDao getTotalTask() "+e);
			}
		}
		return 0;
	}

	@Override
	public int getCompletedTask(String employeeUserName) {
		logger.info("JDBC getCompletedTask() called");
		String queryTotalCompleted = "select count(*) from employee_task where status = ? and admin_name = ?";
		int completeTask = 0;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = DatabaseConnection.getConnection();			
			stmt = con.prepareStatement(queryTotalCompleted);
			stmt.setString(1, "completed");
			stmt.setString(2, employeeUserName);
			rs= stmt.executeQuery();
			while(rs.next()) {	
				logger.info("EmployeeTaskDao complete task : "+rs.getInt(1));
				completeTask = rs.getInt(1);
			}
			return completeTask;
		} catch (SQLException e) {
			logger.error("Connection error EmployeeTaskDao getCompletedTask() "+e);
		}finally {
			try {
				if(rs != null)
					rs.close();
				if(stmt != null)
					stmt.close();
				if(con != null)
					con.close();
			} catch (SQLException e) {
				logger.error("Connection error EmployeeTaskDao getCompletedTask() "+e);
			}
		}
		return 0;
	}

	@Override
	public int getReaminTask(String employeeUserName) {
		logger.info("JDBC getReaminTask() called");
		int remainTask = 0;
		String queryTotalRemain =  "select count(*) from employee_task where status = ? and  admin_name = ?";
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = DatabaseConnection.getConnection();	
			stmt = con.prepareStatement(queryTotalRemain);
			stmt.setString(1, "in progress");
			stmt.setString(2, employeeUserName);
			rs = stmt.executeQuery();
			while(rs.next()) {	
				logger.info("EmployeeTaskDao Remain task : "+rs.getInt(1));
				remainTask =  rs.getInt(1);
			}
			return remainTask;
		} catch (SQLException e) {
			logger.error("Connection error EmployeeTaskDao getReaminTask() "+e);
		}finally {
			try {
				if(rs != null)
					rs.close();
				if(stmt != null)
					stmt.close();
				if(con != null)
					con.close();
			} catch (SQLException e) {
				logger.error("Connection error EmployeeTaskDao getReaminTask() "+e);
			}
		}
		return 0;
	}

	@Override
	public boolean dateExist(String sdate) {
		logger.info("JDBC dateExist() called");
		String queryToCheck="SELECT count(*) FROM employee_task WHERE task_date = TO_DATE( ?, 'YYYY-MM-DD')";
		Connection con = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		try {	
			con = DatabaseConnection.getConnection();	
			pstmt1 = con.prepareStatement(queryToCheck);
			pstmt1.setString(1, sdate);
			rs = pstmt1.executeQuery();
			if(rs.next() && rs.getInt(1) > 0) {
				logger.error("EmployeeTaskDao date exist");
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			logger.error("Connection error EmployeeTaskDao dateExist() "+e);
		}finally {
			try {
				if(rs != null)
					rs.close();
				if(pstmt1 != null)
					pstmt1.close();
				if(con != null)
					con.close();
			} catch (SQLException e) {
				logger.error("Connection error EmployeeTaskDao dateExist() "+e);
			}
		}
		return false;
	}

	@Override
	public List<EmployeeTaskEntity> allEmployeeTask(String employeeUserName) {
		// TODO Auto-generated method stub
		return null;
	}

}
