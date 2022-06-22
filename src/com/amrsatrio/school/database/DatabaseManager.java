package com.amrsatrio.school.database;

import com.amrsatrio.school.database.DatabaseConnection.ConnectionParams;
import com.amrsatrio.school.model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private DatabaseConnection connection;

    public DatabaseManager() throws SQLException {
        ConnectionParams params = new ConnectionParams();
        params.setDatabase("project");
        connection = new DatabaseConnection(params);
    }

    // region Class
    public List<SchoolClass> fetchClasses() {
        List<SchoolClass> classes = new ArrayList<>();

        String query = "SELECT * FROM class";
        try (ResultSet rs = connection.executeQuery(query)) {
            while (rs.next()) {
                classes.add(readClass(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return classes;
    }

    public int getNumClasses() {
        String query = "SELECT COUNT(*) FROM class";
        try (ResultSet rs = connection.executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public int addStudentToClass(Student student, SchoolClass clazz) {
        String query = "INSERT INTO student (classId, studentId) VALUES (?, ?)";
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, clazz.getId());
            st.setString(2, student.getId());
            return st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    // endregion

    // region Student
    public List<Student> fetchStudents() {
        List<Student> students = new ArrayList<>();

        String query = "SELECT * FROM student_detail";
        try (ResultSet rs = connection.executeQuery(query)) {
            while (rs.next()) {
                students.add(readStudent(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return students;
    }

    public Student findStudentById(String id) {
        String query = "SELECT * FROM student_detail WHERE studentId = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return readStudent(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public int getNumStudents() {
        int numStudents = 0;

        String query = "SELECT COUNT(*) FROM student_detail";
        try (ResultSet rs = connection.executeQuery(query)) {
            if (rs.next()) {
                numStudents = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return numStudents;
    }

    private String getNextStudentId() {
        String query = "SELECT studentId FROM student_detail ORDER BY studentId DESC LIMIT 1";
        try (ResultSet rs = connection.executeQuery(query)) {
            if (rs.next()) {
                return "ST" + String.format("%03d", Integer.parseInt(rs.getString(1).substring(2)) + 1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "ST001";
    }

    public int insertStudent(Student student) {
        return internalUpdateStudent(student, "INSERT INTO student_detail (studentId, name, gender, dateOfBirth, email, phone, address, exculId) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", true);
    }

    public int updateStudent(Student student) {
        return internalUpdateStudent(student, "UPDATE student_detail SET name = ?, gender = ?, dateOfBirth = ?, email = ?, phone = ?, address = ?, exculId = ?", false);
    }

    private int internalUpdateStudent(Student student, String query, boolean withId) {
        try (PreparedStatement st = connection.prepareStatement(query + (!withId ? " WHERE studentId = '" + student.getId() + "'" : ""))) {
            int i = 0;
            if (withId) {
                String newId = getNextStudentId();
                student.setId(newId);
                st.setString(++i, newId);
            }
            st.setString(++i, student.getName());
            st.setString(++i, student.getGender());
            st.setDate(++i, new java.sql.Date(student.getDateOfBirth().getTime()));
            st.setString(++i, student.getEmail());
            st.setString(++i, student.getPhone());
            st.setString(++i, student.getAddress());
            Excul excul = student.getExcul();
            st.setString(++i, excul != null ? excul.getId() : null);
            return st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteStudent(Student student) {
        String query = "DELETE FROM student_detail WHERE studentId = '" + student.getId() + "'";
        try {
            return connection.executeUpdate(query) != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    // endregion

    // region Teacher
    public List<Teacher> fetchTeachers() {
        List<Teacher> teachers = new ArrayList<>();

        String query = "SELECT * FROM teacher";
        try (ResultSet rs = connection.executeQuery(query)) {
            while (rs.next()) {
                teachers.add(readTeacher(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return teachers;
    }

    public Teacher findTeacherById(String id) {
        String query = "SELECT * FROM teacher WHERE teacherId = '" + id + "'";
        try (ResultSet rs = connection.executeQuery(query)) {
            if (rs.next()) {
                return readTeacher(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public int getNumTeachers() {
        int numTeachers = 0;

        String query = "SELECT COUNT(*) FROM teacher";
        try (ResultSet rs = connection.executeQuery(query)) {
            if (rs.next()) {
                numTeachers = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return numTeachers;
    }

    private String getNextTeacherId() {
        String query = "SELECT teacherId FROM teacher ORDER BY teacherId DESC LIMIT 1";
        try (ResultSet rs = connection.executeQuery(query)) {
            if (rs.next()) {
                return "TE" + String.format("%03d", Integer.parseInt(rs.getString(1).substring(2)) + 1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "TE001";
    }

    public int insertTeacher(Teacher teacher) {
        return internalUpdateTeacher(teacher, "INSERT INTO teacher (teacherId, name, gender, dateOfBirth, email, phone, address) VALUES (?, ?, ?, ?, ?, ?, ?)", true);
    }

    public int updateTeacher(Teacher teacher) {
        return internalUpdateTeacher(teacher, "UPDATE teacher SET name = ?, gender = ?, dateOfBirth = ?, email = ?, phone = ?, address = ?", false);
    }

    private int internalUpdateTeacher(Teacher teacher, String query, boolean withId) {
        try (PreparedStatement st = connection.prepareStatement(query + (!withId ? " WHERE teacherId = '" + teacher.getId() + "'" : ""))) {
            int i = 0;
            if (withId) {
                String newId = getNextTeacherId();
                teacher.setId(newId);
                st.setString(++i, newId);
            }
            st.setString(++i, teacher.getName());
            st.setString(++i, teacher.getGender());
            st.setDate(++i, new java.sql.Date(teacher.getDateOfBirth().getTime()));
            st.setString(++i, teacher.getEmail());
            st.setString(++i, teacher.getPhone());
            st.setString(++i, teacher.getAddress());
//            st.setString(++i, teacher.getExcul().getId());
            return st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteTeacher(Teacher teacher) {
        String query = "DELETE FROM teacher WHERE teacherId = '" + teacher.getId() + "'";
        try {
            return connection.executeUpdate(query) != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    // endregion

    // region Excul
    public List<Excul> fetchExculs() {
        List<Excul> exculs = new ArrayList<>();

        String query = "SELECT * FROM excul";
        try (ResultSet rs = connection.executeQuery(query)) {
            while (rs.next()) {
                exculs.add(readExcul(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return exculs;
    }

    public Excul findExculById(String id) {
        String query = "SELECT * FROM excul WHERE exculId = '" + id + "'";
        try (ResultSet rs = connection.executeQuery(query)) {
            if (rs.next()) {
                return readExcul(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Excul findExculByName(String name) {
        String query = "SELECT * FROM excul WHERE UPPER(name) = UPPER('" + name + "')";
        try (ResultSet rs = connection.executeQuery(query)) {
            if (rs.next()) {
                return readExcul(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public int getNumExculs() {
        int numExculs = 0;

        String query = "SELECT COUNT(*) FROM excul";
        try (ResultSet rs = connection.executeQuery(query)) {
            if (rs.next()) {
                numExculs = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return numExculs;
    }

    private String getNextExculId() {
        String query = "SELECT exculId FROM excul ORDER BY exculId DESC LIMIT 1";
        try (ResultSet rs = connection.executeQuery(query)) {
            if (rs.next()) {
                return "E" + String.format("%02d", Integer.parseInt(rs.getString(1).substring(1)) + 1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "E01";
    }

    public int insertExcul(Excul excul) {
        return internalUpdateExcul(excul, "INSERT INTO excul (exculId, name) VALUES (?, ?)", true);
    }

    public int updateExcul(Excul excul) {
        return internalUpdateExcul(excul, "UPDATE excul SET name = ?", false);
    }

    private int internalUpdateExcul(Excul excul, String query, boolean withId) {
        try (PreparedStatement st = connection.prepareStatement(query + (!withId ? " WHERE exculId = '" + excul.getId() + "'" : ""))) {
            int i = 0;
            if (withId) {
                String newId = getNextExculId();
                excul.setId(newId);
                st.setString(++i, newId);
            }
            st.setString(++i, excul.getName());
            return st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteExcul(Excul excul) {
        String query = "DELETE FROM excul WHERE exculId = '" + excul.getId() + "'";
        try {
            return connection.executeUpdate(query) != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    // endregion

    // region Subject
    public List<Subject> fetchSubjects() {
        List<Subject> subjects = new ArrayList<>();

        String query = "SELECT * FROM subject_detail";
        try (ResultSet rs = connection.executeQuery(query)) {
            while (rs.next()) {
                subjects.add(readSubject(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return subjects;
    }

    public Subject findSubjectById(String id) {
        String query = "SELECT * FROM subject_detail WHERE subjectId = '" + id + "'";
        try (ResultSet rs = connection.executeQuery(query)) {
            if (rs.next()) {
                return readSubject(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public int getNumSubjects() {
        int numSubjects = 0;

        String query = "SELECT COUNT(*) FROM subject_detail";
        try (ResultSet rs = connection.executeQuery(query)) {
            if (rs.next()) {
                numSubjects = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return numSubjects;
    }
    // endregion

    // region Deserializers
    public SchoolClass readClass(ResultSet rs) throws SQLException {
        SchoolClass c = new SchoolClass();
        c.setId(rs.getString("classId"));
        c.setYear(rs.getInt("year"));
        c.setName(rs.getString("name"));
        //c.setTeacher(readTeacher(rs));

        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM student JOIN student_detail ON student.studentId = student_detail.studentId WHERE classId = '" + c.getId() + "'";
        try (ResultSet studentsRs = connection.executeQuery(query)) {
            while (studentsRs.next()) {
                students.add(readStudent(studentsRs));
            }
        }
        c.setStudents(students);

        List<Subject> subjects = new ArrayList<>();
        query = "SELECT * FROM subject " +
                "JOIN subject_detail ON subject.subjectId = subject_detail.subjectId " +
                "JOIN teacher ON subject_detail.teacherId = teacher.teacherId " +
                "WHERE classId = '" + c.getId() + "'";
        try (ResultSet subjectsRs = connection.executeQuery(query)) {
            while (subjectsRs.next()) {
                subjects.add(readSubject(subjectsRs, true));
            }
        }

        return c;
    }

    public void readPerson(ResultSet rs, Person p, String prefix) throws SQLException {
        p.setId(rs.getString(prefix + "Id"));
        p.setName(rs.getString("name"));
        p.setGender(rs.getString("gender"));
        p.setDateOfBirth(rs.getDate("dateOfBirth"));
        p.setEmail(rs.getString("email"));
        p.setPhone(rs.getString("phone"));
        p.setAddress(rs.getString("address"));
    }

    public Teacher readTeacher(ResultSet rs) throws SQLException {
        Teacher t = new Teacher();
        readPerson(rs, t, "teacher");
        return t;
    }

    public Student readStudent(ResultSet rs) throws SQLException {
        Student s = new Student();
        readPerson(rs, s, "student");

        String exculId = rs.getString("exculId");
        if (exculId != null) {
            s.setExcul(findExculById(exculId));
        }

        return s;
    }

    public Excul readExcul(ResultSet rs) throws SQLException {
        Excul e = new Excul();
        e.setId(rs.getString("exculId"));
        e.setName(rs.getString("name"));
        return e;
    }

    public Subject readSubject(ResultSet rs) throws SQLException {
        return readSubject(rs, false);
    }

    public Subject readSubject(ResultSet rs, boolean joined) throws SQLException {
        Subject s = new Subject();
        s.setId(rs.getString("subjectId"));
        s.setName(rs.getString("name"));

        if (joined) {
            s.setTeacher(readTeacher(rs));
        } else {
            s.setTeacher(findTeacherById(rs.getString("teacherId")));
        }

        return s;
    }
    // endregion
}
