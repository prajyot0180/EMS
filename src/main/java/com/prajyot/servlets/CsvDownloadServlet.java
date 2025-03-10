package com.prajyot.servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.prajyot.pojo.Employee;
import com.prajyot.services.EmployeeService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CsvDownloadServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	static final Logger logger = LogManager.getLogger(CsvDownloadServlet.class);
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("CsvDownloadServlet doGet() called");
		
		HttpSession session = req.getSession(false);
		if(session == null) {
			logger.error("Session is null");
			resp.sendRedirect("login.jsp?msg="+"Your session has expired. Please log in again.");
		}	
		resp.setContentType("text/csv");
		resp.setHeader("Content-Disposition","attachment; filename = Employees"+ LocalDate.now() +".csv");
		
		EmployeeService dao = new EmployeeService();
		List<Employee> employees = dao.allEmployee();
		
		PrintWriter out = resp.getWriter();
		out.println("ID,Name,Gender,Date Of Birth,Email,Mobile Number,Address,Joining Date,Department,Salary");

		for (Employee emp : employees) {
		    out.println(emp.getEmployeeId() + "," + emp.geteName() + "," + emp.getGender() + "," + emp.getDob()+ "," + emp.getEmail() + "," + emp.getMobileNumber() + "," + emp.getAddress()+ "," + emp.getJoinDate() + "," + emp.getDepartment() + "," + emp.getSalary());
		}
		logger.info("Csv file downloaded");
		out.close();
		
	}
}
