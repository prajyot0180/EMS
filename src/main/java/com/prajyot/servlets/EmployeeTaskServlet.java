	package com.prajyot.servlets;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.prajyot.pojo.EmployeeTask;
//import com.prajyot.pojo.EmployeeTask;
import com.prajyot.services.EmployeeTaskService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class EmployeeTaskServlet extends  HttpServlet{

	private static final long serialVersionUID = 1L;
	static final Logger logger = LogManager.getLogger(EmployeeTaskServlet.class);

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("EmployeeTaskServlet doPost() called");

		HttpSession session = req.getSession(false);
		if(session == null) {
			logger.error("Session is null");
			resp.sendRedirect("login.jsp");
		}		
		String employeeUserName = (String) session.getAttribute("employeeUserName");
		String taskname = req.getParameter("task");
		String status = req.getParameter("status");
		String note = req.getParameter("note");
		LocalDate date = LocalDate.now(); 
         Date sqlDate = Date.valueOf(date);
		 	 
		EmployeeTaskService dao = new EmployeeTaskService();
			
		if(dao.dateExist(sqlDate.toString())){
			logger.error("EmployeeTaskServlet not added");
			resp.sendRedirect("employeeMain.jsp?msg="+"Today task is already uploaded. You can update the task if needed");
		}else {
			logger.info("EmployeeTaskServlet added");
			dao.addEmployeeTask(taskname, status, note, sqlDate, employeeUserName);
			resp.sendRedirect("employeeMain.jsp?msg="+"Task Submited");
		}	
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("EmployeeTaskServlet doGet() called");
		
		HttpSession session = req.getSession(false);
		if(session == null) {
			logger.error("Session is null");
			resp.sendRedirect("login.jsp?msg="+"Your session has expired. Please log in again.");
		}		
		String employeeUserName = (String) session.getAttribute("employeeUserName");
		
		EmployeeTaskService dao = new EmployeeTaskService();
		int total = dao.getTotalTask(employeeUserName);
		int totalCompleted = dao.getCompletedTask(employeeUserName);
		int totalRemain = dao.getReaminTask(employeeUserName);

			session.setAttribute("totalTask", total);
			session.setAttribute("totalCompleted",totalCompleted);
			session.setAttribute("totalRemain", totalRemain);
				
			int page = 1;
			int recoordPerPage = 12;
			
			if(req.getParameter("page") != null) {
				page = Integer.parseInt(req.getParameter("page"));
			}
			List<EmployeeTask> list = dao.viewAllEmployeeTask((page-1)*recoordPerPage,employeeUserName);
			int noOfRecord = dao.getNoOfRecords();
			int noOfPages = (int)Math.ceil(noOfRecord * 1.0/ recoordPerPage);	
				req.setAttribute("noOfPagesTask", noOfPages); 
				req.setAttribute("employeeTaskList", list); 
			    req.setAttribute("currentPageTask", page); 
				RequestDispatcher disp = req.getRequestDispatcher("EmployeeView.jsp");
				disp.forward(req, resp);			
	}
}
