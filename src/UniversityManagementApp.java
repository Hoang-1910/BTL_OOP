import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class UniversityManagementApp extends JFrame {
    private JTextField studentIdField, studentNameField, studentDobField, studentGenderField;
    private JTextField lectureIdField, lectureIdField2, lectureNameField, lectureDobField, lectureGenderField;
    private JTextField subjectIdField, subjectNameField, registerStudentIdField, registerSubjectIdField;
    private JTextArea displayArea;
    private JTable studentTable, lecturerTable, subjecTable;
    private JButton addStudentButton, updateStudentButton, deleteStudentButton, registerSubjectButton, deleteRegisterSubjectButton;
    private JButton addLectureButton, updateLectureButton, deleteLectureButton;
    private JButton addSubjectButton, updateSubjectButton, deleteSubjectButton;
    private StudentDAO studentDAO = new StudentDAO();
    private LectureDAO lectureDAO = new LectureDAO();
    private SubjectDAO subjectDAO = new SubjectDAO();

    public UniversityManagementApp() {
        setTitle("University Management System");
        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        //Student Section
        JLabel studentSectionLabel = new JLabel("Add/Update/Delete Student");
        studentSectionLabel.setBounds(10, 10, 200, 25);
        add(studentSectionLabel);

        JLabel studentIdLabel = new JLabel("Student ID:");
        studentIdLabel.setBounds(10, 40, 100, 25);
        add(studentIdLabel);
        studentIdField = new JTextField();
        studentIdField.setBounds(120, 40, 150, 25);
        add(studentIdField);

        JLabel studentNameLabel = new JLabel("Name:");
        studentNameLabel.setBounds(10, 70, 100, 25);
        add(studentNameLabel);
        studentNameField = new JTextField();
        studentNameField.setBounds(120, 70, 150, 25);
        add(studentNameField);

        JLabel studentDobLabel = new JLabel("Date of Birth:");
        studentDobLabel.setBounds(10, 100, 100, 25);
        add(studentDobLabel);
        studentDobField = new JTextField();
        studentDobField.setBounds(120, 100, 150, 25);
        add(studentDobField);

        JLabel studentGenderLabel = new JLabel("Gender:");
        studentGenderLabel.setBounds(10, 130, 100, 25);
        add(studentGenderLabel);
        studentGenderField = new JTextField();
        studentGenderField.setBounds(120, 130, 150, 25);
        add(studentGenderField);

        addStudentButton = new JButton("Add Student");
        addStudentButton.setBounds(10, 160, 150, 25);
        add(addStudentButton);

        updateStudentButton = new JButton("Update Student");
        updateStudentButton.setBounds(170, 160, 150, 25);
        add(updateStudentButton);

        deleteStudentButton = new JButton("Delete Student");
        deleteStudentButton.setBounds(330, 160, 150, 25);
        add(deleteStudentButton);

        // Sự kiện add student
        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String studentId = studentIdField.getText();
                    String name = studentNameField.getText();
                    String dateOfBirth = studentDobField.getText();
                    String gender = studentGenderField.getText();

                    Student student = new Student(studentId, name, dateOfBirth, gender);
                    studentDAO.addStudent(student);

                    JOptionPane.showMessageDialog(null, "Student added successfully!");
                    updateDisplayArea();
                    loadStudentData();
                    clearFields();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error adding student.");
                }
            }
        });

        // Sự kiện update student
        updateStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String studentId = studentIdField.getText();
                    String name = studentNameField.getText();
                    String dateOfBirth = studentDobField.getText();
                    String gender = studentGenderField.getText();

                    Student student = new Student(studentId, name, dateOfBirth, gender);
                    studentDAO.updateStudent(student);

                    JOptionPane.showMessageDialog(null, "Student updated successfully!");
                    updateDisplayArea();
                    loadStudentData();
                    clearFields();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error updating student.");
                }
            }
        });

        // Sự kiện delete student
        deleteStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String studentID = studentIdField.getText();
                    studentDAO.deleteStudent(studentID);
                    JOptionPane.showMessageDialog(null, "Student deleted successfully!");
                    updateDisplayArea();
                    loadStudentData();
                    clearFields();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error deleting student.");
                }
            }
        });

        //Lecture Section
        JLabel lectureSectionLabel = new JLabel("Add/Update/Delete Lecture");
        lectureSectionLabel.setBounds(10, 200, 200, 25);
        add(lectureSectionLabel);

        JLabel lectureIdLabel = new JLabel("Lecture ID:");
        lectureIdLabel.setBounds(10, 230, 100, 25);
        add(lectureIdLabel);
        lectureIdField = new JTextField();
        lectureIdField.setBounds(120, 230, 150, 25);
        add(lectureIdField);

        JLabel lectureNameLabel = new JLabel("Name:");
        lectureNameLabel.setBounds(10, 260, 100, 25);
        add(lectureNameLabel);
        lectureNameField = new JTextField();
        lectureNameField.setBounds(120, 260, 150, 25);
        add(lectureNameField);

        JLabel lectureDobLabel = new JLabel("Date of Birth:");
        lectureDobLabel.setBounds(10, 290, 100, 25);
        add(lectureDobLabel);
        lectureDobField = new JTextField();
        lectureDobField.setBounds(120, 290, 150, 25);
        add(lectureDobField);

        JLabel lectureGenderLabel = new JLabel("Gender:");
        lectureGenderLabel.setBounds(10, 320, 100, 25);
        add(lectureGenderLabel);
        lectureGenderField = new JTextField();
        lectureGenderField.setBounds(120, 320, 150, 25);
        add(lectureGenderField);

        addLectureButton = new JButton("Add Lecture");
        addLectureButton.setBounds(10, 350, 150, 25);
        add(addLectureButton);

        updateLectureButton = new JButton("Update Lecture");
        updateLectureButton.setBounds(170, 350, 150, 25);
        add(updateLectureButton);

        deleteLectureButton = new JButton("Delete Lecture");
        deleteLectureButton.setBounds(330, 350, 150, 25);
        add(deleteLectureButton);
        
        // Sự kiện add Lecture
        addLectureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String lectureId = lectureIdField.getText();
                    String name = lectureNameField.getText();
                    String dateOfBirth = lectureDobField.getText();
                    String gender = lectureGenderField.getText();

                    Lecture lecture = new Lecture(lectureId, name, dateOfBirth, gender);
                    lectureDAO.addLecture(lecture);
                    JOptionPane.showMessageDialog(null, "Lecture added successfully!");
                    updateDisplayArea();
                    loadLecturerData();
                    clearFields();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error adding lecture.");
                }
            }
        });

        // Sự kiện update Lecture
        updateLectureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String lectureId = lectureIdField.getText();
                    String name = lectureNameField.getText();
                    String dateOfBirth = lectureDobField.getText();
                    String gender = lectureGenderField.getText();

                    Lecture lecture = new Lecture(lectureId, name, dateOfBirth, gender);
                    lectureDAO.updateLecture(lecture);
                    
                    JOptionPane.showMessageDialog(null, "Lecture updated successfully!");
                    updateDisplayArea();
                    loadLecturerData();
                    clearFields();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error updating lecture.");
                }
            }
        });

        //Sự kiện delete Lecture
        deleteLectureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String lectureId = lectureIdField.getText();
                    lectureDAO.deleteLecture(lectureId);

                    JOptionPane.showMessageDialog(null, "Lecture deleted successfully!");
                    updateDisplayArea();
                    loadLecturerData();
                    clearFields();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error deleting lecture.");
                }
            }
        });

        //Subject Section
        JLabel subjectSectionLabel = new JLabel("Add/Update/Delete Subject");
        subjectSectionLabel.setBounds(10, 390, 200, 25);
        add(subjectSectionLabel);

        JLabel subjectIdLabel = new JLabel("Subject ID:");
        subjectIdLabel.setBounds(10, 420, 100, 25);
        add(subjectIdLabel);
        subjectIdField = new JTextField();
        subjectIdField.setBounds(120, 420, 150, 25);
        add(subjectIdField);

        JLabel subjectNameLabel = new JLabel("Subject Name:");
        subjectNameLabel.setBounds(10, 450, 100, 25);
        add(subjectNameLabel);
        subjectNameField = new JTextField();
        subjectNameField.setBounds(120, 450, 150, 25);
        add(subjectNameField);
        JLabel lecturerIdLabel = new JLabel("Lecturer ID:");
        lecturerIdLabel.setBounds(10, 480, 100, 25);
        add(lecturerIdLabel);
        lectureIdField2 = new JTextField();
        lectureIdField2.setBounds(120, 480, 150, 25);
        add(lectureIdField2);

        addSubjectButton = new JButton("Add Subject");
        addSubjectButton.setBounds(10, 520, 150, 25);
        add(addSubjectButton);

        updateSubjectButton = new JButton("Update Subject");
        updateSubjectButton.setBounds(170, 520, 150, 25);
        add(updateSubjectButton);

        deleteSubjectButton = new JButton("Delete Subject");
        deleteSubjectButton.setBounds(330, 520, 150, 25);
        add(deleteSubjectButton);

        // Sự kiện add subject
        addSubjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String subjectId = subjectIdField.getText();
                    String subjectName = subjectNameField.getText();
                    String lecturerId = lectureIdField2.getText();
        
                    Subject subject = new Subject(subjectId, subjectName);
                    subjectDAO.addSubject(subject, lecturerId);
        
                    JOptionPane.showMessageDialog(null, "Subject added successfully!");
                    updateDisplayArea();
                    loadSubjectData();
                    clearFields();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error adding subject.");
                }
            }
        });
        
        // Sự kiện update subject
        updateSubjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String subjectId = subjectIdField.getText();
                    String subjectName = subjectNameField.getText();
                    String lecturerId = lectureIdField2.getText();
                    Subject subject = new Subject(subjectId, subjectName);
                    subjectDAO.updateSubject(subject, lecturerId);

                    JOptionPane.showMessageDialog(null, "Subject updated successfully!");
                    updateDisplayArea();
                    loadSubjectData();
                    clearFields();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error updating subject.");
                }
            }
        });

        // Sự kiện delete subject
        deleteSubjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String subjectId = subjectIdField.getText();
                    subjectDAO.deleteSubject(subjectId);

                    JOptionPane.showMessageDialog(null, "Subject deleted successfully!");
                    updateDisplayArea();
                    loadSubjectData();
                    clearFields();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error deleting subject.");
                }
            }
        });

        // Register Subject Section
        JLabel registerSectionLabel = new JLabel("Register Subject for Student");
        registerSectionLabel.setBounds(500, 10, 200, 25);
        add(registerSectionLabel);

        JLabel registerStudentIdLabel = new JLabel("Student ID:");
        registerStudentIdLabel.setBounds(500, 40, 100, 25);
        add(registerStudentIdLabel);
        registerStudentIdField = new JTextField();
        registerStudentIdField.setBounds(600, 40, 150, 25);
        add(registerStudentIdField);

        JLabel registerSubjectIdLabel = new JLabel("Subject ID:");
        registerSubjectIdLabel.setBounds(500, 70, 100, 25);
        add(registerSubjectIdLabel);
        registerSubjectIdField = new JTextField();
        registerSubjectIdField.setBounds(600, 70, 150, 25);
        add(registerSubjectIdField);

        registerSubjectButton = new JButton("Register Subject");
        registerSubjectButton.setBounds(600, 100, 150, 25);
        add(registerSubjectButton);
        deleteRegisterSubjectButton = new JButton("Delete Register");
        deleteRegisterSubjectButton.setBounds(600, 130, 150, 25);
        add(deleteRegisterSubjectButton);

        // Sự kiện đăng ký môn học 
        registerSubjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String studentId = registerStudentIdField.getText();
                    String subjectId = registerSubjectIdField.getText();

                    studentDAO.registerSubject(studentId, subjectId);

                    JOptionPane.showMessageDialog(null, "Subject registered successfully!");
                    updateDisplayArea();
                    clearFields();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error registering subject.");
                }
            }
        });

        // Sự kiện hủy đăng ký môn học
        deleteRegisterSubjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    String studentId = registerStudentIdField.getText();
                    String subjectId = registerSubjectIdField.getText();
    
                    studentDAO.deleteRegisterSubject(studentId, subjectId);
                    JOptionPane.showMessageDialog(null, "Cancel registration successfully!");
                    updateDisplayArea();
                    clearFields();
                }catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error registering subject.");
                }
            }
        });

        // Display Area
        displayArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBounds(800, 10, 300, 200);
        add(scrollPane);
        updateDisplayArea();

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(500, 200,600,300);
        JPanel studentPanel = createStudentPanel();
        JPanel lecturerPanel = createLecturerPanel();
        JPanel subjectPanel = createSubjectPanel();
        tabbedPane.addTab("Students", studentPanel);
        tabbedPane.addTab("Lecturer", lecturerPanel);
        tabbedPane.addTab("Subject", subjectPanel);
        add(tabbedPane, BorderLayout.CENTER);
        loadStudentData();
        loadLecturerData();
        loadSubjectData();
    }
    
    public void loadStudentData() {
        try {
            List<Student> students = studentDAO.getAllStudents();
            DefaultTableModel model = (DefaultTableModel) studentTable.getModel();
            model.setRowCount(0);
            for (Student student : students) {
                model.addRow(new Object[]{student.getStudentId(), student.getName(), student.getDateOfBirth(), student.getGender()});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadLecturerData(){
        try {
            List<Lecture> lectures = lectureDAO.getAllLectures();
            DefaultTableModel model = (DefaultTableModel) lecturerTable.getModel();
            model.setRowCount(0);
            for (Lecture lecture : lectures) {
                model.addRow(new Object[]{lecture.getLectureId(), lecture.getName(), lecture.getDateOfBirth(),lecture.getGender()});
            }
        }
        catch( SQLException e){
            e.printStackTrace();
        }
    }

    public void loadSubjectData(){
        try {
            List<Subject> subjects = subjectDAO.getAllSubjects();

            DefaultTableModel model = (DefaultTableModel) subjecTable.getModel();
            model.setRowCount(0);
            for (Subject subject : subjects) {
                Lecture lecture = subjectDAO.getLecturerForSubject(subject.getSubjectId());
                model.addRow(new Object[]{subject.getSubjectId(), subject.getSubjectName(), lecture.getName()});
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    private JPanel createStudentPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        String[] columnNames = {"Student ID", "Name", "Date of Birth", "Gender"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        studentTable = new JTable(model);
        panel.add(new JScrollPane(studentTable), BorderLayout.CENTER);
        return panel;
    }
    private JPanel createLecturerPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        String[] columnNames = {"Lecturer ID", "Name", "Date of Birth", "Gender"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        lecturerTable = new JTable(model);
        panel.add(new JScrollPane(lecturerTable), BorderLayout.CENTER);
        return panel;
    }
    private JPanel createSubjectPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        String[] columnNames = {"Subject ID", "Subject Name", "Lecturer"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        subjecTable = new JTable(model);
        panel.add(new JScrollPane(subjecTable), BorderLayout.CENTER);
        return panel;
    }

    // xóa các ô sau khi thực hiện 
    public void clearFields() {
        studentIdField.setText("");
        studentNameField.setText("");
        studentDobField.setText("");
        studentGenderField.setText("");
    
        lectureIdField.setText("");
        lectureNameField.setText("");
        lectureDobField.setText("");
        lectureGenderField.setText("");
        lectureIdField2.setText("");
        subjectIdField.setText("");
        subjectNameField.setText("");
        
        registerStudentIdField.setText("");
        registerSubjectIdField.setText("");
    }

    // Cập nhật hiển thị thông tin 
    private void updateDisplayArea() {
        displayArea.setText("");
        try {
            List<Student> students = studentDAO.getAllStudents();
            List<Subject> subjects = subjectDAO.getAllSubjects();
            displayArea.append("List student registered:\n");
            for (Subject subject : subjects) {
                displayArea.append("\n" + subject.getSubjectName() + ":\n");
                List<Student> students1 = studentDAO.getStudentsForSubject(subject.getSubjectId());
                if (!students.isEmpty()) {
                    for (Student student : students1) {
                        displayArea.append("Student: " + student.getName() + " (ID: " + student.getStudentId() + ")\n");
                    }
                } else {
                    displayArea.append("No student registered\n");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UniversityManagementApp().setVisible(true);
            }
        });
    }
}
