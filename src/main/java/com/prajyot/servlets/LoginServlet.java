
package com.prajyot.servlets;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.prajyot.services.AdminService;
import com.prajyot.services.EmployeeService;
import com.prajyot.validate.SessionManager;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



public class LoginServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
//	final static Logger logger = LoggerFactory.getLogger(LoginServlet.class);
	static final Logger logger = LogManager.getLogger(LoginServlet.class);

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("LoginServlet doPost() called");

		HttpSession session1 = req.getSession(false);
    	if(session1 == null) {
    		logger.error("Session is null");
    		resp.sendRedirect("login.jsp?msg="+"Your session has expired. Please log in again.");
    	}else {
    		HttpSession session = req.getSession(true);
        String role	= req.getParameter("role");
        String USERNAME = req.getParameter("username");
        String PASSWORD = req.getParameter("password");     
        
        logger.info("Role : "+ role +"Username : "+ USERNAME +"Password : "+PASSWORD);
        EmployeeService dao = new EmployeeService();
	    AdminService dao1 = new AdminService();
	    
	    
	    if(role.equals("admin")) {
	    	logger.info("admin login invoked");
	        if (dao1.login(USERNAME, PASSWORD)) {
    	
	        	logger.info("LoginServlet login successful");
	    		session.setAttribute("adminUserName", USERNAME);
	    		SessionManager.addSession(USERNAME,session);
	            resp.sendRedirect("welcome.jsp");
	        } else {
	        	logger.error("LoginServlet login Unsuccessful");
	            req.setAttribute("errorMessage","Wrong credentials please enter again !!");    
	            req.setAttribute("role", role);
	            RequestDispatcher disp = req.getRequestDispatcher("login.jsp");
	            disp.forward(req, resp);
	        }
	    }
	    if(role.equals("employee")) {
	    	logger.info("employee login invoked");
	        if (dao.login(USERNAME, PASSWORD)) {
	        	logger.info("LoginServlet login successful");
	        		session.setAttribute("employeeUserName", USERNAME);
	        		SessionManager.addSession(USERNAME,session);
	                resp.sendRedirect("employeeMain.jsp");
	        } else {
	        	logger.error("LoginServlet login Unsuccessful");
	                req.setAttribute("errorMessage","Wrong credentials please enter again !!");    
	                req.setAttribute("role", role);
	                RequestDispatcher disp = req.getRequestDispatcher("login.jsp");
	                disp.forward(req, resp);
	        }
	    }
    	
    }
    }


}


