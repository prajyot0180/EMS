<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Signup Form</title>
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
        	background-image: url("https://img.freepik.com/free-vector/forms-concept-illustration_114360-4797.jpg?t=st=1738732436~exp=1738736036~hmac=468595fbc60ac2bc5272f1f35810726e070a9a11ae915493d0970b4bc6b50e64&w=1380");
        	background-size :cover;
        	margin-left : 20px;
        	width: 60%;
        	height : 70%;
        	align-self: center;
        	
        }
        .signup-container {
        	margin-left : 52%; 
        	align-self :center;
        	position : absolute;
            background-color: #fff;
            border-radius: 8px;
            padding: 30px;
            width: 400px;
            box-shadow: 0 4px 16px rgba(0, 0, 0, 1);
            
        }
        h2 {
        font-family: 'Garamond', serif;
            text-align: center;
            color: #333;
        }
        label {
            font-size: 14px;
            color: #555;
            margin-bottom: 1px;
            display: block;
        }
        input, select {
            width: 100%;
            padding: 10px;
            margin: 5px ;
            border-radius: 5px;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }
        #password{
        	width: 90%;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
            font-size: 16px;
            margin-top: 8px;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        .form-group {
            margin-bottom: 5px;
        }
        .signup-container p {
            text-align: center;
            font-size: 14px;
            color: #777;
        }
        .signup-container p a {
            color: #4CAF50;
            text-decoration: none;
        }
        .signup-container lable {
        	font-size: large;
        	color : black;
        }
        
		video {
		  width: 100%;
		  height: 100%;
		  object-fit: cover; 
		}
	#toggle-icon , #toggle-icon1 {
  				cursor: pointer;	
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


<!--  <div class="video-background">
    <video autoplay loop muted>
      <source src="https://netbanking.kotak.com/OPR/fpass/Images/Offers/SERVICE%20REQUEST.mp4" type="video/mp4">
      Your browser does not support the video tag.
    </video>
  </div> -->
  
  <div class="container">
  <header>
 	<p>Employee Management System</p>
 </header>
<footer>
  <p>&copy; 2025 EMS. All rights reserved.</p>
</footer>
  
  	<div class="login-img" id="login-img">
  		
  	</div>
    <div class="signup-container">
        <h2>Create Account</h2>
        <form action="addAdmin" method="POST" onsubmit="return validatePasswords()">
            <div class="form-group">
                <label for="name">Full Name:</label>
                <input type="text" id="name" name="name" pattern="[A-Za-z\s]+" title="Name can only contain letters and spaces" required>
            </div>
            <div class="form-group">
                <label for="gender">Gender:</label>
                <select id="gender" name="gender" required>
                	<option  ></option>
                    <option value="male">Male</option>
                    <option value="female">Female</option>
                    <option value="other">Other</option>
                </select>
            </div>
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" pattern="^[A-Za-z0-9_-]{3,15}$" tittle="Username must be 3 to 15 charachters long, includes 
                			only alphabets,numbers,underscore and hypen not include special symbol" required>
                <span>
                <c:if test="${not empty errorMessage}">
            	<div style="color: red;">${errorMessage}</div>
        		</c:if> 
        					
        		</span>
            </div>
             
            <div class="form-group">
                <label for="mobilenumber">Mobile Number:</label>
                <input type="text" id="mobilenumber" name="mobilenumber" pattern="[1-9]{1}[0-9]{9}" maxlength="10" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required minlength="8" maxlength="20" 
                					pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$" 
                					title="Password must be at least 8 characters long, include at least one uppercase letter, 
                					one lowercase letter, one number, and one special character (e.g., @, $, !)." />  
                 <i class="fa fa-eye-slash" id="toggle-icon" onclick="togglePassword('toggle-icon','password')"></i>
                 
                 <label for="password">Confirm Password:</label>
                <input style="width: 90%;" type="password" id="password1" name="password1" required minlength="8" maxlength="20" 
                					pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$" 
                					title="Password must be at least 8 characters long, include at least one uppercase letter, 
                					one lowercase letter, one number, and one special character (e.g., @, $, !)." />  
        		<i class="fa fa-eye-slash" id="toggle-icon1" onclick="togglePassword('toggle-icon1','password1')"></i>
                 <span style="color: red;" id="passNotMatch"></span>
                 
            </div>
            <lable>
        		<c:if test="${not empty successMessage}">
				${successMessage}
				
				</c:if>
			</lable> 
				
            <input type="submit" value="signup">
        </form>
        
        <p>For login form <a href="login.jsp">Login here</a></p>
    </div>
 </div>
    
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
