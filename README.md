# Library Management System

## Overview

This Library Management System is designed to demonstrate core Object-Oriented Programming (OOP) principles: Encapsulation, Inheritance, Polymorphism, and Abstraction. The system allows librarians to manage books and members to borrow and return books.

## Features

- **Encapsulation:** Data is hidden within classes and accessed through getter and setter methods.
- **Inheritance:** A base class `User` is created with common attributes, while `Member` and `Librarian` are derived from it.
- **Polymorphism:** The system can manage borrow and return actions differently for members and librarians.
- **Abstraction:** An abstract class `Loanable` defines items that can be loaned, such as books and DVDs.

## Technologies Used

- Java

## Core Classes

### 1. User
- **Attributes:** 
  - `name`
  - `email`
- **Methods:**
  - Getters and setters for `name` and `email`.

### 2. Member (inherits from User)
- **Additional Permissions:** Members can borrow books.

### 3. Librarian (inherits from User)
- **Additional Permissions:** Librarians can add and remove books.

### 4. Loanable (abstract class)
- **Methods:**
  - `borrow()`
  - `return()`

### 5. Book (implements Loanable)
- **Attributes:**
  - `title`
  - `author`
  - `isAvailable`
- **Methods:**
  - `borrow()`
  - `return()`

### 6. Library
- **Methods:**
  - `addBook(Book book)`
  - `borrowBook(User user, Book book)`
  - `returnBook(User user, Book book)`

## Usage

### Adding a Book
Librarians can add a new book to the library by using the `addBook()` method.

```java
Librarian librarian = new Librarian("John Doe", "john@example.com");
Book newBook = new Book("Effective Java", "Joshua Bloch");
library.addBook(newBook);
