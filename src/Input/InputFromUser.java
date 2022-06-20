package Input;

import DataSource.DepartmentNames;
import DataSource.GenderType;
import UtilClass.Checking;

import java.util.Scanner;

import static DataSource.Main.sc;

public class InputFromUser {
    Checking checking = new Checking();
    Scanner input = new Scanner(System.in);
    String GREEN_BACKGROUND = "\u001B[42m";
    String ANSI_RESET = "\u001B[0m";
    String BLUE	= "\u001B[34m";
    String PURPLE = "\u001B[35m";
    Scanner str = new Scanner(System.in);

    public int inputChoice() {
        String userInput;
        while (true) {
            System.out.println(GREEN_BACKGROUND + "Enter your choice : " + ANSI_RESET);
            userInput = sc.next();
            if (!(checking.validateUserChoice(userInput))) {
                System.out.println("Invalid input");
            } else {
                break;
            }
        }
        return Integer.parseInt(userInput);
    }


    public String inputName() {
        String name;
        while (true) {
            name = str.nextLine();
            if (!(checking.validateName(name))) {
                System.out.println("Invalid name");
            } else {
                break;
            }
        }
        return name;
    }

    public String inputId() {
        String id;
        while (true) {
            id = input.next();
            if (!(checking.isValidId(id))) {
                System.out.println("Invalid id");
            } else {
                break;
            }
        }
        return id;
    }

    public String inputDob() {
        String dateOfBirth;
        while (true) {
            System.out.println(PURPLE+"Enter the Date Of Birth (mm/dd/yyyy) format :"+ANSI_RESET);
            dateOfBirth = input.next();
            if (!(checking.checkDOB(dateOfBirth))) {
                System.out.println("Invalid DOB");
            } else {
                break;
            }
        }
        return dateOfBirth;
    }

    public String inputDistrictName() {
        String districtName;
        while (true) {
            System.out.println(PURPLE+"Enter the district Name :"+ANSI_RESET);
            districtName = input.next();
            if (!(checking.isValidName(districtName))) {
                System.out.println("Invalid district Name");
            } else {
                break;
            }
        }
        return districtName;
    }

    public String inputPhoneNumber() {
        String phoneNumber;
        while (true) {
            System.out.println(PURPLE+"Enter the PhoneNumber "+ANSI_RESET+BLUE+"(Starts with 6 to 9 and total 10 digits ): "+ANSI_RESET);
            phoneNumber = sc.next();
            if (!(checking.checkPhoneNumber(phoneNumber))) {
                System.out.println("Invalid Phone Number");
            } else {
                break;
            }
        }
        return phoneNumber;
    }

    public int inputYear() {
        String userInput;
        while (true) {
            System.out.println(PURPLE+"Enter which year he/she is : "+ANSI_RESET);
            userInput = sc.next();
            if (!(checking.validateUserChoice(userInput))) {
                System.out.println("Invalid input");
            } else {
                break;
            }
        }
        return Integer.parseInt(userInput);
    }

    public String inputDepartmentName() {
        int choice;
        while (true) {
            System.out.println(PURPLE+"""
                    Enter the Department name : Enter\s
                    1 for CSE
                    2 for ME
                    3 for ECE"""+ANSI_RESET);
            choice = sc.nextInt();
            if (!(checking.validateUserChoice(String.valueOf(choice)))) {
                System.out.println("Invalid input");
            } else {
                break;
            }
        }
        return String.valueOf(DepartmentNames.values()[choice - 1]);
    }

    public String inputGender() {
        int choice;
        while (true) {
            System.out.println(PURPLE+"""
                    Enter the Gender\s
                    1 for Male
                    2 for Female
                    3 for Others"""+ANSI_RESET);
            choice = sc.nextInt();
            if (!(checking.validateUserChoice(String.valueOf(choice)))) {
                System.out.println("Invalid choice");
            } else {
                break;
            }
        }
        return String.valueOf(GenderType.values()[choice - 1]);

    }

    public String inputMailId() {
        String mailId;
        while (true) {
            System.out.println(PURPLE+"Enter the mailId"+ANSI_RESET);
            mailId = input.next();
            if (!(checking.checkMailId(mailId))) {
                System.out.println("Invalid MailId");
            } else {
                break;
            }
        }
        return mailId;
    }

    public String inputPassword() {
        String password;
        while (true) {
            System.out.println(PURPLE+"Enter the Password "+ANSI_RESET+BLUE+"(E.g : a-z/A-Z/0-9 +(+!@#$%^&*())"+ANSI_RESET);
            password = input.next();
            if (!(checking.isValidPassWord(password))) {
                System.out.println("Invalid Password..Make sure your password contains "+BLUE+"(E.g : a-z/A-Z/0-9 +(+!@#$%^&*())"+ANSI_RESET);
            } else {
                break;
            }
        }
        return password;
    }

    public int inputInteger() {
        String userInput;
        while (true) {
            userInput = sc.next();
            if (!(checking.validateUserChoice(userInput))) {
                System.out.println("Invalid input");
            } else {
                if(!(checking.checkRange(Integer.parseInt(userInput))))
                {
                    System.out.println("Please enter your mark within 100");
                }
                else
                {
                    break;
                }
            }
        }
        return Integer.parseInt(userInput);
    }

}