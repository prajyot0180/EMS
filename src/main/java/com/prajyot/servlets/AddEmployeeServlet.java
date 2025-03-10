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

public class AddEmployeeServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
//	final static Logger logger = LoggerFactory.getLogger(AddEmployeeServlet.class);
	static final Logger logger = LogManager.getLogger(AddEmployeeServlet.class);

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("AddEmployeeServlet doPost() called");

		HttpSession session = req.getSession(false);
		if(session == null) {
			logger.error("Session is null");
			resp.sendRedirect("login.jsp?msg="+"Your session has expired. Please log in again.");
		}	
		
		   	String employeeId = req.getParameter("employee-id");
	        String ename = req.getParameter("name");
	        String gender = req.getParameter("gender");
	        String dob =req.getParameter("dob");
	        String email = req.getParameter("email");
	        String mobilenumber = req.getParameter("mobilenumber");
	        String address = req.getParameter("address");
	        String joiningDate = req.getParameter("joining-date");
	        String department = req.getParameter("department");
	        Float salary = Float.parseFloat( req.getParameter("salary"));
	        String username = req.getParameter("username");
	        String password = req.getParameter("password");
	        String adminUserName = (String) session.getAttribute("adminUserName");
	      
	        EmployeeService dao = new EmployeeService();       	
			if(dao.employeeIdExist("CT"+employeeId)) {
				logger.error("AddEmployeeServlet id already exist");
				session.setAttribute("employeeId",employeeId);
				session.setAttribute("name", ename);
				session.setAttribute("gender", gender);
				session.setAttribute("dob",dob);
				session.setAttribute("email", email);
				session.setAttribute("mobilenumber", mobilenumber);
				session.setAttribute("address", address);
				session.setAttribute("joiningDate",joiningDate);
				session.setAttribute("department",department);
				session.setAttribute("salary",String.valueOf(salary));
				session.setAttribute("password", password);
				session.setAttribute("username", username);
				resp.sendRedirect("viewEmployee?idmsg="+"Employee id already taken");
				
			}else if(dao.emailExist(email)) {
				logger.error("AddEmployeeServlet email already exist");
				session.setAttribute("employeeId",employeeId);
				session.setAttribute("name", ename);
				session.setAttribute("gender", gender);
				session.setAttribute("dob", dob);
				session.setAttribute("email", email);
				session.setAttribute("mobilenumber", mobilenumber);
				session.setAttribute("address", address);
				session.setAttribute("joiningDate",joiningDate);
				session.setAttribute("department",department);
				session.setAttribute("salary",String.valueOf(salary));
				session.setAttribute("password", password);
				session.setAttribute("username", username);
				
				resp.sendRedirect("viewEmployee?emsg="+"email already exist");
				
			}else if(dao.usernameExist(username)) {
				logger.error("AddEmployeeServlet username already exist");
				session.setAttribute("employeeId",employeeId);
				session.setAttribute("name", ename);
				session.setAttribute("gender", gender);
				session.setAttribute("dob",dob);
				session.setAttribute("email", email);
				session.setAttribute("mobilenumber", mobilenumber);
				session.setAttribute("address", address);
				session.setAttribute("joiningDate",joiningDate);
				session.setAttribute("department",department);
				session.setAttribute("salary",String.valueOf(salary));
				session.setAttribute("password", password);
				session.setAttribute("username", username);
				resp.sendRedirect("viewEmployee?umsg="+"username already exist");
			} else {	
				logger.info("AddEmployeeServlet add");
				dao.addEmployee(employeeId, ename, gender, dob, email, mobilenumber, address, joiningDate, department, salary, username, password,adminUserName);
				resp.sendRedirect("viewEmployee?msg="+"Inserted Successfully");
			}
			
		
		
	}
	
}
