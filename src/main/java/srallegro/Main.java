package srallegro;

import java.util.Scanner;

public class Main {

    public static void printMenu() {
        System.out.println("Co chcesz zrobić? \n  1. Wystaw przedmiot \n 2. Pokaż aukcje wg kategorii ");
    }

        public static void main(String[] args) throws EmptyTitleException, AuctionPriceIsBelowZeroOrZeroException {
            Database database = new Database();  // po co mi to
            Scanner sc = new Scanner(System.in);
            User currentUser = null;
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
            } else {
                System.out.println("Do widzenia");
            }

            while (currentUser != null) {
                printMenu();
                menuChoice = sc.nextInt();
                if (menuChoice == 1) {
                    System.out.println("Podaj tytuł aukcji");
                    String title = sc.next();
                    System.out.println("Podaj opis");
                    String description = sc.next();
                    System.out.println("Podaj cenę wywoławczą");
                    double price = sc.nextDouble();
                    System.out.println("Wybierz kategorię");
                    //CategoryView.printCategories();  //jak wyświetlić? jak wybrac kategorie?
                    String chosenCat = sc.next();
                    Category cat = new Category ("Robocza kategoria");
                    AuctionController.createAuction(currentUser, title, description, cat, price);
                }
            }



        }
    }

