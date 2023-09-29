class Student {
    private String unitName;
    private String lastName;
    private String firstName;
    private String studentId;
    private double assignment1;
    private double assignment2;
    private double assignment3;
    private double totalMarks;

    public Student(String unitName, String lastName, String firstName, String studentId, double assignment1, double assignment2, double assignment3) {
        this.unitName = unitName;
        this.lastName = lastName;
        this.firstName = firstName;
        this.studentId = studentId;
        this.assignment1 = assignment1;
        this.assignment2 = assignment2;
        this.assignment3 = assignment3;
    }

    public double getAssignment1() {
        return assignment1;
    }

    public double getAssignment2() {
        return assignment2;
    }

    public double getAssignment3() {
        return assignment3;
    }

    public double getTotalMarks() {
        return totalMarks;
    }
    
    public void setTotalMarks(double totalMarks) {
        this.totalMarks = totalMarks;
    }

    public String getUnitName() {
        return unitName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getStudentId() {
        return studentId;
    }
}
