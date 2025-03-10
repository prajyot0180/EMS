package com.prajyot.pojo;

import java.util.Date;

public class UploadFiles {
	
	private int id;
	private String fileName;
	private Date uploadDate;
	private int addData;
	private int notAddData;
	private String admin;
	
	public UploadFiles() {
	}
	
	public UploadFiles(int id, String fileName, Date uploadDate, int addData, int notAddData, String admin) {
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
