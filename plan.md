# Project Plan – Management System

## 1. Project Overview
This project is a Management System built using Spring Boot. The main goal of the project is to manage quizzes and questions in a clean and structured way. It supports creating questions, storing them in a question bank, assigning them to assessments, and allowing users to attempt quizzes.

The project follows a standard backend architecture so that the code is easy to understand, maintain, and extend in the future.

---

## 2. Assumptions
The following assumptions were made while building this project:

- The backend is built using Spring Boot and Java.
- A relational database (such as MySQL) is used to store data.
- Users are authenticated before accessing admin features.
- Questions can have different types like MCQ and TRUE/FALSE.
- Pagination is handled on the server side to improve performance.
- The frontend or client will consume REST APIs provided by this backend.

---

## 3. Scope of the Project
The scope of this project includes:

- User authentication and JWT security configuration
- Creating and managing questions
- Supporting multiple question types (MCQ, TRUE/FALSE, TEXT)
- Managing a question bank
- Assigning questions to assessments
- Pagination for listing questions
- Handling quiz attempts by users

Only the above features are considered part of the current implementation.

---

## 4. Out of Scope
The following features are not included in the current scope:

- Advanced reporting and analytics
- Admin dashboard UI
- Question import/export via files
- Timed exams and proctoring features

These can be added later if required.

---

## 5. Approach and Design
The project follows a layered architecture:

- **Controller Layer**: Handles incoming HTTP requests and sends responses.
- **Service Layer**: Contains business logic and validations.
- **Repository Layer**: Handles database operations using Spring Data JPA.
- **Entity Layer**: Represents database tables using JPA entities.

Key design decisions:

- REST APIs are used for communication.
- DTOs are used where required to avoid exposing entities directly.
- Pagination is implemented using Spring Data `PageRequest`.
- Enum is used for question types to avoid invalid values.
- Global exception handling is used for consistent error responses.

This approach keeps the code clean, testable, and easy to extend.

---

## 6. Scope Changes During Implementation
Some changes were made during implementation based on practical needs:

- Pagination was shifted fully to the backend instead of client-side handling.
- Assessment identifiers were treated as string-based codes instead of numeric values.
- Validation was added at the API level to avoid invalid data entry.

These changes improved performance, data consistency, and usability.

---

## 7. Conclusion
This project is designed with simplicity and clarity in mind. It follows common industry practices and provides a strong foundation for a quiz or assessment management system. The structure allows easy future enhancements without major refactoring.

