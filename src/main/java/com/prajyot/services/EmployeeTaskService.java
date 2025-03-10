package com.prajyot.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.prajyot.dao.HbEmployeeTaskDao;
import com.prajyot.entity.EmployeeTaskEntity;
import com.prajyot.interfaces.EmployeeTaskInterface;
import com.prajyot.pojo.EmployeeTask;


public class EmployeeTaskService {
	
//	EmployeeTaskInterface employeeTaskInterface = new JdbcEmployeeTaskDao();
	EmployeeTaskInterface employeeTaskInterface = new HbEmployeeTaskDao();
	
	private int noOfRecords;
	public int getNoOfRecords() {
		return noOfRecords;
	}
	public void setNoOfRecords(int noOfRecords) {
		this.noOfRecords = noOfRecords;
	}
	static final Logger logger = LogManager.getLogger(EmployeeTaskService.class);

	public List<EmployeeTask> viewAllEmployeeTask(int offSet,String employeeUserName) {
		logger.info("EmployeeTaskDao viewAllEmployeeTask() called");
		List<EmployeeTaskEntity> list = employeeTaskInterface.viewAllEmployeeTask(offSet, employeeUserName);
		List<EmployeeTaskEntity> list1 = employeeTaskInterface.allEmployeeTask(employeeUserName);
		setNoOfRecords(list1.size());
		List<EmployeeTask> empTaskList = new ArrayList<>();
		for(EmployeeTaskEntity employeeTaskEntity : list) {
			EmployeeTask employeeTask = new EmployeeTask();
			employeeTask.setDate(employeeTaskEntity.getDate());
			employeeTask.setEname(employeeTaskEntity.getEname());
			employeeTask.setId(employeeTaskEntity.getId());
			employeeTask.setLastUpdate(employeeTaskEntity.getLastUpdate());
			employeeTask.setNote(employeeTaskEntity.getNote());
			employeeTask.setStatus(employeeTaskEntity.getStatus());
			employeeTask.setTaskName(employeeTaskEntity.getTaskName());
			empTaskList.add(employeeTask);
		}
		return empTaskList;
	}
	
	public void addEmployeeTask(String taskname, String status, String note, Date sqlDate, String employeeUserName ) {
		logger.info("EmployeeTaskDao addEmployeeTask() called");
		employeeTaskInterface.addEmployeeTask(taskname, status, note, sqlDate, employeeUserName);
	}
	
	public void updateEmployeeTask(String updatetask, String updatestatus, String updatenote, Date sqlDate,	Integer idvalue,Date sqlLastUpdate) {
		logger.info("EmployeeTaskDao updateEmployeeTask() called");
		employeeTaskInterface.updateEmployeeTask(updatetask, updatestatus, updatenote, sqlDate, idvalue, sqlLastUpdate);
	}
	
	public void deleteEmployeeTask(Integer taskId) {
		logger.info("EmployeeTaskDao deleteEmployeeTask() called");
		employeeTaskInterface.deleteEmployeeTask(taskId);
		
	}
	public int getTotalTask(String employeeUserName) {
		logger.info("EmployeeTaskDao getTotalTask() called");
		return employeeTaskInterface.getTotalTask(employeeUserName);
	}
	
	public int getCompletedTask(String employeeUserName) {
		logger.info("EmployeeTaskDao getCompletedTask() called");
		return employeeTaskInterface.getCompletedTask(employeeUserName);
	}
	
	public int getReaminTask(String employeeUserName) {
		logger.info("EmployeeTaskDao getReaminTask() called");
		return employeeTaskInterface.getReaminTask(employeeUserName);
	}
	public boolean dateExist(String sdate) {
		logger.info("EmployeeTaskDao dateExist() called");
		return employeeTaskInterface.dateExist(sdate);
	}
	
}
