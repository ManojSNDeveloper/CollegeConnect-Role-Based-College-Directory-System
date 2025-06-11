# 📘 CollegeConnect – Role-Based College Directory System

## 🔍 Overview
**CollegeConnect** is a full-stack web application designed to manage a college's internal data including students, faculty, departments, courses, and academic records. It provides **role-based dashboards** and features for:
- 🧑‍🎓 Students
- 👨‍🏫 Faculty
- 🛠 Administrators

---

## ⚙️ Tech Stack

**Frontend:**
- HTML, CSS, JavaScript

**Backend:**
- Java, Spring Boot, Hibernate (JPA), JDBC, Servlets

**Database:**
- MySQL

**Tools:**
- Postman, Git, GitHub, Eclipse, Maven

---

## 🔐 Features by Role

### Admin:
- Manage students, faculty, departments, and courses
- View student records by year/branch
- Assign faculty to departments

### Student:
- View profile, enrolled courses
- Access CGPA and attendance (academic section)
- Search departments/courses

### Faculty:
- View and update personal profile
- Manage office hours
- View department info

---

## 📁 Project Structure

```
├── backend/
│   ├── src/main/java/
│   │   ├── entities/
│   │   ├── controllers/
│   │   ├── services/
│   │   └── repositories/
│   └── application.properties
├── frontend/
│   ├── index.html
│   ├── login.html
│   ├── admin-dashboard.html
│   └── style.css
```

---

## 🚀 How to Run Locally

1. **Clone the repo**  
   ```
   git clone https://github.com/ManojSN/college-connect.git
   ```

2. **Set up MySQL DB**  
   - Create database: `college_connect`
   - Update `application.properties` with your DB credentials

3. **Run Spring Boot**  
   - With Maven:
     ```
     mvn spring-boot:run
     ```

4. **Open frontend**  
   - Open `index.html` in browser  
   - Connects to backend on `http://localhost:8080`

---

## 🌐 Live Demo (optional)
> Deployed on:
- Frontend: [GitHub Pages/Render link]
- Backend: [Render/Heroku/Railway link]

---

## 📷 Screenshots (optional)
- Login Page  
- Admin Dashboard  
- Course Management  
- Student Profile View

---

## 📚 Learning Highlights
- One-to-one and many-to-many mapping in Hibernate
- Role-based access control using enums
- REST APIs with Spring Boot
- Data Transfer Objects (DTOs) for clean API responses
- Clean separation of frontend and backend

---

## 👤 Author

**Manoj SN**  
- 📍 Bengaluru, India  
- 💼 [LinkedIn](https://linkedin.com/in/manojgowdasn)  
- 💻 [GitHub](https://github.com/ManojSN)  
- ✉️ snmanoj231@gmail.com
