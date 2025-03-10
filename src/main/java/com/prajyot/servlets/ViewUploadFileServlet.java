package com.prajyot.servlets;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.prajyot.pojo.UploadFiles;
import com.prajyot.services.UploadFileService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ViewUploadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger logger = LogManager.getLogger(ViewUploadFileServlet.class);
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("ViewUploadFileServlet doGet() called");
		HttpSession session = req.getSession(false);
		if(session == null) {
			logger.error("Session is null");
			resp.sendRedirect("login.jsp?msg="+"Your session has expired. Please log in again.");
		}else {
				UploadFileService dao = new  UploadFileService();  
					List<UploadFiles>	list = dao.allUploadFiles();
					req.setAttribute("uploadList", list); 
					RequestDispatcher disp = req.getRequestDispatcher("bulkFiles.jsp");
					disp.forward(req, resp);
		}
	}
}
