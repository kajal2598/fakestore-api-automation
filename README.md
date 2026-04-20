# FakeStore API Automation Framework

##  Overview
A professional REST API automation framework built using **Rest Assured** and **TestNG**. This project demonstrates an engineered approach to API testing, focusing on scalability, type safety, and CI/CD readiness.

##  Core Features
* **POJO Data Modeling**: Uses Plain Old Java Objects (POJOs) for JSON serialization/deserialization, ensuring type safety.
* **Data-Driven Testing**: Implements TestNG `@DataProvider` to execute tests across multiple data sets (e.g., testing multiple product IDs).
* **Schema Validation**: Automated JSON Schema validation to ensure response integrity and structure.
* **Robust Logging**: Integrated `BaseTest` logging to capture all request/response details for debugging.

##  Project Structure
* `api.models`: POJO classes representing API resources (e.g., Product model).
* `api.endpoints`: Centralized management of API routes and base URLs for easy maintenance.
* `api.tests`: Comprehensive TestNG test suites covering CRUD operations.
* `api.base`: Global configurations, setup logic, and reporting hooks.

## Prerequisites
* Java JDK 11+
* Maven 3.6+
* TestNG Plugin for IDE

##  How to Run
1. Navigate to the project root in your terminal.
2. Execute the following command:
   ```bash
   mvn clean test
