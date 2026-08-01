**Smart Expense Tracker API**

A Spring Boot REST API with a web UI for managing personal expenses. 
Developed using Spring Initializr. 
Tested locally on localhost:9966 using both Postman and a web browser.

**Tech Stack**
Backend: Java 17, Spring Boot 3.2, Spring Web, Spring Data JPA
Frontend: Thymeleaf
Database: H2 In-Memory Database
Build Tool: Maven
API Client: Postman

**Features**
Add Expense (title, amount, category, date)
View All Expenses
Filter Expenses by Category
Calculate Total Expenses
Delete Expense by ID
Web UI for managing expenses

**Project Structure**
smartexpensetracker/
├── src/main/java/com/vishnu/smartexpensetracker/
│   ├── SmartexpensetrackerApplication.java     # Main Spring Boot class
│   ├── controller/
│   │   └── ExpenseController.java              # REST + Web endpoints
│   ├── model/
│   │   └── Expense.java                        # Expense entity: id, title, amount, category, date
│   └── repository/
│       └── ExpenseRepository.java              # JPA Repository
├── src/main/resources/
│   ├── templates/
│   │   └── index.html                          # Thymeleaf UI for Add, Filter, Delete
│   ├── static/                                 # CSS/JS if any
│   └── application.yaml                        # Port: 9966, DB config
├── src/test/java/                              # JUnit Tests
├── pom.xml
└── README.md


**How to Run Locally**
1. Install Dependencies
mvn clean install
2. Start the Server
mvn spring-boot:run
3. Access the Application

**Web UI**
http://localhost:9966/
**REST API Base URL**
http://localhost:9966/api/expenses
The server port is configured in:
src/main/resources/application.properties
server.port=9966

**Testing the Web UI**
Open:
http://localhost:9966/
Add a new expense using the form.
Filter expenses by category.
Delete an expense using the Delete button.
Verify that the total expense amount updates correctly.

**Test APIs with Postman**
Feature	              Method	      Endpoint
Add Expense	       POST            http://localhost:9966/api/expenses
View All Expenses	GET	       http://localhost:9966/api/expenses
Filter by Category	GET	       http://localhost:9966/api/expenses?category=Food
Get Total Expenses	GET	       http://localhost:9966/api/expenses/total
Get Category Total	GET	       http://localhost:9966/api/expenses/total?category=Transport
Delete Expense	        DELETE	       http://localhost:9966/api/expenses/{id}

**Sample POST Request**
{
  "title": "Dinner",
  "amount": 400,
  "category": "Food",
  "date": "2026-08-01"
}
**Steps to Test Using Postman**
Open Postman.
Select the appropriate HTTP method (POST, GET, or DELETE).
Enter the API URL.
For POST requests, choose Body → raw → JSON and paste the sample JSON.
Click Send.
Verify that the response returns 200 OK or 201 Created, depending on the endpoint.
