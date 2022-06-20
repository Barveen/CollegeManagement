package DataSource;
import DataClass.*;
import UtilClass.MD5;

import java.sql.*;
import java.util.*;



public class DBConnection implements StudentInterface {
    Connection con = null;
    PreparedStatement ps;
    public ResultSet rs;


    boolean rowUpdated, success;

    public DBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/collegestudentmanagement?" + "user=root&password=Allah786$");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean insertStudentAcademicDetails(Student student) throws SQLException {
        System.out.println(StudentFunction1.uId);
        ps = con.prepareStatement("INSERT INTO  studentacademicdetails(RollNumber,Name,DepartmentName,Year,PhoneNumber,MailId,Role) VALUES(?,?,?,?,?,?,?)");
        ps.setString(1, student.getRollNumber());
        ps.setString(2, student.getStudentName());
        ps.setString(3, student.getDepartmentName());
        ps.setInt(4, student.getYear());
        ps.setString(5, student.getPhoneNumber());
        ps.setString(6, student.getMailId());
        ps.setString(7,student.getRole());

        success = ps.executeUpdate() > 0;

        return success;
    }

    public boolean insertDepartment(int departmentId, String departmentName, int cutOff, int seatavailability) throws SQLException {
        ps = con.prepareStatement("INSERT INTO  departmentdetails(DepartmentId, DepartmentName, CutOffMark, SeatAvailability) VALUES(?,?,?,?)");
        ps.setInt(1, departmentId);
        ps.setString(2, departmentName);
        ps.setInt(3, cutOff);
        ps.setInt(4, seatavailability);

        success = ps.executeUpdate() > 0;
        return success;
    }

    public boolean insertPersonalDetails(PersonalDetails personalDetails) throws SQLException {
        ps = con.prepareStatement("INSERT INTO  personaldetails(RollNumber, DOB, Gender, District) VALUES(?,?,?,?)");
        ps.setString(1, personalDetails.getRollNumber());
        ps.setString(2, personalDetails.getDateOfBirth());
        ps.setString(3, String.valueOf(personalDetails.getGender()));
        ps.setString(5, personalDetails.getDistrict());
        success = ps.executeUpdate() > 0;
        return success;
    }

    public boolean insertNewAdmissionDetails(String name, String dateOfBirth, GenderType gender, int cutOffMark, String departmentName, String status, String mailId, String district) throws SQLException {
        System.out.println(name);
        ps = con.prepareStatement("INSERT INTO  newadmissiondetails(Name, DOB, Gender, CutOffMark, DepartmentName, MailId, Status, District) VALUES(?,?,?,?,?,?,?,?)");
        ps.setString(1, name);
        ps.setString(2,dateOfBirth);
        ps.setString(3, String.valueOf(gender));
        ps.setInt(4, cutOffMark);
        ps.setString(5,departmentName);
        ps.setString(6,mailId);
        ps.setString(7, status);
        ps.setString(8, district);

        success = ps.executeUpdate() > 0;
        return success;
    }

    public boolean insertSubjectDetails(String subjectId, String subjectName, int departmentId, int semester) throws SQLException {
        ps = con.prepareStatement("INSERT INTO subjectdetails(SubjectId, SubjectName, DepartmentId, Semester) VALUES (?,?,?,?)");
        ps.setString(1, subjectId);
        ps.setString(2, subjectName);
        ps.setInt(3, departmentId);
        ps.setInt(4, semester);
        success = ps.executeUpdate() > 0;
        return success;
    }

    public boolean insertMarks(MarkDetails[] markDetails) throws SQLException {
        StringBuilder a = new StringBuilder("INSERT INTO studentmarkdetails(RollNumber, DepartmetName, Semester, SubjectId, Mark) VALUES ");
        int n = markDetails.length;
        for (int i = 0; i < n; i++) {
            String b = markDetails[i].toString();
            a.append(b);
            if (i < n - 1) {
                a.append(",");
            }
        }
        ps = con.prepareStatement(a.toString());
        ps.executeUpdate();
        return success;
    }
    
    public List<AdmissionDetails> getNewAdmissionDetails() throws SQLException {
        List<AdmissionDetails> admissionDetails = new ArrayList<>();
        ps = con.prepareStatement("SELECT * from newadmissiondetails ");
        rs = ps.executeQuery();

        while (rs.next()) {
            String name           = rs.getString("Name");
            GenderType gender     = GenderType.valueOf(rs.getString("Gender"));
            int cutOffMark        = rs.getInt("CutOffMark");
            String departmentName = rs.getString("DepartmentName");
            String mailId         = rs.getString("MailId");
            String status         = rs.getString("Status");
            admissionDetails.add(new AdmissionDetails(name, gender, cutOffMark, departmentName, mailId, status));
        }

        return admissionDetails;
    }

    public List<Student> getStudentDetails() throws SQLException {
        List<Student> students = new ArrayList<>();
        ps = con.prepareStatement("Select RollNumber, Name, DepartmentName, Year, PhoneNumber, MailId,Role from studentacademicdetails");
        rs = ps.executeQuery();
        while (rs.next()) {
            String rollNumber     = rs.getString("RollNumber");
            String studentName    = rs.getString("Name");
            String departmentName = rs.getString("DepartmentName");
            int year              = rs.getInt("Year");
            String phoneNumber    = rs.getString("PhoneNumber");
            String mailId         = rs.getString("MailId");
            String role           = rs.getString("Role");
            students.add(new Student(rollNumber, studentName, departmentName, year, phoneNumber, mailId,role));
        }
        return students;
    }

    public List<Department> getDepartments() throws SQLException {
        List<Department> departmentList = new ArrayList<>();
        ps = con.prepareStatement(" Select DepartmentId, DepartmentName, CutOffMark, SeatAvailability from departmentdetails");
        rs = ps.executeQuery();
        while (rs.next()) {

            int departmentId      = rs.getInt("DepartmentId");
            String departmentName = rs.getString("DepartmentName");
            int cutOff            = rs.getInt("CutOffMark");
            int seatsAvailability = rs.getInt("SeatAvailability");
            departmentList.add(new Department(departmentId, departmentName, seatsAvailability, cutOff));
        }
        return departmentList;
    }

    public List<PersonalDetails> getPersonalDetails() throws SQLException {
        List<PersonalDetails> personalDetails = new ArrayList<>();
        ps = con.prepareStatement(" Select RollNumber, DOB, Gender, District from personaldetails");
        rs = ps.executeQuery();
        while (rs.next()) {
            String rollNumber  = rs.getString("RollNumber");
            String dateOfBirth = rs.getString("DOB");
            GenderType gender  = GenderType.valueOf(rs.getString("Gender"));
            String district    = rs.getString("District");
            personalDetails.add(new PersonalDetails(rollNumber, dateOfBirth, gender, district));
        }
        return personalDetails;
    }

    public List<Faculty> getByFacultyDetails() throws SQLException {
        String role = "Faculty";
        List<Faculty>faculty = new ArrayList<>();
        ps = con.prepareStatement("SELECT  studentacademicdetails.RollNumber, Name, DepartmentName, Year, PhoneNumber, MailId,Role, personaldetails.DOB,Gender,District FROM studentacademicdetails right join personaldetails on studentacademicdetails.RollNumber = personaldetails.RollNumber where studentacademicdetails.Role = ? ");
        ps.setString(1,role);
        rs = ps.executeQuery();
        while (rs.next()) {
            String id             = rs.getString("RollNumber");
            String name           = rs.getString("Name");
            String departmentName = rs.getString("DepartmentName");
            int year              = rs.getInt("Year");
            String mailId         = rs.getString("MailId");
            String phoneNumber    = rs.getString("PhoneNumber");
            String dateOfBirth    = rs.getString("DOB");
            GenderType gender     = GenderType.valueOf(rs.getString ("Gender"));
            String district       = rs.getString("District");
            faculty.add(new Faculty(id, name, departmentName, year, mailId,role, phoneNumber, dateOfBirth, gender, district));
        }
        return faculty;
    }

    public Student getByStudentId(String rollNumber) throws Exception {
        Student student = null;
        String role1    = StudentFunction1.roles;
        StringBuilder a = new StringBuilder("SELECT * FROM studentacademicdetails where RollNumber = '");
        a.append(rollNumber).append("'");
        if(role1.equalsIgnoreCase("Faculty"))
        {
           a.append(" && DepartmentName ='").append(StudentFunction1.departMentName).append("'");
                   a.append(" && Year = ").append(StudentFunction1.years);
        }
            ps = con.prepareStatement(String.valueOf(a));
            rs = ps.executeQuery();
            while (rs.next()) {
                String name           = rs.getString("Name");
                String departmentName = rs.getString("DepartmentName");
                int year              = rs.getInt("Year");
                String phoneNumber    = rs.getString("PhoneNumber");
                String mailId         = rs.getString("MailId");
                String role           = rs.getString("Role");
                student = new Student(rollNumber, name, departmentName, year, phoneNumber, mailId,role);

            }
            return student;
        }

    public Student getByStudentName(String name) throws Exception {
        Student student = null;
        String role1 = StudentFunction1.roles;
        StringBuilder a = new StringBuilder("SELECT * FROM studentacademicdetails where Name = '");
        a.append(name).append("'");
        if(role1.equalsIgnoreCase("Faculty"))
        {
            a.append(" && DepartmentName ='").append(StudentFunction1.departMentName).append("'");
            a.append(" && Year = ").append(StudentFunction1.years);
        }
        ps = con.prepareStatement(String.valueOf(a));
        rs = ps.executeQuery();
        while (rs.next()) {
            String id             = rs.getString("RollNumber");
            String departmentName = rs.getString("DepartmentName");
            int year              = rs.getInt("Year");
            String phoneNumber    = rs.getString("PhoneNumber");
            String mailId         = rs.getString("MailId");
            String role           = rs.getString("Role");
            student = new Student(id, name, departmentName, year, phoneNumber, mailId, role);
        }
        return student;
    }
    public Student getTheirStudentDetails(String name,String departmentName,int year) throws SQLException
    {
            Student student = null;
            ps = con.prepareStatement("SELECT * FROM studentacademicdetails where Name = ? && DepartmentName = ? && Year = ?");
            ps.setString(1, name);
            ps.setString(2,departmentName);
            ps.setInt(3, year);
            rs = ps.executeQuery();
            while (rs.next()) {
                String id          = rs.getString("RollNumber");
                String phoneNumber = rs.getString("PhoneNumber");
                String mailId      = rs.getString("MailId");
                String role        = rs.getString("Role");
                student            = new Student(id, name, departmentName, year, phoneNumber, mailId, role);
            }
        return student;
    }

    public Student authenticationByUserId(String rollNumber) throws SQLException {
        Student student = null;
        ps = con.prepareStatement("SELECT * FROM studentacademicdetails where RollNumber = ?");
        ps.setString(1, rollNumber);
        rs = ps.executeQuery();
        while (rs.next()) {
            String name           = rs.getString("Name");
            String departmentName = rs.getString("DepartmentName");
            int year              = rs.getInt("Year");
            String phoneNumber    = rs.getString("PhoneNumber");
            String mailId         = rs.getString("MailId");
            String role           = rs.getString("Role");
            student               = new Student(rollNumber, name, departmentName, year, phoneNumber, mailId,role);

        }
        return student;

    }

    public boolean updateSeats(int seats, String departmentName) throws SQLException {
        ps = con.prepareStatement("UPDATE departmentdetails SET SeatAvailability = ? WHERE DepartmentName = ?");
        ps.setString(2, departmentName);
        ps.setInt(1, seats);
        rowUpdated = ps.executeUpdate() > 0;
        return rowUpdated;
    }

    public boolean removeStudents(String rollNumber) throws SQLException {
        ps = con.prepareStatement("DELETE FROM studentacademicdetails WHERE RollNumber = ?");
        ps.setString(1, rollNumber);
        success = ps.executeUpdate() > 0;
        return success;
    }

    public List<MarkDetails> getMarksByStudentId(String rollNumber, String departmentName, int years) throws SQLException {

        List<MarkDetails> markDetails = new ArrayList<>();
        ps = con.prepareStatement("select st.RollNumber,studentacademicdetails.Name,st.DepartmetName,st.Semester,st.SubjectId,st.Mark,subjectdetails.SubjectName from studentmarkdetails St inner join subjectdetails using(SubjectId)inner join studentacademicdetails using(RollNumber) where st.RollNumber = ? && DepartmentName = ?  ");
        ps.setString(1, rollNumber);
        ps.setString(2,departmentName);
        rs = ps.executeQuery();
        while (rs.next()) {
            String name        = rs.getString("Name");
            int semester       = rs.getInt("Semester");
            String subjectCode = rs.getString("SubjectId");
            int mark           = rs.getInt("Mark");
            String subName     = rs.getString("SubjectName");

            markDetails.add(new MarkDetails(rollNumber,name, departmentName, semester, subjectCode, mark, subName));
        }
        return markDetails;
    }

    public List<Student> getStudentsByDepartmentAndYear(String departmentName, int year) throws SQLException {
        List<Student> students = new ArrayList<>();
        ps = con.prepareStatement("Select * from studentacademicdetails where DepartmentName = ? && Year = ? && Role = 'Student'");
        ps.setString(1,departmentName);
        ps.setInt(2, year);
        rs = ps.executeQuery();
        while(rs.next())
        {
            String rollNumber  = rs.getString("RollNumber");
            String name        = rs.getString("Name");
            String phoneNumber = rs.getString("PhoneNumber");
            String mailId      = rs.getString("MailId");
            String role        = rs.getString("Role");
            students.add(new Student(rollNumber, name, departmentName, year, phoneNumber, mailId,role));

        }

        return students;
    }

    public List<MarkDetails> getFailStudents() throws SQLException
    {
        List<MarkDetails> markDetailsList = new ArrayList<>();
        ps = con.prepareStatement("select st.RollNumber,studentacademicdetails.Name,st.DepartmetName,st.Semester,st.SubjectId,st.Mark,subjectdetails.SubjectName from studentmarkdetails St inner join subjectdetails using(SubjectId)inner join studentacademicdetails using(RollNumber) where st.Mark < 50");
        rs = ps.executeQuery();
        while (rs.next())
        {
            String rollNumber     = rs.getString("RollNumber");
            String name           = rs.getString("Name");
            String departmentName = rs.getString("DepartmetName");
            String subjectCode    = rs.getString("SubjectId");
            String subjectName    = rs.getString("SubjectName");
            int marks             = rs.getInt("Mark");
            int semester          = rs.getInt("Semester");
            markDetailsList.add(new MarkDetails(rollNumber,name,departmentName,semester,subjectCode,marks,subjectName));
        }
        return markDetailsList;
    }


    public boolean updatePassWord(String userId, String passWord) throws SQLException {
        String passWordHash = MD5.getMd5(passWord);
        ps = con.prepareStatement("UPDATE userdetails SET UserPassword = ? WHERE UserId = ?");
        ps.setString(2, userId);
        ps.setString(1, passWordHash);
        rowUpdated = ps.executeUpdate() > 0;
        return rowUpdated;
    }

    public boolean newUser(String userId, String password,String role) throws SQLException {
        String passWordHash = MD5.getMd5(password);
        ps = con.prepareStatement("INSERT INTO userdetails(UserId, UserPassword,Role) VALUES (?,?,?)");
        ps.setString(1, userId);
        ps.setString(2, passWordHash);
        ps.setString(3,role);
        success = ps.executeUpdate() > 0;
        return success;
    }

    public UserDetails getUser(String userId) throws SQLException {
        UserDetails userDetails = null;
        ps = con.prepareStatement("Select UserId, UserPassword from userdetails where UserId = ? ");
        ps.setString(1, userId);
        rs = ps.executeQuery();
        while (rs.next()) {
            String passwrod = rs.getString("UserPassword");
            userDetails     = new UserDetails(userId, passwrod);
        }
        return userDetails;
    }

    public void close() throws Exception {
        con.close();
        System.out.println("Connection Closed");
    }

}
