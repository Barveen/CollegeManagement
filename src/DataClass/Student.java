package DataClass;

public class Student {

    String rollNumber;
    private final String studentName;
    private final int year;
    private final String mailId;
    private final String phoneNumber;
    String departmentName;
    private final String role;


    public Student(String rollNumber, String name, String departmentName, int year, String mailId, String phoneNumber, String role) {
        this.rollNumber = rollNumber;
        this.studentName = name;
        this.departmentName = departmentName;
        this.year = year;
        this.mailId = mailId;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public String getRole()
    {
        return role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public int getYear() {
        return year;
    }

    public String getMailId() {
        return mailId;
    }

    @Override
    public String toString() {
        return "DataClass.Student{" +
                "rollNumber='" + rollNumber + '\'' +
                ", studentName='" + studentName + '\'' +
                ", departName='" + departmentName + '\'' +
                ", year=" + year +
                ", mailId='" + mailId + '\'' +
                '}';
    }

}