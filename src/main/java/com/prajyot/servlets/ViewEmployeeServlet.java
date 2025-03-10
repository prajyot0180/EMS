package com.prajyot.servlets;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.prajyot.pojo.Employee;
//import com.prajyot.pojo.Employee;
import com.prajyot.services.EmployeeService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ViewEmployeeServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	static final Logger logger = LogManager.getLogger(ViewEmployeeServlet.class);
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("ViewEmployeeServlet doGet() called");
		HttpSession session = req.getSession(false);
		if(session == null) {
			logger.error("Session is null");
			resp.sendRedirect("login.jsp?msg="+"Your session has expired. Please log in again.");
		}else {
		
				int page = 1;
				int recoordPerPage = 11;
				
				if(req.getParameter("page") != null) {
					
					page = Integer.parseInt(req.getParameter("page"));
				}
				
				EmployeeService dao = new EmployeeService();
				try {
					List<Employee>	list =  dao.viewAllEmployee((page-1)*recoordPerPage);
					int noOfRecord = dao.getNoOfRecords();
					
					logger.info(list);
					logger.info(noOfRecord);
					int noOfPages = (int)Math.ceil(noOfRecord * 1.0 / recoordPerPage);	
					req.setAttribute("noOfPages", noOfPages); 
					req.setAttribute("employeeList", list); 
					req.setAttribute("currentPage", page); 
					RequestDispatcher disp = req.getRequestDispatcher("viewEmployee.jsp");
					disp.forward(req, resp);
				}catch(Exception e){
					logger.info(e);
				}
		}
	}
}