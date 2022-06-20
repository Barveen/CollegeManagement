package DataClass;

public class Department {
    int departmentId;
    String departmentName;
    int seatAvailability ;
    int cutOffMarks;

    public Department(int departmentId, String departmentName, int seatAvailability, int cutOffMarks) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.seatAvailability = seatAvailability;
        this.cutOffMarks = cutOffMarks;
    }

    public Department() {
    }

    public int getCutOffMarks() {
        return cutOffMarks;
    }

    public int getSeatAvailability() {
        return seatAvailability;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String toString() {
        return "Department{" +
                "departmentId='" + departmentId + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", seatAvailability=" + seatAvailability +
                ", cutOffMarks=" + cutOffMarks +
                '}';
    }
}
