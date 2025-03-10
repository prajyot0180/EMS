package com.prajyot.interfaces;

import java.util.Date;
import java.util.List;

import com.prajyot.entity.UploadFilesEntity;
import com.prajyot.entity.EmployeeEntity;


import jakarta.servlet.http.Part;

public interface UploadFilesInterface {
	public boolean uploadFile(Part filePart, String username2) ;
	public void ifNotInsert(String ename,String email, String gender, String mobilenumber, String address, String adminUserName, String username, String password, String employeeId, String department, Date join_date, Date dob_date, String salary, List<EmployeeEntity> notUploadList);
	public List<UploadFilesEntity> allUploadFiles();

}
