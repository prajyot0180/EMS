<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee</title>
</head>
<body>
 <%
	    if(session.getAttribute("employeeUserName") == null){
			response.sendRedirect("login.jsp");
		}else{
	    	RequestDispatcher disp = request.getRequestDispatcher("employeeTask");
	    	disp.forward(request, response);
		}
    %>
</body>
</html>