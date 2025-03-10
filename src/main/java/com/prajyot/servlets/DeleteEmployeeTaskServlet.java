package com.prajyot.servlets;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.apache.logging.log4j.Logger;

import com.prajyot.services.EmployeeTaskService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DeleteEmployeeTaskServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
//	final static Logger logger = LoggerFactory.getLogger(DeleteEmployeeTaskServlet.class);
	static final Logger logger = LogManager.getLogger(DeleteEmployeeTaskServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("DeleteEmployeeTaskServlet doGet() called");
		
		HttpSession session = req.getSession(false);
		if(session == null) {
			logger.error("Session is null");
			resp.sendRedirect("login.jsp?msg="+"Your session has expired. Please log in again.");
		}
		Integer taskId = Integer.parseInt( req.getParameter("taskId"));
		EmployeeTaskService dao = new EmployeeTaskService();
		dao.deleteEmployeeTask(taskId);
		resp.sendRedirect("employeeMain.jsp?msg="+"Deleted Successfully");
	}
}
