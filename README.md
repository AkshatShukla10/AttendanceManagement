# AttendanceManagement

Overview

The Attendance Management System is a web application that allows managers to create and manage rosters for staff members. Staff members can mark their attendance by capturing their image using a webcam. The system handles authentication and authorization, ensuring that only authorized users can perform specific actions.

Technologies Used
Backend: Java, Spring Boot
Database: MySQL
Security: Spring Security with JWT

Project Structure

attendance-management/
├── src/main/java/com/attendance/management/
│   ├── config/           # Security and application configuration
│   ├── controller/       # REST controllers
│   ├── entity/           # JPA entities
│   ├── repository/       # JPA repositories
│   ├── services/         # Service layer interfaces
│   ├── services/impl/    # Service layer implementations
│   └── AttendanceManagementApplication.java
├── src/main/resources/
│   ├── application.properties
└── pom.xml


Getting Started

Prerequisites

Java 17 or higher
Maven 3.6.3 or higher
MySQL 8.0 or higher

Setup MySQL Database

Run the following SQL script to create the database and tables:

-- Create the database
CREATE DATABASE attendance_db;

-- Use the database
USE attendance_db;

-- Create the User table
CREATE TABLE User (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role ENUM('MANAGER', 'STAFF') NOT NULL
);

-- Create the StaffDetail table
CREATE TABLE StaffDetail (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone_number VARCHAR(20) NOT NULL,
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES User(id)
);

-- Create the Shift table
CREATE TABLE Shift (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    shift_name VARCHAR(50) NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL
);

-- Create the Roster table
CREATE TABLE Roster (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    staff_detail_id BIGINT,
    shift_id BIGINT,
    work_date DATE NOT NULL,
    FOREIGN KEY (staff_detail_id) REFERENCES StaffDetail(id),
    FOREIGN KEY (shift_id) REFERENCES Shift(id)
);

-- Create the Attendance table
CREATE TABLE Attendance (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    staff_detail_id BIGINT,
    shift_id BIGINT,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    image_path VARCHAR(255) NOT NULL,
    FOREIGN KEY (staff_detail_id) REFERENCES StaffDetail(id),
    FOREIGN KEY (shift_id) REFERENCES Shift(id)
);


Configure Application Properties
Update the src/main/resources/application.properties file with your MySQL database credentials:

spring.datasource.url=jdbc:mysql://localhost:3306/attendance_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update

spring.security.user.name=admin
spring.security.user.password=admin
spring.security.user.roles=MANAGER


Build and Run the Application

mvn clean install
mvn spring-boot:run


API Endpoints

Authentication & Authorization

Register User (Manager or Staff)
Endpoint: POST /api/auth/register
Request Body:   {
    "username": "manager1",
    "password": "password123",
    "role": "MANAGER"
}


Login
Endpoint: POST /api/auth/login
Request Body:   {
    "username": "manager1",
    "password": "password123"
}
Response:   {
    "token": "jwt-token"
}


Roster Management (Manager Only)

Add Roster
Endpoint: POST /api/roster/add
Request Body:   {
    "staffDetailId": 1,
    "shiftId": 1,
    "workDate": "2024-06-23"
}

Update Roster
Endpoint: PUT /api/roster/update/{id}
Request Body:   {
    "staffDetailId": 1,
    "shiftId": 2,
    "workDate": "2024-06-24"
}

View Roster
Endpoint: GET /api/roster/view
Response:   [
    {
        "id": 1,
        "staffDetailId": 1,
        "shiftId": 1,
        "workDate": "2024-06-23"
    },
    {
        "id": 2,
        "staffDetailId": 1,
        "shiftId": 2,
        "workDate": "2024-06-24"
    }
]

Edit Staff Details
Endpoint: PUT /api/roster/edit/{id}
Request Body:   {
    "name": "John Doe",
    "email": "john.doe@example.com",
    "phoneNumber": "1234567890"
}

Attendance Management (Staff Only)

View Assigned Shifts
Endpoint: GET /api/attendance/shifts
Response: [
    {
        "id": 1,
        "shiftName": "Morning Shift",
        "startTime": "08:00:00",
        "endTime": "16:00:00"
    },
    {
        "id": 2,
        "shiftName": "Evening Shift",
        "startTime": "16:00:00",
        "endTime": "00:00:00"
    }
]

Mark Attendance
Endpoint: POST /api/attendance/mark
Request Body:   {
    "staffDetailId": 1,
    "shiftId": 1,
    "imagePath": "/path/to/image.jpg"
}

Additional Information
Security Configuration
The security configuration uses JWT tokens for authentication and @PreAuthorize annotations for method-level security. The following roles are supported:

MANAGER: Can create, update, and view the roster, as well as edit staff details.
STAFF: Can view assigned shifts and mark attendance.
