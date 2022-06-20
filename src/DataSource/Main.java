package DataSource;

import Input.InputFromUser;
import UtilClass.Checking;

import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    static void menu() {
        System.out.println("""
                1) for Login
                2) for New Admission
                3) for menu
                4) for Exit the program""");
    }

    public static void main(String[] args) throws Exception {
        boolean main = true;
        int choice;

        StudentFunction1 object = StudentFunction1.getInstance();
        InputFromUser input = new InputFromUser();
        Checking check = new Checking();
        menu();
        while (main) {
            choice = input.inputChoice();
            switch (choice) {
                case 1 -> object.login();
                case 2 -> object.newAdmissionOperation();
                case 3 -> menu();
                case 4-> {
                    object.close();
                    System.out.println("Program finished");
                    main = false;
                }
                default -> System.out.println("You enter the wrong number");

            }
        }
    }

}

