package srallegro;

import java.util.Scanner;

public class Main {


        public static void main(String[] args) {

            DatabaseUsers databaseUsers = new DatabaseUsers();
            Scanner scanner = new Scanner(System.in);

            System.out.println("Dzien dobty co chcesz szroibc");
            System.out.println("1 - zalogowac sie");
            System.out.println("2 - zarejestrowac");
            String answer = scanner.nextLine();

            switch (answer) {
                case ("2"):
                    User user = RegisterUser.createUser();
                    databaseUsers.addUser(user);
                    break;
            }


        }
    }

