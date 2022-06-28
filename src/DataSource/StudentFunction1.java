package DataSource;

import DataClass.*;
import Input.InputFromUser;
import UtilClass.CalculatingAge;
import UtilClass.Checking;
import UtilClass.MD5;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


public class StudentFunction1 {
    StudentInterface con = new DBConnection();
    Authentication authentications = new Authentication();
    InputFromUser inputFromUser = new InputFromUser();
    CalculatingAge calculatingAge = new CalculatingAge();
    Checking checking = new Checking();
    static String uId;
    static int years;
    static String departMentName;
    static String roles;
    String GREEN_BACKGROUND = "\u001B[42m";

    static Scanner sc = new Scanner(System.in);
    public static Scanner str = new Scanner(System.in);

    private static DataSource.StudentFunction1 single_instance = null;
    int departmentId, cutOffMark, availableSeats;
    String departmentName,id,name, phoneNumber, mailId, fatherName;
    String subjectName, dateOfBirth, district,status;
    int mark, year;
    int choice, semester;
    String passWord;
    int count = 0;
    boolean success;
    GenderType gender;
    String userId , role, subjectCode;
    static String userName;
    String PURPLE =	"\u001B[35m";
    String ANSI_RESET = "\u001B[0m";
    int sem, sem1;


    public static StudentFunction1 getInstance() {
        // To ensure only one instance is created
        //a class that has only one instance and provides a global point of access to it
        if (single_instance == null) {
            single_instance = new DataSource.StudentFunction1();
        }
        return single_instance;
    }
    public void login() throws Exception {
        Scanner userInput = new Scanner(System.in);
        Scanner userPass = new Scanner(System.in);
        String passwrod;
        Student student;
        System.out.println("Enter your user Id( your Id Number ) : ");
        userId = userInput.next();
        uId = userId;
        String msg = "Enter the Password";
        passwrod = getMaskedPassword(msg);
        String hash = MD5.getMd5(passwrod);
        UserDetails userDetails1;
        userDetails1 = con.getUser(userId);
        if(userDetails1 != null) {
            if (userDetails1.getUserId().equalsIgnoreCase(userId)) {
                if (userDetails1.getPassword().equalsIgnoreCase(hash)) {

                    System.out.println("Your successfully logged in");
                    student = con.authenticationByUserId(uId);
                    roles = student.getRole();
                    departMentName = student.getDepartmentName();
                    years = student.getYear();
                    userName = student.getStudentName();
                    verification();
                } else {
                    System.out.println("password wrong");
                }
            } else {
                System.out.println("Your user name is wrong , re enter your user name : ");
                userPass.next();
            }
        }else
        {
            System.out.println("..No Records are found ");
            authentications.logOut();
        }
    }


    public void verification() throws Exception {
        switch (roles)
        {
            case "Admin"   -> adminMenuOperation();
            case "Faculty" -> facultyOperation();
            case "Student" -> studentOperation();
        }
    }


    public void adminMenuOperation() throws Exception {
            System.out.println("Welcome to Admin Menu");
            boolean on = true;

            while (on) {
                System.out.println("-------------------------------------------------");
                System.out.println("""
                        Enter\s
                        1 -->  for Add Departments
                        2 -->  for Add Students
                        3 -->  for Add FacultyDetails
                        4 -->  for Show Departments
                        5 -->  for View Students Details
                        6 -->  for View Student Personal Details
                        7 -->  for Search By StudentId
                        8 -->  for Search By StudentName
                        9 -->  for View Faculty Details
                        10 --> for view admission details
                        11 --> for Remove Student
                        12 --> for view student by their department & year
                        13 --> for Change Password
                        14 --> log out""");
                System.out.println("-------------------------------------------------");
                System.out.println(GREEN_BACKGROUND + "Enter your choice : " + ANSI_RESET);
                choice = inputFromUser.inputChoice();
                switch (choice) {
                    case 1 -> addDepartments();
                    case 2 -> addStudentDetails();
                    case 3 -> addFacultyDetails();
                    case 4 -> displayDepartmentDetails();
                    case 5 -> displayStudentDetails();
                    case 6 -> displayStudentPersonalDetails();
                    case 7 -> searchByStudentId();
                    case 8 -> searchByStudentName();
                    case 9 -> displayFacultyDetails();
                    case 10 -> displayAdmissionDetails();
                    case 11 -> RemoveStudent();
                    case 12 -> viewStudentsByDepartmentAndYear();
                    case 13 -> changePassWord();
                    case 14 -> {
                        authentications.logOut();
                        on = false;
                    }
                    default -> {
                        System.out.println(PURPLE+"Please Enter the correct choice "+ANSI_RESET);
                        adminMenuOperation();
                    }
                }
            }

    }
    public static String getMaskedPassword(String msg) {
        final String password;
        final JPasswordField jpf = new JPasswordField();
        password = JOptionPane.showConfirmDialog(null, jpf, msg,
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION ?
                new String(jpf.getPassword()) : "";
        return password;
    }


    public void facultyOperation() throws Exception
    {
            System.out.println("Welcome to the Faculty Menu ");
            boolean on = true;
            while (on) {
                System.out.println("-------------------------------------------------");
                System.out.println("""
                        Enter your choice:\s
                        1 --> for Add Student Marks
                        2 --> for Search By StudentId
                        3 --> for Search By StudentName
                        4 --> for View Student Marks
                        5 --> for View Student By Department and Year
                        6 --> view Fail Students
                        7 --> view Faculty by their Id
                        8 --> add subject details
                        9 --> Change Password
                        10 --> for Log Out""");
                System.out.println("-------------------------------------------------");
                System.out.println(GREEN_BACKGROUND + "Enter your choice : " + ANSI_RESET);
                choice = inputFromUser.inputChoice();
                switch (choice) {
                    case 1 -> addStudentMarks();
                    case 2 -> searchByStudentId();
                    case 3 -> searchByStudentName();
                    case 4 -> viewStudentMarks();
                    case 5 -> viewStudentsByDepartmentAndYear();
                    case 6 -> viewFailStudents();
                    case 7 -> viewFacultyDetailsById();
                    case 8 -> addSubjectDetails();
                    case 9 -> changePassWord();
                    case 10 -> {
                        authentications.logOut();
                        on = false;
                    }
                    default -> {
                        System.out.println(PURPLE+"You entered the choice is not in range..please enter (1 to 8) "+ANSI_RESET);
                        facultyOperation();
                    }
                }
            }
        }

    public void studentOperation() throws Exception {
            System.out.println("Welcome to Student Menu");
            boolean on = true;
            while (on) {
                System.out.println("-------------------------------------------------");
                System.out.println("""
                        Enter your choice:\s
                        1 --> for View their details
                        2 --> for View their marks
                        3 --> for Change Password
                        4 --> for Log Out""");
                System.out.println("-------------------------------------------------");
                System.out.println(GREEN_BACKGROUND + "Enter your choice : " + ANSI_RESET);
                choice = inputFromUser.inputChoice();
                switch (choice) {
                    case 1 -> searchByStudentId();
                    case 2 -> viewStudentMarks();
                    case 3 -> changePassWord();
                    case 4 -> {
                        authentications.logOut();
                        on = false;
                    }
                    default -> {
                        System.out.println(PURPLE+"Please enter the correct choice"+ANSI_RESET);
                        studentOperation();
                    }
                }
            }
    }
    public void newAdmissionOperation() throws Exception {
            System.out.println("Welcome to New Admission Menu");
            boolean on = true;
            while (on) {
                System.out.println("-------------------------------------------------");
                System.out.println("""
                    Enter your choice:\s
                    1 --> for View Department, Seat & cutoff marks for each department
                    2 --> for New Admission Form
                    3 --> for Log Out""");
                System.out.println("-------------------------------------------------");
                System.out.println(GREEN_BACKGROUND + "Enter your choice : " + ANSI_RESET);
                choice = inputFromUser.inputChoice();
                switch (choice) {
                    case 1 -> displayDepartmentDetails();
                    case 2 -> addNewAdmission();
                    case 3 -> {
                        authentications.logOut();
                        on = false;
                    }
                    default -> {
                        System.out.println(PURPLE + "Please enter the correct choice" + ANSI_RESET);
                        newAdmissionOperation();
                    }

                }
            }
    }


    private void viewFacultyDetailsById() throws Exception {

        if(roles.equalsIgnoreCase("Admin")) {
            System.out.println("Enter the Faculty Id : ");
            userId = inputFromUser.inputId();
        }
        else
        {
            userId = uId;
        }
        Faculty faculty;
        faculty = con.getFacultyById(uId);
        if(faculty != null) {
            String align = "| %-11s | %-19s | %-19s | %-7d | %-19s | %-13s |%n";
            System.out.format("+-------------+---------------------+---------------------+---------+---------------------+---------------+%n");
            System.out.format("|Id           |FacultyName          |DepartmentName       |Year     |MailId               |PhoneNumber    |%n");
            System.out.format("+-------------+---------------------+---------------------+---------+---------------------+---------------+%n");
            System.out.format(align,faculty.getFacultyId(),faculty.getFacultyName(),faculty.getDepartmentName(),faculty.getYear(),faculty.getMailId(),faculty.getPhoneNumber());
            System.out.format("+-------------+---------------------+---------------------+---------+---------------------+---------------+%n");
        }else
        {
            System.out.println("...There is no record found...");
        }
    }

    public void viewFailStudents() throws SQLException {
        List<MarkDetails> markDetailsList;
        markDetailsList = con.getFailStudents();
        String align = "| %-11s | %-28s | %-4s |%n";
        if(!markDetailsList.isEmpty()) {
            for (MarkDetails markDetails : markDetailsList) {
                if (count < 1) {
                    System.out.println("RollNumber : " + markDetails.getRollNumber() + "\nName : " + markDetails.getName() + "\nDepartmentName :" + markDetails.getDepartmentName() + "\nSemester : " + markDetails.getSemester());
                    count = 1;
                    System.out.format("+-------------+------------------------------+------+%n");
                    System.out.format("|SubjectCode  |SubjectName                   |Marks |%n");
                    System.out.format("+-------------+------------------------------+------+%n");

                }

                    System.out.format(align, markDetails.getSubjectId(), markDetails.getSubName(), markDetails.getMarks());
                    System.out.format("+-------------+------------------------------+------+%n");
            }
            count = 0;
        }else
        {
            System.out.println("..There is no records found..");
        }
    }

    public void viewStudentsByDepartmentAndYear() throws SQLException {

        List<Student>studentList;
        System.out.println("Enter the Department Name that you want to search :");
        departmentName = inputFromUser.inputDepartmentName();
        System.out.println("Enter the Year that you want to search : ");
        year = inputFromUser.inputYear();
        String align = "| %-11s | %-19s | %-19s | %-7d | %-19s | %-13s |%n";
        System.out.format("+-------------+---------------------+---------------------+---------+---------------------+---------------+%n");
        System.out.format("|RollId       |StudentName          |DepartmentName       |Year     |MailId               |PhoneNumber    |%n");
        System.out.format("+-------------+---------------------+---------------------+---------+---------------------+---------------+%n");

        studentList = con.getStudentsByDepartmentAndYear(departmentName,year);
        for (Student student : studentList)
        {
            System.out.format(align, student.getRollNumber(), student.getStudentName(), student.getDepartmentName(), student.getYear(), student.getPhoneNumber(), student.getMailId());
            System.out.format("+-------------+---------------------+---------------------+---------+---------------------+---------------+%n");
        }

    }

    public void viewStudentMarks() throws SQLException {
        List<MarkDetails>markDetailsList;
        if(roles.equalsIgnoreCase("Faculty")) {
            System.out.println("Enter your roll Number");
            id = inputFromUser.inputId();
        }else
        {
            id = uId;
        }
        markDetailsList = con.getMarksByStudentId(id,departMentName,years);
        int count = 0;
        String align = "| %-11s | %-28s | %-4s |%n";
        String redBg = "\u001B[41m";
        String bgReset = "\u001B[0m";
        String mark1;
        String ANSI_RESET = "\u001B[0m";
        String ANSI_RED  = 	"\u001B[31m";
        System.out.println(ANSI_RED+"Note : Red colour denotes FailMark"+ANSI_RESET);
        for (MarkDetails markDetails : markDetailsList)
        {
            if(count < 1)
            {
                System.out.println("RollNumber : "+markDetails.getRollNumber()+"\nName : "+markDetails.getName()+"\nDepartmentName :"+markDetails.getDepartmentName()+"\nSemester : "+markDetails.getSemester());
                count = 1;
                System.out.format("+-------------+------------------------------+------+%n");
                System.out.format("|SubjectCode  |SubjectName                   |Marks |%n");
                System.out.format("+-------------+------------------------------+------+%n");

            }

                if(markDetails.getMarks() < 50)
                {
                    mark1 = redBg + markDetails.getMarks() + bgReset;
                }else {
                    mark1 = String.valueOf(markDetails.getMarks());

                }
                System.out.format(align, markDetails.getSubjectId(), markDetails.getSubName(), mark1);
                System.out.format("+-------------+------------------------------+------+%n");

        }
    }

    public void addStudentMarks() throws Exception {
        System.out.println("Enter the roll Number of the student : ");
        id = inputFromUser.inputId();
        year = years;
        System.out.println("Enter the semester number : ");
        semester = sc.nextInt();
        Student student;
        student = con.getByStudentId(id);
        if(student == null)
        {
            System.out.println("Please check the roll number");
            facultyOperation();
        }
        else {
            if (student.getYear() == years && student.getDepartmentName().equalsIgnoreCase(departMentName)) {
                showSubjectDetails(year);
                id = "'" + id + "'";
                departmentName = "'" + departMentName + "'";
                MarkDetails[] markDetails = new MarkDetails[count];
                for (int i = 0; i < count; i++) {
                    System.out.println("Enter the subject code : ");
                    subjectCode = sc.next();
                    subjectCode = "'" + subjectCode + "'";
                    System.out.println("Enter the Mark : ");
                    mark = sc.nextInt();
                    markDetails[i] = new MarkDetails(id, departmentName, semester, subjectCode, mark);
                }
                success = con.insertMarks(markDetails);

                if (!success) {
                    System.out.println("Mark Details are not added");
                } else {
                    System.out.println("Mark details added successfully");
                }
            }
        }

    }

    public void showSubjectDetails(int year) {
        switch (year)
        {
            case 1, 2 -> {
                System.out.println("Four Subject for each semester ");
                count = 4;
            }
            case 3 -> {

                System.out.println("Three Subject for each semester ");
                count = 3;
            }
            case 4 -> {
                sem = 7;
                sem1 = 8;
                System.out.println("Two Subject for each semester ");
                count = 2;
            }
        }
    }

    public void RemoveStudent() throws SQLException {
        System.out.println("Enter the student RollNumber to Remove : ");
        id = sc.next();
        success = con.removeStudents(id);
        if (success)
        {
            System.out.println(".....Student Record Removed Successfully.....");
        }
        else
        {
            System.out.println(".....Student Record not removed.....");
        }

    }



    public void addStudentDetails() throws SQLException {
        System.out.println("Enter the student roll Number : ");
        id = inputFromUser.inputId();
        checkDuplicate(id);
        System.out.println(PURPLE+"Enter the name:"+ANSI_RESET);
        name = inputFromUser.inputName();
        departmentName = inputFromUser.inputDepartmentName();
        detailsOfStudents();
        year = inputFromUser.inputYear();
        dateOfBirth = inputFromUser.inputDob();
        role = "Student";
        Student student = new Student(id,name,departmentName,year,mailId,phoneNumber,role);
        boolean inserted = con.addStudentAcademicDetails(student);
        PersonalDetails personalDetails = new PersonalDetails(id,dateOfBirth,gender,district);
        boolean inserted1 = con.addPersonalDetails(personalDetails);
        success = con.newUser(id,dateOfBirth,role);
        if(inserted1 && inserted)
        {
            System.out.println("Student Details added successfully");
        }
        else
        {
            System.out.println("Please try later");
        }
    }

    private void detailsOfStudents() {

        gender      = GenderType.valueOf(inputFromUser.inputGender());
        System.out.println(PURPLE+"Enter your Father name:"+ANSI_RESET);
        fatherName  = inputFromUser.inputName();
        System.out.println(PURPLE+"Enter the Phone Number : "+ANSI_RESET);
        phoneNumber = sc.next();
        mailId      = inputFromUser.inputMailId();
        district    = inputFromUser.inputDistrictName();
    }


    public void addDepartments() throws SQLException {
        System.out.println("Enter the departmentId : ");
        id = inputFromUser.inputId();
        departmentName = inputFromUser.inputDepartmentName();
        System.out.println("Enter the available seats in this department : ");
        availableSeats = sc.nextInt();
        System.out.println("Enter the cutOff Mark for this department : ");
        cutOffMark = sc.nextInt();
        boolean a = con.addDepartment(departmentId,departmentName,availableSeats,cutOffMark);
        if(a)
        {
            System.out.println("Successfully added");
        }else{
            System.out.println("Please try later");
        }
    }
    public void addFacultyDetails() throws Exception
    {
        System.out.println("Enter the Faculty(ClassIncharge) Id : ");
        id = inputFromUser.inputId();
        checkDuplicate(id);
        System.out.println(PURPLE+"Enter the name:"+ANSI_RESET);
        name = inputFromUser.inputName();
        System.out.println("Enter the Gender in the format of (Male - 1/Female - 2/Others - 3) : ");
        gender = GenderType.values()[sc.nextInt()-1];
        dateOfBirth    = inputFromUser.inputDob();
        departmentName = inputFromUser.inputDepartmentName();
        System.out.println("Enter the year : ");
        year           = inputFromUser.inputChoice();
        mailId         = inputFromUser.inputMailId();
        System.out.println("Enter the Phone number");
        phoneNumber    = checking.checkPhonenumber(sc.next());
        district       = inputFromUser.inputDistrictName();
        role = "Faculty";
        Student student   = new Student(id,name,departmentName,year,mailId,phoneNumber,role);
        boolean inserted  = con.addStudentAcademicDetails(student);
        PersonalDetails personalDetails = new PersonalDetails(id,dateOfBirth,gender,district);
        boolean inserted1 = con.addPersonalDetails(personalDetails);
        con.newUser(id,dateOfBirth,role);
        if(inserted && inserted1)
        {
            System.out.println("Faculty details added successfully");
        }else{
            System.out.println("Please try later");
        }
    }

    public void addSubjectDetails() throws SQLException {
        System.out.println("Enter how many subject you want to add : ");
        choice = sc.nextInt();
        System.out.println("Enter the Department Id");
        departmentId = sc.nextInt();
        System.out.println("Enter the Semester : ");
        semester = sc.nextInt();
        for (int i = 0; i < choice; i++) {
            System.out.println("Enter the subject code");
            subjectCode = sc.next();
            System.out.println("Enter the Subject Name");
            subjectName = str.nextLine();
            success = con.addSubjectDetails(subjectCode, subjectName, departmentId, semester);
        }
        if (success) {
            System.out.println("Subject details added successfully");
        } else
        {
            System.out.println("Subject details can't added");
        }
    }

    public void addNewAdmission() throws Exception
    {
        System.out.println(PURPLE+"Enter the name:"+ANSI_RESET);
        name = inputFromUser.inputName();
        dateOfBirth = inputFromUser.inputDob();
        success = calculatingAge.ageCalculation(dateOfBirth);
        if(success) {
            detailsOfStudents();
            System.out.println("Enter the 12th Physics mark : ");
            int m1 = inputFromUser.inputInteger();
            System.out.println("Enter the 12th Chemistry mark : ");
            int m2 = inputFromUser.inputInteger();
            System.out.println("Enter the 12th Maths mark : ");
            int m3 = inputFromUser.inputInteger();
            cutOffMark = (m1 / 2) + (m2 / 4) + (m3 / 4);
            departmentName = inputFromUser.inputDepartmentName();
            status = checkSeatAvailable(departmentName, cutOffMark);
            success = con.addNewAdmissionDetails(name, dateOfBirth, gender, cutOffMark, departmentName, status, mailId, district);
        }else
        {
            newAdmissionOperation();
        }
    }

    public void displayDepartmentDetails() throws Exception {
        List<Department> departmentdetails;
        String align = "| %-12s | %-14s | %-11d | %-12d |%n";
        System.out.format("+--------------+----------------+-------------+--------------+%n");
        System.out.format("|DepartMentId  |DepartmentName  |CutOffMark   |AvailableSeats|%n");
        System.out.format("+--------------+----------------+-------------+--------------+%n");
        departmentdetails = con.getDepartments();
        for (Department department : departmentdetails) {
            System.out.format(align, department.getDepartmentId(), department.getDepartmentName(), department.getCutOffMarks(), department.getSeatAvailability());
            System.out.format("+--------------+----------------+-------------+--------------+%n");
        }
    }

    public void displayStudentDetails() throws Exception {
        List<Student> studentDetails;
        String align = "| %-11s | %-19s | %-19s | %-7d | %-19s | %-13s |%n";
        System.out.format("+-------------+---------------------+---------------------+---------+---------------------+---------------+%n");
        System.out.format("|RollId       |StudentName          |DepartmentName       |Year     |MailId               |PhoneNumber    |%n");
        System.out.format("+-------------+---------------------+---------------------+---------+---------------------+---------------+%n");
        studentDetails = con.getStudentDetails();
        if(!studentDetails.isEmpty()) {
            for (Student student : studentDetails) {
                System.out.format(align, student.getRollNumber(), student.getStudentName(), student.getDepartmentName(), student.getYear(), student.getPhoneNumber(), student.getMailId());
                System.out.format("+-------------+---------------------+---------------------+---------+---------------------+---------------+%n");
            }
        }else{
            System.out.println("...There is no record...");
        }
    }

    public void displayStudentPersonalDetails() throws Exception {
        List<PersonalDetails>personalDetailsList;
        String align = "| %-9s | %-4s | %-12s | %-14s |%n";
        System.out.format("------------+------------+--------------+----------------+%n");
        System.out.format("RollNumber  |DOB         |Gender        |District        |%n");
        System.out.format("------------+------------+--------------+----------------+%n");
        personalDetailsList = con.getPersonalDetails();
        if(!personalDetailsList.isEmpty()) {
            for (PersonalDetails personalDetails : personalDetailsList) {
                System.out.format(align, personalDetails.getRollNumber(), personalDetails.getDateOfBirth(), personalDetails.getGender(), personalDetails.getDistrict());
                System.out.format("------------+------------+--------------+----------------+%n");
            }
        }else
        {
            System.out.println("..There is no available record..");
        }
    }
    public void displayFacultyDetails() throws Exception {
        List<Faculty>faculties;
        String align = "| %-11s | %-19s | %-19s | %-7d | %-19s | %-13s | %-11s | %-10s | %-9s |%n";
        System.out.format("+-------------+---------------------+---------------------+---------+---------------------+---------------+-------------+-----------+------------+%n");
        System.out.format("|FacultyId    |FacultyName          |DepartmentName       |Year     |MailId               |PhoneNumber    |DOB        |Gender       |District    |%n");
        System.out.format("+-------------+---------------------+---------------------+---------+---------------------+---------------+-------------+-----------+------------+%n");
        faculties = con.getByFacultyDetails();

        if(!faculties.isEmpty()) {
            for (Faculty faculty : faculties) {
                System.out.printf(align, faculty.getFacultyId(), faculty.getFacultyName(), faculty.getDepartmentName(), faculty.getYear(), faculty.getMailId(), faculty.getPhoneNumber(), faculty.getDateOfBirth(), faculty.getGender(), faculty.getDistrict());
                System.out.format("+-------------+---------------------+---------------------+---------+---------------------+---------------+-------------+-----------+------------+%n");
            }
        }else{
            System.out.println("..There is no available record..");
        }
    }

    public void displayAdmissionDetails() throws SQLException {
        List<AdmissionDetails>admissionDetails;
        String align = "| %-16s | %-16s | %-9s | %-7d | %-15s | %-8s |%n";
        System.out.format("+----------------+----------------+---------+---------------+--------+%n");
        System.out.format("|Name            |DepartmentName  |CutOff   |MailId         |Status  |%n");
        System.out.format("+----------------+----------------+---------+---------------+--------+%n");
        admissionDetails = con.getNewAdmissionDetails();
        if(!admissionDetails.isEmpty()) {
            for (AdmissionDetails admissionDetails1 : admissionDetails) {
                System.out.format(align, admissionDetails1.getName(), admissionDetails1.getDepartmentName(), admissionDetails1.getStatus(), admissionDetails1.getMailId());
                System.out.format("+----------------+----------------+---------+---------------+--------+%n");
            }
        }else
        {
            System.out.println("..There is no available record..");
        }
    }

    public void searchByStudentId() throws Exception {
        Student student;
        if (roles.equalsIgnoreCase("Admin") || roles.equalsIgnoreCase("Faculty")) {
            System.out.println("Enter the Student RollNumber to search :");
            id = sc.next();
        } else {
            id = uId;
        }
        student = con.getByStudentId(id);
        if (student == null) {
            System.out.println("There is no record");
        } else
        {
        String align = "| %-11s | %-19s | %-16s | %-7d | %-19s | %-13s | %-12s | %-11s | %-10s |%n";
        System.out.format("+-------------+---------------------+------------------+---------+---------------------+---------------+--------------+-------------+------------+%n");
        System.out.format("|RollId       |StudentName          |DepartmentName    |Year     |PhoneNumber          |MailId         |DOB           |Gender       |District    |%n");
        System.out.format("+-------------+---------------------+------------------+---------+---------------------+---------------+--------------+-------------+------------+%n");
        System.out.format(align, student.getRollNumber(), student.getStudentName(), student.getDepartmentName(), student.getYear(), student.getMailId(), student.getPhoneNumber(), student.getPersonalDetails().getDateOfBirth(),student.getPersonalDetails().getGender(),student.getPersonalDetails().getDistrict());
        System.out.format("+-------------+---------------------+------------------+---------+---------------------+---------------+--------------+-------------+------------+%n");
    }
    }

    public void searchByStudentName() throws Exception {
        Student student1;
        if(roles.equalsIgnoreCase("Admin") || roles.equalsIgnoreCase("Faculty")) {
            System.out.println("Enter the Student Name to search :");
            name = str.nextLine();
        }else
        {
           name = userName;
        }
        student1 = con.getByStudentName(name);
        if(student1 != null) {
            String align = "| %-11s | %-19s | %-19s | %-7d | %-19s | %-13s |%n";
            System.out.format("+-------------+---------------------+---------------------+---------+---------------------+---------------+%n");
            System.out.format("|RollId       |StudentName          |DepartmentName       |Year     |MailId               |PhoneNumber    |%n");
            System.out.format("+-------------+---------------------+---------------------+---------+---------------------+---------------+%n");
            System.out.format(align, student1.getRollNumber(), student1.getStudentName(), student1.getDepartmentName(), student1.getYear(), student1.getPhoneNumber(), student1.getMailId());
            System.out.format("+-------------+---------------------+---------------------+---------+---------------------+---------------+%n");
        }else{
            System.out.println("..There is no record..");
        }
    }

    public void changePassWord() throws SQLException {
        System.out.println("Enter your user Id : ");
        userId = inputFromUser.inputId();
        passWord = inputFromUser.inputPassword();
        success = con.updatePassWord(userId,passWord);
        if(success)
        {
            System.out.println("......Your Password changed successfully......");
        }
    }



    public void checkDuplicate(String id) throws SQLException {
        List<Student> students1 ;
        students1 = con.getStudentDetails();
        for(Student student : students1)
        {
            while (student.getRollNumber().equalsIgnoreCase(id))
            {
                System.out.println("Roll number is already exits..");
                id = sc.next();
            }
        }
    }

    public String  checkSeatAvailable(String departmentName,int cutOffMark) throws SQLException {
        List<Department> departmentList;
        departmentList = con.getDepartments();
        for (Department department : departmentList) {
            if (department.getDepartmentName().equalsIgnoreCase(departmentName)) {
                if (department.getSeatAvailability() > 0 && department.getCutOffMarks() <= cutOffMark) {
                    System.out.println("You are placed....");
                    status = "Selected";
                    availableSeats = department.getSeatAvailability() - 1;
                    success = con.updateSeats(availableSeats, departmentName);
                } else {
                    status = "Rejected";
                    System.out.println("...Sorry, you are not placed....");

                }
            }
        }
        return status;
    }
    public void close() throws Exception {
        con.close();
    }
}
