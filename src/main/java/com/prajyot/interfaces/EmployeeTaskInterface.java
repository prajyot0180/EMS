package com.prajyot.interfaces;

import java.sql.Date;
import java.util.List;

import com.prajyot.entity.EmployeeTaskEntity;
import com.prajyot.pojo.EmployeeTask;

public interface EmployeeTaskInterface {
	public List<EmployeeTaskEntity> viewAllEmployeeTask(int offSet,String employeeUserName);
	public List<EmployeeTaskEntity> allEmployeeTask(String employeeUserName);
	public void addEmployeeTask(String taskname, String status, String note, Date sqlDate, String employeeUserName ) ;
	public void updateEmployeeTask(String updatetask, String updatestatus, String updatenote, Date sqlDate,	Integer idvalue,Date sqlLastUpdate);
	public void deleteEmployeeTask(Integer taskId);
	public int getTotalTask(String employeeUserName) ;
	public int getCompletedTask(String employeeUserName);
	public int getReaminTask(String employeeUserName);
	public boolean dateExist(String sdate) ;
}
