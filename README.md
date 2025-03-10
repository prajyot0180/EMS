# **Employee Task Management System**  

## **Project Overview**  
Employee Task Management System is a **Java-based web application** designed to streamline employee task management and administrative operations. The system enables efficient task assignment, tracking, and file uploads while following the **MVC architecture**.  

## **Features**  
✅ Employee and Admin management  
✅ Task assignment and tracking  
✅ File upload functionality  
✅ Secure authentication and authorization  
✅ Database integration with **Hibernate and JDBC**  
✅ Responsive UI using JSP, HTML, and CSS  

## **Technologies Used**  
- **Backend:** Java 17, Spring Boot, Hibernate, JDBC  
- **Frontend:** JSP, HTML, CSS  
- **Database:** MySQL/Oracle  
- **Build Tool:** Maven (WAR packaging)  

## **Installation & Setup**  
### **Prerequisites**  
Ensure you have the following installed:  
- JDK 17+  
- Apache Tomcat  
- MySQL/Oracle Database  
- Maven  
- IDE (Eclipse/IntelliJ)  

### **Steps to Run the Project**  
1. **Clone the repository**  
   ```bash
   git clone https://github.com/yourusername/employee-task-management.git
   cd employee-task-management
   ```  
2. **Configure the Database**  
   - Create a database in MySQL or Oracle  
   - Update the database connection details in `hibernate.cfg.xml`  

3. **Build the project**  
   ```bash
   mvn clean install
   ```  
4. **Deploy to Tomcat**  
   - Place the generated `.war` file inside Tomcat's `webapps` folder  
   - Start the Tomcat server  
   - Access the app at `http://localhost:8080/EmployeeTaskManagement`  

## **Folder Structure**  
```
EmployeeTaskManagement/
│── src/
│   ├── main/
│   │   ├── java/com/prajyot/
│   │   │   ├── services/  # Business logic
│   │   │   ├── dao/       # Database layer
│   │   │   ├── controllers/ # MVC Controllers
│   │   ├── resources/
│   │   │   ├── hibernate.cfg.xml  # Hibernate Configuration
│   ├── webapp/
│   │   ├── WEB-INF/views/  # JSP Pages
│── pom.xml  # Maven Build Configuration
│── README.md
```

## **Contributing**  
Feel free to fork the repository, submit issues, or make pull requests!  
