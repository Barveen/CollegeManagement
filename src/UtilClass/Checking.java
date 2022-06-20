package UtilClass;

public  class Checking {

    public  boolean isValidName(String name) {
        return name.matches("[a-z A-Z]+");
    }

    public boolean isValidId(String id) {
        return id.matches(".*[a-zA-Z].*") && id.matches(".*[0-9].*");
    }

    public boolean checkPhoneNumber(String phoneNumber) {
        //Matching the given phone number with the expression
        return phoneNumber.matches("[6-9][0-9]{9}}");
    }

    public boolean checkDOB(String dateOfBirth) {
        return  dateOfBirth.matches("^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$");
        }

    public boolean validateName(String inputName){
        return inputName.matches("[a-z A-Z.']+|[a-z A-Z.']+\\s[a-z A-Z.']+");
    }


    public boolean checkMailId(String eMailId) {
        return eMailId.matches("[A-Za-z0-9]+@[a-zA-Z]+.[a-z]+.*[a-z]*");
    }

    public boolean isValidPassWord(String password) {
       return password.matches("[a-z A-Z0-9\\\\d+!@#$%^&*()_-]{8}+");

    }

    public boolean validateUserChoice(String userInput) {
        return userInput.matches("\\d+");
    }

    public boolean checkRange(int m1)
    {
        return m1 <= 100 ;
    }

}
