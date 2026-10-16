# BudgetTracker

A Java Spring Boot budgeting application that helps users manage their monthly finances using the **50/30/20 budgeting method**.

## About the Project

BudgetTracker was developed as a Java software development final project. It provides a simple web interface for calculating a monthly budget, recording expenses, and reviewing spending by category.

## Features

- Calculate monthly budgets using the 50/30/20 rule
- Allocate 50% of income to Needs, 30% to Wants, and 20% to Savings
- Add expenses and savings allocations to categories
- View spending totals and expense history
- Switch between username-based budget profiles
- Save monthly income and expenses using an H2 database
- Preserve saved data between application restarts

## Technologies Used

- Java
- Spring Boot
- Spring MVC
- JavaServer Pages (JSP)
- Spring Data JPA
- H2 Database
- Maven
- Git and GitHub

## Object-Oriented Programming Concepts

The project demonstrates several Java programming concepts, including:

- Classes and objects
- Constructors and encapsulation
- Interfaces
- Enumerations
- Collections
- Database entities and repositories

The `BudgetReport` interface defines budgeting calculations, while `BudgetCalculator` implements those calculations. The `ExpenseCategory` enum represents the three budgeting categories.

## How to Run

1. Clone or download this repository.
2. Open the project in IntelliJ IDEA.
3. Make sure a compatible Java JDK and Maven are available.
4. Allow Maven to download the project dependencies.
5. Run the Spring Boot application.
6. Open `http://localhost:8080` in a web browser.
7. Select a username, enter monthly income, and begin tracking expenses.

## Data Storage

BudgetTracker uses a file-based H2 database to store budget profiles and expenses. The database is created locally when the application runs.

Database files are excluded from GitHub using `.gitignore`.

## Limitations

This project uses usernames to organize budgets but does not implement password authentication. It is intended as a local educational application rather than a production financial service.

## Author

Created as a Software Development final project.