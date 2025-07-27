# 🏥 Hospital Management System

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

A comprehensive **Java-based Hospital Management System** with **MySQL** integration designed to manage patients, doctors, and appointments efficiently within a healthcare facility.

---

## 📋 Description

This system allows hospital staff to:
- Add and manage patient and doctor information
- Schedule and track appointments
- Check doctor availability in real-time
- View patient-specific appointment records

It uses **JDBC** for database connectivity and is built for educational and demonstration purposes.

---

## 🌟 Key Features

- 👤 Patient Management
- 🩺 Doctor Directory with Room Allocation
- 📅 Appointment Booking System
- ⏱ Real-Time Availability Verification
- 🧾 Appointment History Viewer
- 🛡️ Input Validation and Secure Queries

---

## 🛠️ Technologies Used

| Technology     | Description                          |
|----------------|--------------------------------------|
| Java (JDK 24)  | Core application development         |
| MySQL          | Backend relational database          |
| JDBC           | Java Database Connectivity           |
| MySQL Connector| MySQL JDBC driver                    |
| Maven/Gradle   | Dependency & project management      |

---

## 📦 Prerequisites

1. **Java Development Kit (JDK 24)**
   - [Download JDK](https://www.oracle.com/java/technologies/downloads/)
   - Verify: `java -version`

2. **MySQL Server**
   - [Download MySQL](https://dev.mysql.com/downloads/)
   - Default port: `3306`

3. **MySQL Connector/J**
   - [Download MySQL Connector](https://dev.mysql.com/downloads/connector/j/)
   - Choose Platfrom Independent OS if using windows and else select your OS i.e Ubuntu , Debian etc

---

## 🎯 Quick Start Guide

### 1. Database Setup

```sql
CREATE DATABASE hospital;
USE hospital;

CREATE TABLE patients (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    age INT,
    gender VARCHAR(10)
);

CREATE TABLE doctors (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    specialisation VARCHAR(100),
    RoomNumber INT
);

CREATE TABLE appointments (
    patient_id INT,
    doctor_id INT,
    appointment_date DATE,
    FOREIGN KEY (patient_id) REFERENCES patients(id),
    FOREIGN KEY (doctor_id) REFERENCES doctors(id)
);
```

### 2. Configuration

Update database credentials in `HospitalManagementSystem.java`:

```java
private static final String url = "jdbc:mysql://localhost:3306/hospital";
private static final String username = "your_username";
private static final String password = "your_password";
```

### 3. Build and Run

```bash
# Compile
javac HMS/*.java

# Run
java HMS.HospitalManagementSystem
```

---

## 📚 System Manual

### Main Menu Options

1. **Add Patient**
   - Collects name, age, gender

2. **View Patients**
   - Lists all registered patients

3. **View Doctors**
   - Displays doctor name, specialization, room number

4. **Book Appointment**
   - Requires:
     - Patient ID
     - Doctor ID
     - Appointment Date (`YYYY-MM-DD`)
   - Validates availability and saves record

5. **View Appointments**
   - Search by:
     - Patient ID
     - Appointment Date

6. **Exit**
   - Safely terminates application

---

## 🗄️ Database Structure

### `patients` Table

| Column | Type         | Description         |
|--------|--------------|---------------------|
| id     | INT          | Primary Key         |
| name   | VARCHAR(100) | Patient’s name      |
| age    | INT          | Patient’s age       |
| gender | VARCHAR(10)  | Patient’s gender    |

### `doctors` Table

| Column        | Type         | Description              |
|---------------|--------------|--------------------------|
| id            | INT          | Primary Key              |
| name          | VARCHAR(100) | Doctor’s name            |
| specialisation| VARCHAR(100) | Area of specialization   |
| RoomNumber    | INT          | Assigned room number     |

### `appointments` Table

| Column          | Type | Description                      |
|-----------------|------|----------------------------------|
| patient_id      | INT  | Foreign key referencing patients |
| doctor_id       | INT  | Foreign key referencing doctors  |
| appointment_date| DATE | Date of appointment              |

---

## 🔐 Security Features

- Uses `PreparedStatement` to prevent SQL Injection
- Input validation to handle user errors
- Room number and availability tracking
- Handles invalid IDs gracefully

---

## 🧱 Error Handling

- Graceful database failure messages
- Duplicate booking prevention
- Handles empty or incorrect inputs
- Doctor availability checks for date conflicts

---

## 🔧 Future Enhancements

- ✅ User Login and Roles (Admin, Receptionist)
- 🏥 Department-Wise Filtering
- 🗂 Medical History Tracking
- 💊 Prescription and Billing System
- 📧 Email Notifications for Appointments
- 📄 Export Reports to PDF/CSV

---

## 🤝 Contributing

We welcome contributions!

```bash
# Step 1: Fork this repository
# Step 2: Create a new branch
git checkout -b feature/YourFeature

# Step 3: Make changes and commit
git commit -m "Add YourFeature"

# Step 4: Push changes
git push origin feature/YourFeature

# Step 5: Submit a Pull Request
```

---

## 📝 License

This project is licensed under the [MIT License](https://opensource.org/licenses/MIT).

---

## 👥 Support

- Email: satwiksaxena41@gmail.com
- Linkedin: https://www.linkedin.com/in/satwik-12-dev
- Raise an issue on GitHub

---

## 🙏 Acknowledgments

- Java & JDBC Community
- MySQL Documentation
- Oracle JDK Contributors
- All Open Source Supporters

---

**Made by Satwik**
