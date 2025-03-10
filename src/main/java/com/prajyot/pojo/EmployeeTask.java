package com.prajyot.pojo;

import java.util.Date;

public class EmployeeTask {
	
	private int id;
	private String ename;
	private String taskName;
	private String status;
	private String note;
	private Date date;
	private Date lastUpdate;
	
	public EmployeeTask() {
		// TODO Auto-generated constructor stub
	}
	public EmployeeTask(int id, String ename, String taskName, String status, String note, Date date, Date lastUpdate) {
		this.id = id;
		this.ename = ename;
		this.taskName = taskName;
		this.status = status;
		this.note = note;
		this.date = date;
		this.lastUpdate = lastUpdate;
	}

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
}
