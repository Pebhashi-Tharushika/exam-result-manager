## Exam Result Manager (ERM)

### Overview
Exam Result Manager is a **Java-based CLI (Command Line Interface) desktop application** designed to help manage student exam results 
efficiently. This application was developed to apply my knowledge of programming fundamentals, including basic data structures such as 
fixed arrays for efficient memory allocation, one-dimensional arrays for linear data storage and multi-dimensional arrays for structured 
data representation, along with array searching algorithms and array sorting algorithms to solve fundamental programming problems using a variety of skills and strategies.

### Features
- **User-Friendly CLI Interface**
  - Users can navigate through functionalities using option numbers on the home page.
  - Graceful exit from the application following given instructions.
- **Student Management**
  - Add new students (with or without marks initially).
  - Update student details and marks.
  - Delete student records in case of incorrect input.
- **Exam Marks Management**
  - Add marks only for students who initially had no marks.
  - View individual student details and subject-wise marks.
  - View student rankings based on marks.
- **Result Analysis and Reporting**
  - View all students' marks subject-wise.
  - Calculate and display overall average marks of each student.
  - Identify the rank of each student in each subject separately and overall.
  - Identify best and weaker students.
  - Determine which subjects most students struggle with.
- **Validation and Exception Handling**
  - Ensures students are not added multiple times.
  - Validates marks and inputs to prevent errors.
  - Handles exceptions gracefully to provide a smooth user experience.
- **Ranking System**
  - Accounts for students who haven't added marks for all subjects yet.
  - Handles cases where students obtain the same marks (same rank/position).

### How It Works
1. The user enters an option number to access different functionalities.
2. Students can be added, updated, deleted, or viewed.
3. Marks can be added for students who initially had none.
4. The system calculates and displays rankings and summaries.
5. Users can exit the application at any time following provided instructions.

### Technologies Used
- **Programming Language:** Java
  - **Data Structures** 
  - **Array Sorting Algorithm**
  - **Array Searching Algorithm** 

### Use Case Diagram in UML
![use-case-diagram](/assets/use-case-diagram.png)

### Preview

![home-page](/assets/home-page.gif)
![home-page](/assets/add-new-student.gif)
![home-page](/assets/add-new-student-with-marks.gif)
![home-page](/assets/add-marks.gif)
![home-page](/assets/update-student-details.gif)
![home-page](/assets/update-marks.gif)
![home-page](/assets/delete-student.gif)
![home-page](/assets/print-student-details.gif)
![home-page](/assets/print-student-ranks.gif)
![home-page](/assets/best-in-pf.gif)
![home-page](/assets/best-in-dbms.gif)


### License
This project is licensed under the [MIT License](/LICENSE).


