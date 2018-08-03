package srallegro;

import srallegro.auction.Auction;
import srallegro.auction.AuctionController;
import srallegro.auction.LoadAuctionFromDisk;
import srallegro.auction.SaveAuctionOnDisk;
import srallegro.exception.*;
import srallegro.user.*;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.System.out;

public class Main {

    public enum State {
        INIT,
        DURING_LOGIN,
        DURING_REGISTRATION,
        DURING_VIEVING_AUCTION,
        LOGGED_IN,
        STOP,
    }

    public static void main(String[] args) throws Exception {
        Category allcategories = CategoryController.createCategoryTree();
        Database database = Database.getInstance();
        LoadUserFromDisk.readFileCSV("databaseUser.csv");
        LoadAuctionFromDisk.loadAuctionCSV("databaseAuction.csv");

        Scanner sc = new Scanner(System.in);
        User currentUser = null;
        State state = State.INIT;
        while (state != State.STOP) {
            switch (state) {
                case INIT: {
                    printMenu1();
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
                    String login = sc.next();
                    System.out.println("Podaj hasło");
                    String password = sc.next();
                    currentUser = UserController.login(login, password);
                    if (currentUser != null) {
                        state = State.LOGGED_IN;
                    } else {
                        System.out.println("Nie udało się zalogować");
                        state = State.DURING_LOGIN;
                    }
                    break;
                }

                case DURING_REGISTRATION: {
                    currentUser = UserController.createUser();
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
                case DURING_VIEVING_AUCTION: {
                    printMenu3();
                    break;
                }
                case LOGGED_IN: {
                    printMenu2();
                    String answer = sc.next();
                    switch (answer) {
                        case "1": {
                            AuctionController.createAuctionMain(currentUser);
                            break;
                        }
                        //FIXME
                        //tutaj przyda sie klasa viev bo kod jest zdublowany
                        case "2": {
                            AuctionController.vievAuctionByCategories();
                            state = State.DURING_VIEVING_AUCTION;
                            break;
                        }
                        case "3": {
                            System.out.println(AuctionController.viewSellersAuctions(currentUser));
                            break;
                        }
                        case "4": {
                            System.out.println(AuctionController.viewWonAuctions(currentUser));
                            break;
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

    public static void printMenu1() {
        System.out.println("Dzień dobry,\n Co chcesz zrobić ? \n 1 - Zaloguj się \n 2 - Zarejestruj się \n 0 - wyjdź");

    }

    public static void printMenu2() {
        System.out.println("Co chcesz zrobić? \n 1. Wystaw przedmiot \n 2. Pokaż aukcje wg kategorii \n " +
                "3. Wyświetl moje aukcje \n 4. Wyświetl aukcje, które wygrałem \n 5. Wyloguj  sie ");
    }

    public static void printMenu3() {
        System.out.println("Co chcesz zrobic ? \n 1.Zalicytuj \n 2.Zobacz opis \n 0.wróc ");
    }
}