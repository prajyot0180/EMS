package com.prajyot.servlets;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.prajyot.services.UploadFileService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@MultipartConfig(maxFileSize = 1024 * 1024 * 5)
public class UploadFileServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	final static Logger logger = LogManager.getLogger(UploadFileServlet.class);
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			logger.info("UploadFileServlet doPost() called");
			
			HttpSession session = req.getSession(false);
			if(session == null) {
				logger.error("Session is null");
				resp.sendRedirect("login.jsp?msg="+"Your session has expired. Please log in again.");
			}	
			Collection<Part> fileParts =  req.getParts();
			String username = (String) session.getAttribute("adminUserName");
			UploadFileService dao = new UploadFileService();
			String uploadPath = "/home/credentek/Desktop/work/UploadFiles";
			Path uploadDir = Paths.get(uploadPath);
			if(Files.notExists(uploadDir)) {
				logger.info("Folder not exist");
				 try {
					 logger.info("Folder created in path");
					 Files.createDirectories(uploadDir);
				 } catch (Exception e) {
					logger.info("Folder not created "+ e);
				}
			}
			try {
				for(Part file : fileParts) {
					String FileName = Paths.get(file.getSubmittedFileName()).getFileName().toString();
					File uploadFile = new File(uploadPath, FileName);
				
					if(file.getName().equals("file") && file.getSize() > 0) {
							
							if(dao.uploadFile(file,username)) {
								logger.info("UploadFileServlet file uploaded");
								InputStream fileContent = file.getInputStream();
								Files.copy(fileContent, uploadFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
								session.setAttribute("file", file);
								resp.sendRedirect("viewEmployee?msg="+"File uploaded");
							}else {
								logger.info("UploadFileServlet file not uploaded");
								resp.sendRedirect("viewEmployee?msg="+"Data Invalid Not Uploaded");
							}
					}else {
						logger.info("UploadFileServlet file not uploaded");
						resp.sendRedirect("viewEmployee?msg="+"Data Invalid Not Uploaded");
					}
				}
			} catch (Exception e ) {
				e.printStackTrace();
			}
			
					
		}
}
