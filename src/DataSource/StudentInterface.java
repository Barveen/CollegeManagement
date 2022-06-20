package DataSource;

import DataClass.*;

import java.sql.SQLException;
import java.util.List;

public interface StudentInterface {
    boolean insertStudentAcademicDetails(Student student) throws SQLException;
    boolean insertDepartment(int departmentId, String departmentName, int cutOff, int seatavailability) throws SQLException;
    boolean insertPersonalDetails(PersonalDetails personalDetails) throws SQLException;
    boolean insertSubjectDetails(String subjectId,String subjectName, int departmentId, int semester) throws SQLException;
    List<Department> getDepartments() throws SQLException;
    List<Student> getStudentDetails() throws SQLException;
    List<PersonalDetails> getPersonalDetails() throws SQLException;
    Student getByStudentId(String rollNumber) throws Exception;
    Student getByStudentName(String name) throws Exception;
    boolean updateSeats(int seats,String departmentName) throws SQLException;
    boolean removeStudents(String rollNumber) throws SQLException;
    List<Faculty> getByFacultyDetails() throws SQLException;

    List<MarkDetails> getMarksByStudentId(String rollNumber, String departmentName, int years) throws SQLException;

    List<AdmissionDetails> getNewAdmissionDetails() throws SQLException;

    boolean newUser(String userId,String password,String role) throws SQLException;
    UserDetails getUser(String  userId) throws SQLException;

    boolean insertMarks(MarkDetails[] markDetails) throws SQLException;
    void close() throws Exception;

    List<Student> getStudentsByDepartmentAndYear(String departmentName, int year)throws SQLException;

    List<MarkDetails> getFailStudents() throws SQLException;

    boolean updatePassWord(String userId, String passWord) throws SQLException;

    Student getTheirStudentDetails(String name, String departMentName, int years)throws SQLException;
    Student authenticationByUserId(String rollNumber)throws SQLException;

    boolean insertNewAdmissionDetails(String name, String dateOfBirth, GenderType gender, int cutOffMark, String departmentName, String status, String mailId, String district) throws SQLException;

    // void insertMarks(List<MarkDetails> markDetailsList);
}

