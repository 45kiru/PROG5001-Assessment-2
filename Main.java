import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String csvFileName = "prog5001_students_grade_2022.csv";
        // F1: Read Data from Text File
        List<Student> students = readStudentDataFromCSV(csvFileName);

        if (students.isEmpty()) {
            System.out.println("No student data found.");
            return;
        }

        for (Student student : students) {
            System.out.println(student.getLastName()+" "+student.getFirstName());
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
}
