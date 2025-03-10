package com.prajyot.servlets;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import com.prajyot.validate.SessionManager;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LogOutServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
//	final static Logger logger = LoggerFactory.getLogger(LogOutServlet.class);
	static final Logger logger = LogManager.getLogger(LogOutServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("LogOutServlet doGet() called");
		HttpSession session = req.getSession(false);
		if(session != null) {
			
			String adminUsername = (String) session.getAttribute("adminUserName");
			String employeeUsername = (String) session.getAttribute("employeeUserName");
			
			if(adminUsername != null) {
				SessionManager.removeSession(adminUsername);
			}
			if(employeeUsername != null) {
				SessionManager.removeSession(employeeUsername);
			}
			logger.info("Loged Out");
			session.invalidate();
			RequestDispatcher disp = req.getRequestDispatcher("login.jsp");
			disp.forward(req, resp);
		}
		
		
	}
}
