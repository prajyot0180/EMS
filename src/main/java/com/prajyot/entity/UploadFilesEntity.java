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
@Table(name="p_uploaddata")
public class UploadFilesEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "p_uploaddata_seq")
	@SequenceGenerator(name = "p_uploaddata_seq", sequenceName = "p_uploaddata_seq", allocationSize = 1)
	@Column(name="id")
	private int id;
	
	@Column(name="filename")
	private String fileName;
	
	@Column(name="upload_date")
	private Date uploadDate;
	
	@Column(name="added")
	private int addData;
	
	@Column(name="not_added")
	private int notAddData;
	
	@Column(name="admin")
	private String admin;
	
	public UploadFilesEntity() {
	}
	
	public UploadFilesEntity(int id, String fileName, Date uploadDate, int addData, int notAddData, String admin) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.uploadDate = uploadDate;
		this.addData = addData;
		this.notAddData = notAddData;
		this.admin = admin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public int getAddData() {
		return addData;
	}

	public void setAddData(int addData) {
		this.addData = addData;
	}

	public int getNotAddData() {
		return notAddData;
	}

	public void setNotAddData(int notAddData) {
		this.notAddData = notAddData;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

}
