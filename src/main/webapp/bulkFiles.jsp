<%@page import="java.time.ZoneId"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.prajyot.pojo.UploadFiles" %>
<%@ page import="java.util.List" %>
<%@ page import="jakarta.servlet.http.HttpSession"%>
<%@ page import="java.sql.PreparedStatement" %>
 <%@ page import="java.sql.ResultSet" %>
 <%@ page import="com.prajyot.database.DatabaseConnection" %>
 <%@ page import="java.sql.Connection" %>
  <%@ page import= "com.prajyot.pojo.Admin" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bulk Files</title>

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


.view-files{
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
    border: 1px solid balck;
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
	 footer{
        display:flex;
       	position :absolute;
        	align-self:flex-end;
        	margin-top: 39%;
        	margin-right:  42%;
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

	 	String msg =  request.getParameter("msg");
	 	
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

	 <div class="view-files" id="view-files">
	 	   <footer>
			  <p>&copy; 2025 EMS. All rights reserved.</p>
			</footer>
		 
	 <div style=" display: flex ; align-self: flex-start; width: 100%; ">
			 <h1 style="margin-left: 43%;" >Uploaded File's</h1>
			 <button style="margin-left: 38%; height: 40px ;align-self: center;  background-color: #4CAF50; color: white; border : none" onclick="window.location.href='viewEmployee'">View Employee</button>
	</div>
 	<%
    List<UploadFiles> uploadList = (List<UploadFiles>) request.getAttribute("uploadList");
    if (uploadList != null && !uploadList.isEmpty()) {
    	
	%>
	 	<table border="1" style="width: 100%; border-collapse: collapse; text-align: center;">
	 		<thead>
	 			<tr>
	 				<th>Date</th>
					<th>Filename</th>
					<th>Admin</th>
					<th>Saved Data</th>
					<th>Rejected Data</th>
					<th>Download Rejected Data</th>
	 			</tr>
	 		</thead>
	 		<tbody>
	 			<%
                    for (UploadFiles uploadfile : uploadList) {
        %> 
	 				<tr>
	 					<td style="text-align: right; width: 15%" ><%= uploadfile.getUploadDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()  %></td>
	 					<td style="	 width: 40%"><%= uploadfile.getFileName() %></td>
	 					<td><%= uploadfile.getAdmin() %></td>
	 					<td style="text-align: right;"><%= uploadfile.getAddData() %></td>
	 					<td style="text-align: right;" ><%= uploadfile.getNotAddData() %></td>
	 					<td style="text-align: center; width: 12% ">
							<span class="material-symbols-outlined" onclick="window.location.href='downloadSkipedCSV?fileName=<%= uploadfile.getFileName() %> '">
									download
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
<%
    }
%> 
<div class="pagination">
	   
	 <%--  <% 
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
   %> --%>

</div>
		
</div>

<!-- UPDATE / DELETE / INSERT MESSAGE  *********************************************************************************  -->
	 <div class="pop-up" id="popupMessage">
	 <span id="span-popup">Hi</span>		
	 </div>
	 
 <!-- ADD EMPLOYEE ************************************************************************************************* -->

<!-- JavaScript to Toggle Password Visibility -->

<!-- FUNCTIONS ************************************************************************************************* -->	
	<script type="text/javascript">
	
	
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



