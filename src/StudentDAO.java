import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // Hàm thêm sinh viên
    public void addStudent(Student student) throws SQLException {
        String query = "INSERT INTO students (studentId, name, dateOfBirth, gender) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, student.getStudentId());
            statement.setString(2, student.getName());
            statement.setString(3, student.getDateOfBirth());
            statement.setString(4, student.getGender());
            statement.executeUpdate();
        }
    }

    // Hàm sửa sinh viên
    public void updateStudent(Student student) throws SQLException {
        String query = "UPDATE students SET name=?, dateOfBirth=?, gender=? WHERE studentId=?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, student.getName());
            statement.setString(2, student.getDateOfBirth());
            statement.setString(3, student.getGender());
            statement.setString(4, student.getStudentId());
            statement.executeUpdate();
        }
    }

    // Hàm xóa sinh viên
    public void deleteStudent(String studentId) throws SQLException {
        String query = "DELETE FROM students WHERE studentId=?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, studentId);
            statement.executeUpdate();
        }
    }

    // Hàm Đăng ký môn học
    public void registerSubject(String studentId, String subjectId) throws SQLException {
        String query = "INSERT INTO registrations (studentId, subjectId) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, studentId);
            statement.setString(2, subjectId);
            statement.executeUpdate();
        }
    }

    // Hàm hủy đăng ký học
    public void deleteRegisterSubject(String studentId, String subjectId) throws SQLException {
        String query = "DELETE FROM registrations WHERE studentId=? AND subjectId=?";
        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, studentId);
                statement.setString(2, subjectId);
                statement.executeUpdate();
            }
    }

    // Hàm lấy danh sách sinh viên
    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                String studentId = resultSet.getString("studentId");
                String name = resultSet.getString("name");
                String dateOfBirth = resultSet.getString("dateOfBirth");
                String gender = resultSet.getString("gender");
                Student student = new Student(studentId, name, dateOfBirth, gender);
                students.add(student);
            }
        }
        return students;
    }

    // Hàm lấy danh sách sinh viên trong môn học
    public List<Student> getStudentsForSubject(String subjectId) throws SQLException {
        String sql = "SELECT st.studentId, st.name, st.dateOfBirth, st.gender FROM registrations r JOIN students st ON st.studentId = r.studentId WHERE r.subjectId = ?";
        List<Student> students = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, subjectId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String studentId = resultSet.getString("studentId");
                    String name = resultSet.getString("name");
                    String dateOfBirth = resultSet.getString("dateOfBirth");
                    String gender = resultSet.getString("gender");
                    students.add(new Student(studentId, name, dateOfBirth, gender));
                }
            }
        }
        return students;
    }
}
