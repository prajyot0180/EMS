<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.prajyot.pojo.Employee" %>
<%@ page import="java.util.List" %>


    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LogedIn</title>
<!-- 
<style type="text/css">
 body {
            background-color: #f0f4f8;
            display: block;
            justify-content: center;
            align-items: center;
            margin: 0;
            padding: 0
        }
.container{
	margin-top : 1%;
	background-color: #fff;
	border-radius: 8px;
	padding: 30px;
    width: 100%;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}


.top-button{
	margin-right : 10px;
	background-color: #4CAF50;
	color : white;
	padding: 10px 20px;
	border: none;
	border-radius: 3px;
	cursor: pointer;
}
.top-button-logout {
	margin-left : 68%;
	background-color: #ff4d4d;
	border: none;
	padding: 5px 10px;
	border-radius: 3px;
	color: white;
	cursor: pointer;
}
.add-employee{
	display : none;
	position :fixed;
	top : 50%;
	left: 50%;
	transform : translate(-50%, -50%);
	background-color : #fff;
	padding : 30px;
	border-radius: 8px;
	box-shadow:0 2px 8px rgba(0, 0, 0.2);
}
.add-employee input{
	margin : 10px;
	padding : 6px;
	width: 100%;
	border-radius: 5px;
	border: 1px solid;
}
.add-employee lable,select{
	margin-left: 10px;
	padding :px;
}
.add-employee button{
	margin : 10px;
	margin-left : 30%;
	background-color: #4CAF50;
	color : #ffff;
	border:  none;
	padding : 5px;
	border-radius: 5px;
	cursor: pointer;
}
 .add-employee .close-btn {
 	background-color: red;
	margin-left : 15%;
 }
 .update-button{
 	background-color: #4CAF50;
 	color : #ffff;
 	border:  none;
	padding : 5px;
	border-radius: 5px;
	cursor: pointer;
 }
 .delete-button{
 	margin-left : 20px;
 	background-color: red;
 	color : #ffff;
 	border:  none;
	padding : 5px;
	border-radius: 5px;
	cursor: pointer;
 }
</style>
 -->
</head>
<body>		
    <%
	    if(session.getAttribute("adminUserName") == null){
			response.sendRedirect("login.jsp");
		}else{
	    	RequestDispatcher disp = request.getRequestDispatcher("viewEmployee");
	    	disp.forward(request, response);
		}
    %>
</body>
</html>