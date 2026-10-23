# Library Management System (Java)

A console-based Java application that manages books and library members. It is built to demonstrate core Object-Oriented Programming concepts.

## Features
- Add new books and members (Student or Teacher)
- View all books with availability status
- Search books by title
- Borrow and return books with validation
- Different borrowing rules for each member type:
  - Student: up to 3 books, 14 days
  - Teacher: up to 5 books, 30 days
- View the books borrowed by a member

## OOP Concepts Used
- **Encapsulation:** private fields with getters/setters in `Book` and `Member`
- **Inheritance:** `Student` and `Teacher` extend the abstract class `Member`
- **Abstraction:** `Member` declares abstract methods that subclasses must implement
- **Polymorphism:** `getBorrowLimit()` and `getLoanDays()` behave differently for each member type
- **Collections:** `ArrayList` is used to store books and members

## Project Structure
| File | Purpose |
|---|---|
| `Book.java` | Book data and availability |
| `Member.java` | Abstract base class for members |
| `Student.java` / `Teacher.java` | Member types with their own rules |
| `Library.java` | Core logic (add, search, borrow, return) |
| `LibraryApp.java` | Console menu (main class) |

## How to Run
Requires Java 11 or above.

```bash
javac *.java
java LibraryApp
```

## Author
Arooj Fatima, BS Artificial Intelligence, GSCWU Bahawalpur
