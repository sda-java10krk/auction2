package srallegro;


import srallegro.Auction.AuctionController;
import srallegro.Exception.AuctionPriceIsBelowZeroOrZeroException;
import srallegro.Exception.EmptyDescriptionException;
import srallegro.Exception.EmptyTitleException;

import srallegro.User.RegisterUser;
import srallegro.User.User;
import srallegro.User.UserController;

import java.util.Map;
import java.util.Scanner;

import java.util.*;

import static java.lang.System.out;

public class Main {

    public static void printMenu() {
        System.out.println("Co chcesz zrobić? \n 1. Wystaw przedmiot \n 2. Pokaż aukcje wg kategorii \n 3. Wyświetl moje aukcje \n 4. Wyświetl aukcje, które wygrałem \n ");
    }

        public static void main(String[] args) throws EmptyTitleException, EmptyDescriptionException, AuctionPriceIsBelowZeroOrZeroException {
    public static void main(String[] args) throws EmptyTitleException, EmptyDescriptionException, AuctionPriceIsBelowZeroOrZeroException {
        User janek = new User("janek", "janek", 0, "janek", "janek", "janek", "janek");
        Database.usersByName.put("janek", janek); //tymczasowy ziomek do testow

        Map<String, Category> categoriesByName = new HashMap<>(); // all categories stored here
        Category allcategories = CategoryController.createCategoryTree(categoriesByName);

            srallegro.Database database = new srallegro.Database();  // po co mi to
            Scanner sc = new Scanner(System.in);
            User currentUser = null;
            System.out.println("1 - zaloguj się, 2 - zarejestruj się");
        Database database = new Database();  // po co mi to
        Scanner sc = new Scanner(System.in);
        User currentUser = null;
        System.out.println("1 - zaloguj się, 2 - zarejestruj się");
        try {
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
        } catch (InputMismatchException ime) {
            System.out.println("Spierdalaj");
        }

        while (currentUser != null) {
            printMenu();
            int menuChoice = sc.nextInt();
            if (menuChoice == 1) {
                System.out.println("Podaj tytuł aukcji");
                String title = sc.next();
                System.out.println("Podaj opis");
                String description = sc.next();
                System.out.println("Podaj cenę wywoławczą");
                double price = sc.nextDouble();
                System.out.println("Wybierz kategorię");
                //CategoryController.printCategories();  //jak wyświetlić? jak wybrac kategorie?
                String chosenCat = sc.next();
                Category cat = new Category("Robocza kategoria");
                AuctionController.createAuction(currentUser, title, description, cat, price);
            } else if (menuChoice == 2) {
                CategoryController.printCategories(allcategories, 0, out);
                System.out.println("Wybierz kategorię");
                String chosenCategory = sc.next();
                try {
                    System.out.println(categoriesByName.get(chosenCategory).getAuctions());
                } catch (NullPointerException npe) {
                    System.out.println("Zła kategoria, npe");
                }
            } else if (menuChoice == 3) {
                System.out.println(AuctionController.viewSellersAuctions(currentUser));
                break;
            } else if (menuChoice == 4) {
                System.out.println(AuctionController.viewWonAuctions(currentUser));
                break;
            }
        }
    }
}

