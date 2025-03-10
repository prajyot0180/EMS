package com.prajyot.services;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.prajyot.dao.HbUploadFilesDao;
import com.prajyot.entity.EmployeeEntity;
import com.prajyot.entity.UploadFilesEntity;
import com.prajyot.interfaces.UploadFilesInterface;
import com.prajyot.pojo.UploadFiles;

import jakarta.servlet.http.Part;

public class UploadFileService {
	
	static final Logger logger = LogManager.getLogger(UploadFileService.class);
	
//	UploadFilesInterface filesInterface = new JdbcUploadFilesDao();
	UploadFilesInterface filesInterface = new HbUploadFilesDao();
	
	public boolean uploadFile(Part filePart, String username2)  {
		logger.info("EmployeeDao uploadFile() called");
		return 	filesInterface.uploadFile(filePart, username2);	
	}

  public void ifNotInsert(String ename,String email, String gender, String mobilenumber, String address, String adminUserName, String username, String password, String employeeId, String department, Date join_date, Date dob_date, String salary, List<EmployeeEntity> notUploadList) {
	  logger.info("in ifNotInsert");
	  filesInterface.ifNotInsert(ename, email, gender, mobilenumber, address, adminUserName, username, password, employeeId, department, join_date, dob_date, salary, notUploadList);	  
   }

	public List<UploadFiles> allUploadFiles() {
		logger.info("UploadFileDao allUploadFiles() called");
		List<UploadFilesEntity> list = filesInterface.allUploadFiles();
		List<UploadFiles> uploadFilesList = new ArrayList<>();
	
		for(UploadFilesEntity uploadFilesEntity : list) {
			UploadFiles uploadFiles = new UploadFiles();
			uploadFiles.setAddData(uploadFilesEntity.getAddData());
			uploadFiles.setAdmin(uploadFilesEntity.getAdmin());
			uploadFiles.setFileName(uploadFilesEntity.getFileName());
			uploadFiles.setId(uploadFilesEntity.getId());
			uploadFiles.setNotAddData(uploadFilesEntity.getNotAddData());
			uploadFiles.setUploadDate(uploadFilesEntity.getUploadDate());
			uploadFilesList.add(uploadFiles);
		}
		return uploadFilesList;
	}
	
}
