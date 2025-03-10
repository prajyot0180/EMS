package com.prajyot.servlets;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.prajyot.pojo.Employee;
//import com.prajyot.pojo.Employee;
import com.prajyot.services.EmployeeService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ExlDownloadServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	static final Logger logger = LogManager.getLogger(ExlDownloadServlet.class);
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("ExlDownloadServlet doGet() called");
		HttpSession session = req.getSession(false);
		if(session == null) {
			logger.error("Session is null");
			resp.sendRedirect("login.jsp?msg="+"Your session has expired. Please log in again.");
		}	
        resp.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        resp.setHeader("Content-Disposition","attachment; filename=Employees"+LocalDate.now() +".xls");
        
        EmployeeService dao = new EmployeeService();
       
		try {
			 List<Employee> employees = dao.allEmployee();
			
			 logger.info("employee size:-"+employees.size());
			 
				Workbook  workbook = new HSSFWorkbook();
		        Sheet sheet = workbook.createSheet(); 
		        
		        Row header = sheet.createRow(0);
		        header.createCell(0).setCellValue("ID");
		        header.createCell(1).setCellValue("Name");
		        header.createCell(2).setCellValue("Gender");
		        header.createCell(3).setCellValue("Date Of Birth");
		        header.createCell(4).setCellValue("Email");
		        header.createCell(5).setCellValue("Mobile Number");
		        header.createCell(6).setCellValue("Address");
		        header.createCell(7).setCellValue("Joining Date");
		        header.createCell(8).setCellValue("Department");
		        header.createCell(9).setCellValue("Salary");    
		        
		        int rowSize = 1;
		        for(Employee emp : employees) {
			        Row row = sheet.createRow(rowSize++);
			        row.createCell(0).setCellValue(emp.getEmployeeId());
			        row.createCell(1).setCellValue(emp.geteName());
			        row.createCell(2).setCellValue(emp.getGender());
			        row.createCell(3).setCellValue(emp.getDob());
			        row.createCell(4).setCellValue(emp.getEmail());
			        row.createCell(5).setCellValue(emp.getMobileNumber());
			        row.createCell(6).setCellValue(emp.getAddress());
			        row.createCell(7).setCellValue(emp.getJoinDate());
			        row.createCell(8).setCellValue(emp.getDepartment());
			        row.createCell(9).setCellValue(emp.getSalary());
		        }
		        OutputStream outputStream = resp.getOutputStream();
		        workbook.write(outputStream);
		        workbook.close();
		        outputStream.close();
		        logger.info("Excel file downloaded");
		} catch (Exception e) {
			logger.info(" Exception "+e);
			
		}
	}
}
