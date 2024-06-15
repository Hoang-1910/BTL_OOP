public class Lecture extends Person {
    private String lectureId;

    public Lecture(String lectureId, String name, String dateOfBirth, String gender) {
        super(name, dateOfBirth, gender);
        this.lectureId = lectureId;
    }

    // Getters và Setters
    public String getLectureId() {
        return lectureId;
    }

    public void setLectureId(String lectureId) {
        this.lectureId = lectureId;
    }
}
