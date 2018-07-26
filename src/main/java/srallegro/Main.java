package srallegro;


import srallegro.auction.Auction;
import srallegro.auction.AuctionController;
import srallegro.exception.AuctionPriceIsBelowZeroOrZeroException;
import srallegro.exception.EmptyDescriptionException;
import srallegro.exception.EmptyTitleException;

import srallegro.user.*;

import java.util.Map;
import java.util.Scanner;

import java.util.*;

import static java.lang.System.out;

public class Main {
    
    public enum State {
        INIT,
        DURING_LOGIN,
        DURING_REGISTRATION,
        LOGGED_IN,
        STOP,

    }


    public static void main(String[] args) throws EmptyTitleException, EmptyDescriptionException, AuctionPriceIsBelowZeroOrZeroException {
        Database database = Database.getInstance();

        User janek = new User("janek", "janek", 0, "janek", "janek", "janek", "janek");
        database.addUserToAllUsers(janek); //tymczasowy ziomek do testow

        Map<String, Category> categoriesByName = new HashMap<>(); // all categories stored here
        Category allcategories = CategoryController.createCategoryTree(categoriesByName);

        //Database database = new Database();  // po co mi to
        Scanner sc = new Scanner(System.in);
        User currentUser = null;
        
        State state = State.INIT;
        while (state != State.STOP) {
            switch (state) {
                case INIT: {
                    System.out.println("Dzień dobry");
                    System.out.println("Co chcesz zrobić");
                    System.out.println("1 - Zaloguj się");
                    System.out.println("2 - Zarejestruj się");
                    System.out.println("0 - wyjdź");

                    String answer = sc.next();
                    switch (answer) {
                        case "1":
                            state = State.DURING_LOGIN;
                            break;
                        case "2":
                            state = State.DURING_REGISTRATION;
                            break;
                        case "0":
                            state = State.STOP;
                            break;
                        default:
                            System.out.println("Zła odpowiedź");
                            state = State.INIT;
                            break;
                    }
                    break;
                }
                case DURING_LOGIN: {
                    System.out.println("Podaj login");
                    String login = sc.nextLine();
                    System.out.println("Podaj hasło");
                    String password = sc.nextLine();
                    currentUser = UserController.login(login, password);
                    if (currentUser != null) {
                        state = State.LOGGED_IN;
                    } else {
                        System.out.println("Nie udało się zalogować");
                        state = State.DURING_LOGIN;
                    }
                    break;
                }
                //FIXME
                //brak metod do sprawdzania poprawnosci wprowadzonych danych
                case DURING_REGISTRATION: {
                    currentUser = RegisterUser.createUser();
                    if (currentUser != null) {
                        System.out.println("Zarejestrowano użytkownika.");
                        state = State.LOGGED_IN;
                        break;
                    }
                        System.out.println("Proba zalogowania nieudana, spobój ponownie");
                        state = State.DURING_REGISTRATION;
                        break;
                        
                }
                case STOP: {
                    state = State.STOP;
                }
                case LOGGED_IN: {
                    System.out.println("Co chcesz zrobić? \n 1. Wystaw przedmiot \n 2. Pokaż aukcje wg kategorii \n " +
                            "3. Wyświetl moje aukcje \n 4. Wyświetl aukcje, które wygrałem \n 5. Wyloguj  sie ");
                    String answer = sc.next();
                    switch (answer) {
                        case "1": {

                            System.out.println("Podaj tytuł aukcji");
                            String title = sc.next();
                            System.out.println("Podaj opis");
                            String description = sc.next();
                            System.out.println("Podaj cenę wywoławczą");
                            double price = sc.nextDouble();
                            System.out.println("Wybierz kategorię");
                            //CategoryController.printCategories();  //jak wyświetlić? jak wybrac kategorie?
                            String chosenCat = sc.next();   //do zmiany
                            Category cat = new Category("Robocza kategoria");   //do zmiany
                            AuctionController.createAuction(currentUser, title, description, cat, price);
                            break;
                        }
                        case "2": {
                            CategoryController.printCategories(allcategories, 0, out);
                            System.out.println("Wybierz kategorię");
                            String chosenCategory = sc.next();
                            try {
                                System.out.println(categoriesByName.get(chosenCategory).getAuctions());
                            } catch (NullPointerException npe) {
                                System.out.println("Zła kategoria, npe");
                            }
                            break;
                        }
                        case "3": {
                            System.out.println(AuctionController.viewSellersAuctions(currentUser));
                            break;
                        }
                        case "4": {
                            for (Auction a : AuctionController.viewWonAuctions(currentUser)) {
                                if (a.getBids() >= 3) {
                                    System.out.println(a);
                                }
                                break;
                            }
                        }
                        case "5": {
                            state = State.INIT;
                            break;
                        }
                        default: {
                            System.out.println("Zła odpowiedź");
                            state = State.LOGGED_IN;
                            break;
                        }

                    }
                    break;
                }

            }
        }
    }
}