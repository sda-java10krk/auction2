package srallegro;

import java.util.Scanner;

public class Main {

        public static void main(String[] args) {
            Database database = new Database();  // po co mi to
            Scanner sc = new Scanner(System.in);
            User currentUser;
            System.out.println("1 - zaloguj się, 2 - zarejestruj się");
            int menuChoice = sc.nextInt();

            if (menuChoice == 1) {
                System.out.println("Podaj login");
                String login = sc.next();
                System.out.println("Podaj hasło");
                String password = sc.next();
                try {
                    currentUser = UserController.login(login, password);
                } catch (NullPointerException npe) {
                    System.out.println("Błędne dane, do widzenia");
                }
            } else if (menuChoice == 2) {
                RegisterUser.createUser();
                System.out.println("Zarejestrowano użytkownika. Uruchom program ponownie i zaloguj się");
            }
            
        }
    }

