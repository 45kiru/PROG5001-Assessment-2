import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String csvFileName = "prog5001_students_grade_2022.csv";
        // F1: Read Data from Text File
        List<Student> students = readStudentDataFromCSV(csvFileName);

        if (students.isEmpty()) {
            System.out.println("No student data found.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    // F2: Calculate Total Marks
                    calculateTotalMarks(students);
                    printStudents(students);
                    break;
                case 2:
                    // F3: Print Students Below Threshold
                    // System.out.print("Enter the threshold: ");
                    // int threshold = scanner.nextInt();
                    // printStudentsBelowThreshold(students, threshold);
                    break;
                case 3:
                    // F4: Print Top 5 and Bottom 5 Students
                    // printTopAndBottomStudents(students);
                    break;
                case 4:
                    // Exit the program
                    System.out.println("Exiting the program.");
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    
    }

    private static List<Student> readStudentDataFromCSV(String csvFileName) {
        List<Student> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFileName))) {
            String unitName = br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    String lastName = parts[0].trim();
                    String firstName = parts[1].trim();
                    String studentId = parts[2].trim();
                    double assignment1 = parseDouble(parts[3]);
                    double assignment2 = parseDouble(parts[4]);
                    double assignment3 = parseDouble(parts[5]);

                    students.add(new Student(unitName, lastName, firstName, studentId, assignment1, assignment2, assignment3));
                }
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }

        return students;
    }

    private static double parseDouble(String s) {
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return 0.0; // Return 0.0 for missing or invalid values
        }
    }

    private static void calculateTotalMarks(List<Student> students) {
        for (Student student : students) {
            double totalMarks = student.getAssignment1() + student.getAssignment2() + student.getAssignment3();
            student.setTotalMarks(totalMarks);
        }
    }

    private static void printStudents(List<Student> students) {
        System.out.println("List of Students with Total Marks:");
        String border = "+---------------+---------------------------+----------------------+---------------+---------------+---------------+---------------+";
        System.out.println(border);
        System.out.printf(
            "| %-13s | %-25s | %-20s | %-13s | %-13s | %-13s | %-13s |%n",
        "Student ID", "Last Name", "First Name", "Assignment 1", "Assignment 2", "Assignment 3", "Total Marks"
        );
        System.out.println(border);
        // Print each student as a table row
        for (Student student : students) {
            System.out.printf(
                "| %-13s | %-25s | %-20s | %-13.2f | %-13.2f | %-13.2f | %-13.2f |%n",
                student.getStudentId(),
                student.getLastName(),
                student.getFirstName(),
                student.getAssignment1(),
                student.getAssignment2(),
                student.getAssignment3(),
                student.getTotalMarks()
            );
            System.out.println(border);
        }
    }

    private static void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Calculate Total Marks and Print All Students");
        System.out.println("2. Print Students Below Threshold");
        System.out.println("3. Print Top 5 and Bottom 5 Students");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }
}
