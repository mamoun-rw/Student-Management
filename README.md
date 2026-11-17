# Student Management System

## Overview
Student Management System is a Java console-based application developed to manage student records using fundamental data structures. The project focuses on applying Object-Oriented Programming (OOP) concepts and implementing data structures manually without using Java Collection classes.

---

## Project Objectives

The main objectives of this project are:

- Practice Object-Oriented Programming in Java.
- Understand how different data structures work together.
- Manage student information efficiently.
- Implement search, insertion, deletion, and update operations.
- Apply Stack and Queue concepts in a real-world scenario.
- Handle invalid user input using exception handling.

---

## Data Structures Used

### 1. Unsorted Array
Used as the main storage for student records.

Functions:
- Insert student records.
- Search students by ID.
- Remove student records.

### 2. Sorted Linked List
Maintains students sorted by their IDs.

Functions:
- Sorted insertion.
- Deletion.
- Display all students.

### 3. Stack (UndoStack)
Used to restore deleted or modified student records.

Functions:
- Push previous records.
- Undo recent operations.

### 4. Queue (WaitingQueue)
Represents a waiting list for students who have not yet been officially registered.

Functions:
- Enqueue waiting students.
- Dequeue students into the official records.
- Display waiting students.

---

## Main Features

### Add New Student
- Automatically generates a unique ID.
- Stores the student in both the array and sorted list.

### View Students
- Displays all registered students in ascending order by ID.

### Update GPA
- Searches for a student using ID.
- Updates academic grade.
- Saves previous data for undo capability.

### Delete Student
- Removes a student from the system.
- Stores a backup in the stack.

### Undo Operation
- Restores the most recently deleted or modified student.

### Waiting List Management
- Add students to a queue.
- Transfer students from the waiting list to official records.

---

## Exception Handling

The system uses `InputMismatchException` to prevent crashes caused by invalid user input and ensures a smoother user experience.

---

## Educational Value

This project demonstrates practical applications of:

- Arrays
- Linked Lists
- Stacks (LIFO)
- Queues (FIFO)
- Searching techniques
- Data management
- Object-Oriented Programming
- Exception handling

---

## Conclusion

The Student Management System is a simple but effective educational project that integrates multiple data structures into a single application. It provides a practical example of how arrays, linked lists, stacks, and queues can be used together to manage and organize student records while reinforcing fundamental Java programming concepts.