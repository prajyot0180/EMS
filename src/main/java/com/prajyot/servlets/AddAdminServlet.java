package com.prajyot.servlets;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.prajyot.services.AdminService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



public class AddAdminServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
//	final static Logger logger = LoggerFactory.getLogger(AddAdminServlet.class);
	static final Logger logger = LogManager.getLogger(AddAdminServlet.class);

		
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("AddAdminServlet doPost() called");
		
		HttpSession session = req.getSession(false);
		if(session == null) {
			logger.error("Session is null");
			resp.sendRedirect("login.jsp?msg="+"Your session has expired. Please log in again.");
		}
		String name =req.getParameter("name");
		String gender = req.getParameter("gender");
		String username = req.getParameter("username");
		String mobilenumber = req.getParameter("mobilenumber");
		String password = req.getParameter("password");
		
		AdminService dao = new AdminService();

			if(dao.usernameExist(username)){
				logger.error("AddAdminServlet username exist");
				req.setAttribute("errorMessage","Username Already Taken");
				RequestDispatcher disp = req.getRequestDispatcher("signup.jsp");
				disp.forward(req, resp);
			} else {
				logger.info("AddAdminServlet add");
				dao.addAdmin(name, gender, username, mobilenumber, password);
				resp.sendRedirect("login.jsp?msg="+"Account created"); 
			}
		
	}

}
