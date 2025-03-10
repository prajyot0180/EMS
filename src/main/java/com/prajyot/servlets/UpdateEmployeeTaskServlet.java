package com.prajyot.servlets;

import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.prajyot.services.EmployeeTaskService;

import java.sql.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class UpdateEmployeeTaskServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
//	final static Logger logger = LoggerFactory.getLogger(UpdateEmployeeTaskServlet.class);
	static final Logger logger = LogManager.getLogger(UpdateEmployeeTaskServlet.class);
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("UpdateEmployeeTaskServlet doPost() called");
		HttpSession session = req.getSession(false);
		if(session == null) {
			logger.error("Session is null");
			resp.sendRedirect("login.jsp?msg="+"Your session has expired. Please log in again.");
		}
	 	String updatetask = req.getParameter("updatetask");
        String updatestatus = req.getParameter("updatestatus");
        String updatedate = req.getParameter("updatedate");
        String updatenote =req.getParameter("updatenote");
        Integer idvalue =Integer.parseInt( req.getParameter("idvalue"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
	    LocalDate udate =LocalDate.parse(updatedate, formatter);
	    Date sqlDate = Date.valueOf(udate);
	    LocalDate lastUpdate = LocalDate.now();
	    Date sqlLastUpdate = Date.valueOf(lastUpdate);	    
	    
	   EmployeeTaskService dao = new EmployeeTaskService();
	   dao.updateEmployeeTask(updatetask, updatestatus, updatenote, sqlDate, idvalue,sqlLastUpdate);
	   resp.sendRedirect("employeeMain.jsp?msg="+"Updated Successfully");
	}

}
