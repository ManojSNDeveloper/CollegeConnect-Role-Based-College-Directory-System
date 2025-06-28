# 🎓 CollegeConnect – Role-Based College Directory System

CollegeConnect is a Spring Boot-based web application designed as a role-based college directory system for Admins, Faculty, and Students. The backend uses REST APIs documented with Swagger, and MySQL is used for data storage.

---

## 🚀 Features

- 👨‍💼 **Admin**: Full control over users, departments, faculty, and students.
- 👩‍🏫 **Faculty**: View and update own profile, view department info.
- 👨‍🎓 **Student**: Manage their own profile, view courses and faculty.
- 🔐 Role-based login and access control
- 🔄 REST APIs with **Swagger documentation**
- 🗃️ MySQL database
- 🌐 Simple HTML/CSS/JavaScript frontend (served from `static/` folder)

---

## 🛠️ Tech Stack

- Java 17  
- Spring Boot  
- Spring MVC  
- Spring Data JPA (Hibernate)  
- MySQL  
- Swagger (SpringFox or springdoc-openapi)  
- HTML, CSS, JavaScript  
- Spring Tool Suite (STS 4)  
- Postman (for testing)

---

## 📁 Project Structure

college-connect/
├── src/
│ ├── main/
│ │ ├── java/com/collegeconnect/
│ │ │ ├── controller/
│ │ │ ├── entity/
│ │ │ ├── service/
│ │ │ └── repository/
│ │ └── resources/
│ │ ├── static/
│ │ └── application.properties
├── pom.xml
└── README.md

## 2. Configure application.properties

spring.datasource.url=jdbc:mysql://localhost:3306/college_connect
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect

## 3. Run Project

App runs at:
➡️ http://localhost:8080

## 📘 Swagger API Documentation
Swagger UI available at:
➡️ http://localhost:8080/swagger-ui.html
or
➡️ http://localhost:8080/swagger-ui/ (springdoc-ui version)

Use it to test APIs like:
GET /api/departments
POST /api/faculty
GET /api/students/{id}

## 👨‍💻 Author
Manoj S N
Java Full Stack Developer
📍 Bengaluru, India
📧 manojsn.dev@gmail.com
