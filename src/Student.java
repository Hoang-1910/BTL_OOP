public class Student extends Person {
    private String studentId;


    public Student(String studentId, String name, String dateOfBirth, String gender) {
        super(name, dateOfBirth, gender);
        this.studentId = studentId;
    }

    // Getters và Setters
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
