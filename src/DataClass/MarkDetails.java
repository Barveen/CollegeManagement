package DataClass;


public class MarkDetails  {
    private final String  rollNumber;
    private String name;
    private final int semester;
    private final String subjectId;
    private final int marks;
    String departmentName;
    private String subName;

    public MarkDetails(String rollNumber, String departmentName, int semester, String subjectId, int marks) {
        super();
        this.rollNumber = rollNumber;
        this.departmentName = departmentName;
        this.semester = semester;
        this.subjectId = subjectId;
        this.marks = marks;
    }

    public MarkDetails(String rollNumber, String name, String departmentName, int semester, String subjectCode, int mark, String subName) {
        super();
        this.rollNumber = rollNumber;
        this.name = name;
        this.departmentName = departmentName;
        this.semester = semester;
        this.subjectId = subjectCode;
        this.marks = mark;
        this.subName = subName;
    }


    public String getName() {
        return name;
    }

    public String getSubName() {
        return subName;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public int getSemester() {
        return semester;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public int getMarks() {
        return marks;
    }

    @Override
    public String toString() {
        return "("+rollNumber +", "+departmentName+","+semester+","+subjectId+","+marks+")";


    }
}

