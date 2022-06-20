package DataClass;

import DataSource.GenderType;

public class AdmissionDetails {
    private final String newAdmissionName;
    private final int cutoff;
    private final GenderType gender;
    private final String status;
    private final String mailId;
    private final String departmentName;
    private  String dateOfBirth;
    private  String district;

    public AdmissionDetails(String newAdmissionName, String dateOfBirth, GenderType gender, int cutoff, String departmentName, String status, String mailId, String district) {
        this.newAdmissionName = newAdmissionName;
        this.cutoff = cutoff;
        this.gender = gender;
        this.status = status;
        this.mailId = mailId;
        this.departmentName = departmentName;
        this.dateOfBirth = dateOfBirth;
        this.district = district;
    }




    public String getName() {
        return newAdmissionName;
    }

    public String getStatus() {
        return status;
    }

    public GenderType getGender() {
        return gender;
    }


    public String getDepartmentName() {
        return departmentName;
    }

    public AdmissionDetails(String name, GenderType gender, int cutOffMark, String departmentName, String mailId, String status) {
        this.newAdmissionName = name;
        this.gender = gender;
        this.cutoff = cutOffMark;
        this.departmentName = departmentName;
        this.status = status;
        this.mailId = mailId;
    }


    public String getMailId() {
        return mailId;
    }

    @Override
    public String toString() {
        return newAdmissionName+"  "+cutoff+"  "+status+"  "+mailId;
    }
}
