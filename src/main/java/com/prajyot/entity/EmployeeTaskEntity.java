package com.prajyot.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="employee_task")
public class EmployeeTaskEntity {
	
	@Id
	@Column(name= "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_task_seq")
	@SequenceGenerator(name = "employee_task_seq", sequenceName = "employee_task_seq",allocationSize = 1 )
	private int id;
	
	@Column(name= "admin_name")
	private String ename;
	
	@Column(name= "task_name")
	private String taskName;
	
	@Column(name= "status")
	private String status;
	
	@Column(name= "note")
	private String note;
	
	@Column(name= "task_date")
	private Date date;
	
	@Column(name= "last_update")
	private Date lastUpdate;
	
	public EmployeeTaskEntity() {
		// TODO Auto-generated constructor stub
	}
	public EmployeeTaskEntity(int id, String ename, String taskName, String status, String note, Date date, Date lastUpdate) {
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
