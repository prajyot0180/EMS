package com.prajyot.servlets;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class CsvSkipDownloadServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	static final Logger logger = LogManager.getLogger(CsvSkipDownloadServlet.class);
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("CsvDownloadServlet doGet() called");
		HttpSession session = req.getSession(false);
		if(session == null) {
			logger.error("Session is null");
			resp.sendRedirect("login.jsp?msg="+"Your session has expired. Please log in again.");
		}	
		resp.setContentType("text/csv");
		resp.setHeader("Content-Disposition","attachment; filename = Employees"+ LocalDate.now() +".csv");
		
		String fileName = req.getParameter("fileName");
		String csvDir = "/home/credentek/Desktop/work/RejectedData/";
		
		if (fileName == null || fileName.isEmpty()) {
			logger.info("CsvSkipDownloadServlet file is empty");
			resp.sendRedirect("bulkFiles.jsp?msg="+"File not Downloded file is empty");
        }
		
		File file = new File(csvDir,fileName);
		if (!file.exists()) {
			logger.info("CsvSkipDownloadServlet file not exist");
			resp.sendRedirect("bulkFiles.jsp?msg="+"File not Downloded file not exist");
        }
		
		try (FileInputStream in = new FileInputStream(file)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                resp.getOutputStream().write(buffer, 0, bytesRead);
            }
        }
	}
}
