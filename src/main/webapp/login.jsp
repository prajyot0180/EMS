<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            
            display: flex;
            height: 80vh;
            margin: 0;
            justify-content: center;
            align-items: center;
            background-image: url("https://img.freepik.com/free-vector/blue-abstract-background-business_53876-100597.jpg?t=st=1738733701~exp=1738737301~hmac=1677ea27e5b75299b2533a74aae180fc037a5263f5682fc06193bcb2f56656ba&w=1380");           
            background-size: cover;
        }
        .container{
        	display: flex;
        	width : 80%;
        	height : 100%;
        	margin-top : 200px;
        	background-color: white;
        	border-radius: 50px;	
        	 box-shadow: 0 0px 20px rgba(0, 0, 0, 0.5);
        } 
        .login-img{
        	background-image: url("https://img.freepik.com/premium-vector/woman-sits-floor-with-phone-that-says-get-up_1314854-10356.jpg?semt=ais_incoming");
        	background-size :contain;
        	margin-left : 80px;
        	width: 54%;
        	height : 100%;
        }
        .login-container {
        	
        	margin-left : 52%; 
        	align-self :center;
        	position : absolute;
            background-color: #fff;
            border-radius: 8px;
            padding: 30px;
            width: 400px;
            box-shadow: 0 4px 16px rgba(0, 0, 0, 1);
            height: 450px;
   
        }
       .forgotPassword{
       		display : none;
       			margin-left : 52%; 
        	align-self :center;
        	position : absolute;
            background-color: #fff;
            border-radius: 8px;
            padding: 30px;
            width: 400px;
            box-shadow: 0 4px 16px rgba(0, 0, 0, 1);
            height: auto;
       }
       
        h2 {
        font-family: 'Garamond', serif;
            text-align: center;
            color: #333;
        }
        label {
            font-size: 14px;
            color: #555;
            margin-bottom: 5px;
            display: block;
        }
        input {
            width: 90%;
            padding: 10px;
            margin: 8px 0;
            border-radius: 5px;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
            font-size: 16px;
            margin-top: 15px;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .login-container p,.forgotPassword p {
            text-align: center;
            font-size: 14px;
            color: #777;
        }
        .login-container p a,.forgotPassword p a {
            color: #4CAF50;
            text-decoration: none;
        }
 		
		video {
		  width: 100%;
		  height: 100%;
		  object-fit: cover; 
		}
		 .error{
        	color:black;
        	
        	}
        	#toggle-icon ,#toggle-icon1,#toggle-icon2{
  				cursor: pointer;	
			}
	.pop-up{
		 	display :none;
		 	position: absolute;
		 	top: 40%;
		 	background-color:  rgba(0, 0, 0, 0.9);
		 	padding: 20px;
		 	font-size: xx-large;
		 	border-radius: 20px;
		 	color : white;
		 	z-index: 12;
		 	
		 }			
	.role-select {
            margin: 10px 0;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            width: 90%;
            font-size: 16px;
        }
        footer{
        display:flex;
        position:absolute;
        	align-self: flex-end;
        	margin-left: 33%;
        }
        header{
        	  display:flex;
        	position:absolute;
        	font-size : 35px;
        	margin-left: 26%;
        	margin-top: -15px;
        	font-weight :bold;
        	font-family: 'Garamond', serif;
        }
    </style>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
		<%-- <%	
			String sessionExpired = (String) request.getAttribute("sessionExpired"); 
        	if(sessionExpired.isEmpty()){
        		response.sendRedirect("login.jsp");
        	}
			if (!sessionExpired.equals("")) {
        %>
           <script> 
	 		window.onload = function () {
	 			
	 			openForm('popupMessage');
	 			
	 			document.getElementById('span-popup').innerText = '<%= sessionExpired %>';
	 			setTimeout(() => {
	 				document.getElementById('popupMessage').style.display = 'none';
	 			  }, 3000);
	 		}			
	 		</script>
        <%
            }
        %> --%>

<%
String msg =  request.getParameter("msg");
String error = (String)request.getAttribute("errorMessage1");
String uname = (String)request.getAttribute("username1");
%> 
 <%	if(msg != null){ %>
	 		<script> 
	 		window.onload = function () {
	 			
	 			openForm('popupMessage');
	 			
	 			document.getElementById('span-popup').innerText = '<%= msg %>';
	 			setTimeout(() => {
	 				document.getElementById('popupMessage').style.display = 'none';
	 			  }, 3000);
	 		}			
	 		</script>
	 <%	}	%>   
 <%	if(error != null){ %>
	 		<script> 
	 		window.onload = function () {
	 			openForm('forgotPassword');
	 			closeForm('login-container');
	 			document.getElementById('role-select1').value = '<%= uname %>';
	 			document.getElementById('usernameNotMatch').innerText = '<%= error %>';
	 		}			
	 		</script>
	 <%	}	%>   

<!-- UPDATE / DELETE / INSERT *************************************************************************************  -->
	 <div class="pop-up" id="popupMessage">
	 <span id="span-popup">Hi</span>		
	 </div>


	 
  
 <div class="container">
 <header>
 	<p>Employee Management System</p>
 </header>
<footer>
  <p>&copy; 2025 EMS. All rights reserved.</p>
</footer>
  	<div class="login-img" id="login-img">
  		
  	</div>
<!-- LOG In ************************************************************************************************************************  -->
    <div class="login-container" id="login-container">
        <h2 > Login </h2>
      
        <form action="login" method="POST">
        	<div class="form-group">
	        	  <select class="role-select" name="role" id="role-select" required>
	                <option value="">Select Role</option>
	                <option value="admin">Admin</option>
	                <option value="employee">Employee</option>
	            </select> 
             </div>
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
               <i class="fa fa-eye-slash" id="toggle-icon" onclick="togglePassword('toggle-icon','password')"></i>
                
            </div>
            <label style="color: red;" class="error">
                	<c:if test=${not empty errorMessage}>
                		${errorMessage}
                		<script>
                			document.getElementById('role-select').value = '<%= request.getAttribute("role") %>'; 
                		</script>
                	</c:if>           	
                </label>
              <label style="cursor: pointer; " onclick="openForm('forgotPassword'),closeForm('login-container')" >Forgot password?</label>
            <input type="submit" value="login">
            
        </form>
        
           <p>For signup form <a href="signup.jsp">sign up</a></p>
    </div>

 <!-- FORGOT PASSWORD ************************************************************************************************************************** -->
  
  
  
  <div class="forgotPassword" id="forgotPassword">
        <h2> Forgot Password </h2>
      
        <form action="forgotPassword" method="POST" onsubmit="return validatePasswords()">
        	<div class="form-group" >
	        	  <select class="role-select" name="role1" id="role-select1" required>
	                <option value="">Select Role</option>
	                <option value="admin">Admin</option>
	                <option value="employee">Employee</option>
	            </select> 
             </div>
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username1" name="username1" required>
                <span style="color: red;" id="usernameNotMatch"></span>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password1" name="password1" required minlength="8" maxlength="20" 
                					pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$" 
                					title="Password must be at least 8 characters long, include at least one uppercase letter, 
                					one lowercase letter, one number, and one special character (e.g., @, $, !)." />  
                 <i class="fa fa-eye-slash" id="toggle-icon2" onclick="togglePassword('toggle-icon2','password1')"></i>
                
            </div>
            <div class="form-group">
                <label for="password">Enter password again:</label>
               <input type="password" id="password2" name="password2" required minlength="8" maxlength="20" 
                					pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$" 
                					title="Password must be at least 8 characters long, include at least one uppercase letter, 
                					one lowercase letter, one number, and one special character (e.g., @, $, !)." />  
                 <i class="fa fa-eye-slash" id="toggle-icon1" onclick="togglePassword('toggle-icon1','password2')"></i>
                 <span style="color: red;" id="passNotMatch"></span>
            </div>
  
          
            <input type="submit" value="Reset">
            
        </form>
        
           <p>For signup form <a href="signup.jsp">sign up</a></p>
           <p>For login form <a href="login.jsp">Login here</a></p>
    </div>
    
  </div>    
  <!--SCRIPT ****************************************************************************************************************  -->
    <script type="text/javascript">
    
    
		    function validatePasswords() {
		        var password = document.getElementById("password1").value;
		        var confirmPassword = document.getElementById("password2").value;
		        if (password !== confirmPassword) {
		        	document.getElementById("passNotMatch").innerText = "Passwords do not match!";
		            return false;
		        }
		        return true;
		    }
    
    
	    function openForm(formId) {
	        document.getElementById(formId).style.display = 'block';
	        changeImg();
	    }
	    function closeForm(formId) {
	    
	        document.getElementById(formId).style.display = 'none';
	        changeImg();
	    }
	    
	     function changeImg() {
	        var loginDiv = document.getElementById('login-container');
	        var forgotDiv = document.getElementById('forgotPassword');
	        var loginImg = document.getElementById('login-img');

	        
	        var loginDivDisplay = window.getComputedStyle(loginDiv).display;
	        var forgotDivDisplay = window.getComputedStyle(forgotDiv).display;

	        if (loginDivDisplay === 'block' && forgotDivDisplay === 'none') {
	            
	            loginImg.style.backgroundImage = "url('https://img.freepik.com/premium-vector/woman-sits-floor-with-phone-that-says-get-up_1314854-10356.jpg?semt=ais_incoming')";
	        } else {
	           
	            loginImg.style.backgroundImage = "url('https://img.freepik.com/free-vector/two-factor-authentication-concept-illustration_114360-30848.jpg?t=st=1738675277~exp=1738678877~hmac=fec7dc8a653b59106d9f60a929ad6e32553ea2419b22c6e33ce6365b4e5870ff&w=826')";
	        }
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
    </script>
</body>
</html>
