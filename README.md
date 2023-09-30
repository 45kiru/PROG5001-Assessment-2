# PROG5001-Assessment-2
This Java code reads student data from a CSV file and allows the user to interact with the data through a console-based menu. The code starts by initializing a String variable with the name of the CSV file and calls a method to read the data and create a list of Student objects. The code then enters a while loop that displays a menu of options to the user and executes the appropriate code block based on the user's selection. The available options include displaying a list of all students, displaying a list of students with a specific grade, displaying a list of students with a specific name, and exiting the program.

## Functional Requirements

- F1: Read Data from Text File
  - The program should read the unit name and students' marks from a given text file.
  - The user will provide the file name.
  - The file contains the unit name and the list of students with their names, student IDs, and marks for three assignments.
  - The file also contains lines that are comments, and the program should ignore them when reading the students' marks.
- F2: Calculate Total Marks
  - The program should calculate the total mark for each student from the assessment marks.
  - It should print out the list of students with their name, student ID, assessment marks, and the total mark.
- F3: Print Students Below Threshold
  - The program should print the list of students with total marks less than a certain threshold.
  - The threshold will be entered from the keyboard by the user.
  - To complete this function requirement, follow these steps:
  - Step 1: Create an algorithm (Algorithm 3) in pseudo code to print the list of students with total marks less than a certain threshold.
  - Step 2: Implement Algorithm 3 based on the pseudo code developed in Step 1.
  - Note that the implementation must be in line with the pseudo code, and existing searching and sorting library functions are not allowed in Algorithm 3.
- F4: Print Top 5 and Bottom 5 Students
  - The program should print the top 5 students with the highest total marks and the top 5 students with the lowest total marks.
  - To complete this function requirement, follow these steps:
  - Step 1: Create an algorithm (Algorithm 4) in pseudo code to print the top 5 students with the highest total marks and the top 5 students with the lowest total marks.
  - Step 2: Implement Algorithm 4 based on the pseudo code developed in Step 1.
  - Note that the implementation must be in line with the pseudo code, and existing searching and sorting library functions are not allowed in Algorithm 4.
- F5: Menu System
  - Create a simple menu system to allow users to select and execute each function.

## Usage
To use this program, follow these steps:
1. Clone the repository to your local machine.
2. Open the project in an IDE of your choice.
3. Run the Main class to start the program.
4. When prompted, enter the name of the CSV file containing the student data.
5. Follow the on-screen instructions to interact with the student data.

## Additional Files
- Algorithm [PseudoCode.txt](PseudoCode.txt)

## References

- Conventional Commit [Link](https://www.conventionalcommits.org/en/v1.0.0)
