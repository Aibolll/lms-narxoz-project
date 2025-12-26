#  Learning Management System (LMS)
### Narxoz University Edition

A backend Learning Management System developed as a final project for Final Project. The system automates academic workflows and manages interactions between administrators, teachers, and students using a secure role-based architecture.

##  Features
* **User registration and authentication**
* **Role-based access control** (ADMIN, TEACHER, STUDENT)
* **Course creation and management**
* **Student enrollment**
* **Grading and evaluation system**
* **Secure REST API**

##  Architecture
The application follows a **Layered Architecture** pattern to ensure clean separation of concerns, scalability, and maintainability.

**Application layers:**
* **Controller Layer** — handles HTTP requests and input validation.
* **Service Layer** — contains business logic and system rules.
* **Repository Layer** — manages database access using Spring Data JPA.
* **Security Layer** — provides role-based access control using Spring Security.



##  Getting Started

### Prerequisites
* **Java 17** or higher
* **PostgreSQL** (running locally)
* **IntelliJ IDEA** (recommended)

### Database Setup
1. Create an empty PostgreSQL database named: `final_db`
2. Configure your database credentials in: `src/main/resources/application.properties`

### Database Migrations
**Liquibase** is used for database version control. On the first application startup, Liquibase automatically:
* Creates all required tables.
* Applies relationships and constraints.
* Initializes default roles.

### Running the Application
Run the application from the project root directory using Gradle. After successful startup, the backend server will be available for API testing.

##  Default Admin Account
For testing and demonstration purposes, a default administrator account is created on application startup via the `AdminSeeder` class.
* **Username**: `sergeant@narxoz.kz`
* **Password**: `123`

##  API Documentation & Testing
A complete Postman collection is included in the project root directory: `Final Project_LMS.postman_collection.json`.

**The Postman collection includes:**
* Pre-configured requests for **ADMIN**, **TEACHER**, and **STUDENT** roles.
* Authentication and registration endpoints.
* Course and grading management endpoints.
* **Saved DTO response examples** with real-world data structures.
* Pre-set **Basic Authentication** headers for instant testing.

##  Technology Stack
* **Backend Framework**: Java 17, Spring Boot 3
* **Security**: Spring Security (Role-Based Access Control)
* **Data Access**: Spring Data JPA / Hibernate
* **Database**: PostgreSQL
* **Migrations**: Liquibase
* **Testing**: JUnit 5, Mockito
* **API Testing**: Postman

##  Project Purpose
The purpose of this project is to demonstrate:
* Backend development using Spring Boot.
* Clean layered architecture design.
* Secure REST API implementation.
* Role-based authorization.
* Integration with PostgreSQL and Liquibase.
