package com.prajyot.servlets;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.prajyot.services.AdminService;
import com.prajyot.services.EmployeeService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ForgotPasswordServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
//	final static Logger logger = LoggerFactory.getLogger(ForgotPasswordServlet.class);
	static final Logger logger = LogManager.getLogger(ForgotPasswordServlet.class);

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("ForgotPasswordServlet doPost() called");

		String role	= req.getParameter("role1");
		String USERNAME = req.getParameter("username1");
	    String PASSWORD = req.getParameter("password1");     
	    EmployeeService dao = new EmployeeService();
	    AdminService dao1 = new AdminService();
 
	    if(role.equals("admin")) {
	    	logger.info("admin forgot password invoked");
	    	if(dao1.forgotPassword(USERNAME, PASSWORD)) {
	    		logger.info("ForgotPasswordServlet Reset password successful");
				resp.sendRedirect("login.jsp?msg="+"Reset password successfully"); 
	    	
			} else {	
				logger.error("ForgotPasswordServlet reset password unsuccessful");
				req.setAttribute("username1",USERNAME);
				req.setAttribute("errorMessage1","Username not found");
				RequestDispatcher disp = req.getRequestDispatcher("login.jsp");
				disp.forward(req, resp);
			}
	    }  
	    
	    if(role.equals("employee")) {
	    	logger.info("employee forgot password invoked");
			if(dao.forgotPassword(USERNAME, PASSWORD)) {
				logger.info("ForgotPasswordServlet Reset password successful");
				resp.sendRedirect("login.jsp?msg="+"Reset password successfull"); 
			} else {
				logger.error("ForgotPasswordServlet reset password unsuccessful");
				req.setAttribute("username1",USERNAME);
				req.setAttribute("errorMessage1","Username not found");
				RequestDispatcher disp = req.getRequestDispatcher("login.jsp");
				disp.forward(req, resp);
			}
		}
	}
}
