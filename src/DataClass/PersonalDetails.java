package DataClass;

import DataSource.GenderType;

public class PersonalDetails  {
    private final String dateOfBirth;
    private final GenderType gender;
    private final String district;
    private final String rollNumber;

    public GenderType getGender() {
        return gender;
    }

    public PersonalDetails(String rollNumber, String dateOfBirth, GenderType gender, String district) {
        this.rollNumber = rollNumber;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.district = district;
    }


    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getDistrict() {
        return district;
    }

    public String getRollNumber() {
        return rollNumber;
    }


    @Override
    public String toString() {
        return rollNumber+" "+dateOfBirth+"  "+gender+"  "+"    "+district;
    }
}
