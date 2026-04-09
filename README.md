# 🎓 Student Management System

A full-stack web application to manage student records — built with **Java Servlets**, **Oracle Database**, and **HTML/CSS**, deployed on Apache Tomcat.

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Oracle](https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white)
![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white)
![CSS3](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white)
![Apache Tomcat](https://img.shields.io/badge/Tomcat-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=black)

---

## 🚀 Features

| Feature | Description |
|---|---|
| ✅ Add Student | Form with client + server side validation |
| ✅ View All Students | Dynamic table loaded from Oracle DB |
| ✅ Edit Student | Pre-filled form with instant DB update |
| ✅ Delete Student | Confirmation dialog before deletion |
| ✅ Live Search | Real-time filter by name or course |
| ✅ Grade Badges | Auto-calculated A / B / C / F from marks |
| ✅ Stats Dashboard | Total students, top performers, average marks |

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Frontend | HTML5, CSS3, JavaScript |
| Backend | Java Servlets (Jakarta EE) |
| Database | Oracle Database (JDBC) |
| Server | Apache Tomcat 11 |
| IDE | Eclipse IDE for Enterprise Java |

---

## 📁 Project Structure

```
StudentManagementSystem/
├── src/main/java/com/sms/
│   ├── DBConnection.java           # Oracle JDBC connection
│   ├── Student.java                # Student model class
│   ├── StudentDAO.java             # All DB operations (CRUD)
│   ├── AddStudentServlet.java      # Handles add form (POST)
│   ├── ListStudentServlet.java     # Shows all students (GET)
│   ├── EditStudentServlet.java     # Handles edit form (GET/POST)
│   └── DeleteStudentServlet.java   # Handles delete (GET)
└── src/main/webapp/
    ├── index.html                  # Student list page
    ├── add-student.html            # Add student form
    ├── edit-student.html           # Edit student form
    ├── css/
    │   └── style.css               # All styles
    └── WEB-INF/
        ├── web.xml                 # Tomcat deployment descriptor
        └── lib/
            └── ojdbc11.jar         # Oracle JDBC driver
```

---

## ⚙️ Setup Instructions

### Prerequisites
- Java JDK 11 or higher
- Oracle Database (with user `advjava` created)
- Apache Tomcat 10.x or 11.x
- Eclipse IDE for Enterprise Java
- `ojdbc11.jar` — [Download here](https://www.oracle.com/database/technologies/appdev/jdbc-downloads.html)

---

### Step 1 — Oracle Database Setup

Connect to Oracle as `advjava` and run:

```sql
-- Create students table
CREATE TABLE students (
    id         NUMBER PRIMARY KEY,
    name       VARCHAR2(100) NOT NULL,
    email      VARCHAR2(100) NOT NULL UNIQUE,
    phone      VARCHAR2(15)  NOT NULL,
    course     VARCHAR2(100) NOT NULL,
    marks      NUMBER(5,2)   NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Auto-increment sequence
CREATE SEQUENCE students_seq
    START WITH 1 INCREMENT BY 1 NOCACHE;

-- Trigger to use sequence on INSERT
CREATE OR REPLACE TRIGGER students_trg
BEFORE INSERT ON students FOR EACH ROW
BEGIN
    :NEW.id := students_seq.NEXTVAL;
END;
/

-- Sample data
INSERT INTO students (name,email,phone,course,marks)
VALUES ('Ravi Kumar','ravi@example.com','9876543210','B.Tech CSE',85.50);
COMMIT;
```

---

### Step 2 — Configure Database Connection

Open `src/main/java/com/sms/DBConnection.java` and update:

```java
private static final String URL      = "jdbc:oracle:thin:@localhost:1521:ORCLDB";
private static final String USERNAME = "advjava";
private static final String PASSWORD = "your_password";
```

---

### Step 3 — Setup in Eclipse

1. **File → New → Dynamic Web Project**
2. Project name: `StudentManagementSystem`
3. Target runtime: Apache Tomcat v10/v11
4. Add `ojdbc11.jar` to `WEB-INF/lib/` and Build Path
5. Copy all Java files into `src/main/java/com/sms/`
6. Copy HTML/CSS into `src/main/webapp/`

---

### Step 4 — Run

1. Right-click project → **Run As → Run on Server**
2. Select **Apache Tomcat**
3. Open browser at:

```
http://localhost:9090/StudentManagementSystem/ListStudentServlet
```

---

## 📸 Screenshots

> 📌 Add screenshots here after running the app!
 <img width="1731" height="800" alt="image" src="https://github.com/user-attachments/assets/b3d43cbe-0f4a-49d3-90ad-6628290d7bca" />

<img width="1731" height="876" alt="image" src="https://github.com/user-attachments/assets/513c0451-fa90-46ff-beef-881574d30f2f" />

 <img width="1731" height="800" alt="image" src="https://github.com/user-attachments/assets/42590f38-6861-43cb-9df2-27c8ca61e83e" />

---

## 🔑 Key Concepts Used

- **DAO Pattern** — Separates database logic from servlet logic
- **MVC Architecture** — Model (Student.java), View (HTML), Controller (Servlets)
- **PreparedStatements** — Prevents SQL Injection attacks
- **Oracle Sequences + Triggers** — Auto-increment IDs in Oracle
- **Jakarta EE Servlets** — Server-side Java for handling HTTP requests
- **Session Management** — HTTP request/response lifecycle

---

## 👨‍💻 Author

**Bale Subba Raju**
- 🔗 LinkedIn: [linkedin.com/in/subba-raju-bale-aa0178354](https://linkedin.com/in/subba-raju-bale-aa0178354)
- 📧 Email: subbaraju538@gmail.com
- 💻 GitHub: [github.com/Subba-Raju](https://github.com/Subba-Raju)

---

## 📄 License

MIT License — feel free to use and modify.
