# Java JDBC – Employee Database App

## 📌 Objective
A Java console-based application that connects to a MySQL or PostgreSQL database using JDBC and performs **CRUD** (Create, Read, Update, Delete) operations on an `employees` table.

---

## 🛠 Tools & Technologies
- **Java** (JDK 8+)
- **MySQL** or **PostgreSQL**
- **JDBC Driver**
  - MySQL → `mysql-connector-j-x.x.xx.jar`
  - PostgreSQL → `postgresql-42.x.x.jar`
- **Eclipse IDE** (or IntelliJ / VS Code)

---

## 📂 Project Structure

EmployeeDatabaseApp/
├── lib/ (JDBC driver .jar)
├── src/
│ └── EmployeeApp.java
└── README.md


---

## 🗄 Database Setup

### **For MySQL**
```sql
CREATE DATABASE company_db;

USE company_db;

CREATE TABLE employees (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    position VARCHAR(100),
    salary DOUBLE
);

⚙️ Configuration
private static final String URL = "jdbc:mysql://localhost:3306/company_db";
private static final String USER = "root"; // your DB username
private static final String PASSWORD = "12345678"; // your DB password


▶️ How to Run in Eclipse

Create New Java Project → EmployeeDatabaseApp.

Add JDBC Driver JAR

Right-click Project → Properties → Java Build Path → Libraries → Add External JARs.

Create EmployeeApp.java in src folder and paste the code.

Run Database Server (MySQL or PostgreSQL).

Run Program:

Right-click EmployeeApp.java → Run As → Java Application.

Use menu in console to Add, View, Update, Delete employees.

💻 Example Output
=== Employee Database Menu ===
1. Add Employee
2. View Employees
3. Update Employee
4. Delete Employee
5. Exit
Choose option: 1
Enter name: John
Enter position: Manager
Enter salary: 55000
Employee added successfully!

📜 Features

Add new employees to the database.

View all employees in a tabular format.

Update existing employee records.

Delete employees by ID.

Uses PreparedStatement for secure SQL execution.
