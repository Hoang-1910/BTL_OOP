import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LectureDAO {

    // Hàm thêm giảng viên
    public void addLecture(Lecture lecture) throws SQLException {
        String query = "INSERT INTO lectures (lectureId, name, dateOfBirth, gender) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, lecture.getLectureId());
            statement.setString(2, lecture.getName());
            statement.setString(3, lecture.getDateOfBirth());
            statement.setString(4, lecture.getGender());
            statement.executeUpdate();
        }
    }

    // Hàm sửa thông tin giảng viên
    public void updateLecture(Lecture lecture) throws SQLException {
        String query = "UPDATE lectures SET name=?, dateOfBirth=?, gender=? WHERE lectureId=?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, lecture.getName());
            statement.setString(2, lecture.getDateOfBirth());
            statement.setString(3, lecture.getGender());
            statement.setString(4, lecture.getLectureId());
            statement.executeUpdate();
        }
    }

    // Hàm xóa giảng viên
    public void deleteLecture(String lectureId) throws SQLException {
        String query = "DELETE FROM lectures WHERE lectureId=?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, lectureId);
            statement.executeUpdate();
        }
    }

    // Hàm lấy danh sách giảng viên
    public List<Lecture> getAllLectures() throws SQLException {
        List<Lecture> lectures = new ArrayList<>();
        String query = "SELECT * FROM lectures";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                String lectureId = resultSet.getString("lectureId");
                String name = resultSet.getString("name");
                String dateOfBirth = resultSet.getString("dateOfBirth");
                String gender = resultSet.getString("gender");
                Lecture lecture = new Lecture(lectureId, name, dateOfBirth, gender);
                lectures.add(lecture);
            }
        }
        return lectures;
    }
}
