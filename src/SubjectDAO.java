import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO {

    // Hàm thêm môn học
    public void addSubject(Subject subject, String lecturerId) throws SQLException {
        String sql = "INSERT INTO subjects (subjectId, subjectName, lecturerId) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, subject.getSubjectId());
            statement.setString(2, subject.getSubjectName());
            statement.setString(3, lecturerId);
            statement.executeUpdate();
        }
    }
    // Hàm sửa thông tin môn học
    public void updateSubject(Subject subject, String lecturerId) throws SQLException {
        String sql = "UPDATE subjects SET subjectName = ?, lecturerId = ? WHERE subjectId = ?";
        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, subject.getSubjectName());
            statement.setString(2, lecturerId);
            statement.setString(3, subject.getSubjectId());
            statement.executeUpdate();
        }
    }
    
    // Hàm xóa môn học
    public void deleteSubject(String subjectId) throws SQLException {
        String query = "DELETE FROM subjects WHERE subjectId=?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, subjectId);
            statement.executeUpdate();
        }
    }

    // Hàm lấy danh sách các môn học
    public List<Subject> getAllSubjects() throws SQLException {
        List<Subject> subjects = new ArrayList<>();
        String query = "SELECT * FROM subjects";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                String subjectId = resultSet.getString("subjectId");
                String subjectName = resultSet.getString("subjectName");
                Subject subject = new Subject(subjectId, subjectName);
                subjects.add(subject);
            }
        }
        return subjects;
    }

    // Hàm lấy giảng viên dạy môn học
    public Lecture getLecturerForSubject(String subjectId) throws SQLException {
        String sql = "SELECT l.lectureId, l.name, l.dateOfBirth, l.gender " +
                     "FROM subjects s " +
                     "JOIN lectures l ON s.lecturerId = l.lectureId " +
                     "WHERE s.subjectId = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, subjectId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String lectureId = resultSet.getString("lectureId");
                    String name = resultSet.getString("name");
                    String dateOfBirth = resultSet.getString("dateOfBirth");
                    String gender = resultSet.getString("gender");
                    return new Lecture(lectureId, name, dateOfBirth, gender);
                }
            }
        }
        return null;
    }
    
}
