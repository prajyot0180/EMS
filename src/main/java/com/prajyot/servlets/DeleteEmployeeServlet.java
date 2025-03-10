
package com.prajyot.servlets;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.prajyot.services.EmployeeService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DeleteEmployeeServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	static final Logger logger = LogManager.getLogger(DeleteEmployeeServlet.class);
	
	
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			logger.info("DeleteEmployeeServlet doGet() called");
			HttpSession session = req.getSession(false);	
			if(session == null) {
				logger.error("Session is null");
				resp.sendRedirect("login.jsp?msg="+"Your session has expired. Please log in again.");
			}
			int page = (int) session.getAttribute("currentPage");
			int eid = Integer.parseInt(req.getParameter("eId"));
			
			EmployeeService dao = new EmployeeService();
			if(dao.deleteEmployee(eid))
				resp.sendRedirect("viewEmployee?page="+page+"&msg="+"Deleted Successfully");
			else
				resp.sendRedirect("viewEmployee?msg="+"Not Deleted");
		}
}