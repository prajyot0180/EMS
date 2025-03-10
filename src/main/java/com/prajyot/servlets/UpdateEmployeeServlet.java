package com.prajyot.servlets;

import java.io.IOException;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.prajyot.services.EmployeeService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class UpdateEmployeeServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
//	final static Logger logger = LoggerFactory.getLogger(UpdateEmployeeServlet.class);
	static final Logger logger = LogManager.getLogger(UpdateEmployeeServlet.class);
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			logger.info("UpdateEmployeeServlet doPost() called");
			HttpSession session = req.getSession(false);
			if(session == null) {
				logger.error("Session is null");

				resp.sendRedirect("login.jsp?msg="+"Your session has expired. Please log in again.");
			}else {
			
			 	String employeeId = req.getParameter("updateid");
		        String ename = req.getParameter("updatename");
		        String gender = req.getParameter("updategender");
		        String dob =req.getParameter("updatedob");
		        String email = req.getParameter("updateemail");
		        String mobilenumber = req.getParameter("updatemobilenumber");
		        String address = req.getParameter("updateaddress");
		        String joiningDate = req.getParameter("updatejoiningdate");
		        String department = req.getParameter("updatedepartment");
		        Float salary = Float.parseFloat( req.getParameter("updatesalary"));
		        int page = (int) session.getAttribute("currentPage");
		        String adminUserName = (String) session.getAttribute("adminUserName");
 		
	 			EmployeeService dao = new EmployeeService();
				 dao.updateEmployee(ename, gender, dob, email, mobilenumber, address, joiningDate, department, salary, adminUserName, employeeId);
				resp.sendRedirect("viewEmployee?page="+page+"&msg="+"Updated Successfully");
				
			}
		}
	
}
