<%@page import="java.time.ZoneId"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.prajyot.pojo.Employee" %>
<%@ page import="java.util.List" %>
<%@ page import="jakarta.servlet.http.HttpSession"%>
<%@ page import="java.sql.PreparedStatement" %>
 <%@ page import="java.sql.ResultSet" %>
 <%@ page import="com.prajyot.database.DatabaseConnection" %>
 <%@ page import="java.sql.Connection" %>
<%--   <%@ page import= "com.prajyot.pojo.Admin" %> --%>
<%--     <%@ page import= "com.prajyot.entity.AdminEntity" %> --%>
<%--   <%@ page import="com.prajyot.entity.EmployeeEntity" %> --%>
  




    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Eployees</title>

<!-- CSS *************************************************************************************************  -->
<style type="text/css">
 body {
         background-color: #ffff;
            display: block;
            justify-content: center;
            align-items: center;
            margin: 0;
            padding: 0;
   	     }
.container{
 	display :flex;
 	justify-content: space-between;
 	align-items :center;
 	width: 100%;
 	height: 70px;
 	background-color: #99ffff;
	box-shadow: 0 1px 10px rgba(0, 0, 0, 1);
	z-index: 1;
}
.top-button{
	margin-left : 30px;
	background-color: #99ffff;
	width : 14%;
	height : 13%;
	font-size: 25px;
	padding: 15px 30px;
	border: none;
	border-radius: 3px;
	cursor: pointer;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.3); 
}
.top-button-logout {
	width: 100px;
	height: 35px;
	margin-left: 32%;
	align-self : center;
	margin-top : 25px;
	background-color: #ff4d4d;
	border: none;
	padding: 5px 10px;
	border-radius: 5px;
	color: white;
	cursor: pointer;
	text-shadow: 0px 0px 1px #737373;
} 
.btn-emp{
	margin-top : 50px;
	width: 100%;
	display :flex;
	justify-content :center;
	align-items:center;
	flex-direction:column;
	z-index: -1;
	background-color: #ffff;

}

.view-employee{
	display :block;
	margin-top:60px;
	margin-left : 36px;
	width: 95%;
	display :flex;
	justify-content :flex-start;
	align-items:center;
	flex-direction:column;
	text-shadow: 0px 0px 1px #737373;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.5);
	background-color: white;
	padding: 10px;
	height: 750px;
	
}
.pagination{
	
	display :flex;
	align-items:center;
	justify-content: flex-end;
	
	margin-right: 80px;
	overflow: hidden;

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
	width: 30%;
	height: auto;
	z-index: 10;
}
.add-employee input{
	margin : 5px;
	padding : 3px;
	width: 90%;
	height : 30px;
	border-radius: 5px;
	border: 1px solid;
}
.gender{
	margin : 10px;
	padding : 5px;
	font-size: large;
}
.gender-selection{
	margin : 10px;
	padding : 5px;
	font-size: small;
}
.add-employee button{
	margin : 10px;
	margin-left : 30%;
	background-color: #4CAF50;
	color : #ffff;
	border:  none;
	padding : 10px;
	border-radius: 5px;
	cursor: pointer;
	font-size: medium;
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
	margin-left: 30px;
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

.update-button:hover,.delete-button:hover,.top-button-logout:hover,.top-button:hover, .add-employee button:hover ,  .gender-selection:hover {
  transform: scale(1.1); 
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.4); 
}
input:hover{
	background-color: #f2f2f2;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.4); 
}
 i:hover{
 	transform: scale(1.1); 
 }
 .pop-up{
 	display :none;
 	position: fixed;
 	top: 40%;
 	left: 40%;
 	background-color:  rgba(0, 0, 0, 0.7);
 	padding: 20px;
 	font-size: xx-large;
 	border-radius: 20px;
 	color : white;
 	z-index: 10;
 }
 
 
 table {
    width: 100%;
    border-collapse: collapse;
    margin: 20px 0px;
    border: 0px solid #f2f2f2;
    
}

table th, table td {
    border: 0px solid #ffff;
    padding: 10px;
    text-align: left;
   
}
tr:nth-child(even) {background-color: #f2f2f2}
table th {
 	border: 1px solid #f2f2f2;
    background-color: #d9d9d9;
    color: #333;
    text-align: center;
    ;
}

button {
    padding: 5px 10px;
    border-radius: 3px;
    cursor: pointer;
}

.update-button {
    background-color: #4CAF50;
    color: white;
    border: none;
    
}

.delete-button {
    background-color: red;
    color: white;
    border: none;
    
}
  .profile-popup{
 	position: absolute;
 	display : none;
 	justify-content: flex-start;
 	align-items:flex-start;
 	width: 350px;
 	margin-left: 80%;
 	margin-top : 65px;
 	height: 40%;
 	background-color: aqua;
 	border-radius: 15px;
 	box-shadow: 0 4px 16px rgba(0, 0, 0, 0.5);
 	z-index: 10;
 	

 }
 .profile-popup1{
 	position: fixed;
 	display :flex;
 	justify-content: flex-start;
 	width: 350px;
 	margin-top : 100px;
 	height: 22%;
 	background-color: white;
 	border-radius: 15px;
 	flex-flow: column;
 	
 	
 }
  .profile-img{
 	 	position: absolute;
 	display :flex;
 	margin-left:75px;
 	justify-content: center;
 	width: 200px;
 	height: 160px;
 	background-image: url("https://png.pngtree.com/png-vector/20220901/ourmid/pngtree-company-employee-avatar-icon-wearing-a-suit-png-image_6133899.png");
 	background-position: right;
 	background-size: cover;
 }
 h1{
 	
 	text-transform: capitalize;
 	margin-top: 20px;
 	text-align: center;

 }
 #toggle-icon , #toggle-icon1 {
  				cursor: pointer;	
			}
			
			
			
.pagination {
  display: flex;
  position:absolute;
  align-self :flex-end;
	margin-right : 20px;
  margin-top: 38%;
}

.pagination a {
  color: black;
  float: left;
  padding: 8px 16px;
  text-decoration: none;
  transition: background-color .3s;
  border: 1px solid #ddd;
}

.pagination a.active {
  background-color: #99ffff;
  color: black;
  border: 1px solid #99ffff;
}

.pagination a:hover:not(.active) {background-color: #ddd;}

.material-symbols-outlined{
	cursor: pointer;
}
.profile-view{
	display: none;
	justify-content :flex-start;
	position: absolute;
	width: 35%;
	height: 50%;
	margin-left: 30%; 
	margin-top: 13%;
	box-shadow:0 2px 8px rgba(0, 0, 0.2);
	background-color: white;
}
	 footer{
        display:flex;
       	position :absolute;
        	align-self:flex-end;
        	margin-top: 39%;
        	margin-right:  42%;
        }
   .download{
	   	display: none;
	   	position: absolute;
	   	margin-top: 60px;
	   	margin-left: 86.5%;
	   	z-index: 0;
	   	background-color: white;
	   	border-radius: 5px;
	   	border: 1px solid black;
	   	box-shadow:0 2px 8px rgba(0, 0, 0.2);
   }
   .download button{
   		margin: 10px;
   		font-size: 10px;
   		background-color: #4CAF50;
	    color: white;
	    border: none;
   }
   .upload{
   		display: none;
	   	position: absolute;
	   	margin-top: 60px;
	   	margin-left: 73%;
	   	z-index: 0;
	   	background-color: white;
	   	border-radius: 5px;
	   	border: 1px solid black;
	   	box-shadow:0 2px 8px rgba(0, 0, 0.2);
	   	padding: 10px;
	   	width: auto;
   }
   .upload input{
   		border: 1px solid ;
   		padding: 2px;  
   	}
   .upload button{
   		margin: 10px;
   		margin-left : 38%;
   		font-size: 15px;
   		background-color: #4CAF50;
	    color: white;
	    border: none;
   	}
</style>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

<!-- <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0&icon_names=account_circle" /> -->
<link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet" />

</head>
<body>

	<%
		if(session.getAttribute("adminUserName") == null){
			response.sendRedirect("login.jsp");
		}
	%>
	
		
	 <%
	 	String ename =(String) session.getAttribute("name");
	 	String gender =(String) session.getAttribute("gender");
	 	String mobilenumber =(String) session.getAttribute("mobilenumber");
	 	String address =(String) session.getAttribute("address");
	 	String username =(String) session.getAttribute("username");
	 	String password =(String) session.getAttribute("password");
	 	String email =(String) session.getAttribute("email");
	 	String employeeId =(String) session.getAttribute("employeeId");
	 	String dob=(String) session.getAttribute("dob");
	 	String joinDate =(String) session.getAttribute("joiningDate");
	 	String department =(String) session.getAttribute("department");
		String salary =(String) session.getAttribute("salary");
	 	String emsg =  request.getParameter("emsg");
	 	String umsg =  request.getParameter("umsg");
	 	String msg =  request.getParameter("msg");
	 	String idmsg =  request.getParameter("idmsg");
	 %>     
	 <%	if(emsg != null){  %>
	 		<script> 
	 		window.onload = function () {
	 			openForm('employee');
	 			
	 			document.getElementById('name').value = '<%= ename %>';
	 			document.getElementById('gender').value = '<%= gender %>';
	 			document.getElementById('mobilenumber').value = '<%= mobilenumber %>';
	 			document.getElementById('address').value = '<%= address %>'; 
	 			document.getElementById('username').value = '<%= username %>';
	 			document.getElementById('password').value = '<%= password %>'; 
	 			document.getElementById('employee-id').value = '<%= employeeId %>'; 
	 			
	 			document.getElementById('department').value = '<%= department %>'; 
	 			document.getElementById('salary').value = '<%= salary %>'; 
	 			document.getElementById('emailError').innerText = '<%= emsg %>';
	 			document.getElementById('dob').value = '<%= dob %>';
	 			document.getElementById('joiningdate').value = '<%= joinDate %>'; 
	 		}			
	 		</script>
	 <%	}	%>   
	 <%	if(idmsg != null){  %>
	 		<script> 
	 		window.onload = function () {
	 			openForm('employee');
	 		
	 			document.getElementById('name').value = '<%= ename %>';
	 			document.getElementById('gender').value = '<%= gender %>';
	 			document.getElementById('email').value = '<%= email %>';
	 			document.getElementById('mobilenumber').value = '<%= mobilenumber %>';
	 			document.getElementById('address').value = '<%= address %>'; 
	 			document.getElementById('password').value = '<%= password %>';
	 			document.getElementById('username').value = '<%= username %>';
	 			document.getElementById('department').value = '<%= department %>'; 
	 			document.getElementById('salary').value = '<%= salary %>'; 
	 			document.getElementById('idError').innerText = '<%= idmsg %>';
	 			document.getElementById('dob').value = '<%= dob %>'; 
	 			document.getElementById('joiningdate').value = '<%= joinDate %>';
	 		}			
	 		</script>
	 <%	}	%>  
	 <%	if(umsg != null){  %>
	 		<script> 
	 		window.onload = function () {
	 			openForm('employee');
	 		
	 			document.getElementById('name').value = '<%= ename %>';
	 			document.getElementById('gender').value = '<%= gender %>';
	 			document.getElementById('email').value = '<%= email %>';
	 			document.getElementById('mobilenumber').value = '<%= mobilenumber %>';
	 			document.getElementById('address').value = '<%= address %>'; 
	 			document.getElementById('password').value = '<%= password %>';
	 			document.getElementById('employee-id').value = '<%= employeeId %>'; 
	 			document.getElementById('department').value = '<%= department %>'; 
	 			document.getElementById('salary').value = '<%= salary %>'; 
	 			document.getElementById('usernameError').innerText = '<%= umsg %>';
	 			document.getElementById('dob').value = '<%= dob %>'; 
	 			document.getElementById('joiningdate').value = '<%= joinDate %>';
	 		}			
	 		</script>
	 <%	}	%>  
	 <%	if(msg != null){  %>
	 		<script> 
	 		window.onload = function () {
	 			openForm('popupMessage');
	 			document.getElementById('span-popup').innerText = '<%= msg %>';
	 			setTimeout(() => {
	 				
	 				closeForm('popupMessage');
	 			  }, 3000);
	 		}			
	 		</script>
	 <%	}	%>  
	      	
<!-- VIEW PROFILE  ***********************************************************************************************-->
<div class="profile-view" id="profile-view">
		<div style="position: absolute; margin-left: 95%; margin-top: 8px;"" onclick="closeForm('profile-view')">
			<span class="material-symbols-outlined">
				close
			</span>
		</div>
	<div style="margin-left:50%; position: absolute; width: 40%; height: 50%;  background-size: 300px;	background-image: url('https://png.pngtree.com/png-vector/20220901/ourmid/pngtree-company-employee-avatar-icon-wearing-a-suit-png-image_6133899.png');">

	</div>
	<div style="margin-left:48%; margin-top: 37%; display:flex;flex-direction: column; align-self:flex-end; position: absolute; width: 50%; height: 50%; align-items: center; justify-content: center;">
				<%-- <label style="margin-top: 0px; font-size: 20px;  ">Total Task's</label>
				<label style="margin-top: 3px; font-size: 20px; "><%= totalTask %></label>
				
				<label style="margin-top: 20px; font-size: 20px; ">Remaining</label>
				<label style="margin-top: 3px; font-size: 20px;"><%= totalRemain  %></label>
				
				<label style="margin-top: 20px; font-size: 20px; ">Completed</label>
				<label style="margin-top: 3px; font-size: 20px; "><%= totalComplete %></label> --%>
		
	</div>
	<div style=" padding: 20px ; height: auto; width:70%; ">
		<span id="sname" style="font-size: 40px; margin-left: 15px;  font-weight: bold;">Prajyot Pimpale</span><br><br><br>
		<label style = "font-size: 20px;  "> Employee Id :</label> <span id="seid" style="font-size: 20px ; margin-left: 17px;">CT15</span><br><br>
		<label style = "font-size: 20px;  "> Gender :</label> <span id="sgender" style="font-size: 20px ; margin-left: 60px;">Male</span><br><br>	
		<label style = "font-size: 20px;  "> Date Of Birth :</label> <span id="sbirthdate" style="font-size: 20px ; margin-left: 10px;">2001-12-18</span><br><br>
		<label style = "font-size: 20px;  "> Email :</label> <span id="semail" style="font-size: 20px ; margin-left: 74px;">prajyot0180@gmail.com</span><br><br>	
		<label style = "font-size: 20px;  "> Mobile No. :</label> <span id="smobile" style="font-size: 20px ; margin-left: 30px;">9834980457</span><br><br>	
		<label style = "font-size: 20px;  "> Address :</label> <span id="saddress" style="font-size: 20px ; margin-left: 58px;">Pune</span><br><br>	
		<label style = "font-size: 20px;  "> Join date :</label> <span id="sjoindate" style="font-size: 20px ; margin-left: 51px;">2024-10-18</span><br><br>	
		<label style = "font-size: 20px;  "> Department :</label> <span id="sdepartment" style="font-size: 20px ; margin-left: 29px;">Developer</span><br><br>	
		<label style = "font-size: 20px;  "> Salary :</label> <span id="ssalary" style="font-size: 20px ; margin-left: 72px;">90000</span><br><br>	
	</div>
</div>


	<div class="profile-popup" id="profile-popup">
			<%
			String aname = null;
			String ausername = null;
			String amobilenumber = null;
			String agender = null;
		
			String adminUserName = (String) session.getAttribute("adminUserName");
		
			Connection con = DatabaseConnection.getConnection();
			String queryToCheckUsername="select * from p_admin where username = ? ";
			PreparedStatement pstmt1 = con.prepareStatement(queryToCheckUsername);
			pstmt1.setString(1, adminUserName);
			ResultSet rs1 = pstmt1.executeQuery();
			while(rs1.next()) {
				aname = rs1.getString("name");
				ausername = rs1.getString("username");
				amobilenumber = rs1.getString("mobile_number");
				agender = rs1.getString("gender");
			}
			con.close();
			%>
			<h1><%= aname %></h1>
			<div class="profile-img"></div>
			<div class="profile-popup1" id="profile-popup1">
				<br>
				<label style="font-size: 20px;margin-left: 8px; margin-top : 20px;  "> Username : <label style="font-size: 18px;margin-left: 56px; margin-top : 21px; "><%= ausername %></label></label>
				<label style="font-size: 20px;margin-left: 8px; margin-top : 15px;  "> Mobile number :<label style="font-size: 17px; margin-left: 19px; margin-top : 10px;  "><%= amobilenumber %></label></label>
				<label style="font-size: 20px;margin-left: 8px; margin-top : 15px;  "> Gender : <label style="font-size: 18px;margin-left: 80px; margin-top : 10px; "><%= agender %></label></label>
				<div >
					<button class="top-button-logout"  onclick ="window.location.href='logOut'">Logout</button> 
				</div>
			</div>
		</div>

<!-- TOP BUTTONS *************************************************************************************************  -->
	 <div class="container" id="container">
		<!--  -->
		<label style="font-size: 40px; margin-left: 80px;font-weight: bolder; cursor: pointer; " onclick="window.location.href='viewEmployee' ">EMS</label>
			 <span style="font-size: 40px; margin :2%; cursor: pointer; align-self: center;"  onclick="changeDisplay('profile-popup')" class="material-symbols-outlined"> 
			account_circle
		 	</span> 
		
	</div> 



<!-- VIEW EMPLOYEE ************************************************************************************************* -->	 

	 <div class="view-employee" id="view-employee">
	 	   <footer>
			  <p>&copy; 2025 EMS. All rights reserved.</p>
			</footer>
		 
	 <div style=" display: flex ; align-self: flex-start; width: 100%; ">
	 
			 <h1 style="margin-left: 38%;" >Employee Management System</h1>
			 <span class="material-symbols-outlined" style="font-size: 40px;  cursor: pointer; align-self:center;  margin-left: 24%;" onclick = "window.location.href='viewUploadFile'">
				files
			</span>
			<span class="material-symbols-outlined" style="font-size: 40px;  cursor: pointer; align-self:center;  margin-left: 1%;" onclick ="changeDisplay('upload'),closeForm('download');">
				upload_file
			</span>
			<span class="material-symbols-outlined" style="font-size: 40px;  cursor: pointer; align-self:center;  margin-left: 1%;" onclick ="changeDisplay('download'),closeForm('upload');">
				download
			</span>
			<span class="material-symbols-outlined"  style="font-size: 40px;  cursor: pointer; align-self:center;  margin-left: 1%; " onclick ="changeDisplay('employee')" >
				person_add
			</span>
			<div class="upload" id="upload">
				<form action="uploadFile" method="POST" enctype="multipart/form-data">
					<input type="file" name="file" accept=".csv,.txt" multiple="multiple" required><br>	
					<button type="submit">Upload</button>
				</form>
			</div>
			<div class="download" id="download">
				<button onclick="window.location.href='downloadCSV'">CSV</button>
				<button onclick="window.location.href='downloadEXL'">EXCEL</button>
			</div>
	</div>
	<%
    List<Employee> employeeList = (List<Employee>) request.getAttribute("employeeList");
    if (employeeList != null && !employeeList.isEmpty()) {
    	
	%>
	 	<table border="1" style="width: 100%; border-collapse: collapse; text-align: center;">
	 		<thead>
	 			<tr>
	 				<th>ID</th>
	 				<th>Name</th>
	 				<th>Gender</th>
	 				<th>Date of birth</th>
	 				<th>Email</th>
	 				<th>Mobile Number</th>
	 				<th>Address</th>
	 				<th>Joining date</th>
	 				<th>Department</th>
	 				<th>Salary</th>
	 				<th>Mentor</th>
	 				<th>Actions</th>
	 			</tr>
	 		</thead>
	 		<tbody>
	 			<%
                    for (Employee employee : employeeList) {
        %> 
	 				<tr>
	 					<td style="text-align: right;" ><%= employee.getEmployeeId() %></td>
	 					<td><%= employee.geteName() %></td>
	 					<td><%= employee.getGender() %></td>
	 					<td style="text-align: right;"><%= employee.getDob().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() %></td>
	 					<td><%= employee.getEmail() %></td>
	 					<td style="text-align: right;" ><%= employee.getMobileNumber() %></td>
	 					<td><%= employee.getAddress() %></td>
	 					<td style="text-align: right;"><%= employee.getJoinDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() %></td>
	 					<td><%= employee.getDepartment() %></td>
	 					<td style="text-align: right;"><%= employee.getSalary() %></td>
	 					<td><%= employee.getAdmin() %></td>
	 					<td style="text-align: center; ">
	 					
	 							<span class="material-symbols-outlined" onclick="openUpdateEmployee('<%= employee.getEmployeeId() %>','<%= employee.geteName() %>','<%= employee.getGender() %>','<%= employee.getDob() %>','<%= employee.getEmail() %>','<%= employee.getMobileNumber() %>','<%= employee.getAddress() %>','<%= employee.getJoinDate() %>','<%= employee.getDepartment() %>','<%= employee.getSalary() %>')">
									edit_square
								</span>
	 							<span style="margin-left: 10px;" class="material-symbols-outlined " onclick="window.location.href='deleteEmployee?eId=<%= employee.geteId() %>'">
										delete
								</span> 
								<span style="margin-left: 10px;"  class="material-symbols-outlined"  onclick="viewPerson('<%= employee.getEmployeeId() %>','<%= employee.geteName() %>','<%= employee.getGender() %>','<%= employee.getDob() %>','<%= employee.getEmail() %>','<%= employee.getMobileNumber() %>','<%= employee.getAddress() %>','<%= employee.getJoinDate() %>','<%= employee.getDepartment() %>','<%= employee.getSalary() %>')">
									person
								</span>
	 					</td>
	 				</tr>
            <% 
                }
            %>
	 		</tbody>
	 	</table>
	
<%
    } else {
%>	
	<script>
	window.onload = function () {
		openForm('employee');
	}
	</script>
<%
    }
%>
<div class="pagination">
	   
	  <% 
	  	int  currentPage = (int)request.getAttribute("currentPage");
	  	session.setAttribute("currentPage", currentPage);
	  	int noOfPages = (int)request.getAttribute("noOfPages");
	  %>
	  <% 
      // Check if we're on the first page, and if so, don't show the previous button
      if (currentPage > 1) {
   %>
      <a href="viewEmployee?page=<%= currentPage - 1 %>">❮</a>
   <% 
      }
   %>

   <% 
      // Optional: Display page numbers if desired
      for (int i = 1; i <= noOfPages; i++) {
         String activeClass = (i == currentPage) ? "active" : "";
   %>
      <a href="viewEmployee?page=<%= i %>" class="<%= activeClass %>"><%= i %></a>
   <% 
      }
   %>

   <% 
      // Check if we're on the last page, and if so, don't show the next button
      if (currentPage < noOfPages) {
   %>
      <a href="viewEmployee?page=<%= currentPage + 1 %>">❯</a>
   <% 
      }
   %>

</div>
		
</div>

<!-- UPDATE / DELETE / INSERT MESSAGE  *********************************************************************************  -->
	 <div class="pop-up" id="popupMessage">
	 <span id="span-popup">Hi</span>		
	 </div>
	 
 <!-- ADD EMPLOYEE ************************************************************************************************* -->
<div class="add-employee" id="employee">
    <form action="addEmployee" method="POST" onsubmit="return validatePasswords()">
        <h2 align="center"> Add Employee </h2>

        <!-- Employee ID -->
        <input type="text" id="employee-id" name="employee-id" pattern="^\d+$" placeholder="Enter EMPLOYEE-ID" title="Enter only numbers"required>
        <span style="color : red; margin: 15px;" id="idError"></span>

        <!-- Employee Name -->
        <input type="text" id="name" name="name" placeholder="Enter EMPLOYEE-NAME" pattern="[A-Za-z\s]+" title="Name can only contain letters and spaces" required>

        <!-- Gender -->
        <label class="gender" for="gender">Gender:</label>
        <select class="gender-selection" id="gender" name="gender" required>
            <option></option>
            <option value="male">Male</option>
            <option value="female">Female</option>
            <option value="other">Other</option>
        </select>

        <!-- Date of Birth -->
         <label class="gender" for="gender">Date of birth:</label>
        <input type="date" style ="width: 130px; " id="dob" name="dob" required >
        <!-- Employee Email -->
        <input type="email" id="email" name="email" pattern="[a-zA-Z0-9._%+-]+@gmail\.com" placeholder="Enter EMPLOYEE-EMAIL" title="Please enter a valid Gmail address (e.g., user@gmail.com)" required>
        <span style="color : red; margin: 15px;" id="emailError"></span>

        <!-- Mobile Number -->
        <input type="text" id="mobilenumber" name="mobilenumber" pattern="[1-9]{1}[0-9]{9}" maxlength="10" placeholder="Enter EMPLOYEE-MOBILE NUMBER" required>

        <!-- Address -->
        <input type="text" id="address" name="address" placeholder="Enter EMPLOYEE-ADDRESS" required>

        <!-- Joining Date -->
         <label class="gender" for="gender">Joining date:</label>
        <input type="date"  style ="width: 130px;"id="joiningdate" name="joining-date" required>

        <!-- Department -->
        <input type="text" id="department" name="department" placeholder="Enter DEPARTMENT" required>

        <!-- Salary -->
        <input type="text" id="salary" name="salary" pattern="^[0-9.]+$" title="Enter only numbers" placeholder="Enter SALARY" required>

        <!-- Username -->
        <input type="text" id="username" name="username" pattern="^[a-zA-Z0-9-_]+$" title="Username must be 3 to 15 characters long, includes only alphabets, numbers, underscore and hyphen, and does not include special symbols" placeholder="Enter EMPLOYEE-USERNAME" required>
        <span style="color : red; margin: 15px;" id="usernameError"></span>

        <!-- Password -->
        <input style="width: 84%" type="password" id="password" name="password" required minlength="8" maxlength="20"
               pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$"
               title="Password must be at least 8 characters long, include at least one uppercase letter, one lowercase letter, one number, and one special character (e.g., @, $, !)." placeholder="Enter EMPLOYEE-PASSWORD"/>
        <i class="fa fa-eye-slash" id="toggle-icon" onclick="togglePassword('toggle-icon','password')"></i>
        <span style="color: red;" id="passNotMatch"></span>	
        <!-- rePass  -->
        <input style="width: 84%" type="password" id="password1" name="password1" required minlength="8" maxlength="20" 
               pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$"
               title="Password must be at least 8 characters long, include at least one uppercase letter, one lowercase letter, one number, and one special character (e.g., @, $, !)." placeholder="CONFIRM PASSWORD"/>
        <i class="fa fa-eye-slash" id="toggle-icon1" onclick="togglePassword('toggle-icon1','password1')"></i>
       <span style="color: red;" id="passNotMatch"></span>
       
        <!-- Submit & Close Buttons -->
        <button type="submit">Submit</button>
        <button type="button" class="close-btn" id="unblur" onclick="closeForm('employee')">Close</button>
    </form>
</div>

<!--  UPDATE EMPLOYEE ************************************************************************************************* -->

<div class="add-employee" id="update-employee">
    <form action="updateEmployee" method="POST">
        <h2 align="center">Update Employee</h2>

        <!-- Employee ID (readonly) -->
        <input type="text" readonly="readonly" id="updateid" name="updateid" placeholder="Employee ID" required>

        <!-- Employee Name -->
        <input type="text" id="updatename" name="updatename" pattern="[A-Za-z\s]+" title="Name can only contain letters and spaces" placeholder="Enter Employee Name" required>

        <!-- Gender -->
        <label class="gender" for="updategender">Gender:</label>
        <select class="gender-selection" id="updategender" name="updategender" required>
            <option value="male">Male</option>
            <option value="female">Female</option>
            <option value="other">Other</option>
        </select>

        <!-- Date of Birth -->
        <label class="gender" for="updatedob">Date of Birth:</label>
        <input type="date" style="width: 130px;" id="updatedob" name="updatedob" required>

        <!-- Employee Email -->
        <input type="email" id="updateemail" name="updateemail" pattern="[a-zA-Z0-9._%+-]+@gmail\.com" placeholder="Enter Employee Email" title="Please enter a valid Gmail address (e.g., user@gmail.com)" required>

        <!-- Mobile Number -->
        <input type="text" id="updatemobilenumber" name="updatemobilenumber" pattern="[1-9]{1}[0-9]{9}" maxlength="10" placeholder="Enter Mobile Number" required>

        <!-- Address -->
        <input type="text" id="updateaddress" name="updateaddress" placeholder="Enter Address" required>

        <!-- Joining Date -->
        <label class="gender" for="updatejoiningdate">Joining Date:</label>
        <input type="date" style="width: 130px;" id="updatejoiningdate" name="updatejoiningdate" required>

        <!-- Department -->
        <input type="text" id="updatedepartment" name="updatedepartment" placeholder="Enter Department" required>

        <!-- Salary -->
        <input type="text" id="updatesalary" pattern="^[0-9.]+$" title="Enter only numbers" name="updatesalary" placeholder="Enter Salary" required>


        <!-- Submit & Close Buttons -->
        <button type="submit">Update</button>
        <button type="button" class="close-btn" onclick="closeForm('update-employee')">Close</button>
    </form>
</div>

<!-- JavaScript to Toggle Password Visibility -->
<script>
    function togglePassword() {
        var passwordField = document.getElementById("updatepassword");
        var toggleIcon = document.getElementById("toggle-icon");
        if (passwordField.type === "password") {
            passwordField.type = "text";
            toggleIcon.classList.remove("fa-eye-slash");
            toggleIcon.classList.add("fa-eye");
        } else {
            passwordField.type = "password";
            toggleIcon.classList.remove("fa-eye");
            toggleIcon.classList.add("fa-eye-slash");
        }
    }

    function closeForm(id) {
        var form = document.getElementById(id);
        form.style.display = 'none';
    }
</script>


<!-- FUNCTIONS ************************************************************************************************* -->	
	<script type="text/javascript">
	
	
	
	
			function validatePasswords() {
		        var password = document.getElementById("password").value;
		        var confirmPassword = document.getElementById("password1").value;
		        if (password !== confirmPassword) {
		        	document.getElementById("passNotMatch").innerText = "Passwords do not match!";
		            return false;
		        }
		        return true;
		    }
	
	
	  function showError(message){
		  alert(message);
     
	}  
    function openForm(formId) {
        document.getElementById(formId).style.display = 'block';
    }
    function closeForm(formId) {
    
        document.getElementById(formId).style.display = 'none';
    }
    
    function viewPerson(empId, eName, gender, dob, email, mobile, address, joinDate, department, salary){
    	changeDisplay('profile-view')
    	document.getElementById('seid').innerText = empId;
    	document.getElementById('sname').innerText = eName;
    	document.getElementById('sgender').innerText = gender;
    	document.getElementById('sbirthdate').innerText = dob;
    	document.getElementById('semail').innerText = email;
    	document.getElementById('smobile').innerText = mobile;
    	document.getElementById('saddress').innerText = address;
    	document.getElementById('sjoindate').innerText = joinDate;
    	document.getElementById('sdepartment').innerText = department;
    	document.getElementById('ssalary').innerText = salary;
    	
		
    }
    
    function openUpdateEmployee(empId, eName, gender, dob, email, mobile, address, joinDate, department, salary){
    	document.getElementById('updateid').value = empId;
    	document.getElementById('updatename').value = eName;
    	document.getElementById('updategender').value = gender;
    	document.getElementById('updatedob').value = dob;
    	document.getElementById('updateemail').value = email;
    	document.getElementById('updatemobilenumber').value = mobile;
    	document.getElementById('updateaddress').value = address;
    	document.getElementById('updatejoiningdate').value = joinDate;
    	document.getElementById('updatedepartment').value = department;
    	document.getElementById('updatesalary').value = salary;
    	
    	
    	document.getElementById('update-employee').style.display ='block';

    }

    function togglePassword(formId,password) {
		var passwordField = document.getElementById(password);
		  var toggleIcon = document.getElementById(formId);
		  
		  if (passwordField.type === 'password') {
		    passwordField.type = 'text';
		    toggleIcon.classList.remove('fa-eye');
		    toggleIcon.classList.add('fa-eye-slash');
		  } else {
		    passwordField.type = 'password';
		    toggleIcon.classList.remove('fa-eye-slash');
		    toggleIcon.classList.add('fa-eye');
			}
    }

   
   
   
   function changeDisplay(formId) {
 	

   	const element = document.getElementById(formId);
   	const style = window.getComputedStyle(element);
   
   	 if (style.display === 'none') {
   		element.style.display = 'block';
   	} else if (style.display === 'block') {
   		element.style.display  = 'none';
   	} else {
   		element.style.display  = 'none';
   	} 

	   }
	</script>
</body>
</html>



