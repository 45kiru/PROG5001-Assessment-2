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
            // F5: Menu System
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
                    calculateTotalMarks(students);
                    System.out.print("Enter the threshold: ");
                    int threshold = scanner.nextInt();
                    printStudentsBelowThreshold(students, threshold);
                    break;
                case 3:
                    // F4: Print Top 5 and Bottom 5 Students
                    calculateTotalMarks(students);
                    printTopAndBottomStudents(students);
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
            String headerLine = br.readLine();

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

    private static void printStudentsBelowThreshold(List<Student> students, int threshold) {
        System.out.println("Students Below Threshold (" + threshold + "):");
        String border = "+---------------+---------------------------+----------------------+---------------+";
        System.out.println(border);
        System.out.printf(
            "| %-13s | %-25s | %-20s | %-13s |%n",
        "Student ID", "Last Name", "First Name", "Total Marks"
        );
        System.out.println(border);
        for (Student student : students) {
            if (student.getTotalMarks() < threshold) {
                System.out.printf(
                    "| %-13s | %-25s | %-20s | %-13.2f |%n",
                    student.getStudentId(),
                    student.getLastName(),
                    student.getFirstName(),
                    student.getTotalMarks()
                );
                System.out.println(border);
            }
        }
    }

    private static void printTopAndBottomStudents(List<Student> students) {
        List<Student> topFiveHighestMarks = new ArrayList<>();
        List<Student> topFiveLowestMarks = new ArrayList<>();
        List<Double> highestMarks = new ArrayList<>();
        List<Double> lowestMarks = new ArrayList<>();

        for (Student student : students) {
            if (highestMarks.size() < 5) {
                highestMarks.add(student.getTotalMarks());
                topFiveHighestMarks.add(student);
            } else {
                double lowestHighMark = findLowestValue(highestMarks);
                if (student.getTotalMarks() > lowestHighMark) {
                    int index = highestMarks.indexOf(lowestHighMark);
                    highestMarks.set(index, student.getTotalMarks());
                    topFiveHighestMarks.set(index, student);
                }
            }

            if (lowestMarks.size() < 5) {
                lowestMarks.add(student.getTotalMarks());
                topFiveLowestMarks.add(student);
            } else {
                double highestLowMark = findHighestValue(lowestMarks);
                if (student.getTotalMarks() < highestLowMark) {
                    int index = lowestMarks.indexOf(highestLowMark);
                    lowestMarks.set(index, student.getTotalMarks());
                    topFiveLowestMarks.set(index, student);
                }
            }
        }

        System.out.println("Top 5 Students with the Highest Total Marks:");
        String border = "+---------------+---------------------------+----------------------+---------------+";
        System.out.println(border);
        System.out.printf(
            "| %-13s | %-25s | %-20s | %-13s |%n",
            "Student ID", "Last Name", "First Name", "Total Marks"
        );
        System.out.println(border);
        for (Student student : topFiveHighestMarks) {
            System.out.printf(
                "| %-13s | %-25s | %-20s | %-13.2f |%n",
                student.getStudentId(),
                student.getLastName(),
                student.getFirstName(),
                student.getTotalMarks()
            );
            System.out.println(border);
        }

        System.out.println("\nBottom 5 Students with the Lowest Total Marks:");
        System.out.println(border);
        System.out.printf(
            "| %-13s | %-25s | %-20s | %-13s |%n",
            "Student ID", "Last Name", "First Name", "Total Marks"
        );
        System.out.println(border);
        for (Student student : topFiveLowestMarks) {
            System.out.printf(
                "| %-13s | %-25s | %-20s | %-13.2f |%n",
                student.getStudentId(),
                student.getLastName(),
                student.getFirstName(),
                student.getTotalMarks()
            );
            System.out.println(border);
        }
    }

    private static double findLowestValue(List<Double> values) {
        double lowest = Double.MAX_VALUE;
        for (double value : values) {
            if (value < lowest) {
                lowest = value;
            }
        }
        return lowest;
    }

    private static double findHighestValue(List<Double> values) {
        double highest = Double.MIN_VALUE;
        for (double value : values) {
            if (value > highest) {
                highest = value;
            }
        }
        return highest;
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
