<%@page import="java.time.ZoneId"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import=" java.sql.PreparedStatement" %>
 <%@ page import="java.sql.ResultSet" %>
 <%@ page import="com.prajyot.database.DatabaseConnection" %>
 <%@ page import=" java.sql.Connection" %>
  <%@ page import= "com.prajyot.pojo.EmployeeTask" %>
<%-- <%@ page import= "com.prajyot.entity.EmployeeTaskEntity" %> --%>
  <%@ page import="java.util.List" %>
  
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Task form</title>
<style type="text/css">

body {
         background-color: white;
 		   height: 100vh; 
 		   width : 180vh;
            display: block;
            justify-content: center;
            align-items: center;
            margin: 0;
            padding: 0;
            
   	     }
.background {
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
   
    z-index: -1; 
}

.task-container{
		margin-top : 10%;
		margin-left : 38%;
		display :none;
		justify-content: center;
		align-content: center;
	 	background-color: #f5fcff; 	
       	border-radius: 8px;
        padding: 30px;
        width: 400px;
        box-shadow: 0 4px 16px rgba(0, 0, 0, 1);
        height: 500px;
        position :absolute;
        z-index: 10;
}
.updatetask-container{
		margin-top : 10%;
		margin-left : 38%;
		display :none;
		justify-content: center;
		align-content: center;
	 	background-color: #f5fcff; 	
       	border-radius: 8px;
        padding: 30px;
        width: 400px;
        box-shadow: 0 4px 16px rgba(0, 0, 0, 1);
        height: 500px;
        position :absolute;
        z-index: 10;
}
.task-display-container{
		
			margin-top : 5%;
		display :flex;
            width: 99%;
            height: 87%;
        position: fixed;
        flex-flow:wrap;
    
}
.task-display-count{
	margin-top : 70px;
	background-color: #fff;
	width: 20%;
	height: 93%;
	display :flex;
	flex-direction: column; 
  	align-items: center; 
  		padding: 20px 0;
  	}
.task-display{
	display :flex;
	margin-top : 40px;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.5); 
	width: 79%;
	height: 95%;
	justify-content: flex-start;
	align-items: flex-start;
	flex-direction: column;
	
}
.view-task{
	display:flex;
	margin : 10px;
    width: 98%; 
    flex-direction: column; 
    align-items: center; 
    justify-content: center; 
	text-align: center;
  }

.task-input{
	margin : 10px;
	margin-top : 50px;
	margin-bottom : 20px;
	padding : 6px;
	width: 79%;
	height : 30px;
	border-radius: 5px;
	border: 1px solid;
} 

.task-form{
	margin: 0px;
}
.status{
	margin-left: 40px;
	margin-right: 10px;
	margin-bottom : 20px;
}
.note{
	margin-top : 10px;
	margin-bottom : 20px;
	padding : 6px;
	width: 100%;
	height : 150px;
	border-radius: 5px;
	border: 1px solid;
}
h1{
	text-align: center;
	margin: 30px;
	margin-top: 0px;
}
.form-submit {
		margin-left: 24%;
		background-color: #4CAF50;
		color: white;
		border-radius: 5px;
		padding: 10px;
    	border: none;
		cursor: pointer;
}
.form-close {
		margin-left: 24%;
		background-color: #ff4d4d;
		color: white;
		border-radius: 5px;
		padding: 10px;
    	border: none;
		cursor: pointer;
}
.date{
	margin-left: 10px;
	font-size: medium;
}
 .pop-up{
 	display : none;
 	width : 100%;
    position :absolute;
 	top: 50%;

 	text-align :center;
 
 		z-index: 10;
 }
 .pop-up span{
 	background-color:  rgba(0, 0, 0, 0.7);
 	padding: 20px;
 	font-size: xx-large;
 	border-radius: 20px;
 	
 	color : white;
 }
 .profile-icon{
 	position: absolute;
 	display :flex;
 	justify-content: flex-end;
 	width: 100%;
 	height: 70px;
 	background-color: #99ffff;
 	box-shadow: 0 1px 10px rgba(0, 0, 0, 1);
 	
 }
 .profile-icon button{
 	align-self:center;
 	margin-left : 87%;
 	width: 100px;
 	height: 40px;
 	background-color: black;
		color: white;
		border-radius: 5px;
		padding: 5px;
		text-shadow: 0 0 2px white;
		cursor: pointer;
 }
 .profile-popup{
 	position: absolute;
 	display :none;
 	justify-content: flex-start;
 	align-items:flex-start;
 	width: 350px;
 	margin-left: 80%;
 	margin-top : 65px;
 	height: 42%;
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
 	height: 26%;
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
 	margin-bottom: 0;
 }
 .top-button{
	background-color: #4CAF50;
	color : white;
	padding: 10px 20px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	text-shadow: 0px 0px 1px #737373;
	width: 100px;
	height: 35px;
	margin-left: 50px;
	margin-top: 30px;
}
.top-button-logout {
	width: 100px;
	height: 35px;
	margin-left: 34%;
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
table {
    width: 100%;
    border-collapse: collapse;
    margin: 20px 0px;
   border: 0.1px solid #d9d9d9;
    
}

table th, table td {
    border: 0px solid #ffff;
    padding: 10px;
    text-align: center;
    border-color: #d9d9d9;
    
   
}
tr:nth-child(even) {background-color: #f2f2f2}
table th{
    background-color: #d9d9d9;
    
   	border: 1px solid #f2f2f2;

}

.material-symbols-outlined{
	cursor: pointer;
}
.update-button {
    background-color: #4CAF50;
    color: white;
    border: none;
    border-radius: 5px;
	padding: 5px;
	cursor: pointer; 
}

.delete-button {
	margin-left : 20px;
    background-color: red;
    color: white;
    border: none;
    border-radius: 5px;
		padding: 5px;
		
		cursor: pointer; 
}

	
.pagination {
  
  position:absolute;
	align-self :flex-end;
	margin-right : 20px;
		margin-top: 40%;
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
	 
 footer{
        display:flex;
        position:absolute;
        	align-self: center;
        	margin-top: 41%;
        }
</style>

<link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet" /></head>
<body>
		<%  	String msg =  request.getParameter("msg");
		 %>  
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
	 
	  <div class="pop-up" id="popupMessage">
	 			<span  id="span-popup">Hi</span>		
	 	</div>
	 
	 
		
		<!-- <div style="position: fixed; margin-left: 90%" ><a href ="logOut"><p>Log out</p></a></div> -->
		<div class="profile-icon">
			<span style="font-size: 40px; margin :2%; cursor: pointer; align-self: center;"  onclick="changeDisplay('profile-popup')" class="material-symbols-outlined">
			account_circle
			</span>
		</div>
		<div class="profile-popup" id="profile-popup">
			<%
			String ename = null;
			String email = null;
			String mobilenumber = null;
			String address = null;
			String admin = null;
			String employeeUserName = (String) session.getAttribute("employeeUserName");
			int totalTask =(int)session.getAttribute("totalTask");
			int totalComplete =(int)session.getAttribute("totalCompleted");
			int totalRemain =(int)session.getAttribute("totalRemain");
			
			Connection con = DatabaseConnection.getConnection();
			String queryToCheckUsername="select employee_name,email,mobile_number,address,admin from p_employee where username = ? ";
			PreparedStatement pstmt1 = con.prepareStatement(queryToCheckUsername);
			pstmt1.setString(1, employeeUserName);
			ResultSet rs1 = pstmt1.executeQuery();
			while(rs1.next()) {
				ename = rs1.getString(1);
				email = rs1.getString(2);
				mobilenumber = rs1.getString(3);
				address = rs1.getString(4);
				admin = rs1.getString(5);
			}
			%>
			<h1><%= ename %></h1>
			<div class="profile-img"></div>
			<div class="profile-popup1" id="profile-popup1">
				<br>
				<label style="font-size: 20px;margin-left: 8px; margin-top : 20px;  "> Email : <label style="font-size: 18px;margin-left: 90px; margin-top : 21px; "><%= email %></label></label>
				<label style="font-size: 20px;margin-left: 8px; margin-top : 15px;  "> Mobile number :<label style="font-size: 17px; margin-left: 19px; margin-top : 10px;  "><%= mobilenumber %></label></label>
				<label style="font-size: 20px;margin-left: 8px; margin-top : 15px;  "> Address : <label style="font-size: 18px;margin-left: 74px; margin-top : 10px; "><%= address %></label></label>
				<label style="font-size: 20px;margin-left: 8px; margin-top : 15px;  "> Role : <label style="font-size: 18px;margin-left: 103px;  ">Employee</label></label>
				<div >
					<button class="top-button-logout"  onclick ="window.location.href='logOut'">Logout</button> 
				</div>
			</div>
		</div>
		
		
		
		
	 	
	 	<div class="task-display-container">
	 		
			<div class="task-display-count">
				
			
				<label style="margin-top: 30px; font-size: 50px; text-shadow: 0 1px 3px rgba(0,0,0,0.5); ">Total Task's</label>
				<label style="margin-top: 30px; font-size: 50px; "><%= totalTask %></label>
				
				<label style="margin-top: 90px; font-size: 50px;text-shadow: 0 1px 3px rgba(0,0,0,0.5) ">In Progress</label>
				<label style="margin-top: 30px; font-size: 50px;"><%= totalRemain  %></label>
				
				<label style="margin-top: 90px; font-size: 50px; text-shadow: 0 1px 3px rgba(0,0,0,0.5)">Completed</label>
				<label style="margin-top: 30px; font-size: 50px; "><%= totalComplete %></label>
			</div>
			
			<div class="task-display">
					   <footer>
						  <p>&copy; 2025 EMS. All rights reserved.</p>
						</footer>
						<div style="display: flex; align-self: flex-start; width: 100%; ">
							 	<label style="margin-top: 0px; font-size: 40px; margin-left: 48%; ">Task's</label>
							 	<span class="material-symbols-outlined" style="font-size: 40px; margin-left : 42%; cursor: pointer; align-self: center; " onclick ="changeDisplay('task-container')" >
									add_circle
								</span>
					 	</div>
					<%
					     List<EmployeeTask> employeeTaskList = (List<EmployeeTask>) request.getAttribute("employeeTaskList");
					    if (employeeTaskList != null && !employeeTaskList.isEmpty()) { 
					%>
						
					 <div class="view-task" id="view-task" >
				
						<table border="" style="width: 100%; border-collapse: collapse; text-align: center;">
					    <thead>
					        <tr>
					            <th style=" width: 100px;">Date</th>
					            <th style=" width: 100px;">Last update</th>
					            <th style=" width: 300px;">Task name</th>
					            <th style=" width: auto;;">Note</th>
					            <th style=" width: 100px;">Status</th>
					            <th style=" width: 150px;">Action</th>
					            
					        </tr>
					    </thead>
					    <tbody>
					        <% for (EmployeeTask employeeTask : employeeTaskList) { %>
					            <tr>
					                <td style=" text-align: right;"><%= employeeTask.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()  %></td>
					                <td style=" text-align: right;"><%= employeeTask.getLastUpdate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() %></td>
					                <td style=" text-align: left;"><%= employeeTask.getTaskName() %></td>
					                <td style=" text-align: left;"><%= employeeTask.getNote() %></td>
					                <td style=" text-align: left;"><%= employeeTask.getStatus() %></td>
					                <td style="text-align: center; ">
	 					
			 							<span class="material-symbols-outlined" onclick="updateTask('<%= employeeTask.getDate() %>','<%= employeeTask.getTaskName() %>','<%= employeeTask.getNote() %>','<%= employeeTask.getStatus() %>','<%= employeeTask.getId() %>')">
											edit_square
										</span>
			 							<span style="margin-left: 10px;" class="material-symbols-outlined " onclick="window.location.href='deleteTask?taskId=<%= employeeTask.getId() %>'">
												delete
										</span> 
	 					
					                	<%-- <button  class="update-button" onclick="updateTask('<%= employeeTask.getDate() %>','<%= employeeTask.getTaskName() %>','<%= employeeTask.getNote() %>','<%= employeeTask.getStatus() %>','<%= employeeTask.getId() %>')">update</button>
					                 	<button  class="delete-button" onclick="window.location.href='deleteTask?taskId=<%= employeeTask.getId() %>'">Delete</button>
					              --%>   </td>
					            </tr>
					            
					        <% } %>
					    </tbody>
						</table>
					
			<% } /* else {} */ %>
					   <%--  <label style=" font-size: 40px; margin: 40%;opacity: 0.3">No Task found</label>
			<% } %>  --%>
							
					
			</div>
					<div class="pagination">
								     <% 
								  	int  currentPage = (int)request.getAttribute("currentPageTask");
								  	int noOfPages = (int)request.getAttribute("noOfPagesTask");
								  %>
								  <% 
							      // Check if we're on the first page, and if so, don't show the previous button
							      if (currentPage > 1) {
							   %>
							      <a href="employeeTask?page=<%= currentPage - 1 %>">❮</a>
							   <% 
							      }
							   %>
							
							   <% 
							      // Optional: Display page numbers if desired
							      for (int i = 1; i <= noOfPages; i++) {
							         String activeClass = (i == currentPage) ? "active" : "";
							   %>
							      <a href="employeeTask?page=<%= i %>" class="<%= activeClass %>"><%= i %></a>
							   <% 
							      }
							   %>
							
							   <% 
							      // Check if we're on the last page, and if so, don't show the next button
							      if (currentPage < noOfPages) {
							   %>
							      <a href="employeeTask?page=<%= currentPage + 1 %>">❯</a>
							   <% 
							      }
							   %> 
				</div>		
			</div>
			
		</div>
	 	
<!-- Add Task ************************************************************************************************************************-->	 	
		<div class="task-container" id="task-container">
			<h1>Upload your task</h1>
			<form class="task-form" action="employeeTask" method="POST">
			<lable>Task   : </lable>
			<input type="text" class="task-input" name="task" placeholder="Enter today's task" required>
			<lable>Status :</lable>
			<input type="radio" class="status" name="status" value="completed"required>Completed
			<input type="radio" class="status" name="status" value="in progress"required/>In progress<br>
			<lable>Date : <span id="date" class="date"></span> </lable><br><br>
			<lable>Note : </lable>
			<input type="text" class="note" name="note" placeholder="Enter note" required>
			<div >
				<button class="form-submit" type="submit">Submit</button>
				<button class="form-close" onclick="closeForm('task-container')">Close</button>	
			</div>
			</form>
		</div>
		
<!-- Update Task ************************************************************************************************************************-->	 	
		<div class="updatetask-container" id="updatetask-container">
			<h1>Update your task</h1>
			<form class="task-form" action="updateTask" method="POST">
					
					<input type="hidden" id="idvalue" name="idvalue">
					
					<lable>Task   : </lable>
					<input type="text" class="task-input" id="updatetask" name="updatetask" placeholder="Enter today's task" required>
					
					<lable>Status :</lable>
					<input type="radio" class="status" id="updatestatus" name="updatestatus" value="completed"required>Completed
					<input type="radio" class="status" id="updatestatus" name="updatestatus" value="in progress"required/>In progress<br>
					
					<lable>Date : <span id="updatedate" class="date"></span> </lable><br><br>
					<input type="hidden" id="updateDateValue" name="updatedate">
					
					<lable>Note : </lable>
					<input type="text" class="note" id="updatenote" name="updatenote" placeholder="Enter note" required>
					
					<div >
						<button class="form-submit" type="submit">Submit</button>
						<button class="form-close" onclick="closeForm('updatetask-container')">Close</button>	
					</div>
			</form>
		</div>
		

		<script type="text/javascript">
		
		 function updateTask(date,taskName,note,status,id){
			 	
		    	document.getElementById('updatedate').innerText = date;
		    	document.getElementById('updateDateValue').value = date;
		    	document.getElementById('updatetask').value = taskName;
		    	document.getElementById('updatenote').value = note;
		    	document.getElementById('idvalue').value = id;

		    	document.getElementById('updatetask-container').style.display ='block';

		    }
		
		
		 const currentDate = new Date();
		 const formattedDate = currentDate.toLocaleDateString('en-GB', {
			 year: 'numeric',
		     month: '2-digit',
		     day: '2-digit'
		 });

		 document.getElementById('date').innerText = formattedDate;
				
				  function openForm(formId) {
				        document.getElementById(formId).style.display = 'block';
				    }
				    function closeForm(formId) {
				    
				        document.getElementById(formId).style.display = 'none';
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